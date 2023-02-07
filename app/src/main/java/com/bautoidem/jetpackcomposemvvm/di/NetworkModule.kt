package com.bautoidem.jetpackcomposemvvm.di

import com.bautoidem.jetpackcomposemvvm.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideApiService() : ApiService{
        val okHttpClientBuilder = createClientBuilder()
        val okHttpClient = okHttpClientBuilder.build()
        val retrofit = Retrofit.Builder().baseUrl(provideUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }


    private fun createClientBuilder(
    ): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        })
        okHttpClientBuilder.addInterceptor { chain ->
            val newRequestBuilder = chain.request().newBuilder()
            chain.proceed(newRequestBuilder.build())
        }
        okHttpClientBuilder.connectTimeout(30L, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30L, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }


    private fun provideUrl():String{
        return "https://api.github.com/"
    }
}