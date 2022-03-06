package github.sachin2dehury.owlmail.data.enums

sealed class ZimbraFetch(val value: String) {
    object ALL : ZimbraFetch("all")
    object UNREAD : ZimbraFetch("u")
}
