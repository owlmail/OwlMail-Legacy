package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.ZimbraApi
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import github.sachin2dehury.owlmail.data.remote.search.Conversation
import github.sachin2dehury.owlmail.database.ConversationDao
import github.sachin2dehury.owlmail.utils.NetworkState
import github.sachin2dehury.owlmail.utils.getZimbraSearchRequest
import github.sachin2dehury.owlmail.utils.mapToRemoteQuery
import kotlinx.coroutines.flow.Flow

class SearchRequestPagingSource(
    private val conversationDao: ConversationDao,
    networkStateFlow: Flow<NetworkState>,
    private val query: Pair<String?, ZimbraFolder>,
    private val zimbraApi: ZimbraApi,
) : ZimbraPagingSource<Conversation>(networkStateFlow) {

    override suspend fun loadPageFromRemote(
        offset: Int,
        limit: Int,
    ): LoadResult<Int, Conversation> {
        val request = getZimbraSearchRequest(query.mapToRemoteQuery(), offset, limit)
        val response = zimbraApi.makeSearchRequest(request).body()?.body?.searchResponse
//        coroutineScope { launch { conversationDao.insertConversation(response?.conversations) } }
        return LoadResult.Page(
            data = response?.conversations ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    }

    override suspend fun loadPageFromLocal(params: LoadParams<Int>) =
        when (query.first.isNullOrEmpty()) {
            true -> conversationDao.getConversation().load(params)
            else -> conversationDao.searchConversation(query.first!!, query.second.index)
                .load(params)
        }
}
