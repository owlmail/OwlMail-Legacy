package github.sachin2dehury.owlmail.di

import android.content.Context
import coil.ComponentRegistry
import coil.ImageLoader
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.owlmail.api.BasicAuthInterceptor
import github.sachin2dehury.owlmail.api.CoilImageGetter
import github.sachin2dehury.owlmail.api.MailApiExt
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor() = BasicAuthInterceptor()

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
        @ApplicationContext context: Context,
        basicAuthInterceptor: BasicAuthInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(basicAuthInterceptor)
        .addInterceptor(chuckerInterceptor)
//        .cache(CoilUtils.createDefaultCache(context))
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideMailApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): MailApiExt = MailApiExt(moshi, okHttpClient)

    @Singleton
    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context,
//        okHttpClient: OkHttpClient
    ) = ImageLoader.Builder(context).crossfade(true).components(fun ComponentRegistry.Builder.() {
//        add(ByteArrayFetcher())
//        add(SvgDecoder(context))
//        if (SDK_INT >= 28) {
//            add(ImageDecoderDecoder(context, enforceMinimumFrameDelay = true))
//        } else {
//            add(GifDecoder(enforceMinimumFrameDelay = true))
//        }
//        add(VideoFrameFileFetcher(context))
//        add(VideoFrameUriFetcher(context))
//        add(VideoFrameDecoder(context))
    }).build()

    @Singleton
    @Provides
    fun provideCoilImageGetter(
        @ApplicationContext context: Context,
        imageLoader: ImageLoader,
    ) = CoilImageGetter(context, imageLoader)
}