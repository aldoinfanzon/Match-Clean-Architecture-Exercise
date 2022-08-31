package com.neuronride.match.cleanarch.application.framework.di.module

import com.neuronride.match.cleanarch.core.framework.api.currency.ExchangeRatesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TIMEOUT_MILLIS = 15000L
private const val READ_TIMEOUT_MILLIS = 10000L
private const val WRITE_TIMEOUT_MILLIS = 10000L
private const val CALL_TIMEOUT_MILLIS = 10000L

private const val COINGECKO_BASE_HOST = "https://api.coingecko.com/api/v3/"

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .callTimeout(CALL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .build()

    @Singleton
    @Provides
    fun provideCoingeckoRetrofitApi(client: OkHttpClient): ExchangeRatesApi =
        Retrofit.Builder()
            .baseUrl(COINGECKO_BASE_HOST)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRatesApi::class.java)
}