package github.sachin2dehury.owlmail.utils

import androidx.appcompat.app.AppCompatDelegate

fun switchToTheme(shouldEnable: Boolean?) = when (shouldEnable) {
    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
}
