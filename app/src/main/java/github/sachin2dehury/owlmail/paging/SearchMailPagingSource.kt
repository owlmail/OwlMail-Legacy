package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.datamodel.Mail
import github.sachin2dehury.owlmail.epoxy.UiModel

class SearchMailPagingSource(
    private val request: String,
    private val mailApi: MailApi,
    private val mailDao: MailDao
) : BasePagingSource<Mail>() {

    override suspend fun load(params: LoadParams<Int>) = try {
        getMails(request, params.key ?: 0)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private suspend fun getMails(request: String, page: Int): LoadResult<Int, UiModel<Mail>> {
        val mails = mailApi.searchMails(request).body()?.mailList
        mails?.let { mailDao.insertMails(it) }
        val uiModel = mails?.map { mail ->
            UiModel.Item(mail)
        } ?: emptyList()
        return LoadResult.Page(
            uiModel,
            if (page > 0) page - 1 else null,
            if (page < 100) page + 1 else null
        )
    }
}