package github.sachin2dehury.owlmail.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthDetails(
    val authToken: String? = null,
    val authTokenExpireTime: Long? = null,
) : Parcelable
