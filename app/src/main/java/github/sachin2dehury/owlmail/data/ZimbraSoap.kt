package github.sachin2dehury.owlmail.data


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ZimbraSoap(
    @Json(name = "Body")
    val body: Body? = null
) : Parcelable