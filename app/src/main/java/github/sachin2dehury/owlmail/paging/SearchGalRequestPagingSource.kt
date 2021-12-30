package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.data.searchgal.Contact
import github.sachin2dehury.owlmail.utils.getZimbraSearchGalRequest

class SearchGalRequestPagingSource(
    private val mailApi: MailApi,
    private val query: String?
) : ZimbraPagingSource<Contact>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Contact> = try {
        val offset = params.key ?: 0
        val limit = params.loadSize
        val request = getZimbraSearchGalRequest(query, offset, limit)
        val response = mailApi.makeSearchRequest(request).body()?.body?.searchGalResponse
        LoadResult.Page(
            data = response?.contact ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}