package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class At(
    @Json(name = "a")
    val a: String?,
    @Json(name = "cutype")
    val cutype: String?,
    @Json(name = "d")
    val d: String?,
    @Json(name = "ptst")
    val ptst: String?,
    @Json(name = "role")
    val role: String?,
    @Json(name = "rsvp")
    val rsvp: Boolean?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "xparam")
    val xparam: List<Xparam>?
)