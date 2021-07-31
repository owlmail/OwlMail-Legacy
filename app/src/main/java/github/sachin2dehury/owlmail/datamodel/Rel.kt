package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rel(
    @Json(name = "m")
    val m: Int? = null,
    @Json(name = "neg")
    val neg: Boolean? = null,
    @Json(name = "related")
    val related: String? = null
)