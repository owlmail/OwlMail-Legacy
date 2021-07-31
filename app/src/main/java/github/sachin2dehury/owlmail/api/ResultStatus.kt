package github.sachin2dehury.owlmail.api

sealed class ResultStatus<out T> {
    data class Success<out T>(val value: T) : ResultStatus<T>()
    data class Error(val exception: Exception) : ResultStatus<Nothing>()
    object Loading : ResultStatus<Nothing>()
}