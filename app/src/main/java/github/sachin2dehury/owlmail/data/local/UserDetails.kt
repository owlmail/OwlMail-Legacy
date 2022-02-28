package github.sachin2dehury.owlmail.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetails(
    val baseUrl: String? = null,
    val username: String? = null,
    val password: String? = null,
) : Parcelable
