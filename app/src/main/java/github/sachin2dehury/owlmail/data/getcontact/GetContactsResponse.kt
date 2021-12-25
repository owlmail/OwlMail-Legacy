package github.sachin2dehury.owlmail.data.getcontact


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.searchgal.Contact
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GetContactsResponse(
    @Json(name = "cn")
    val contact: List<Contact>? = null
) : Parcelable