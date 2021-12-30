package github.sachin2dehury.owlmail.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class ZimbraPagingSource<T : Any> : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> = try {
        tryLoadingPage(params.key ?: 0, params.loadSize)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    abstract suspend fun tryLoadingPage(offset: Int, limit: Int): LoadResult.Page<Int, T>
}