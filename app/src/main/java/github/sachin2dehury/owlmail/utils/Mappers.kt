package github.sachin2dehury.owlmail.utils

import android.text.format.DateUtils
import github.sachin2dehury.owlmail.data.ZimbraSoap
import github.sachin2dehury.owlmail.data.local.AuthDetails

fun ZimbraSoap.mapToAuthDetails() = body?.authResponse?.let {
    val authTokenExpireTime =
        System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS + (it.lifetime ?: 0)
    val authToken = it.authToken?.firstOrNull()?.content
    AuthDetails(authToken, authTokenExpireTime)
}
