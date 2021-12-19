package github.sachin2dehury.owlmail.data.auth


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.ZimbraSoapJson
import github.sachin2dehury.owlmail.data.Content
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthRequest(
    val account: Content? = null,
    @Json(name = "_jsns")
    val jsns: String = ZimbraSoapJson.Account.value,
    val password: Content? = null
) : Parcelable