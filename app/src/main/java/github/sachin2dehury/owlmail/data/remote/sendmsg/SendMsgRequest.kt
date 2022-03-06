package github.sachin2dehury.owlmail.data.remote.sendmsg

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.enums.ZimbraSoapJson
import github.sachin2dehury.owlmail.data.remote.common.DraftMsg
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SendMsgRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value,
    @Json(name = "m")
    val draftMsg: DraftMsg? = null,
    @Json(name = "suid")
    val suid: Long? = null,
) : Parcelable
