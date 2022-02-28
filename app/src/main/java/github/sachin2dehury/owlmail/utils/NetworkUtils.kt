package github.sachin2dehury.owlmail.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import github.sachin2dehury.owlmail.api.ResultState
import retrofit2.Response

fun isInternetConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities =
        connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
        else -> false
    }
}

suspend inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: suspend () -> ResultType,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> ResultType,
    crossinline shouldFetch: () -> Boolean = { true },
) = if (shouldFetch()) {
    try {
        saveFetchResult(fetch())
        ResultState.Success(query())
    } catch (throwable: Throwable) {
        ResultState.Error(throwable.message)
    }
} else {
    ResultState.Success(query())
}

fun <T> Response<T>.mapToResultState() = when {
    isSuccessful && code() == 200 -> ResultState.Success(body())
    else -> ResultState.Error(message())
}
