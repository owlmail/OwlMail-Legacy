package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Appt(
    @Json(name = "d")
    val d: Long? = null,
    @Json(name = "f")
    val f: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "inv")
    val inv: List<Inv>? = null,
    @Json(name = "l")
    val l: String? = null,
    @Json(name = "nextAlarm")
    val nextAlarm: Long? = null,
    @Json(name = "rev")
    val rev: Int? = null,
    @Json(name = "s")
    val s: Int? = null,
    @Json(name = "uid")
    val uid: String? = null
)