package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EXX(
    @Json(name = "d")
    val d: String?,
    @Json(name = "u")
    val u: Long?
)