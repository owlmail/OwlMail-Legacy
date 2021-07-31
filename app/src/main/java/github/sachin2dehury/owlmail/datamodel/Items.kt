package github.sachin2dehury.owlmail.datamodel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Items(
    @Json(name = "appt")
    val apptList: List<Appt>? = null,
    @Json(name = "cn")
    val contactList: List<Contact>? = null,
    @Json(name = "m")
    val mailList: List<Mail>? = null
)