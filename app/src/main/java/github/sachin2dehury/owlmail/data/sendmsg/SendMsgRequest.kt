package github.sachin2dehury.owlmail.data.sendmsg


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.DraftMsg
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SendMsgRequest(
    @Json(name = "_jsns")
    val jsns: String? = null,
    @Json(name = "m")
    val draftMsg: DraftMsg? = null,
    @Json(name = "suid")
    val suid: Long? = null
) : Parcelable