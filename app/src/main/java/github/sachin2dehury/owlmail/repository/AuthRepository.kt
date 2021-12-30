package github.sachin2dehury.owlmail.repository

import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApiExt
import github.sachin2dehury.owlmail.data.UserDetails
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.utils.getZimbraAuthRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepository(
    private val basicAuthInterceptor: BasicAuthInterceptor,
    private val mailApiExt: MailApiExt,
    private val mailDao: MailDao
) {

    fun setAuthToken(authToken: String?) {
        basicAuthInterceptor.authToken = authToken
    }

    suspend fun makeAuthRequest(userDetails: UserDetails?) =
        mailApiExt.provideMailApi(userDetails?.baseUrl)
            .makeAuthRequest(getZimbraAuthRequest(userDetails))

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        basicAuthInterceptor.authToken = null
        mailDao.deleteAllMails()
    }
}