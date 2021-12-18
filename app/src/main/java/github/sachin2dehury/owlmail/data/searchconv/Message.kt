package github.sachin2dehury.owlmail.data.searchconv


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.Email
import github.sachin2dehury.owlmail.data.common.MultiPartMsg
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Message(
    @Json(name = "cid")
    val cid: String? = null,
    @Json(name = "cm")
    val cm: Boolean? = null,
    @Json(name = "d")
    val date: Long? = null,
    @Json(name = "e")
    val email: List<Email>? = null,
    @Json(name = "f")
    val flags: String? = null,
    @Json(name = "fr")
    val body: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "l")
    val folder: String? = null,
    @Json(name = "mid")
    val mid: String? = null,
    @Json(name = "mp")
    val multiPartMsg: List<MultiPartMsg>? = null,
    @Json(name = "rev")
    val rev: Int? = null,
    @Json(name = "s")
    val size: Int? = null,
    @Json(name = "sd")
    val sd: Long? = null,
    @Json(name = "sf")
    val sf: String? = null,
    @Json(name = "su")
    val subject: String? = null
) : Parcelable


