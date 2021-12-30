package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.database.ParsedMailDao
import github.sachin2dehury.owlmail.paging.SearchConvRequestPagingSource
import github.sachin2dehury.owlmail.paging.SearchGalRequestPagingSource
import github.sachin2dehury.owlmail.paging.SearchRequestPagingSource
import github.sachin2dehury.owlmail.paging.ZimbraPagingSource

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

    fun getSearchConvRequestPagingSource(conversationId: String?) =
        getZimbraPagingSourceFlow(SearchConvRequestPagingSource(mailApi, conversationId))

    fun getSearchGalRequestPagingSource(query: String?) =
        getZimbraPagingSourceFlow(SearchGalRequestPagingSource(mailApi, query))
}