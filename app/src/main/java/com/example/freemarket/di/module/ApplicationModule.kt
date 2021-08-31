package com.example.freemarket.di.module

import android.app.Application
import android.content.Context
import com.example.freemarket.di.scope.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @Singleton
    internal fun provideApplication():Application{
        return application
    }

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext():Context{
        return application
    }
}