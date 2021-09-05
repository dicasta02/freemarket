package com.example.freemarket.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.freemarket.ui.base.BaseActivity
import com.example.freemarket.ui.search.SearchActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {
    @Inject
    lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        initUI()
    }

    private fun initUI() {
        splashPresenter.attachView(this)
        splashPresenter.setDestiny()
    }

    override fun showSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
        finish()
    }

}