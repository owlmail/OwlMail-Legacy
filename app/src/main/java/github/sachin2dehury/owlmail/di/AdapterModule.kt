package github.sachin2dehury.owlmail.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import github.sachin2dehury.owlmail.R

@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {

    @FragmentScoped
    @Provides
    fun provideColorList(@ApplicationContext context: Context) =
        context.resources.getIntArray(R.array.colors)
}