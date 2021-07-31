package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlarmX(
    @Json(name = "action")
    val action: String?,
    @Json(name = "desc")
    val desc: List<DescXX>?,
    @Json(name = "trigger")
    val trigger: List<TriggerX>?
)