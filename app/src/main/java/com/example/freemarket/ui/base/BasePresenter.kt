package com.example.freemarket.ui.base

import java.lang.RuntimeException

open class BasePresenter<T : BaseView> : Presenter<T> {
    private var _view: T? = null

    val view: T
        get() {
            return _view ?: throw ViewAttachedException()
        }

    override fun attachView(view: T) {
        _view = view
    }

    override fun detachView() {
        _view = null
    }

    class ViewAttachedException :
        RuntimeException("Please call Presenter.attachView(View) Before requesting data to the presenter")
}