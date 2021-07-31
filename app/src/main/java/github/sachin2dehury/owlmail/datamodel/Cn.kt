package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cn(
    @Json(name = "_attrs")
    val attrs: Attrs?,
    @Json(name = "d")
    val d: Long?,
    @Json(name = "fileAsStr")
    val fileAsStr: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "l")
    val l: String?,
    @Json(name = "rev")
    val rev: Int?
)