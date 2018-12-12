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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.widget

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import me.panpf.androidx.widget.Toastx

/*
 * Toast related extension method
 */


/* ************************************* Context ***************************************** */


inline fun Context.showLongToast(message: String) = Toastx.showLong(this, message)

inline fun Context.showLongToast(message: String, vararg params: Any) = Toastx.showLong(this, message, *params)

inline fun Context.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun Context.showLongToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLong(this, messageResId, *params)

inline fun Context.showShortToast(message: String) = Toastx.showShort(this, message)

inline fun Context.showShortToast(message: String, vararg params: Any) = Toastx.showShort(this, message, *params)

inline fun Context.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun Context.showShortToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShort(this, messageResId, *params)


/* ************************************* androidx.fragment.app.Fragment ***************************************** */


inline fun androidx.fragment.app.Fragment.showLongToast(message: String) = Toastx.showLong(this, message)

inline fun androidx.fragment.app.Fragment.showLongToast(message: String, vararg params: Any) = Toastx.showLong(this, message, *params)

inline fun androidx.fragment.app.Fragment.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun androidx.fragment.app.Fragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLong(this, messageResId, *params)

inline fun androidx.fragment.app.Fragment.showShortToast(message: String) = Toastx.showShort(this, message)

inline fun androidx.fragment.app.Fragment.showShortToast(message: String, vararg params: Any) = Toastx.showShort(this, message, *params)

inline fun androidx.fragment.app.Fragment.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun androidx.fragment.app.Fragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShort(this, messageResId, *params)


/* ************************************* android.app.Fragment ***************************************** */


inline fun android.app.Fragment.showLongToast(message: String) = Toastx.showLong(this, message)

inline fun android.app.Fragment.showLongToast(message: String, vararg params: Any) = Toastx.showLong(this, message, *params)

inline fun android.app.Fragment.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun android.app.Fragment.showLongToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLong(this, messageResId, *params)

inline fun android.app.Fragment.showShortToast(message: String) = Toastx.showShort(this, message)

inline fun android.app.Fragment.showShortToast(message: String, vararg params: Any) = Toastx.showShort(this, message, *params)

inline fun android.app.Fragment.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun android.app.Fragment.showShortToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShort(this, messageResId, *params)


/* ************************************* View ***************************************** */


inline fun View.showLongToast(message: String) = Toastx.showLong(this, message)

inline fun View.showLongToast(message: String, vararg params: Any) = Toastx.showLong(this, message, *params)

inline fun View.showLongToast(@StringRes messageResId: Int) = Toastx.showLong(this, messageResId)

inline fun View.showLongToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showLong(this, messageResId, *params)

inline fun View.showShortToast(message: String) = Toastx.showShort(this, message)

inline fun View.showShortToast(message: String, vararg params: Any) = Toastx.showShort(this, message, *params)

inline fun View.showShortToast(@StringRes messageResId: Int) = Toastx.showShort(this, messageResId)

inline fun View.showShortToast(@StringRes messageResId: Int, vararg params: Any) = Toastx.showShort(this, messageResId, *params)


/* ************************************* Show View ***************************************** */


inline fun View.showLongToastWithSelf() = Toastx.showLongWithView(this)

inline fun View.showShortToastWithSelf() = Toastx.showShortWithView(this)