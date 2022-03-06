package github.sachin2dehury.owlmail.di

import android.content.Context
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.owlmail.api.AuthInterceptor
import github.sachin2dehury.owlmail.api.ZimbraApiExt
import github.sachin2dehury.owlmail.utils.NetworkStateFlowBuilder
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor() = AuthInterceptor()

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ) = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(chuckerInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMailApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ): ZimbraApiExt = ZimbraApiExt(moshi, okHttpClient)

    @ExperimentalCoilApi
    @Singleton
    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
    ) = ImageLoader.Builder(context)
        .crossfade(true)
        .okHttpClient(okHttpClient)
        .diskCache(DiskCache.Builder().build())
        .memoryCache(MemoryCache.Builder(context).build()).build()

    @Singleton
    @Provides
    fun providesNetworkStateFlowBuilder(@ApplicationContext context: Context) =
        NetworkStateFlowBuilder(context)
}
