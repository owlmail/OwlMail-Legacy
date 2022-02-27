package github.sachin2dehury.owlmail.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Singleton
//    @Provides
//    fun provideMailDatabase(
//        @ApplicationContext context: Context,
//    ) = Room.databaseBuilder(
//        context,
//        OwlMailDatabase::class.java,
//        context.getString(R.string.database_name)
//    ).fallbackToDestructiveMigration().build()
//
//    @Singleton
//    @Provides
//    fun provideContactDao(database: OwlMailDatabase) = database.getContactDao()
//
//    @Singleton
//    @Provides
//    fun provideConversationDao(database: OwlMailDatabase) = database.getConversationDao()
//
//    @Singleton
//    @Provides
//    fun provideMessageDao(database: OwlMailDatabase) = database.getMessageDao()
}
