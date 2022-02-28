package github.sachin2dehury.owlmail.data.remote.common

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class DraftMsg(
    @Json(name = "attach")
    val attachment: Attachment? = null,
    @Json(name = "e")
    val email: List<Email>? = null,
    @Json(name = "mp")
    val multiPartMsg: List<MultiPartMsg>? = null,
    @Json(name = "msu")
    val subject: Content? = null,
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Attachment(
        @Json(name = "m")
        val msgId: List<MsgId>? = null,
    ) : Parcelable
}
