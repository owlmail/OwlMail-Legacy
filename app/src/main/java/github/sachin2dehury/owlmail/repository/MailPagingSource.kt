package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.sachin2dehury.owlmail.api.MONTH_QUERY
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.api.getMonth
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.datamodel.Mail

class MailPagingSource(
    private val box: Byte,
    private val context: Context,
    private val request: String,
    private val mailApi: MailApi,
    private val mailDao: MailDao
) : PagingSource<Int, Mail>() {
    override fun getRefreshKey(state: PagingState<Int, Mail>) = 0

    override suspend fun load(params: LoadParams<Int>) = try {
        getMails(params.key ?: 0)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private suspend fun getMails(page: Int): LoadResult<Int, Mail> {
        val month = getMonth(page, context)
        val mailList = mailApi.getMails(MONTH_QUERY + month).body()?.mailList
        mailList?.let { mailDao.insertMails(it) }
        return LoadResult.Page(
            mailList ?: emptyList(),
            if (page > 0) page - 1 else null,
            if (page < 100) page + 1 else null
        )
    }
}