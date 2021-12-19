package github.sachin2dehury.owlmail.api

sealed class ResultState<out T> {
    object Empty : ResultState<Nothing>()
    data class Error(val value: String? = null) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    data class Success<T>(val value: T?) : ResultState<T>()
}