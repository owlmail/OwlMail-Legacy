package github.sachin2dehury.owlmail.repository

import github.sachin2dehury.owlmail.api.AuthInterceptor
import github.sachin2dehury.owlmail.api.ZimbraApiExt
import github.sachin2dehury.owlmail.data.UserDetails
import github.sachin2dehury.owlmail.utils.getZimbraAuthRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepository(
    private val authInterceptor: AuthInterceptor,
    private val zimbraApiExt: ZimbraApiExt,
) {

    fun setAuthToken(authToken: String?) {
        authInterceptor.authToken = authToken
    }

    suspend fun makeAuthRequest(userDetails: UserDetails?) =
        zimbraApiExt.provideMailApi(userDetails?.baseUrl)
            .makeAuthRequest(getZimbraAuthRequest(userDetails))

    fun resetLogin() = CoroutineScope(Dispatchers.IO).launch {
        authInterceptor.authToken = null
    }
}