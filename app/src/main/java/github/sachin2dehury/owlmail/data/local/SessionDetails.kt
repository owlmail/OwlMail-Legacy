package github.sachin2dehury.owlmail.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionDetails(
    val userDetails: UserDetails? = null,
    val authDetails: AuthDetails? = null,
) : Parcelable
