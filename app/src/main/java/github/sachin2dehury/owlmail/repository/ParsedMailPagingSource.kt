package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.api.isInternetConnected
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.database.ParsedMailDao
import github.sachin2dehury.owlmail.datamodel.ParsedMail
import kotlinx.coroutines.flow.first

class ParsedMailPagingSource(
    private val context: Context,
    private val conversationId: Int,
    private val mailApi: MailApi,
    private val mailDao: MailDao,
    private val parsedMailDao: ParsedMailDao
) : PagingSource<Int, ParsedMail>() {
    override fun getRefreshKey(state: PagingState<Int, ParsedMail>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ParsedMail> {
        return try {
            val result = getParsedMails(conversationId)
            LoadResult.Page(result, null, null)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getParsedMails(conversationId: Int): List<ParsedMail> {
        if (isInternetConnected(context)) {
//            insertParsedMails(conversationId)
        }
        return parsedMailDao.getConversationMails(conversationId).first()
    }

//    private suspend fun insertParsedMails(conversationId: Int) =
//        mailDao.getMailsId(conversationId).first().forEach { id ->
//            val response = Jsoup.parse(mailApi.getParsedMail(id).string())
//            val parsedMail = ParsedMail(id, conversationId, response)
//            parsedMailDao.insertMail(parsedMail)
//        }
}