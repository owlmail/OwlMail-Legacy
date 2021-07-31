package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Items(
    @Json(name = "appt")
    val appt: List<Appt>?,
    @Json(name = "cn")
    val cn: List<Cn>?,
    @Json(name = "m")
    val m: List<M>?
)