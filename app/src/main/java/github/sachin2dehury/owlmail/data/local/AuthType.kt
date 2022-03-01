package github.sachin2dehury.owlmail.data.local

sealed class AuthType {
    object VALID_TOKEN : AuthType()
    object REFRESH_TOKEN : AuthType()
    object INVALID_TOKEN : AuthType()
    object INVALID_BASEURL : AuthType()
}
