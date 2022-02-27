package github.sachin2dehury.owlmail.data.getmessage

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.searchconv.Message
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetMsgResponse(
    @Json(name = "m")
    val message: List<Message>? = null,
) : Parcelable
