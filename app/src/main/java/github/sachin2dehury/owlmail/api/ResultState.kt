package github.sachin2dehury.owlmail.api

sealed class ResultState<out T> {
    object Empty : ResultState<Nothing>()
    data class Error<T>(val error: String? = null, val value: T? = null) : ResultState<T>()
    object Loading : ResultState<Nothing>()
    data class Success<T>(val value: T?) : ResultState<T>()
}