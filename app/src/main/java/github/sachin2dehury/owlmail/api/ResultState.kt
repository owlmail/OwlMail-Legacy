package github.sachin2dehury.owlmail.api

sealed class ResultState {
    data class Success<T>(val value: T) : ResultState()
    data class Error(val value: String? = "Expected a notnull value") : ResultState()
    object Loading : ResultState()
}