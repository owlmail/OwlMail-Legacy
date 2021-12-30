package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.data.search.Conversation
import github.sachin2dehury.owlmail.utils.getZimbraSearchRequest

class SearchRequestPagingSource(
    private val mailApi: MailApi,
    private val query: String?
) : ZimbraPagingSource<Conversation>() {

    override suspend fun tryLoadingPage(
        offset: Int,
        limit: Int
    ): LoadResult.Page<Int, Conversation> {
        val request = getZimbraSearchRequest(query, offset, limit)
        val response = mailApi.makeSearchRequest(request).body()?.body?.searchResponse
        return LoadResult.Page(
            data = response?.conversations ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    }
}