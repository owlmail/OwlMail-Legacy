package github.sachin2dehury.owlmail.api

//const val BASE_URL = "https://mail.nitrkl.ac.in/"
const val PRINT_HTML_URL = "h/printmessage"

enum class ZimbraSoapJson(val value: String) {
    Account("urn:zimbraAccount"),
    Mail("urn:zimbraMail")
}

enum class Types(val value: String) {
    Conversation("conversation")
}