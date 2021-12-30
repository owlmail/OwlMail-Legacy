package github.sachin2dehury.owlmail.data.convaction


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.constants.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ConvAction(
    val action: Action? = null,
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Action(
        val id: String? = null,
        @Json(name = "l")
        val folder: String? = null,
        val op: String? = null
    ) : Parcelable
}