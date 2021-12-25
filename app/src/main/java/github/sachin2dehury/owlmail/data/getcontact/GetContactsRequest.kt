package github.sachin2dehury.owlmail.data.getcontact


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import github.sachin2dehury.owlmail.data.common.ZimbraSoapJson

@JsonClass(generateAdapter = true)
@Parcelize
data class GetContactsRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.Mail.value
) : Parcelable