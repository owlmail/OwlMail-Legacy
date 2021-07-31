package github.sachin2dehury.owlmail.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.flow.*

@Suppress("DEPRECATION")
fun isInternetConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
    return connectivityManager.activeNetworkInfo?.isAvailable ?: false
}

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Exception) -> Unit = { },
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
) = flow {
    emit(ResultStatus.Loading)
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(ResultStatus.Loading)
        try {
            saveFetchResult(fetch())
            query().map { ResultStatus.Success(it) }
        } catch (e: Exception) {
            onFetchFailed(e)
            query().map { ResultStatus.Failure(e) }
        }
    } else {
        query().map { ResultStatus.Success(it) }
    }
    emitAll(flow)
}