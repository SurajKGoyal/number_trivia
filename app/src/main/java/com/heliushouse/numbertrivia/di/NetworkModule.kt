package com.heliushouse.numbertrivia.di

import com.heliushouse.numbertrivia.api.NumberService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): NumberService = Retrofit.Builder()
        .baseUrl(NumberService.NUMBER_API_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(NumberService::class.java)
}