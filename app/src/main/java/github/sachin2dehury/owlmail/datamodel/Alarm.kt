package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alarm(
    @Json(name = "action")
    val action: String?,
    @Json(name = "desc")
    val desc: List<Desc>?,
    @Json(name = "trigger")
    val trigger: List<Trigger>?
)