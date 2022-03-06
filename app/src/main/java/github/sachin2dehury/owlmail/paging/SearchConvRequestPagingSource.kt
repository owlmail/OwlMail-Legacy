package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.ZimbraApi
import github.sachin2dehury.owlmail.data.remote.searchconv.Message
import github.sachin2dehury.owlmail.database.MessageDao
import github.sachin2dehury.owlmail.utils.NetworkState
import github.sachin2dehury.owlmail.utils.getZimbraSearchConvRequest
import kotlinx.coroutines.flow.Flow

class SearchConvRequestPagingSource(
    private val conversationId: String?,
    private val messageDao: MessageDao,
    networkStateFlow: Flow<NetworkState>,
    private val zimbraApi: ZimbraApi,
) : ZimbraPagingSource<Message>(networkStateFlow) {

    override suspend fun loadPageFromRemote(
        offset: Int,
        limit: Int,
    ): LoadResult<Int, Message> {
        val request = getZimbraSearchConvRequest(conversationId, offset, limit)
        val response = zimbraApi.makeSearchConvRequest(request).body()?.body?.searchConvResponse
//        coroutineScope { launch { messageDao.insertMessage(response?.message) } }
        return LoadResult.Page(
            data = response?.message ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    }

    override suspend fun loadPageFromLocal(params: LoadParams<Int>) =
        messageDao.getMessage().load(params)
}
