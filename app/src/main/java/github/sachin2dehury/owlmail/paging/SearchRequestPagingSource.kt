package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.ZimbraApi
import github.sachin2dehury.owlmail.data.search.Conversation
import github.sachin2dehury.owlmail.utils.getZimbraSearchRequest

class SearchRequestPagingSource(
    private val zimbraApi: ZimbraApi,
    private val query: String?
) : ZimbraPagingSource<Conversation>() {

    override suspend fun tryLoadingPage(
        offset: Int,
        limit: Int
    ): LoadResult.Page<Int, Conversation> {
        val request = getZimbraSearchRequest(query, offset, limit)
        val response = zimbraApi.makeSearchRequest(request).body()?.body?.searchResponse
        return LoadResult.Page(
            data = response?.conversations ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    }
}