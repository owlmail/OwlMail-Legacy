package github.sachin2dehury.owlmail.repository

import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApiExt
import github.sachin2dehury.owlmail.api.mapToResultState
import github.sachin2dehury.owlmail.data.Body
import github.sachin2dehury.owlmail.data.ZimbraSoap
import github.sachin2dehury.owlmail.data.auth.AuthRequest
import github.sachin2dehury.owlmail.database.MailDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepository(
    private val basicAuthInterceptor: BasicAuthInterceptor,
    private val mailApiExt: MailApiExt,
    private val mailDao: MailDao
) {

    var authToken = basicAuthInterceptor.authToken

    suspend fun makeAuthRequest(baseURL: String, authRequest: AuthRequest?) =
        mailApiExt.provideMailApi(baseURL)
            .makeAuthRequest(ZimbraSoap(Body(authRequest = authRequest))).mapToResultState()

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        basicAuthInterceptor.authToken = null
        mailDao.deleteAllMails()
    }
}