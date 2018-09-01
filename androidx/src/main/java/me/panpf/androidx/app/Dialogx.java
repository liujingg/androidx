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

package me.panpf.androidx.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.panpf.javax.lang.Classx;

@SuppressWarnings("WeakerAccess")
public class Dialogx {

    /**
     * Whether to automatically close the dialog box when setting the click button
     */
    public static void setClickButtonClosable(@NotNull Dialog dialog, boolean close) {
        try {
            Classx.setFieldValue(dialog, "mShowing", close);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display a progress dialog
     */
    @NotNull
    public static ProgressDialog showProgressDialog(@NotNull Activity activity, @NotNull String message) {
        ProgressDialog newDialog = new ProgressDialog(activity);
        newDialog.setTitle(null);
        newDialog.setMessage(message);
        newDialog.setIndeterminate(true);
        newDialog.setCancelable(false);
        newDialog.setOnCancelListener(null);
        newDialog.setCanceledOnTouchOutside(false);
        newDialog.show();
        return newDialog;
    }

    /**
     * Display a progress dialog
     */
    @NotNull
    public static ProgressDialog showProgressDialog(@NotNull Activity activity, int messageId) {
        return showProgressDialog(activity, activity.getString(messageId));
    }

    /**
     * Display a progress dialog
     */
    @Nullable
    public static ProgressDialog showProgressDialog(@NotNull Fragment fragment, @NotNull String message) {
        Activity activity = fragment.getActivity();
        return activity != null ? showProgressDialog(activity, message) : null;
    }

    /**
     * Display a progress dialog
     */
    @Nullable
    public static ProgressDialog showProgressDialog(@NotNull Fragment fragment, int messageId) {
        Activity activity = fragment.getActivity();
        return activity != null ? showProgressDialog(activity, messageId) : null;
    }
}
