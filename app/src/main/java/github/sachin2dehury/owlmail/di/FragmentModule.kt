package github.sachin2dehury.owlmail.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import github.sachin2dehury.owlmail.data.remote.search.Conversation
import github.sachin2dehury.owlmail.epoxy.EpoxyModelOnClickListener
import github.sachin2dehury.owlmail.epoxy.controller.ZimbraPagingEpoxyController

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @FragmentScoped
    @Provides
    fun providesEpoxyAdapter(fragment: Fragment) =
        ZimbraPagingEpoxyController(fragment as EpoxyModelOnClickListener<Conversation>)
}
