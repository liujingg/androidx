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

package me.panpf.androidx.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import me.panpf.androidx.Androidx;

public class Toastx {

    /* ************************************* Context ***************************************** */

    public static void showLong(@NotNull Context context, @NotNull final String message) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull Context context, @NotNull final String message, @NotNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull Context context, @StringRes final int messageResId) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull Context context, @StringRes final int messageResId, @NotNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showShort(@NotNull Context context, @NotNull final String message) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull Context context, @NotNull final String message, @NotNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull Context context, @StringRes final int messageResId) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull Context context, @StringRes final int messageResId, @NotNull final Object... params) {
        final Context appContext = context.getApplicationContext();
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* ************************************* SupportFragment ***************************************** */

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @NotNull final String message) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @NotNull final String message, @NotNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId, @NotNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @NotNull final String message) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @NotNull final String message, @NotNull final Object... params) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId) {
        final Context appContext = fragment.getActivity() != null ? fragment.getActivity().getApplicationContext() : null;
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @StringRes final int messageResId, @NotNull final Object... params) {
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

    public static void showLong(@NotNull View view, @NotNull final String message) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull View view, @NotNull final String message, @NotNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull View view, @StringRes final int messageResId) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showLong(@NotNull View view, @StringRes final int messageResId, @NotNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, appContext.getString(messageResId, params), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void showShort(@NotNull View view, @NotNull final String message) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull View view, @NotNull final String message, @NotNull final Object... params) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, String.format(message, params), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull View view, @StringRes final int messageResId) {
        final Context appContext = view.getContext().getApplicationContext();
        if (appContext == null) return;
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, messageResId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showShort(@NotNull View view, @StringRes final int messageResId, @NotNull final Object... params) {
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

    public static void showLongWithView(@NotNull final View view) {
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

    public static void showShortWithView(@NotNull final View view) {
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