package github.sachin2dehury.owlmail.data.remote.getmessage

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.enums.ZimbraSoapJson
import github.sachin2dehury.owlmail.data.remote.common.MsgId
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetMsgRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value,
    @Json(name = "m")
    val msgId: MsgId? = null,
) : Parcelable
