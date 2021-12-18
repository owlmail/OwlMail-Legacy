package github.sachin2dehury.owlmail.data.savedraft


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import github.sachin2dehury.owlmail.data.common.DraftMsg

@JsonClass(generateAdapter = true)
@Parcelize
data class SaveDraftRequest(
    @Json(name = "_jsns")
    val jsns: String? = null,
    @Json(name = "m")
    val draftMsg: DraftMsg? = null
) : Parcelable