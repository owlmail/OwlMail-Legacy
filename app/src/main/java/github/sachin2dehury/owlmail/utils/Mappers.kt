package github.sachin2dehury.owlmail.utils

import android.text.format.DateUtils
import android.webkit.URLUtil
import github.sachin2dehury.owlmail.data.enums.ZimbraFolder
import github.sachin2dehury.owlmail.data.local.AuthDetails
import github.sachin2dehury.owlmail.data.local.AuthType
import github.sachin2dehury.owlmail.data.local.SessionDetails
import github.sachin2dehury.owlmail.data.remote.ZimbraSoap

fun ZimbraSoap.mapToAuthDetails() = body?.authResponse?.let {
    val authTokenExpireTime =
        System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS + (it.lifetime ?: 0)
    val authToken = it.authToken?.firstOrNull()?.content
    AuthDetails(authToken, authTokenExpireTime)
}

fun SessionDetails.mapToAuthType(): AuthType {
    val isBaseUrlValid = URLUtil.isValidUrl(userDetails?.baseUrl)
    val hasTokenExpired = authDetails?.authTokenExpireTime.hasTokenExpired()
    return when {
        isBaseUrlValid && !hasTokenExpired -> AuthType.VALID_TOKEN
        isBaseUrlValid && hasTokenExpired -> AuthType.REFRESH_TOKEN
        isBaseUrlValid -> AuthType.INVALID_TOKEN
        else -> AuthType.INVALID_BASEURL
    }
}

fun Pair<String?, ZimbraFolder>.mapToRemoteQuery() = when (first.isNullOrBlank()) {
    true -> second.mapToQuery()
    else -> "$first ${second.mapToQuery()}"
}
