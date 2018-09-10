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

package me.panpf.androidx.view.inputmethod;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Selection;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

@SuppressWarnings("WeakerAccess")
public class InputMethodx {

    public static void showSoftInput(@NonNull EditText editText, boolean moveCursorToEnd) {
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new IllegalStateException("InputMethodManager not found");
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        if (moveCursorToEnd) moveCursorToEnd(editText);
    }

    public static void showSoftInput(@NonNull EditText editText) {
        showSoftInput(editText, false);
    }

    public static void hideSoftInput(@NonNull Activity activity) {
        View currentFocusView = activity.getCurrentFocus();
        if (currentFocusView == null) return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new IllegalStateException("InputMethodManager not found");
        imm.hideSoftInputFromWindow(currentFocusView.getWindowToken(), 0);
    }

    public static void hideSoftInput(@NonNull android.support.v4.app.Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) hideSoftInput(activity);
    }

    public static void hideSoftInput(@NonNull android.app.Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) hideSoftInput(activity);
    }

    public static void hideSoftInput(@NonNull EditText editText) {
        if (editText.getWindowToken() == null) return;
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) throw new IllegalStateException("InputMethodManager not found");
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static void moveCursorToEnd(@NonNull EditText editText) {
        Selection.setSelection(editText.getEditableText(), editText.length());
    }

    public static void moveCursorToStart(@NonNull EditText editText) {
        Selection.setSelection(editText.getEditableText(), 0);
    }

    public static void moveCursorTo(@NonNull EditText editText, int index) {
        Selection.setSelection(editText.getEditableText(), index);
    }
}
