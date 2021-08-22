package github.sachin2dehury.owlmail.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.repository.MailRepository

class MailPagingSource(
    private val request: String,
    private val mailRepository: MailRepository
) : PagingSource<Int, UiModel<Mail>>() {

    override fun getRefreshKey(state: PagingState<Int, UiModel<Mail>>): Int? {
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

    override suspend fun load(params: LoadParams<Int>) = try {
        getMailUiModels(params.key ?: 0)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private suspend fun getMailUiModels(page: Int) = LoadResult.Page(
        mailRepository.getMailUiModels(request, page),
        if (page > 0) page - 1 else null,
        if (page < 100) page + 1 else null
    )
}