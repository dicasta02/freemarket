package com.example.freemarket.di.component

import android.content.Context
import com.example.freemarket.data.dataManager.RepositoryDataManager
import com.example.freemarket.data.network.RetrofitService
import com.example.freemarket.di.module.ApplicationModule
import com.example.freemarket.di.module.ConnectionModule
import com.example.freemarket.di.scope.ApplicationContext
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (ConnectionModule::class)])
interface ApplicationComponent {
    @ApplicationContext
    fun context():Context

    fun repositoryDataManager():RepositoryDataManager

    fun retrofitService(): RetrofitService
}