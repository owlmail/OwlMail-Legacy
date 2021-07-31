package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class M(
    @Json(name = "cid")
    val cid: String?,
    @Json(name = "d")
    val d: Long?,
    @Json(name = "e")
    val e: List<EX>?,
    @Json(name = "f")
    val f: String?,
    @Json(name = "fr")
    val fr: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "inv")
    val inv: List<InvX>?,
    @Json(name = "l")
    val l: String?,
    @Json(name = "rev")
    val rev: Int?,
    @Json(name = "s")
    val s: Int?,
    @Json(name = "su")
    val su: String?
)