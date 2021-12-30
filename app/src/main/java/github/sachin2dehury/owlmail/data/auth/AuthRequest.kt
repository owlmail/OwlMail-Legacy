package github.sachin2dehury.owlmail.data.auth


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.Content
import github.sachin2dehury.owlmail.data.constants.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthRequest(
    val account: Content? = null,
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.ACCOUNT.value,
    val password: Content? = null
) : Parcelable