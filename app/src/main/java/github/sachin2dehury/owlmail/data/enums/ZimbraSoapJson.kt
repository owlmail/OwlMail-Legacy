package github.sachin2dehury.owlmail.data.enums

sealed class ZimbraSoapJson(val value: String) {
    object ACCOUNT : ZimbraSoapJson("urn:zimbraAccount")
    object MAIL : ZimbraSoapJson("urn:zimbraMail")
}
