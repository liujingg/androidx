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

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class Toastx {

    /* ************************************* Context ***************************************** */

    public static void showLong(@NotNull Context context, @NotNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@NotNull Context context, @NotNull String message, @NotNull Object... params) {
        Toast.makeText(context, String.format(message, params), Toast.LENGTH_LONG).show();
    }

    public static void showLong(@NotNull Context context, @StringRes int messageResId) {
        Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@NotNull Context context, @StringRes int messageResId, @NotNull Object... params) {
        Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show();
    }

    public static void showShort(@NotNull Context context, @NotNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(@NotNull Context context, @NotNull String message, @NotNull Object... params) {
        Toast.makeText(context, String.format(message, params), Toast.LENGTH_SHORT).show();
    }

    public static void showShort(@NotNull Context context, @StringRes int messageResId) {
        Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(@NotNull Context context, @StringRes int messageResId, @NotNull Object... params) {
        Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show();
    }

    /* ************************************* SupportFragment ***************************************** */

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @NotNull String message) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @NotNull String message, @NotNull Object... params) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, String.format(message, params), Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @StringRes int messageResId) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull android.support.v4.app.Fragment fragment, @StringRes int messageResId, @NotNull Object... params) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show();
        }
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @NotNull String message) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @NotNull String message, @NotNull Object... params) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, String.format(message, params), Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @StringRes int messageResId) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull android.support.v4.app.Fragment fragment, @StringRes int messageResId, @NotNull Object... params) {
        Context context = fragment.getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show();
        }
    }

    /* ************************************* OriginFragment ***************************************** */

    public static void showLong(@NotNull android.app.Fragment fragment, @NotNull String message) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull android.app.Fragment fragment, @NotNull String message, @NotNull Object... params) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, String.format(message, params), Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull android.app.Fragment fragment, @StringRes int messageResId) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull android.app.Fragment fragment, @StringRes int messageResId, @NotNull Object... params) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show();
        }
    }

    public static void showShort(@NotNull android.app.Fragment fragment, @NotNull String message) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull android.app.Fragment fragment, @NotNull String message, @NotNull Object... params) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, String.format(message, params), Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull android.app.Fragment fragment, @StringRes int messageResId) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull android.app.Fragment fragment, @StringRes int messageResId, @NotNull Object... params) {
        Activity context = fragment.getActivity();
        if (context != null) {
            Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show();
        }
    }

    /* ************************************* View ***************************************** */

    public static void showLong(@NotNull View view, @NotNull String message) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull View view, @NotNull String message, @NotNull Object... params) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, String.format(message, params), Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull View view, @StringRes int messageResId) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, messageResId, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(@NotNull View view, @StringRes int messageResId, @NotNull Object... params) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_LONG).show();
        }
    }

    public static void showShort(@NotNull View view, @NotNull String message) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull View view, @NotNull String message, @NotNull Object... params) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, String.format(message, params), Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull View view, @StringRes int messageResId) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(@NotNull View view, @StringRes int messageResId, @NotNull Object... params) {
        Context context = view.getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(messageResId, params), Toast.LENGTH_SHORT).show();
        }
    }

    /* ************************************* Show View ***************************************** */

    public static void showLong(@NotNull View view) {
        Context context = view.getContext();
        if (context != null) {
            Toast toast = new Toast(context);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public static void showShort(@NotNull View view) {
        Context context = view.getContext();
        if (context != null) {
            Toast toast = new Toast(context);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}