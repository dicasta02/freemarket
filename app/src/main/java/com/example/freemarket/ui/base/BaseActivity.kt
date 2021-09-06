package com.example.freemarket.ui.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.freemarket.CustomApp
import com.example.freemarket.R
import com.example.freemarket.di.component.ActivityComponent
import com.example.freemarket.di.component.ConfigPersistentComponent
import com.example.freemarket.di.module.ActivityModule
import com.example.freemarket.helpers.DialogsManager
import com.example.freemarket.utilities.Constants
import com.example.freemarket.utilities.LogManager
import java.util.concurrent.atomic.AtomicLong

abstract class BaseActivity : AppCompatActivity(), BaseView {
    companion object {
        @JvmStatic
        private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"

        @JvmStatic
        private val NEXT_ID = AtomicLong(0)

        @JvmStatic
        private val componentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

    lateinit var activityComponent: ActivityComponent
    private var progressDialog: ProgressDialog? = null
    private var activityId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()

        if (componentsMap[activityId] != null)
            LogManager(c = BaseActivity::class.java).printInfo(
                "Reusing ConfigPersistentComponent id=%d",
                activityId
            )

        val configPersistentComponent = componentsMap.getOrPut(activityId, {
            LogManager(c = BaseActivity::class.java).printInfo(
                "Creating new ConfigPersistentComponent id=%d",
                activityId
            )

            val component = (applicationContext as CustomApp).applicationComponent

            DaggerConfigPersistentComponent.builder()
                .applicationComponent(component)
                .build()
        })

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        progressDialog = ProgressDialog(this, R.style.AlertDialog_Theme)
        progressDialog!!.setCancelable(false)
    }

    override fun showDialog(title: String?, msg: String?) {
        showDialog(DialogsManager.newInstance(DialogsManager.INFORMATION, title, msg))
    }

    override fun showDialog(
        title: String?,
        msg: String?,
        positiveTitle: String?,
        positiveButton: DialogInterface.OnClickListener?
    ) {
        val dialogsManager = DialogsManager.newInstance(
            DialogsManager.INFORMATION_1_BUTTON,
            title, msg
        )
        if (positiveTitle != null) {
            dialogsManager.setAcceptListener(positiveTitle, positiveButton)
        }
        showDialog(dialogsManager)
    }


    override fun showDialogNoInternet() {
        showDialog(
            DialogsManager.newInstance(
                DialogsManager.INFORMATION, getString(R.string.app_name),
                getString(R.string.allNoInternet)
            )
        )
    }

    override fun showCustomProgress(message: String?) {
        progressDialog!!.setMessage(message)
        progressDialog!!.show()
    }


    override fun hideCustomProgress() {
        try {
            if (!this.isFinishing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            LogManager(BaseActivity::class.java).printError(e)
        }
    }

    override fun hideKeyBoard(view: View?) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun showDialog(dialogsManager: DialogsManager) {
        dismissDialog()
        val fm = supportFragmentManager
        fm.beginTransaction().add(dialogsManager, Constants.DIALOG).commitAllowingStateLoss()
    }

    private fun dismissDialog() {
        try {
            if (fragmentManager != null) {
                fragmentManager.executePendingTransactions()
                val prev = supportFragmentManager.findFragmentByTag(Constants.DIALOG)
                if (prev != null) {
                    val df = prev as DialogsManager
                    df.dismissAllowingStateLoss()
                }
            }
        } catch (e: Exception) {
            Log.e(Constants.TAG, "Error in dismiss dialog -> " + e.message)
        }
    }
}