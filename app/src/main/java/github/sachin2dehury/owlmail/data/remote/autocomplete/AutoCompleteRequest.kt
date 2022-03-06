package github.sachin2dehury.owlmail.data.remote.autocomplete

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.enums.ZimbraSoapJson
import github.sachin2dehury.owlmail.data.remote.common.Content
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AutoCompleteRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value,
    val name: Content? = null,
) : Parcelable
