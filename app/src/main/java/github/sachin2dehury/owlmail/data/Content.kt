package github.sachin2dehury.owlmail.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Content(
    @Json(name = "_content")
    val content: String? = null
) : Parcelable