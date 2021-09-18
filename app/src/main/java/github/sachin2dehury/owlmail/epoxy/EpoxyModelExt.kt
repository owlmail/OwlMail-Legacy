package github.sachin2dehury.owlmail.epoxy

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyModel

open class EpoxyModelExt<T : ViewBinding>(
    @LayoutRes private val layoutRes: Int,
    private val epoxyModelOnClickListener: EpoxyModelOnClickListener?
) : EpoxyModel<View>() {

    private var _binding: T? = null

    override fun bind(view: View) {
        _binding = bindExt(view)
    }

    override fun unbind(view: View) {
        unbindExt(_binding!!)
        _binding?.root?.setOnClickListener(null)
        _binding = null
    }

    override fun shouldSaveViewState() = true

    open fun bindExt(view: View): T? = null

    open fun unbindExt(binding: T) = Unit

    protected fun <E> setOnModelClickListener(uiModel: UiModel<E>) =
        _binding?.root?.setOnClickListener { epoxyModelOnClickListener?.onModelClick(uiModel) }

    override fun getDefaultLayout() = layoutRes
}