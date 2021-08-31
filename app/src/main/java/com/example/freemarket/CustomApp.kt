package com.example.freemarket

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.freemarket.di.component.ApplicationComponent
import com.example.freemarket.di.component.DaggerApplicationComponent
import com.example.freemarket.di.module.ApplicationModule

class CustomApp : Application() {
    lateinit var applicationComponent: ApplicationComponent
    private set

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    @VisibleForTesting
    fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}