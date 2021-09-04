package github.sachin2dehury.owlmail.repository

import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApiExt
import github.sachin2dehury.owlmail.api.mapToResultState
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.database.ParsedMailDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepository(
    private val basicAuthInterceptor: BasicAuthInterceptor,
    private val mailApiExt: MailApiExt,
    private val mailDao: MailDao,
    private val parsedMailDao: ParsedMailDao,
) {

    suspend fun attemptLogin(baseURL: String) =
        mailApiExt.provideMailApi(baseURL).attemptLogin().mapToResultState()

    fun setCredential(credential: String) {
        basicAuthInterceptor.credential = credential
    }

    fun getCredential() = basicAuthInterceptor.credential ?: ""

    fun setToken(token: String) {
        basicAuthInterceptor.token = token
    }

    fun getToken() = basicAuthInterceptor.token ?: ""

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        mailDao.deleteAllMails()
        parsedMailDao.deleteAllMails()
        basicAuthInterceptor.credential = null
        basicAuthInterceptor.token = null
    }
}