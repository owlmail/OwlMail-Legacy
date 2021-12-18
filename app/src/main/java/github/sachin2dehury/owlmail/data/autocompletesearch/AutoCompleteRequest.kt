package github.sachin2dehury.owlmail.data.autocompletesearch


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.api.ZimbraSoapJson
import github.sachin2dehury.owlmail.data.Content
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AutoCompleteRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.Mail.value,
    val name: Content? = null
) : Parcelable