package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mail(
    @Json(name = "cid")
    val conversationId: String? = null,
    @Json(name = "d")
    val time: Long? = null,
    @Json(name = "e")
    val addressList: List<Address>? = null,
    @Json(name = "f")
    val flags: String? = null,
    @Json(name = "fr")
    val body: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "inv")
    val inv: List<Inv>? = null,
    @Json(name = "l")
    val box: String? = null,
    @Json(name = "rev")
    val rev: Int? = null,
    @Json(name = "s")
    val size: Int? = null,
    @Json(name = "su")
    val subject: String? = null
)