package github.sachin2dehury.owlmail.ui.views

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView
import github.sachin2dehury.owlmail.api.CoilImageGetter

class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialTextView(context, attributeSet, defStyleAttr) {

    var imageGetter: Html.ImageGetter = CoilImageGetter(this)

    fun setHtml(html: String) {
        text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT, imageGetter, null)
    }
}
