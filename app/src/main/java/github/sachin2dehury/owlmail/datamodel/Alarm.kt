package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alarm(
    @Json(name = "action")
    val action: String? = null,
    @Json(name = "desc")
    val desc: List<Desc>? = null,
    @Json(name = "trigger")
    val trigger: List<Trigger>? = null
)