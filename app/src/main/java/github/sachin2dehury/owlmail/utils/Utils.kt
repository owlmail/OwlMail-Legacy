package github.sachin2dehury.owlmail.utils

import androidx.appcompat.app.AppCompatDelegate

fun enableDarkTheme(shouldEnable: Boolean) {
    when (shouldEnable) {
        false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}

fun String.getFirstCharacter() = trimStart()[0].lowercaseChar().toString()