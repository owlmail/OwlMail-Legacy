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
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.other.getFormattedHeaderDate
import github.sachin2dehury.owlmail.other.getMonthLocal
import github.sachin2dehury.owlmail.other.getMonthRemote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MailRepository(
    private val basicAuthInterceptor: BasicAuthInterceptor,
    private val context: Context,
    private val mailApi: MailApi,
    private val mailDao: MailDao,
    private val parsedMailDao: ParsedMailDao,
    private val pagerConfig: PagingConfig,
) {

    suspend fun getMailUiModels(request: String, page: Int) =
        getMailList(request, page).first().let {
            when (it) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<Mail>> =
                        it.value?.map { mail -> UiModel.Item(mail) } ?: emptyList()
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

//    fun getSearchMails(request: String) =
//        getPager(SearchMailPagingSource(context, request, mailApi, mailDao))
//
//    fun getParsedMails(conversationId: Int) =
//        getPager(ParsedMailPagingSource(context, conversationId, mailApi, mailDao, parsedMailDao))

    suspend fun attemptLogin() = mailApi.attemptLogin().mapToResultState()

    fun setCredential(credential: String) {
        basicAuthInterceptor.credential = credential
    }

    fun getCredential() = basicAuthInterceptor.credential ?: ""

    fun setToken(token: String) {
        basicAuthInterceptor.token = token
    }

    fun getToken() = basicAuthInterceptor.token ?: ""

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        mailDao.deleteAllMails()
        parsedMailDao.deleteAllMails()
        basicAuthInterceptor.credential = null
        basicAuthInterceptor.token = null
    }

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

    private fun getMailList(request: String, page: Int): Flow<ResultState<List<Mail>?>> {
        val box = getBox(request) ?: 2
        val monthRemote = getMonthRemote(page, context)
        val monthLocal = getMonthLocal(page, context)
        return networkBoundResource(
            { mailDao.getMails(box, monthLocal.first, monthLocal.second) },
            { mailApi.getMails(MONTH_QUERY + monthRemote).body()?.mailList },
            {
                it?.apply {
                    mailDao.deleteMails(box, monthLocal.first, monthLocal.second)
                    mailDao.insertMails(it)
                }
            },
            { isInternetConnected(context) }
        )
    }
}