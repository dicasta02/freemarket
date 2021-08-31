package com.example.freemarket.di.component

import com.example.freemarket.di.module.ActivityModule
import com.example.freemarket.di.scope.PerActivity
import com.example.freemarket.ui.splash.SplashActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
}