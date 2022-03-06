package github.sachin2dehury.owlmail.data.remote.common

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Email(
    @Json(name = "a")
    val address: String? = null,
    @Json(name = "d")
    val firstName: String? = null,
    @Json(name = "p")
    val fullName: String? = null,
    @Json(name = "t")
    val type: String? = null,
) : Parcelable
