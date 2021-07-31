package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attrs(
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "firstName")
    val firstName: String? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "lastName")
    val lastName: String? = null,
    @Json(name = "middleName")
    val middleName: String? = null
)