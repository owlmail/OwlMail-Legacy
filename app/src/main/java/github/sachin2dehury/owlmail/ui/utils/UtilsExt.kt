package github.sachin2dehury.owlmail.ui.utils

import androidx.appcompat.app.AppCompatDelegate

fun enableDarkTheme(shouldEnable: Boolean) {
    when (shouldEnable) {
        false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}