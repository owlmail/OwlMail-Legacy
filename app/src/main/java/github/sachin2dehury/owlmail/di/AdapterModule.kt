package github.sachin2dehury.owlmail.di

import android.content.Context
import android.webkit.WebChromeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import github.sachin2dehury.owlmail.R

@Module
@InstallIn(ActivityRetainedComponent::class)
object AdapterModule {

    @ActivityRetainedScoped
    @Provides
    fun provideWebChromeClient() = WebChromeClient()

    @ActivityRetainedScoped
    @Provides
    fun provideColorList(@ApplicationContext context: Context) =
        context.resources.getIntArray(R.array.colors)
}