package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Xprop(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "value")
    val value: String? = null
)