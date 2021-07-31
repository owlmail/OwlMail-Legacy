package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RelX(
    @Json(name = "m")
    val m: Int?,
    @Json(name = "neg")
    val neg: Boolean?,
    @Json(name = "related")
    val related: String?
)