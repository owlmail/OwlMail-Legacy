package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attrs(
    @Json(name = "email")
    val email: String?,
    @Json(name = "firstName")
    val firstName: String?,
    @Json(name = "fullName")
    val fullName: String?,
    @Json(name = "lastName")
    val lastName: String?,
    @Json(name = "middleName")
    val middleName: String?
)