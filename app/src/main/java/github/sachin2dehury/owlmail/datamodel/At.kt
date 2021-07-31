package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class At(
    @Json(name = "a")
    val a: String? = null,
    @Json(name = "cutype")
    val cutype: String? = null,
    @Json(name = "d")
    val d: String? = null,
    @Json(name = "ptst")
    val ptst: String? = null,
    @Json(name = "role")
    val role: String? = null,
    @Json(name = "rsvp")
    val rsvp: Boolean? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "xparam")
    val xparam: List<Xparam>? = null
)