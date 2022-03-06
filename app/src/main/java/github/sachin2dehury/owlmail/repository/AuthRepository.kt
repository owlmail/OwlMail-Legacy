package github.sachin2dehury.owlmail.repository

import github.sachin2dehury.owlmail.api.AuthInterceptor
import github.sachin2dehury.owlmail.api.ZimbraApiExt
import github.sachin2dehury.owlmail.data.local.UserDetails
import github.sachin2dehury.owlmail.utils.getZimbraAuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val authInterceptor: AuthInterceptor,
    private val zimbraApiExt: ZimbraApiExt,
) {

    fun setAuthToken(authToken: String?) {
        authInterceptor.authToken = authToken
    }

    suspend fun makeAuthRequest(userDetails: UserDetails?) = withContext(Dispatchers.IO) {
        zimbraApiExt.provideMailApi(userDetails?.baseUrl)
            .makeAuthRequest(getZimbraAuthRequest(userDetails))
    }

    suspend fun resetLogin() = withContext(Dispatchers.IO) {
        authInterceptor.authToken = null
    }
}
