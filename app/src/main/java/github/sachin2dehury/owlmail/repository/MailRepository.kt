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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.jsoup.Jsoup

class MailRepository(
    private val context: Context,
    private val mailApi: MailApi,
    private val mailDao: MailDao,
    private val parsedMailDao: ParsedMailDao,
    private val pagerConfig: PagingConfig,
) {

    fun getMailUiModels(request: String, page: Int) =
        getMailList(request, page).map {
            when (it) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<Mail>> =
                        it.value?.map { mail -> UiModel.Item(mail) }
                            ?: emptyList()
                    uiModels.toMutableList().apply {
                        if (!isNullOrEmpty()) {
                            add(0, UiModel.Header(getFormattedHeaderDate(page, context)))
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
                    listOf<UiModel<Mail>>(UiModel.Loader(false), UiModel.Ad(AdSize.FLUID))
                }
                is ResultState.Loading -> {
                    listOf<UiModel<Mail>>(UiModel.Loader(false), UiModel.Ad(AdSize.FLUID))
                }
            }
        }

    fun getParsedMailUiModels(conversationId: Int) =
        getParsedMailList(conversationId).map {
            when (it) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<ParsedMail>> =
                        it.value?.map { mail -> UiModel.Item(mail) }
                            ?: emptyList()
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
                    listOf<UiModel<ParsedMail>>(UiModel.Loader(false), UiModel.Ad(AdSize.FLUID))
                }
                is ResultState.Loading -> {
                    listOf<UiModel<ParsedMail>>(UiModel.Loader(false), UiModel.Ad(AdSize.FLUID))
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

    private fun getMailList(request: String, page: Int) = getMailList(
        getBox(request) ?: 2,
        getMonthLocal(page, context),
        getMonthRemote(page, context)
    )

    private fun getMailList(box: Byte, monthLocal: Pair<Long, Long>, monthRemote: String) =
        networkBoundResource(
            query = { mailDao.getMails(box, monthLocal.first, monthLocal.second) },
            fetch = { mailApi.getMails(MONTH_QUERY + monthRemote).body() },
            saveFetchResult = { result ->
                result?.mailList?.apply {
                    mailDao.deleteMails(box, monthLocal.first, monthLocal.second)
                    mailDao.insertMails(this)
                } ?: emptyList()
            },
            shouldFetch = { isInternetConnected(context) }
        )

    private fun getParsedMailList(conversationId: Int) = networkBoundResource(
        query = { parsedMailDao.getConversationMails(conversationId) },
        fetch = {
            mailDao.getMailsId(conversationId).first().map { id ->
                id to mailApi.getParsedMail(id).string()
            }
        },
        saveFetchResult = { result ->
            result.map { pair ->
                val parsedMail = ParsedMail(pair.first, conversationId, Jsoup.parse(pair.second))
                parsedMailDao.insertMail(parsedMail)
                parsedMail
            }
        },
        shouldFetch = { isInternetConnected(context) }
    )
}