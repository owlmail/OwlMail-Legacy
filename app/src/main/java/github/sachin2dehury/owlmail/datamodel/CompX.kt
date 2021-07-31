package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompX(
    @Json(name = "alarm")
    val alarm: List<AlarmX>?,
    @Json(name = "apptId")
    val apptId: String?,
    @Json(name = "at")
    val at: List<AtX>?,
    @Json(name = "calItemId")
    val calItemId: String?,
    @Json(name = "ciFolder")
    val ciFolder: String?,
    @Json(name = "class")
    val classX: String?,
    @Json(name = "compNum")
    val compNum: Int?,
    @Json(name = "d")
    val d: Long?,
    @Json(name = "desc")
    val desc: List<DescXXX>?,
    @Json(name = "e")
    val e: List<EXX>?,
    @Json(name = "fb")
    val fb: String?,
    @Json(name = "fba")
    val fba: String?,
    @Json(name = "fr")
    val fr: String?,
    @Json(name = "loc")
    val loc: String?,
    @Json(name = "method")
    val method: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "or")
    val or: OrX?,
    @Json(name = "rsvp")
    val rsvp: Boolean?,
    @Json(name = "s")
    val s: List<SX>?,
    @Json(name = "seq")
    val seq: Int?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "transp")
    val transp: String?,
    @Json(name = "uid")
    val uid: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "x_uid")
    val xUid: String?,
    @Json(name = "xprop")
    val xprop: List<XpropX>?
)