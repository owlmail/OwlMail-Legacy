package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "a")
    val email: String? = null,
    @Json(name = "d")
    val firstName: String? = null,
    @Json(name = "p")
    val name: String? = null,
    @Json(name = "t")
    val isReceiver: String? = null
)