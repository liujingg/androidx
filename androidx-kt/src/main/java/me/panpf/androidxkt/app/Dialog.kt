package me.panpf.androidxkt.app

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.support.v4.app.Fragment
import me.panpf.javaxkt.lang.setFieldValue

/**
 * Whether to automatically close the dialog box when setting the click button
 */
fun Dialog.setClickButtonClosable(close: Boolean) {
    try {
        this.setFieldValue("mShowing", close)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    }
}

/**
 * Display a progress dialog
 */
fun Activity.showProgressDialog(message: String): ProgressDialog {
    val newDialog = ProgressDialog(this)
    newDialog.setTitle(null)
    newDialog.setMessage(message)
    newDialog.isIndeterminate = true
    newDialog.setCancelable(false)
    newDialog.setOnCancelListener(null)
    newDialog.setCanceledOnTouchOutside(false)
    newDialog.show()
    return newDialog
}

/**
 * Display a progress dialog
 */
fun Activity.showProgressDialog(messageId: Int): ProgressDialog {
    return this.showProgressDialog(this.getString(messageId))
}

/**
 * Display a progress dialog
 */
fun Fragment.showProgressDialog(message: String): ProgressDialog? {
    return this.activity?.showProgressDialog(message)
}

/**
 * Display a progress dialog
 */
fun Fragment.showProgressDialog(messageId: Int): ProgressDialog? {
    return this.activity?.showProgressDialog(messageId)
}