package github.sachin2dehury.owlmail.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import github.sachin2dehury.owlmail.api.ZimbraApi
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import github.sachin2dehury.owlmail.database.ContactDao
import github.sachin2dehury.owlmail.database.ConversationDao
import github.sachin2dehury.owlmail.database.MessageDao
import github.sachin2dehury.owlmail.paging.SearchConvRequestPagingSource
import github.sachin2dehury.owlmail.paging.SearchGalRequestPagingSource
import github.sachin2dehury.owlmail.paging.SearchRequestPagingSource
import github.sachin2dehury.owlmail.paging.ZimbraPagingSource
import github.sachin2dehury.owlmail.utils.NetworkState
import kotlinx.coroutines.flow.Flow

class ZimbraRepository(
    private val contactDao: ContactDao,
    private val conversationDao: ConversationDao,
    private val messageDao: MessageDao,
    private val networkStateFlow: Flow<NetworkState>,
    private val pagingConfig: PagingConfig,
    private val zimbraApi: ZimbraApi,
) {

    private fun <T : Any> getZimbraPagingSourceFlow(pagingSource: ZimbraPagingSource<T>) =
        Pager(pagingConfig, 0) { pagingSource }.flow

    fun getSearchRequestPagingSource(query: Pair<String?, ZimbraFolder>) =
        getZimbraPagingSourceFlow(
            SearchRequestPagingSource(
                conversationDao,
                networkStateFlow,
                query,
                zimbraApi
            )
        )

    fun getSearchConvRequestPagingSource(conversationId: String?) =
        getZimbraPagingSourceFlow(
            SearchConvRequestPagingSource(
                conversationId,
                messageDao,
                networkStateFlow,
                zimbraApi
            )
        )

    fun getSearchGalRequestPagingSource(query: String?) =
        getZimbraPagingSourceFlow(
            SearchGalRequestPagingSource(
                contactDao,
                networkStateFlow,
                query,
                zimbraApi
            )
        )
}
