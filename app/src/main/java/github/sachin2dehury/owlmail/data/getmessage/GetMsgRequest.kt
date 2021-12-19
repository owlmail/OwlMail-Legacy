package github.sachin2dehury.owlmail.data.getmessage


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.ZimbraSoapJson
import github.sachin2dehury.owlmail.data.common.MsgId
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetMsgRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.Mail.value,
    @Json(name = "m")
    val msgId: MsgId? = null
) : Parcelable