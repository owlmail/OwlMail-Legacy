package github.sachin2dehury.owlmail.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

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
    crossinline saveFetchResult: suspend (RequestType) -> ResultType,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
) = flow {
    emit(ResultState.Loading)
    val data = query().first()
    emit(if (shouldFetch(data)) {
        try {
            ResultState.Success(saveFetchResult(fetch()))
        } catch (throwable: Throwable) {
            query().map { ResultState.Error(throwable.message) }
        }
    } else {
        query().map { ResultState.Success(it) }
    })
}

fun <T> Response<T>.mapToResultState() = when {
    isSuccessful && code() == 200 -> ResultState.Success(body()!!)
    else -> ResultState.Error(message())
}