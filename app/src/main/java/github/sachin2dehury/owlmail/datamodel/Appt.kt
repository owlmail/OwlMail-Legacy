package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Appt(
    @Json(name = "d")
    val d: Long?,
    @Json(name = "f")
    val f: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "inv")
    val inv: List<Inv>?,
    @Json(name = "l")
    val l: String?,
    @Json(name = "nextAlarm")
    val nextAlarm: Long?,
    @Json(name = "rev")
    val rev: Int?,
    @Json(name = "s")
    val s: Int?,
    @Json(name = "uid")
    val uid: String?
)