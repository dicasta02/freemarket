package com.example.freemarket.ui.base

import android.content.DialogInterface
import android.view.View
import com.example.freemarket.di.component.ActivityComponent


interface BaseView {

    fun showDialog(title: String?, msg: String?)

    fun showDialog(
        title: String?,
        msg: String?,
        positiveTitle: String?,
        positiveButton: DialogInterface.OnClickListener?
    )

    fun showDialogNoInternet()

    fun showCustomProgress(message: String?)

    fun hideCustomProgress()

    fun hideKeyBoard(view: View?)

}