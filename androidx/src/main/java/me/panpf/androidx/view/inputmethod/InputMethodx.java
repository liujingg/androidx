/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
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

package me.panpf.androidx.view.inputmethod;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("WeakerAccess")
public class InputMethodx {

    public static void showSoftInput(@NotNull EditText editText, boolean moveCursorToEnd) {
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new IllegalStateException("InputMethodManager not found");
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        if (moveCursorToEnd) moveCursorToEnd(editText);
    }

    public static void showSoftInput(@NotNull EditText editText) {
        showSoftInput(editText, false);
    }

    public static void hideSoftInput(@NotNull Activity activity) {
        View currentFocusView = activity.getCurrentFocus();
        if (currentFocusView == null) return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new IllegalStateException("InputMethodManager not found");
        imm.hideSoftInputFromWindow(currentFocusView.getWindowToken(), 0);
    }

    public static void hideSoftInput(@NotNull Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) hideSoftInput(activity);
    }

    public static void hideSoftInput(@NotNull EditText editText) {
        if (editText.getWindowToken() == null) return;
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new IllegalStateException("InputMethodManager not found");
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void moveCursorToEnd(@NotNull EditText editText) {
        Selection.setSelection(editText.getEditableText(), editText.length());
    }

    public static void moveCursorToStart(@NotNull EditText editText) {
        Selection.setSelection(editText.getEditableText(), 0);
    }

    public static void moveCursorTo(@NotNull EditText editText, int index) {
        Selection.setSelection(editText.getEditableText(), index);
    }
}
