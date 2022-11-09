package com.adnanbal.alzuracasestudy.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiProvider(
    endpoint: String,
    private val username: String,
    private val password: String
) {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(endpoint)
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
            .build()
    }

    private fun createAuthInterceptor() = Interceptor { chain ->
        val authenticatedRequest = chain.request().newBuilder()
            .header("Authorization", Credentials.basic(username, password))
        chain.proceed(authenticatedRequest.build())
    }

    private fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(createAuthInterceptor())
        .build()

    private fun createMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun <S> create(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}