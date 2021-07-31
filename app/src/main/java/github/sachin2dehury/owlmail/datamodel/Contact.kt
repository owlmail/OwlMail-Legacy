package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "_attrs")
    val attrs: Attrs? = null,
    @Json(name = "d")
    val d: Long? = null,
    @Json(name = "fileAsStr")
    val fileAsStr: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "l")
    val l: String? = null,
    @Json(name = "rev")
    val rev: Int? = null
)