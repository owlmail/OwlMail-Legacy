package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.repository.MailRepository

class SearchMailPagingSource(
    private val query: String,
    private val mailRepository: MailRepository
) : BasePagingSource<Mail>() {

    override suspend fun load(params: LoadParams<Int>) = try {
        getSearchMailUiModels(query, params.key ?: 0)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private suspend fun getSearchMailUiModels(query: String, page: Int) = LoadResult.Page(
        mailRepository.getSearchMailUiModels(query),
        if (page > 0) page - 1 else null,
        if (page < 20) page + 1 else null
    )
}