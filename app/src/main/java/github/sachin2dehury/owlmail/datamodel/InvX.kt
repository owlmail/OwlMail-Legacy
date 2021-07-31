package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InvX(
    @Json(name = "comp")
    val comp: List<CompX>?,
    @Json(name = "type")
    val type: String?
)