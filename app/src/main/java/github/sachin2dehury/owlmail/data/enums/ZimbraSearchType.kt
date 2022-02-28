package github.sachin2dehury.owlmail.data.enums

sealed class ZimbraSearchType(val value: String) {
    @Deprecated("Not valid for Search Api")
    object ACCOUNT : ZimbraSearchType("account")
    object APPOINTMENT : ZimbraSearchType("appointment")
    object CONTACT : ZimbraSearchType("contact")
    object CONVERSATION : ZimbraSearchType("conversation")
    object DOCUMENT : ZimbraSearchType("document")
    object MESSAGE : ZimbraSearchType("message")
    object TASK : ZimbraSearchType("task")
    object WIKI : ZimbraSearchType("wiki")
}
