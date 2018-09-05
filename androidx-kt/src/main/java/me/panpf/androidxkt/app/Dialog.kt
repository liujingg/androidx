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