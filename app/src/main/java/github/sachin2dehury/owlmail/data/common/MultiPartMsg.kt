package github.sachin2dehury.owlmail.data.common


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MultiPartMsg(
    val body: Boolean? = null,
    @Json(name = "cd")
    val cd: String? = null,
    val content: String? = null,
    @Json(name = "ct")
    val contentType: String? = null,
    @Json(name = "filename")
    val fileName: String? = null,
    @Json(name = "mp")
    val multiPartMsg: List<MultiPartMsg>? = null,
    val part: String? = null,
    @Json(name = "s")
    val size: Int? = null,
) : Parcelable