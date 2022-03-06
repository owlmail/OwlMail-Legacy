package github.sachin2dehury.owlmail.data.remote.savedraft

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.remote.searchconv.Message
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SaveDraftResponse(
    @Json(name = "m")
    val message: List<Message>? = null,
) : Parcelable
