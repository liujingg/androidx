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

package me.panpf.androidx.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.arch.lifecycle.Lifecycle;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import me.panpf.javax.lang.Classx;

@SuppressWarnings({"WeakerAccess"})
public class Activityx {

    private Activityx() {
    }

    /**
     * Return true if the activity has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull Activity activity) {
        // First determine that FragmentActivity can use the compatible isDestroyed method in versions below 17.
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return activity.isDestroyed() || activity.isFinishing();
        } else {
            return activity.isFinishing();
        }
    }

    /**
     * Return true if the activity has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull FragmentActivity activity) {
        return activity.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
    }


    /**
     * Convert a translucent themed Activity
     * [android.R.attr.windowIsTranslucent] to a fullscreen opaque
     * Activity.
     * <p>
     * Call this whenever the background of a translucent Activity has changed
     * to become opaque. Doing so will allow the [android.view.Surface] of
     * the Activity behind to be released.
     * <p>
     * This call has no effect on non-translucent activities or on activities
     * with the [android.R.attr.windowIsFloating] attribute.
     */
    public static boolean convertFromTranslucent(@NonNull Activity activity) {
        try {
            Classx.callMethod(activity, "convertFromTranslucent");
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Convert a translucent themed Activity
     * [android.R.attr.windowIsTranslucent] back from opaque to
     * translucent following a call to
     * [.convertActivityFromTranslucent] .
     * <p>
     * <p>
     * Calling this allows the Activity behind this one to be seen again. Once
     * all such Activities have been redrawn
     * <p>
     * <p>
     * This call has no effect on non-translucent activities or on activities
     * with the [android.R.attr.windowIsFloating] attribute.
     */
    public static boolean convertToTranslucent(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                ActivityOptions options = (ActivityOptions) Classx.callMethod(activity, "getActivityOptions");
                Field mTranslucentCallbackField = Classx.getFieldWithParent(activity, "mTranslucentCallback");
                Method method = Classx.getMethodWithParent(activity, "convertToTranslucent", mTranslucentCallbackField.getType(), ActivityOptions.class);
                Classx.callMethod(activity, method, Classx.getFieldValue(activity, mTranslucentCallbackField), options);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                Field mTranslucentCallbackField = Classx.getFieldWithParent(activity, "mTranslucentCallback");
                Method method = Classx.getMethodWithParent(activity, "convertToTranslucent", mTranslucentCallbackField.getType());
                Classx.callMethod(activity, method, Classx.getFieldValue(activity, mTranslucentCallbackField));
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    /**
     * If the own or parent activity implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplWithParent(@NonNull Activity activity, @NonNull Class<T> clazz) {
        Activity parent = activity;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parent = parent.getParent();
            }
        }
        return null;
    }


    @NonNull
    public static Context appContext(@NonNull Activity activity) {
        return activity.getApplicationContext();
    }


    /* ************************************* canStart ***************************************** */


    /**
     * Test if you can start Activity
     */
    public static boolean canStart(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfoList != null && resolveInfoList.size() > 0;
    }


    /* ************************************* start ***************************************** */


    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull Context context, @NonNull Intent intent) {
        context.startActivity(intent);
    }

    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull android.support.v4.app.Fragment fragment, @NonNull Intent intent) {
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull android.app.Fragment fragment, @NonNull Intent intent) {
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified intent
     */
    public static void start(@NonNull View view, @NonNull Intent intent) {
        view.getContext().startActivity(intent);
    }


    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(context, clazz);
        if (args != null) intent.putExtras(args);
        context.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        start(context, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull android.support.v4.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Fragmentx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull android.support.v4.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz) {
        start(fragment, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull android.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Fragmentx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull android.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz) {
        start(fragment, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull View view, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(view.getContext(), clazz);
        if (args != null) intent.putExtras(args);
        view.getContext().startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void start(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        start(view, clazz, null);
    }


    /* ************************************* safeStart ***************************************** */


    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull android.support.v4.app.Fragment fragment, @NonNull Intent intent) {
        try {
            fragment.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull android.app.Fragment fragment, @NonNull Intent intent) {
        try {
            fragment.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull View view, @NonNull Intent intent) {
        return safeStart(view.getContext(), intent);
    }


    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(context, clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(context, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        return safeStart(context, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull android.support.v4.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Fragmentx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(fragment, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull android.support.v4.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz) {
        return safeStart(fragment, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull android.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Fragmentx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(fragment, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull android.app.Fragment fragment,
                             @NonNull Class<? extends Activity> clazz) {
        return safeStart(fragment, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull View view, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(view.getContext(), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(view, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        return safeStart(view, clazz, null);
    }
}