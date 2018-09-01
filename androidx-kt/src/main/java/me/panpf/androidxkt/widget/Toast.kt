package me.panpf.androidxkt.widget

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import android.widget.Toast
import android.app.Fragment as OriginFragment
import android.support.v4.app.Fragment as SupportFragment

/*
 * Toast related extension method
 */

/* ************************************* Context ***************************************** */

fun Context.showLongToast(message: String) {
    val context = this
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(message: String, vararg params: Any) {
    val context = this
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(@StringRes messageResId: Int) {
    val context = this
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(message: String) {
    val context = this
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Context.showShortToast(message: String, vararg params: Any) {
    val context = this
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun Context.showShortToast(@StringRes messageResId: Int) {
    val context = this
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun Context.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* SupportFragment ***************************************** */

fun SupportFragment.showLongToast(message: String) {
    val context = this.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun SupportFragment.showLongToast(message: String, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun SupportFragment.showLongToast(@StringRes messageResId: Int) {
    val context = this.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun SupportFragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun SupportFragment.showShortToast(message: String) {
    val context = this.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun SupportFragment.showShortToast(message: String, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun SupportFragment.showShortToast(@StringRes messageResId: Int) {
    val context = this.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun SupportFragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* OriginFragment ***************************************** */

fun OriginFragment.showLongToast(message: String) {
    val context = this.activity ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun OriginFragment.showLongToast(message: String, vararg params: Any) {
    val context = this.activity ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun OriginFragment.showLongToast(@StringRes messageResId: Int) {
    val context = this.activity ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun OriginFragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this.activity ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun OriginFragment.showShortToast(message: String) {
    val context = this.activity ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun OriginFragment.showShortToast(message: String, vararg params: Any) {
    val context = this.activity ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun OriginFragment.showShortToast(@StringRes messageResId: Int) {
    val context = this.activity ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun OriginFragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this.activity ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* View ***************************************** */

fun View.showLongToast(message: String) {
    val context = this.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun View.showLongToast(message: String, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun View.showLongToast(@StringRes messageResId: Int) {
    val context = this.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun View.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun View.showShortToast(message: String) {
    val context = this.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showShortToast(message: String, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun View.showShortToast(@StringRes messageResId: Int) {
    val context = this.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun View.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val context = this.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* Show View ***************************************** */

fun View.showLongToast() {
    val context = this.context
    if (context != null) {
        val toast = Toast(context)
        toast.view = this
        toast.duration = Toast.LENGTH_LONG
        toast.show()
    }
}

fun View.showShortToast() {
    val context = this.context
    if (context != null) {
        val toast = Toast(context)
        toast.view = this
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }
}