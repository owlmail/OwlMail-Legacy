package github.sachin2dehury.owlmail.utils

import github.sachin2dehury.owlmail.api.ResultState
import retrofit2.Response

suspend inline fun <T> networkBoundResource(
    crossinline loadFromLocal: suspend () -> T,
    crossinline loadFromRemote: suspend () -> T,
    shouldLoadFromRemote: Boolean = true,
) = when (shouldLoadFromRemote) {
    true -> loadFromRemote()
    else -> loadFromLocal()
}

fun <T> Response<T>.mapToResultState() = when {
    isSuccessful && code() == 200 -> ResultState.Success(body())
    else -> ResultState.Error(message())
}
