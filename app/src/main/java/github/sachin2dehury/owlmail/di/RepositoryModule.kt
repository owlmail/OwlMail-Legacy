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
import github.sachin2dehury.owlmail.api.AuthInterceptor
import github.sachin2dehury.owlmail.api.ZimbraApiExt
import github.sachin2dehury.owlmail.database.ContactDao
import github.sachin2dehury.owlmail.database.ConversationDao
import github.sachin2dehury.owlmail.database.MessageDao
import github.sachin2dehury.owlmail.repository.AuthRepository
import github.sachin2dehury.owlmail.repository.DataStoreRepository
import github.sachin2dehury.owlmail.repository.ZimbraRepository
import github.sachin2dehury.owlmail.utils.NetworkStateFlowBuilder
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
        dataStore: DataStore<Preferences>,
    ) = DataStoreRepository(context, dataStore)

    @Singleton
    @Provides
    fun provideAuthRepository(
        authInterceptor: AuthInterceptor,
        zimbraApiExt: ZimbraApiExt,
    ) = AuthRepository(authInterceptor, zimbraApiExt)

    @Singleton
    @Provides
    fun providePagingConfig() = PagingConfig(10, 5, false)

    @Singleton
    @Provides
    fun provideZimbraRepository(
        contactDao: ContactDao,
        conversationDao: ConversationDao,
        messageDao: MessageDao,
        networkStateFlowBuilder: NetworkStateFlowBuilder,
        pagingConfig: PagingConfig,
        zimbraApiExt: ZimbraApiExt,
    ) = ZimbraRepository(
        contactDao,
        conversationDao,
        messageDao,
        networkStateFlowBuilder.networkStateFlow,
        pagingConfig,
        zimbraApiExt.provideMailApi(),
    )
}
