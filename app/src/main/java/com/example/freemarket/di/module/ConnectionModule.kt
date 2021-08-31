package com.example.freemarket.di.module

import com.example.freemarket.data.network.RetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConnectionModule {
    @Provides
    @Singleton
    fun provideRetrofitService(): RetrofitService {
        return RetrofitService.Creator.getRetrofitServiceAdapter()
    }
}