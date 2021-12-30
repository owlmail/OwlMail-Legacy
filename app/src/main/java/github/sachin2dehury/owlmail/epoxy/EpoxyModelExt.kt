package github.sachin2dehury.owlmail.epoxy

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyModel

open class EpoxyModelExt<T>(
    private val item: T?,
    @LayoutRes private val layoutRes: Int,
    private val epoxyModelOnClickListener: EpoxyModelOnClickListener<T>?
) : EpoxyModel<View>() {

    private var _binding: ViewBinding? = null

    override fun bind(view: View) {
        super.bind(view)
        show(item != null)
        _binding = bindItem(view, item)
        _binding?.root?.setOnClickListener {
            epoxyModelOnClickListener?.onItemClick(item)
        }
    }

    override fun unbind(view: View) {
        super.unbind(view)
        unbindItem(_binding, item)
        _binding?.root?.setOnClickListener(null)
        _binding = null
    }

    override fun shouldSaveViewState() = true

    protected fun bindItem(view: View, item: T?): ViewBinding? = null

    protected fun unbindItem(binding: ViewBinding?, item: T?) = Unit

    override fun getDefaultLayout() = layoutRes
}