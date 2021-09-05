package com.example.freemarket.ui.base

interface Presenter<in V : BaseView> {
    fun attachView(view: V)

    fun detachView()
}