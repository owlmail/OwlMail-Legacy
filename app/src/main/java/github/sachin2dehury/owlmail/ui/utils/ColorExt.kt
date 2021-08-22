package github.sachin2dehury.owlmail.ui.utils

import github.sachin2dehury.owlmail.R

private val colorMap: Map<Char, Int> = mapOf(
    'a' to R.color.letter_color_1,
    'b' to R.color.letter_color_2,
    'c' to R.color.letter_color_3,
    'd' to R.color.letter_color_4,
    'e' to R.color.letter_color_5,
    'f' to R.color.letter_color_6,
    'g' to R.color.letter_color_7,
    'h' to R.color.letter_color_8,
    'i' to R.color.letter_color_1,
    'j' to R.color.letter_color_2,
    'k' to R.color.letter_color_3,
    'l' to R.color.letter_color_4,
    'm' to R.color.letter_color_5,
    'n' to R.color.letter_color_6,
    'o' to R.color.letter_color_7,
    'p' to R.color.letter_color_8,
    'q' to R.color.letter_color_1,
    'r' to R.color.letter_color_2,
    's' to R.color.letter_color_3,
    't' to R.color.letter_color_4,
    'u' to R.color.letter_color_5,
    'v' to R.color.letter_color_6,
    'w' to R.color.letter_color_7,
    'x' to R.color.letter_color_8,
    'y' to R.color.letter_color_1,
    'z' to R.color.letter_color_2
)

@androidx.annotation.ColorRes
fun getColorForText(value: String): Int {
    return colorMap[getCharacterKey(value)] ?: android.R.color.transparent
}

private fun getCharacterKey(value: String) = value.trimStart()[0].lowercaseChar()
