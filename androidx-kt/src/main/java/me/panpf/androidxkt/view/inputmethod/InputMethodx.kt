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

package me.panpf.androidxkt.view.inputmethod

import android.app.Activity
import android.widget.EditText
import me.panpf.androidx.view.inputmethod.InputMethodx

/*
 * 输入法相关的扩展方法或属性
 */

inline fun EditText.showSoftInput(moveCursorToEnd: Boolean = false) = InputMethodx.showSoftInput(this, moveCursorToEnd)

inline fun EditText.delayShowSoftInput(moveCursorToEnd: Boolean = false, delayMillisecond: Long = 100) =
        InputMethodx.delayShowSoftInput(this, moveCursorToEnd, delayMillisecond)

inline fun Activity.hideSoftInput() = InputMethodx.hideSoftInput(this)

inline fun android.support.v4.app.Fragment.hideSoftInput() = InputMethodx.hideSoftInput(this)

inline fun android.app.Fragment.hideSoftInput() = InputMethodx.hideSoftInput(this)

inline fun EditText.hideSoftInput() = InputMethodx.hideSoftInput(this)

inline fun EditText.moveCursorToEnd() = InputMethodx.moveCursorToEnd(this)

inline fun EditText.moveCursorToStart() = InputMethodx.moveCursorToStart(this)

inline fun EditText.moveCursorTo(index: Int) = InputMethodx.moveCursorTo(this, index)








