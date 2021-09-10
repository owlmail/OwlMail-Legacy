package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.repository.MailRepository

class MailPagingSource(
    private val request: String,
    private val mailRepository: MailRepository
) : BasePagingSource<Mail>() {

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