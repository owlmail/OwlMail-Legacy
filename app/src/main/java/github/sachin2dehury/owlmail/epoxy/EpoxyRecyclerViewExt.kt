package github.sachin2dehury.owlmail.epoxy

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView

class EpoxyRecyclerViewExt @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : EpoxyRecyclerView(context, attrs, defStyleAttr) {

    override fun shouldShareViewPoolAcrossContext() = false
}
