package github.sachin2dehury.owlmail.api

sealed class Event<out T>(private val value: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        value
    }

    fun peek() = value
}