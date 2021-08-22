package github.sachin2dehury.owlmail.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.sachin2dehury.owlmail.database.ParsedMailDao
import github.sachin2dehury.owlmail.datamodel.ParsedMail
import kotlinx.coroutines.flow.first

class ParsedMailPagingSource(
    private val conversationId: Int,
    private val parsedMailDao: ParsedMailDao
) : PagingSource<Int, ParsedMail>() {

    override fun getRefreshKey(state: PagingState<Int, ParsedMail>): Int? {
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

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ParsedMail> {
        return try {
            val result = getParsedMails(conversationId)
            LoadResult.Page(result, null, null)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getParsedMails(conversationId: Int): List<ParsedMail> {
        return parsedMailDao.getConversationMails(conversationId).first()
    }

//    private suspend fun insertParsedMails(conversationId: Int) =
//        mailDao.getMailsId(conversationId).first().forEach { id ->
//            val response = Jsoup.parse(mailApi.getParsedMail(id).string())
//            val parsedMail = ParsedMail(id, conversationId, response)
//            parsedMailDao.insertMail(parsedMail)
//        }
}