package github.sachin2dehury.owlmail.data.remote.sendmsg

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.remote.common.MsgId
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SendMsgResponse(
    @Json(name = "m")
    val msgId: List<MsgId>? = null,
) : Parcelable
