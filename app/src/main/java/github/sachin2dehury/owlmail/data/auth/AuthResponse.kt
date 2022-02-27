package github.sachin2dehury.owlmail.data.auth

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import github.sachin2dehury.owlmail.data.common.Content
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthResponse(
    val authToken: List<Content>? = null,
    val lifetime: Long? = null
) : Parcelable
