package github.sachin2dehury.owlmail.utils

sealed class NetworkState {
    object Available : NetworkState()
    object Unavailable : NetworkState()
    object Lost : NetworkState()
}
