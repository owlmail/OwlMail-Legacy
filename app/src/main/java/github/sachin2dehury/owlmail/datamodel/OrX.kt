package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrX(
    @Json(name = "a")
    val a: String?,
    @Json(name = "d")
    val d: String?,
    @Json(name = "url")
    val url: String?
)