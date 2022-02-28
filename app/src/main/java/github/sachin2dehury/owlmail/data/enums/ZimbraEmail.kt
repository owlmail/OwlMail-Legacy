package github.sachin2dehury.owlmail.data.enums

sealed class ZimbraEmail(val value: String) {
    object TO : ZimbraEmail("t")
    object FROM : ZimbraEmail("f")
    object CC : ZimbraEmail("c")
    object BCC : ZimbraEmail("b")
}
