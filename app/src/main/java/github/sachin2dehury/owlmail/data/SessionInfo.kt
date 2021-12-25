package github.sachin2dehury.owlmail.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionInfo(
    val userDetails: UserDetails? = null,
    val authDetails: AuthDetails? = null,
) : Parcelable {

    @Parcelize
    data class UserDetails(
        val username: String? = null,
        val password: String? = null,
    ) : Parcelable

    @Parcelize
    data class AuthDetails(
        val authToken: String? = null,
        val authTokenExpireTime: Long? = null,
        val baseUrl: String? = null,
    ) : Parcelable
}
