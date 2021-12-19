package github.sachin2dehury.owlmail.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.owlmail.R
import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.MailApiExt
import github.sachin2dehury.owlmail.database.MailDao
import github.sachin2dehury.owlmail.database.ParsedMailDao
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.repository.MailRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) =
        preferencesDataStore(context.getString(R.string.data_store_name))
            .getValue(context, Preferences::javaClass)

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context,
        dataStore: DataStore<Preferences>
    ) = DataStoreRepository(context, dataStore)

    @Singleton
    @Provides
    fun provideAuthRepository(
        basicAuthInterceptor: BasicAuthInterceptor,
        mailApiExt: MailApiExt,
        mailDao: MailDao,
    ) = AuthRepository(basicAuthInterceptor, mailApiExt, mailDao)

    @Singleton
    @Provides
    fun providePagerConfig() = PagingConfig(20, 5, false)

    @Singleton
    @Provides
    fun provideMailRepository(
        @ApplicationContext context: Context,
        mailApiExt: MailApiExt,
        mailDao: MailDao,
        parsedMailDao: ParsedMailDao,
        pagingConfig: PagingConfig
    ) = MailRepository(context, mailDao, parsedMailDao, pagingConfig)
}