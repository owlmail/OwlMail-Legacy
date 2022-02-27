package github.sachin2dehury.owlmail.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    var authToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response = request(chain)

    private fun request(chain: Interceptor.Chain) = chain.proceed(
        when (!authToken.isNullOrEmpty()) {
            true -> chain.request().newBuilder().header("Cookie", "ZM_AUTH_TOKEN=$authToken")
                .build()
            else -> chain.request().newBuilder().build()
        }
    )
}
