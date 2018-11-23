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
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.text.Selection;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import me.panpf.androidx.content.Contextx;
import me.panpf.androidx.view.Displayx;

public class InputMethodx {


    private InputMethodx() {
    }


    /**
     * Return true if the soft keyboard has been popped up, Not applicable to landscape
     */
    public static boolean isSoftInputShowing(@NonNull Activity activity) {
        if (Displayx.isOrientationPortrait(activity)) {
            View decorView = activity.getWindow().getDecorView();

            // Screen height
            int screenHeight = decorView.getHeight();

            // Subtract the height of the content area after the status bar and navigation bar
            int expectContentHeight = screenHeight - Displayx.getStatusBarHeight(activity);
            if (Displayx.hasNavigationBar(activity)) {
                expectContentHeight -= Displayx.getNavigationBarHeight(activity);
            }

            // The height of the real content area
            Rect rect = new Rect();
            decorView.getWindowVisibleDisplayFrame(rect);
            int realContentHeight = rect.height();

            // The actual content area height is less than 75% of the height of the desired content area,
            // and the soft keyboard has been popped up.
            return realContentHeight < (int) (expectContentHeight * 0.75f);
        } else {
            // In the horizontal screen, the system displays a floating layer on the page to receive the input,
            // so the DecorView will not be changed, so this method is not applicable to the horizontal screen.
            return false;
        }
    }


    /**
     * Display soft keyboard and move the cursor to the end of the EditText
     */
    public static void showSoftInputAndMoveCursorToEnd(@NonNull EditText editText) {
        moveCursorToEnd(editText);

        editText.requestFocus();
        Contextx.inputMethodManager(editText.getContext()).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Display soft keyboard
     */
    public static void showSoftInput(@NonNull EditText editText) {
        editText.requestFocus();
        Contextx.inputMethodManager(editText.getContext()).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    /**
     * Delay display soft keyboard and move the cursor to the end of the EditText
     *
     * @param delayMillisecond Delay milliseconds
     */
    public static void delayShowSoftInputAndMoveCursorToEnd(final EditText editText, long delayMillisecond) {
        // Position the cursor to the end of the entered text, the positioning cursor can not be delayed, or you can clearly see the change of the cursor
        InputMethodx.moveCursorToEnd(editText);

        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftInput(editText);
            }
        }, delayMillisecond);
    }

    /**
     * Delay display soft keyboard and move the cursor to the end of the EditText
     */
    public static void delayShowSoftInputAndMoveCursorToEnd(final EditText editText) {
        delayShowSoftInputAndMoveCursorToEnd(editText, 100);
    }

    /**
     * Delay display soft keyboard
     *
     * @param delayMillisecond Delay milliseconds
     */
    public static void delayShowSoftInput(final EditText editText, long delayMillisecond) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftInput(editText);
            }
        }, delayMillisecond);
    }

    /**
     * Delay display soft keyboard
     */
    public static void delayShowSoftInput(final EditText editText) {
        delayShowSoftInput(editText, 100);
    }


    /**
     * Hide soft keyboard
     */
    public static void hideSoftInput(@NonNull Activity activity) {
        View currentFocusView = activity.getCurrentFocus();
        if (currentFocusView != null) {
            Contextx.inputMethodManager(activity).hideSoftInputFromWindow(currentFocusView.getWindowToken(), 0);
        }
    }

    /**
     * Hide soft keyboard
     */
    public static void hideSoftInput(@NonNull android.support.v4.app.Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) hideSoftInput(activity);
    }

    /**
     * Hide soft keyboard
     */
    public static void hideSoftInput(@NonNull android.app.Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity != null) hideSoftInput(activity);
    }

    /**
     * Hide soft keyboard
     */
    public static void hideSoftInput(@NonNull EditText editText) {
        if (editText.getWindowToken() == null) return;
        Contextx.inputMethodManager(editText.getContext()).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    /**
     * Move the cursor to the end of the EditText
     */
    public static void moveCursorToEnd(@NonNull EditText editText) {
        Selection.setSelection(editText.getEditableText(), editText.length());
    }

    /**
     * Move the cursor to the start of the EditText
     */
    public static void moveCursorToStart(@NonNull EditText editText) {
        Selection.setSelection(editText.getEditableText(), 0);
    }

    /**
     * Move the cursor to the specified index of the EditText
     */
    public static void moveCursorTo(@NonNull EditText editText, int index) {
        Selection.setSelection(editText.getEditableText(), index);
    }
}
