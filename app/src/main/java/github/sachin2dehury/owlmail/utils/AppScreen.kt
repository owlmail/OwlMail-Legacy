package github.sachin2dehury.owlmail.utils

sealed class AppScreen(val name: String) {
    object HOME : AppScreen("home")
    object SETTINGS : AppScreen("settings")
}
