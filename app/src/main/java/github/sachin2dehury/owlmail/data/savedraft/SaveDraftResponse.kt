package github.sachin2dehury.owlmail.data.savedraft


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import github.sachin2dehury.owlmail.data.searchconv.Message

@JsonClass(generateAdapter = true)
@Parcelize
data class SaveDraftResponse(
    @Json(name = "m")
    val message: List<Message>? = null,
) : Parcelable