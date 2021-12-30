package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.data.searchconv.Message
import github.sachin2dehury.owlmail.utils.getZimbraSearchConvRequest

class SearchConvRequestPagingSource(
    private val mailApi: MailApi,
    private val conversationId: String?
) : ZimbraPagingSource<Message>() {

    override suspend fun tryLoadingPage(offset: Int, limit: Int): LoadResult.Page<Int, Message> {
        val request = getZimbraSearchConvRequest(conversationId, offset, limit)
        val response = mailApi.makeSearchConvRequest(request).body()?.body?.searchConvResponse
        return LoadResult.Page(
            data = response?.message ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    }
}