package com.example.freemarket.helpers

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.freemarket.R
import com.example.freemarket.utilities.Constants

class DialogsManager : DialogFragment() {
    var currentDialog = -1
    private var dialog: AlertDialog.Builder? = null
    private var args: Bundle? = null
    private var acceptTitle: String? = null
    private var acceptListener: DialogInterface.OnClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        args =arguments
        isCancelable= false
        setStyle(STYLE_NORMAL, R.style.AppDialogStyle)
        currentDialog = args!!.getInt(Constants.TYPE)
        when (args!!.getInt(Constants.TYPE)) {
            PROGRESS -> return buildProgress()
            INFORMATION -> buildInformation()
            INFORMATION_1_BUTTON -> buildInformationOneButton()
            else -> buildInformation()
        }
        return dialog!!.create()
    }

    fun setAcceptListener(acceptTitle: String, acceptListener: DialogInterface.OnClickListener?) {
        this.acceptTitle = acceptTitle
        this.acceptListener = acceptListener
    }

    private fun buildInformation() {
        dialog = AlertDialog.Builder(requireActivity(), R.style.AppDialogStyle)
        dialog!!.setTitle(args!!.getString(Constants.TITLE))
        dialog!!.setMessage(args!!.getString(Constants.MSG))
        dialog!!.setPositiveButton(getString(R.string.allAccept)){dialog: DialogInterface?, which: Int->dismiss()}
    }

    private fun buildProgress(): ProgressDialog {
        val progressDialog = ProgressDialog(activity, R.style.AlertDialog_Theme)
        progressDialog.setMessage(args!!.getString(Constants.MSG))
        progressDialog.isIndeterminate= true
        return progressDialog
    }

    private fun buildInformationOneButton() {
        dialog = AlertDialog.Builder(requireActivity(), R.style.AppDialogStyle)
        dialog!!.setTitle(args!!.getString(Constants.TITLE))
        dialog!!.setMessage(args!!.getString(Constants.MSG))
        if (acceptListener != null) {
            dialog!!.setPositiveButton(acceptTitle, acceptListener)
        } else {
            dialog!!.setPositiveButton(getString(R.string.allAccept)){dialog: DialogInterface?, which: Int->dismiss()}
        }
    }

    companion object {
        const val PROGRESS = 0
        const val INFORMATION = 1
        const val INFORMATION_1_BUTTON = 2
        @kotlin.jvm.JvmStatic
        fun newInstance(type: Int, title: String?, message: String?): DialogsManager {
            val itmDialogs = DialogsManager()
            val args = Bundle()
            args.putInt(Constants.TYPE, type)
            args.putString(Constants.TITLE, title)
            args.putString(Constants.MSG, message)
            itmDialogs.arguments= args
            return itmDialogs
        }
    }
}