package github.sachin2dehury.owlmail.epoxy

import com.google.android.gms.ads.AdSize

sealed class UiModel<out T> {
    data class Item<out T>(val value: T) : UiModel<T>()
    data class Header(val value: String) : UiModel<Nothing>()
    data class Footer(val value: Int) : UiModel<Nothing>()
    data class Ad(val adSize: AdSize) : UiModel<Nothing>()
    data class Loader(val value: Boolean = false) : UiModel<Nothing>()
    data class Empty(val value: String) : UiModel<Nothing>()
    data class Error(val value: String) : UiModel<Nothing>()
}