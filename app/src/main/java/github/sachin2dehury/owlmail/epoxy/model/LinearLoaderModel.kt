package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import github.sachin2dehury.owlmail.R

@EpoxyModelClass
abstract class LinearLoaderModel : EpoxyModelWithHolder<LinearLoaderModel.Holder>() {

    override fun getDefaultLayout() = R.layout.item_linear_loader_view

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {}
    }
}
