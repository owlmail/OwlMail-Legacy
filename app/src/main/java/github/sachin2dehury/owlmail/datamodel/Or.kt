package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Or(
    @Json(name = "a")
    val a: String? = null,
    @Json(name = "d")
    val d: String? = null,
    @Json(name = "url")
    val url: String? = null
)