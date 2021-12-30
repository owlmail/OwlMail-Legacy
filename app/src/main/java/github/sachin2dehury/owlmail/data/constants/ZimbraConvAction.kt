package github.sachin2dehury.owlmail.data.constants

enum class ZimbraConvAction(val value: String) {
    READ("read"),
    UNREAD("!read"),
    SPAM("spam"),
    UN_SPAM("!spam"),
    FLAG("flag"),
    UN_FLAG("!flag"),
    MOVE("move"),
    TRASH("trash")
}