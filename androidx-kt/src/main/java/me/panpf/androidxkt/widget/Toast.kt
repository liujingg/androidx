/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.panpf.androidxkt.widget

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import android.widget.Toast
import me.panpf.androidxkt.runInUI

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

fun android.support.v4.app.Fragment.showLongToast(message: String) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_LONG).show() }
}

fun android.support.v4.app.Fragment.showLongToast(message: String, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_LONG).show() }
}

fun android.support.v4.app.Fragment.showLongToast(@StringRes messageResId: Int) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show() }
}

fun android.support.v4.app.Fragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_LONG).show() }
}

fun android.support.v4.app.Fragment.showShortToast(message: String) {
    val appContext = this.activity ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show() }
}

fun android.support.v4.app.Fragment.showShortToast(message: String, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_SHORT).show() }
}

fun android.support.v4.app.Fragment.showShortToast(@StringRes messageResId: Int) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show() }
}

fun android.support.v4.app.Fragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_SHORT).show() }
}

/* ************************************* OriginFragment ***************************************** */

fun android.app.Fragment.showLongToast(message: String) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_LONG).show() }
}

fun android.app.Fragment.showLongToast(message: String, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_LONG).show() }
}

fun android.app.Fragment.showLongToast(@StringRes messageResId: Int) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show() }
}

fun android.app.Fragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, appContext.getString(messageResId, *params), Toast.LENGTH_LONG).show() }
}

fun android.app.Fragment.showShortToast(message: String) {
    val appContext = this.activity ?: return
    runInUI { Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show() }
}

fun android.app.Fragment.showShortToast(message: String, vararg params: Any) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, message.format(*params), Toast.LENGTH_SHORT).show() }
}

fun android.app.Fragment.showShortToast(@StringRes messageResId: Int) {
    val appContext = this.activity?.applicationContext ?: return
    runInUI { Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show() }
}

fun android.app.Fragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) {
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