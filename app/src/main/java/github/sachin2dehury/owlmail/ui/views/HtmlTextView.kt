package github.sachin2dehury.owlmail.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.owlmail.api.CoilImageGetter
import javax.inject.Inject

@AndroidEntryPoint
class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialTextView(context, attributeSet, defStyleAttr) {

    @Inject
    lateinit var imageGetter: CoilImageGetter

    fun setHtml(html: String) {
        text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT, imageGetter, null)
    }
}