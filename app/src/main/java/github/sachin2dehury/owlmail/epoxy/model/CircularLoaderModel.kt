package github.sachin2dehury.owlmail.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import github.sachin2dehury.owlmail.R

@EpoxyModelClass
abstract class CircularLoaderModel : EpoxyModelWithHolder<CircularLoaderModel.Holder>() {

    override fun getDefaultLayout() = R.layout.item_circular_loader_view

    inner class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {}
    }
}
