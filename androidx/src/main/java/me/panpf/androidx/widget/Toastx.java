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

package me.panpf.androidx.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import me.panpf.androidx.Androidx;

public class Toastx {

    /* ************************************* Context ***************************************** */

    public static void showLong(@NonNull Context context, @NonNull final String message) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull Context context, @NonNull final String message, @NonNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull Context context, @StringRes final int messageResId) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull Context context, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showShort(@NonNull Context context, @NonNull final String message) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull Context context, @NonNull final String message, @NonNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull Context context, @StringRes final int messageResId) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull Context context, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* ************************************* SupportFragment ***************************************** */

    public static void showLong(@NonNull android.support.v4.app.Fragment fragment, @NonNull final String message) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull android.support.v4.app.Fragment fragment, @NonNull final String message, @NonNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showShort(@NonNull android.support.v4.app.Fragment fragment, @NonNull final String message) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull android.support.v4.app.Fragment fragment, @NonNull final String message, @NonNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* ************************************* View ***************************************** */

    public static void showLong(@NonNull View view, @NonNull final String message) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull View view, @NonNull final String message, @NonNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull View view, @StringRes final int messageResId) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NonNull View view, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showShort(@NonNull View view, @NonNull final String message) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull View view, @NonNull final String message, @NonNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull View view, @StringRes final int messageResId) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NonNull View view, @StringRes final int messageResId, @NonNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* ************************************* Show View ***************************************** */

    public static void showLongWithView(@NonNull final View view) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast toast = new Toast(appContext);
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public static void showShortWithView(@NonNull final View view) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast toast = new Toast(appContext);
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}