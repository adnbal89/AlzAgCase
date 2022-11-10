package com.adnanbal.alzuracasestudy.api

import com.adnanbal.alzuracasestudy.BuildConfig
import com.adnanbal.alzuracasestudy.util.LocalStorage
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiProvider(
    endpoint: String,
    private val localStorage: LocalStorage
) {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(endpoint)
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(createLoggingInterceptor())
        .addInterceptor(createAuthInterceptor())
        .build()

    private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private fun createAuthInterceptor() = Interceptor { chain ->
        val authenticatedRequest = chain.request().newBuilder()
            .header("X-AUTH-TOKEN", localStorage.token.orEmpty())
        chain.proceed(authenticatedRequest.build())
    }

    private fun createMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun <S> create(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}