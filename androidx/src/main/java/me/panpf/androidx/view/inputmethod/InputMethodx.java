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
import android.support.annotation.NonNull;
import android.text.Selection;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import me.panpf.androidx.content.Contextx;

@SuppressWarnings("WeakerAccess")
public class InputMethodx {

    public static void showSoftInput(@NonNull EditText editText, boolean moveCursorToEnd) {
        editText.requestFocus();

        Contextx.inputMethodManager(editText.getContext()).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        if (moveCursorToEnd) moveCursorToEnd(editText);
    }

    public static void showSoftInput(@NonNull EditText editText) {
        showSoftInput(editText, false);
    }

    public static void delayShowSoftInput(final EditText editText, boolean moveCursorToEnd, long delayMillisecond) {
        // 定位光标到已输入文本的最后，定位光标不能延迟，要不然页面上看上去会有光标的跳动
        if (moveCursorToEnd) InputMethodx.moveCursorToEnd(editText);

        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftInput(editText, false);
            }
        }, delayMillisecond);
    }

    public static void delayShowSoftInput(final EditText editText, boolean moveCursorToEnd) {
        delayShowSoftInput(editText, moveCursorToEnd, 100);
    }

    public static void delayShowSoftInput(final EditText editText, long delayMillisecond) {
        delayShowSoftInput(editText, false, delayMillisecond);
    }

    public static void delayShowSoftInput(final EditText editText) {
        delayShowSoftInput(editText, false, 100);
    }

    public static void hideSoftInput(@NonNull Activity activity) {
        View currentFocusView = activity.getCurrentFocus();
        if (currentFocusView != null) {
            Contextx.inputMethodManager(activity).hideSoftInputFromWindow(currentFocusView.getWindowToken(), 0);
        }
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
        Contextx.inputMethodManager(editText.getContext()).hideSoftInputFromWindow(editText.getWindowToken(), 0);
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
