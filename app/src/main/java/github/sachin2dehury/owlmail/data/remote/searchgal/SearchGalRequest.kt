package github.sachin2dehury.owlmail.data.remote.searchgal

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.enums.ZimbraSoapJson
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchGalRequest(
    @Json(name = "_jsns")
    val jsns: String? = ZimbraSoapJson.ACCOUNT.value,
    val limit: Int? = null,
    val name: String? = null,
    val offset: Int? = null,
) : Parcelable
