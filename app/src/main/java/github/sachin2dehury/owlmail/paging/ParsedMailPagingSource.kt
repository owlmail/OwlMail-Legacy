package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.datamodel.ParsedMail
import github.sachin2dehury.owlmail.epoxy.UiModel
import github.sachin2dehury.owlmail.repository.MailRepository
import kotlinx.coroutines.flow.last

class ParsedMailPagingSource(
    private val conversationId: Int,
    private val mailRepository: MailRepository
) : BasePagingSource<ParsedMail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UiModel<ParsedMail>> = try {
        val result = mailRepository.getParsedMailUiModels(conversationId).last()
        LoadResult.Page(result, null, null)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}