package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comp(
    @Json(name = "alarm")
    val alarm: List<Alarm>? = null,
    @Json(name = "allDay")
    val allDay: Boolean? = null,
    @Json(name = "apptId")
    val apptId: String? = null,
    @Json(name = "at")
    val at: List<At>? = null,
    @Json(name = "calItemId")
    val calItemId: String? = null,
    @Json(name = "ciFolder")
    val ciFolder: String? = null,
    @Json(name = "class")
    val classX: String? = null,
    @Json(name = "compNum")
    val compNum: Int? = null,
    @Json(name = "d")
    val d: Long? = null,
    @Json(name = "desc")
    val desc: List<Desc>? = null,
    @Json(name = "e")
    val address: List<Address>? = null,
    @Json(name = "fb")
    val fb: String? = null,
    @Json(name = "fba")
    val fba: String? = null,
    @Json(name = "fr")
    val fr: String? = null,
    @Json(name = "isOrg")
    val isOrg: Boolean? = null,
    @Json(name = "loc")
    val loc: String? = null,
    @Json(name = "method")
    val method: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "noBlob")
    val noBlob: Boolean? = null,
    @Json(name = "or")
    val or: Or? = null,
    @Json(name = "rsvp")
    val rsvp: Boolean? = null,
    @Json(name = "s")
    val s: List<S>? = null,
    @Json(name = "seq")
    val seq: Int? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "transp")
    val transp: String? = null,
    @Json(name = "uid")
    val uid: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "x_uid")
    val xUid: String? = null,
    @Json(name = "xprop")
    val xprop: List<Xprop>? = null
)