package github.sachin2dehury.owlmail.data.getcontact


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.constants.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetContactsRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.MAIL.value
) : Parcelable