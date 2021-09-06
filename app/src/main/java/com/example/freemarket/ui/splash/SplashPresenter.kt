package com.example.freemarket.ui.splash

import android.content.Context
import android.os.SystemClock
import com.example.freemarket.ui.base.BasePresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject constructor() : BasePresenter<SplashView>() {
    @Inject
    internal lateinit var context: Context

    private val SPLASH_DELAY = TimeUnit.SECONDS.toMillis(2)

    fun setDestiny() {
        SystemClock.sleep(SPLASH_DELAY)
        view.showSearchActivity()
    }
}