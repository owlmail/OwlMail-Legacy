package github.sachin2dehury.owlmail.data.savedraft


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import github.sachin2dehury.owlmail.data.common.DraftMsg
import github.sachin2dehury.owlmail.data.common.ZimbraSoapJson

@JsonClass(generateAdapter = true)
@Parcelize
data class SaveDraftRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.Mail.value,
    @Json(name = "m")
    val draftMsg: DraftMsg? = null
) : Parcelable