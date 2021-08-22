package github.sachin2dehury.owlmail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import github.sachin2dehury.owlmail.epoxy.controller.MailBoxController
import kotlinx.coroutines.ObsoleteCoroutinesApi

@Module
@InstallIn(FragmentComponent::class)
@ObsoleteCoroutinesApi
object ControllerModule {

    @FragmentScoped
    @Provides
    fun provideMailBoxController() = MailBoxController()
}