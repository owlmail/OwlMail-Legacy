package github.sachin2dehury.owlmail.api

sealed class ResultState<out T> {
    data class Success<out T>(val value: T) : ResultState<T>()
    data class Error(val value: String? = null) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}