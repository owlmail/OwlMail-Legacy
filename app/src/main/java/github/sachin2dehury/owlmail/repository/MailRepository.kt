package github.sachin2dehury.owlmail.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.api.ResultStatus
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.datamodel.Mail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MailRepository(
    private val basicAuthInterceptor: BasicAuthInterceptor,
    private val context: Context,
    private val mailApi: MailApi,
    private val mailDao: MailDao,
//    private val parsedMailDao: ParsedMailDao,
    private val pagerConfig: PagingConfig,
) {

    fun getSearchMails(request: String) =
        getPager(SearchMailPagingSource(context, request, mailApi, mailDao))

//    fun getParsedMails(conversationId: Int) =
//        getPager(ParsedMailPagingSource(context, conversationId, mailApi, mailDao, parsedMailDao))

    fun getMails(request: String) =
        getPager(MailPagingSource(getBox(request), context, request, mailApi, mailDao))

    suspend fun login(): ResultStatus<List<Mail>> = try {
        val response = mailApi.login()
        if (response.isSuccessful && response.code() == 200) {
            ResultStatus.Success(response.body()?.mailList!!)
        } else {
            ResultStatus.Error(Exception(response.message()))
        }
    } catch (e: Exception) {
        ResultStatus.Error(e)
    }

    fun setCredential(credential: String) {
        basicAuthInterceptor.credential = credential
    }

    fun getCredential() = basicAuthInterceptor.credential

    fun setToken(token: String) {
        basicAuthInterceptor.token = token
    }

    fun getToken() = basicAuthInterceptor.token

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        mailDao.deleteAllMails()
//        parsedMailDao.deleteAllMails()
        basicAuthInterceptor.credential = ""
        basicAuthInterceptor.token = ""
    }

    private fun <T : Any> getPager(pagingSource: PagingSource<Int, T>) =
        Pager(pagerConfig, 0, { pagingSource }).flow

    fun getBox(request: String) = when (request) {
        context.getString(R.string.inbox) -> 2
        context.getString(R.string.trash) -> 3
        context.getString(R.string.junk) -> 4
        context.getString(R.string.sent) -> 5
        context.getString(R.string.draft) -> 6
        else -> 0
    }.toByte()
}