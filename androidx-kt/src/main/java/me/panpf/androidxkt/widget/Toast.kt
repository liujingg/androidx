package me.panpf.androidxkt.widget

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import android.widget.Toast
import me.panpf.androidxkt.runInUI
import android.support.v4.app.Fragment as SupportFragment

/*
 * Toast related extension method
 */

/* ************************************* Context ***************************************** */

fun Context.showLongToast(message: String) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_LONG).show() }
}

fun Context.showLongToast(message: String, vararg params: Any) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_LONG).show() }
}

fun Context.showLongToast(@StringRes messageResId: Int) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show() }
}

fun Context.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_LONG).show() }
}

fun Context.showShortToast(message: String) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show() }
}

fun Context.showShortToast(message: String, vararg params: Any) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_SHORT).show() }
}

fun Context.showShortToast(@StringRes messageResId: Int) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show() }
}

fun Context.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.applicationContext
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_SHORT).show() }
}

/* ************************************* SupportFragment ***************************************** */

fun SupportFragment.showLongToast(message: String) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_LONG).show() }
}

fun SupportFragment.showLongToast(message: String, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_LONG).show() }
}

fun SupportFragment.showLongToast(@StringRes messageResId: Int) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show() }
}

fun SupportFragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_LONG).show() }
}

fun SupportFragment.showShortToast(message: String) {
    val appContext = this.activity ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show() }
}

fun SupportFragment.showShortToast(message: String, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_SHORT).show() }
}

fun SupportFragment.showShortToast(@StringRes messageResId: Int) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show() }
}

fun SupportFragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_SHORT).show() }
}

/* ************************************* View ***************************************** */

fun View.showLongToast(message: String) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_LONG).show() }
}

fun View.showLongToast(message: String, vararg params: Any) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_LONG).show() }
}

fun View.showLongToast(@StringRes messageResId: Int) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show() }
}

fun View.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_LONG).show() }
}

fun View.showShortToast(message: String) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show() }
}

fun View.showShortToast(message: String, vararg params: Any) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_SHORT).show() }
}

fun View.showShortToast(@StringRes messageResId: Int) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show() }
}

fun View.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.context?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_SHORT).show() }
}

/* ************************************* Show View ***************************************** */

fun View.showLongToastWithSelf() {
    val appContext = this.context.applicationContext ?: return
    runInUI {
        Toast(appContext).apply {
            view = this@showLongToastWithSelf
            duration = Toast.LENGTH_LONG
        }.show()
    }
}

fun View.showShortToastWithSelf() {
    val appContext = this.context.applicationContext ?: return
    runInUI {
        Toast(appContext).apply {
            view = this@showShortToastWithSelf
            duration = Toast.LENGTH_SHORT
        }.show()
    }
}