@file:Suppress("unused")

package me.panpf.androidxkt.widget

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import android.widget.Toast
import android.app.Fragment as OriginFragment
import android.support.v4.app.Fragment as SupportFragment

/*
 * Toast 相关的扩展方法或属性
 */

/* ************************************* Context ***************************************** */

fun Context?.toastL(message: String) {
    val context = this ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Context?.toastL(message: String, vararg params: Any) {
    val context = this ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun Context?.toastL(@StringRes messageResId: Int) {
    val context = this ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun Context?.toastL(@StringRes messageResId: Int, vararg params: Any) {
    val context = this ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun Context?.toastS(message: String) {
    val context = this ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Context?.toastS(message: String, vararg params: Any) {
    val context = this ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun Context?.toastS(@StringRes messageResId: Int) {
    val context = this ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun Context?.toastS(@StringRes messageResId: Int, vararg params: Any) {
    val context = this ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* SupportFragment ***************************************** */

fun SupportFragment?.toastL(message: String) {
    val context = this?.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun SupportFragment?.toastL(message: String, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun SupportFragment?.toastL(@StringRes messageResId: Int) {
    val context = this?.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun SupportFragment?.toastL(@StringRes messageResId: Int, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun SupportFragment?.toastS(message: String) {
    val context = this?.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun SupportFragment?.toastS(message: String, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun SupportFragment?.toastS(@StringRes messageResId: Int) {
    val context = this?.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun SupportFragment?.toastS(@StringRes messageResId: Int, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* OriginFragment ***************************************** */

fun OriginFragment?.toastL(message: String) {
    val context = this?.activity ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun OriginFragment?.toastL(message: String, vararg params: Any) {
    val context = this?.activity ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun OriginFragment?.toastL(@StringRes messageResId: Int) {
    val context = this?.activity ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun OriginFragment?.toastL(@StringRes messageResId: Int, vararg params: Any) {
    val context = this?.activity ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun OriginFragment?.toastS(message: String) {
    val context = this?.activity ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun OriginFragment?.toastS(message: String, vararg params: Any) {
    val context = this?.activity ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun OriginFragment?.toastS(@StringRes messageResId: Int) {
    val context = this?.activity ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun OriginFragment?.toastS(@StringRes messageResId: Int, vararg params: Any) {
    val context = this?.activity ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}

/* ************************************* View ***************************************** */

fun View?.toastL(message: String) {
    val context = this?.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun View?.toastL(message: String, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_LONG).show()
}

fun View?.toastL(@StringRes messageResId: Int) {
    val context = this?.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show()
}

fun View?.toastL(@StringRes messageResId: Int, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show()
}

fun View?.toastS(message: String) {
    val context = this?.context ?: return
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View?.toastS(message: String, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, message.format(params), Toast.LENGTH_SHORT).show()
}

fun View?.toastS(@StringRes messageResId: Int) {
    val context = this?.context ?: return
    Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show()
}

fun View?.toastS(@StringRes messageResId: Int, vararg params: Any) {
    val context = this?.context ?: return
    Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show()
}