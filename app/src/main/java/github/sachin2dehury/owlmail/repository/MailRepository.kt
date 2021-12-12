package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.google.android.gms.ads.AdSize
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.*
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.database.ParsedMailDao
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.datamodel.ParsedMail
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.other.getFormattedHeaderDate
import github.sachin2dehury.owlmail.other.getMonthLocal
import github.sachin2dehury.owlmail.other.getMonthRemote
import org.jsoup.Jsoup

class MailRepository(
    private val context: Context,
    private val mailApi: MailApi,
    private val mailDao: MailDao,
    private val parsedMailDao: ParsedMailDao,
    private val pagerConfig: PagingConfig,
) {

    suspend fun getMailUiModels(request: String, page: Int) =
        getMailList(request, page).let { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<Mail>> =
                        resultState.value.map { mail -> UiModel.Item(mail) }
                    uiModels.toMutableList().apply {
                        if (isNullOrEmpty()) {
                            add(UiModel.Ad(AdSize.FLUID))
                        } else {
                            add(0, UiModel.Header(getFormattedHeaderDate(page, context)))
//                            add(UiModel.Footer(8))
//                            for (i in 0..lastIndex step 30) {
//                                add(i, UiModel.Ad(AdSize.FLUID))
//                            }
                        }
                    }
                }
                is ResultState.Error -> {
                    listOf<UiModel<Mail>>(UiModel.Ad(AdSize.FLUID))
                }
                is ResultState.Loading -> {
                    listOf<UiModel<Mail>>(UiModel.Loader(false))
                }
            }
        }

    suspend fun getSearchMailUiModels(query: String) =
        getSearchMailList(query).let { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<Mail>> =
                        resultState.value.map { mail -> UiModel.Item(mail) }
                    uiModels.toMutableList().apply {
                        if (isNullOrEmpty()) {
                            add(UiModel.Ad(AdSize.FLUID))
                        } else {
                            add(UiModel.Footer(8))
                            for (i in 0..lastIndex step 30) {
                                add(i, UiModel.Ad(AdSize.FLUID))
                            }
                        }
                    }
                }
                is ResultState.Error -> {
                    listOf<UiModel<Mail>>(UiModel.Ad(AdSize.FLUID))
                }
                is ResultState.Loading -> {
                    listOf<UiModel<Mail>>(UiModel.Loader(false))
                }
            }
        }

    suspend fun getParsedMailUiModels(conversationId: Int) =
        getParsedMailList(conversationId).let { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<ParsedMail>> =
                        resultState.value.map { mail -> UiModel.Item(mail) }
                    uiModels.toMutableList().apply {
                        if (!isNullOrEmpty()) {
//                            add(0, UiModel.Header(getFormattedHeaderDate(page, context)))
                            add(UiModel.Footer(8))
                            for (i in 0..lastIndex step 30) {
                                add(i, UiModel.Ad(AdSize.FLUID))
                            }
                        } else {
                            add(UiModel.Ad(AdSize.FLUID))
                        }
                    }
                }
                is ResultState.Error -> {
                    listOf<UiModel<ParsedMail>>(UiModel.Ad(AdSize.FLUID))
                }
                is ResultState.Loading -> {
                    listOf<UiModel<ParsedMail>>(UiModel.Loader(false))
                }
            }
        }

    //    fun getSearchMails(request: String) =
//        getPager(SearchMailPagingSource(context, request, mailApi, mailDao))
//

    fun <T : Any> getPager(pagingSource: PagingSource<Int, T>) =
        Pager(pagerConfig, 0, { pagingSource }).flow

    fun getBox(request: String): Byte? = when (request) {
        context.getString(R.string.inbox) -> 2
        context.getString(R.string.trash) -> 3
        context.getString(R.string.junk) -> 4
        context.getString(R.string.sent) -> 5
        context.getString(R.string.draft) -> 6
        else -> null
    }

    private suspend fun getMailList(request: String, page: Int) = getMailList(
        getBox(request) ?: 2,
        getMonthLocal(page, context),
        getMonthRemote(page, context)
    )

    private suspend fun getMailList(box: Byte, monthLocal: Pair<Long, Long>, monthRemote: String) =
        networkBoundResource(
            query = { mailDao.getMails(box, monthLocal.first, monthLocal.second) },
            fetch = { mailApi.getMails(MONTH_QUERY + monthRemote).body() },
            saveFetchResult = { result ->
                result!!.mailList!!.apply {
                    mailDao.deleteMails(box, monthLocal.first, monthLocal.second)
                    mailDao.insertMails(this)
                }
            },
            shouldFetch = { isInternetConnected(context) }
        )

    private suspend fun getSearchMailList(query: String) = networkBoundResource(
        query = { mailDao.searchMails(query) },
        fetch = { mailApi.searchMails(query).body() },
        saveFetchResult = { result -> result!!.mailList!!.also { mailDao.insertMails(it) } },
        shouldFetch = { isInternetConnected(context) }
    )

    private suspend fun getParsedMailList(conversationId: Int) = networkBoundResource(
        query = { parsedMailDao.getConversationMails(conversationId) },
        fetch = {
            mailDao.getMailsId(conversationId)
                .map { id -> id to mailApi.getParsedMail(id).string() }
        },
        saveFetchResult = { result -> processParsedMails(result, conversationId) },
        shouldFetch = { isInternetConnected(context) }
    )

    private suspend fun processParsedMails(
        result: List<Pair<Int, String>>,
        conversationId: Int
    ) = result.map { pair ->
        val id = pair.first
        val mail = Jsoup.parse(pair.second)
        val time = mail.select(".MsgHdrSent").text().toLongOrNull()
        val details = mail.select(".MsgHdrValue").mapNotNull { it.text().trim() }
        val scriptBody = if (mail.select(".MsgBody noscript").text().isNullOrEmpty()) {
            ""
        } else {
            mailApi.getParsedMailParts(id).string()
        }
        val body = mail.select(".MsgBody").html().substringBefore("<hr>") + scriptBody
        val noOfAttachments =
            mail.select(".MsgHdrAttAnchor").text().substringBefore(' ', "0").toIntOrNull()
        val attachments = mail.select(".MsgBody tbody tr").mapNotNull { it?.html()?.trim() }

        ParsedMail(
            id,
            conversationId,
            time,
            details,
            body,
            noOfAttachments,
            attachments
        ).also { parsedMailDao.insertMail(it) }
    }
}