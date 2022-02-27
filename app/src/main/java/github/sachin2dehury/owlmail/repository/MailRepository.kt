package github.sachin2dehury.owlmail.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import github.sachin2dehury.owlmail.api.ZimbraApi
import github.sachin2dehury.owlmail.paging.SearchConvRequestPagingSource
import github.sachin2dehury.owlmail.paging.SearchGalRequestPagingSource
import github.sachin2dehury.owlmail.paging.SearchRequestPagingSource
import github.sachin2dehury.owlmail.paging.ZimbraPagingSource

class MailRepository(
    private val zimbraApi: ZimbraApi,
    private val pagingConfig: PagingConfig,
) {

    private fun <T : Any> getZimbraPagingSourceFlow(pagingSource: ZimbraPagingSource<T>) =
        Pager(pagingConfig, 0) { pagingSource }.flow

    fun getSearchRequestPagingSource(query: String?) =
        getZimbraPagingSourceFlow(SearchRequestPagingSource(zimbraApi, query))

    fun getSearchConvRequestPagingSource(conversationId: String?) =
        getZimbraPagingSourceFlow(SearchConvRequestPagingSource(zimbraApi, conversationId))

    fun getSearchGalRequestPagingSource(query: String?) =
        getZimbraPagingSourceFlow(SearchGalRequestPagingSource(zimbraApi, query))
}
