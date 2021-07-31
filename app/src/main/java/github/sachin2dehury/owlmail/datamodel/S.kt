package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class S(
    @Json(name = "d")
    val d: String? = null,
    @Json(name = "u")
    val u: Long? = null
)