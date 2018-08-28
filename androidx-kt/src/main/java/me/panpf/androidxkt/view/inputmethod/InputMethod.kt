@file:Suppress("unused")

package me.panpf.androidxkt.view.inputmethod

import android.app.Activity
import android.content.Context
import android.text.Selection
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.app.Fragment as OriginFragment
import android.support.v4.app.Fragment as SupportFragment

/*
 * 输入法相关的扩展方法或属性
 */

fun EditText.showSoftInput(moveCursorToEnd: Boolean = false) {
    this.requestFocus()

    val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

    if (moveCursorToEnd) {
        this.moveCursorToEnd()
    }
}

fun Activity.hideSoftInput() {
    val currentFocusView = this.currentFocus ?: return
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
}

fun OriginFragment.hideSoftInput() {
    activity?.hideSoftInput()
}

fun SupportFragment.hideSoftInput() {
    activity?.hideSoftInput()
}

fun EditText.hideSoftInput() {
    this.windowToken ?: return
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
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








