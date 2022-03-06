package github.sachin2dehury.owlmail.data.enums

sealed class ZimbraConvAction(val value: String) {
    object READ : ZimbraConvAction("read")
    object UNREAD : ZimbraConvAction("!read")
    object SPAM : ZimbraConvAction("spam")
    object UN_SPAM : ZimbraConvAction("!spam")
    object FLAG : ZimbraConvAction("flag")
    object UN_FLAG : ZimbraConvAction("!flag")
    object MOVE : ZimbraConvAction("move")
    object TRASH : ZimbraConvAction("trash")
}
