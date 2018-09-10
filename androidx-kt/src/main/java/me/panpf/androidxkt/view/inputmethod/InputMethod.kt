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

package me.panpf.androidxkt.view.inputmethod

import android.app.Activity
import android.content.Context
import android.text.Selection
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/*
 * 输入法相关的扩展方法或属性
 */

fun EditText.showSoftInput(moveCursorToEnd: Boolean = false) {
    this.requestFocus()

    val inputMethodManager = (this.context.getSystemService(Context.INPUT_METHOD_SERVICE)
            ?: throw IllegalStateException("InputMethodManager not found")) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

    if (moveCursorToEnd) this.moveCursorToEnd()
}

fun Activity.hideSoftInput() {
    val currentFocusView = this.currentFocus ?: return
    val inputMethodManager = (this.getSystemService(Context.INPUT_METHOD_SERVICE)
            ?: throw IllegalStateException("InputMethodManager not found")) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
}

fun android.support.v4.app.Fragment.hideSoftInput() {
    activity?.hideSoftInput()
}

fun android.app.Fragment.hideSoftInput() {
    activity?.hideSoftInput()
}

fun EditText.hideSoftInput() {
    this.windowToken ?: return
    val inputMethodManager = (this.context.getSystemService(Context.INPUT_METHOD_SERVICE)
            ?: throw IllegalStateException("InputMethodManager not found")) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun EditText.moveCursorToEnd() {
    Selection.setSelection(this.editableText, this.length())
}

fun EditText.moveCursorToStart() {
    Selection.setSelection(this.editableText, 0)
}

fun EditText.moveCursorTo(index: Int) {
    Selection.setSelection(this.editableText, index)
}








