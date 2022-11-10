package com.adnanbal.alzuracasestudy.di

import com.adnanbal.alzuracasestudy.api.AlzuraApiService
import com.adnanbal.alzuracasestudy.api.ApiProvider
import com.adnanbal.alzuracasestudy.util.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideApiProvider(localStorage: LocalStorage): ApiProvider =
        ApiProvider(endpoint = AlzuraApiService.BASE_URL, localStorage)

    @Singleton
    @Provides
    fun provideAdsService(apiProvider: ApiProvider): AlzuraApiService =
        apiProvider.create(AlzuraApiService::class.java)
}