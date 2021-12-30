package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.data.searchconv.Message
import github.sachin2dehury.owlmail.utils.getZimbraSearchConvRequest

class SearchConvRequestPagingSource(
    private val mailApi: MailApi,
    private val conversationId: String?
) : ZimbraPagingSource<Message>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Message> = try {
        val offset = params.key ?: 0
        val limit = params.loadSize
        val request = getZimbraSearchConvRequest(conversationId, offset, limit)
        val response = mailApi.makeSearchConvRequest(request).body()?.body?.searchConvResponse
        LoadResult.Page(
            data = response?.message ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}