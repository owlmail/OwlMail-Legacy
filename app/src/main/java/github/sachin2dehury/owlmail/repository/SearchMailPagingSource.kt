package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.datamodel.Mail

class SearchMailPagingSource(
    private val context: Context,
    private val request: String,
    private val mailApi: MailApi,
    private val mailDao: MailDao
) : PagingSource<Int, Mail>() {
    override fun getRefreshKey(state: PagingState<Int, Mail>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Mail> = try {
        getMails(request, params.key ?: 0)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private suspend fun getMails(request: String, page: Int): LoadResult<Int, Mail> {
        val mails = mailApi.searchMails(request).body()?.mailList
        mails?.let { mailDao.insertMails(it) }
        return LoadResult.Page(mails ?: emptyList(), null, null)
    }
}