package com.heliushouse.numbertrivia.di

import android.content.Context
import com.heliushouse.numbertrivia.BuildConfig
import com.heliushouse.numbertrivia.api.NumberService
import com.heliushouse.numbertrivia.repository.NumberRepository
import com.heliushouse.numbertrivia.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NumberService = Retrofit.Builder()
        .baseUrl(NumberService.NUMBER_API_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(NumberService::class.java)

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideNumberRepository(
        api: NumberService
    ) = NumberRepository(api) as Repository
}