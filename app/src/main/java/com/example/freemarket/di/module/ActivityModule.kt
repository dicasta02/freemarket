package com.example.freemarket.di.module

import android.app.Activity
import android.content.Context
import com.example.freemarket.di.scope.ActivityContext
import com.example.freemarket.di.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {
    @Provides
    @PerActivity
    internal fun provideActivity():Activity{
        return activity
    }

    @Provides
    @PerActivity
    @ActivityContext
    internal fun providesContext():Context{
        return activity
    }
}