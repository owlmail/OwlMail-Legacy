package github.sachin2dehury.owlmail.data.remote.search

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MsgMeta(
    @Json(name = "f")
    val flags: String? = null,
    val id: String? = null,
    @Json(name = "l")
    val folder: String? = null,
    @Json(name = "s")
    val size: String? = null,
) : Parcelable
