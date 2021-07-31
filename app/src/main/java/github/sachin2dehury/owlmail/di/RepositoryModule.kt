package github.sachin2dehury.owlmail.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApi
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.repository.MailRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @ActivityRetainedScoped
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) =
        preferencesDataStore(context.getString(R.string.data_store_name)).getValue(
            context,
            Preferences::javaClass
        )

    @ActivityRetainedScoped
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context,
        dataStore: DataStore<Preferences>
    ) = DataStoreRepository(context, dataStore)

    @ActivityRetainedScoped
    @Provides
    fun providePagerConfig() = PagingConfig(20, 5, false)

    @ActivityRetainedScoped
    @Provides
    fun provideMailRepository(
        @ApplicationContext context: Context,
        basicAuthInterceptor: BasicAuthInterceptor,
        mailApi: MailApi,
        mailDao: MailDao,
//        parsedMailDao: ParsedMailDao,
        pagingConfig: PagingConfig
    ) = MailRepository(basicAuthInterceptor, context, mailApi, mailDao, pagingConfig)
}