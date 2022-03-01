package github.sachin2dehury.owlmail.data.local

sealed class AppScreen(val name: String) {
    object HOME : AppScreen("home")
    object SETTINGS : AppScreen("settings")
}
