package github.sachin2dehury.owlmail.data.constants

enum class ZimbraSearchType(val value: String) {
    //    ACCOUNT("account"),//Not valid for Search Api
    APPOINTMENT("appointment"),
    CONTACT("contact"),
    CONVERSATION("conversation"),
    DOCUMENT("document"),
    MESSAGE("message"),
    TASK("task"),
    WIKI("wiki"),
}
