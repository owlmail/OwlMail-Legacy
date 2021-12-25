package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.android.gms.ads.AdSize
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.api.ResultState
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.database.ParsedMailDao
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.paging.SearchRequestPagingSource
import github.sachin2dehury.owlmail.paging.ZimbraPagingSource
import github.sachin2dehury.owlmail.utils.*

class MailRepository(
    private val context: Context,
    private val mailApi: MailApi,
    private val mailDao: MailDao,
    private val parsedMailDao: ParsedMailDao,
    private val pagingConfig: PagingConfig,
) {

    private fun <T : Any> getZimbraPagingSourceFlow(pagingSource: ZimbraPagingSource<T>) =
        Pager(pagingConfig, 0, { pagingSource }).flow

    fun getSearchRequestPagingSource(query: String?) =
        getZimbraPagingSourceFlow(SearchRequestPagingSource(mailApi, query))

    suspend fun getMailUiModels(request: String, page: Int) =
        getMailList(request, page).let { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    val uiModels: List<UiModel<Mail>> = emptyList()
//                        resultState.value.map { mail -> UiModel.Item(mail) }
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
                else -> {
                    listOf<UiModel<Mail>>(UiModel.Loader(false))
                }
            }
        }

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
            fetch = { /*mailApi.getMails(MONTH_QUERY + monthRemote).body()*/ },
            saveFetchResult = { result ->
//                result!!.mailList!!.apply {
//                    mailDao.deleteMails(box, monthLocal.first, monthLocal.second)
//                    mailDao.insertMails(this)
//                }
            },
            shouldFetch = { isInternetConnected(context) }
        )
}