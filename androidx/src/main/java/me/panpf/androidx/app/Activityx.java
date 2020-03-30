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
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import me.panpf.androidx.content.Contextx;

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
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    public static boolean convertToTranslucentCompat(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                ActivityOptions options = callMethod(activity, "getActivityOptions");
                Field mTranslucentCallbackField = getDeclaredFieldRecursive(activity.getClass(), "mTranslucentCallback");
                Method method = getDeclaredMethodRecursive(activity.getClass(), "convertToTranslucent", mTranslucentCallbackField.getType(), ActivityOptions.class);
                callMethod(activity, method, getFieldValue(activity, mTranslucentCallbackField), options);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Field mTranslucentCallbackField = getDeclaredFieldRecursive(activity.getClass(), "mTranslucentCallback");
                Method method = getDeclaredMethodRecursive(activity.getClass(), "convertToTranslucent", mTranslucentCallbackField.getType());
                callMethod(activity, method, getFieldValue(activity, mTranslucentCallbackField));
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
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
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    public static boolean convertFromTranslucentCompat(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                callMethod(activity, "convertFromTranslucent");
                return true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * If the own or parent activity implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplFromParent(@NonNull Activity activity, @NonNull Class<T> clazz) {
        Activity parent = activity;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) parent;
            } else {
                parent = parent.getParent();
            }
        }
        return null;
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
    public static void start(@NonNull Fragment fragment, @NonNull Intent intent) {
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
    public static void startByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(context, clazz);
        if (args != null) intent.putExtras(args);
        context.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        startByClass(context, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Fragment fragment,
                                    @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Contextx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull Fragment fragment,
                                    @NonNull Class<? extends Activity> clazz) {
        startByClass(fragment, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull android.app.Fragment fragment,
                                    @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Contextx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        fragment.startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull android.app.Fragment fragment,
                                    @NonNull Class<? extends Activity> clazz) {
        startByClass(fragment, clazz, null);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(view.getContext(), clazz);
        if (args != null) intent.putExtras(args);
        view.getContext().startActivity(intent);
    }

    /**
     * Start the activity of the specified Class
     */
    public static void startByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        startByClass(view, clazz, null);
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
    public static boolean safeStart(@NonNull Fragment fragment, @NonNull Intent intent) {
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
    public static boolean safeStartByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(context, clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(context, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(context, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Fragment fragment,
                                           @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Contextx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(fragment, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull Fragment fragment,
                                           @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(fragment, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull android.app.Fragment fragment,
                                           @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(Contextx.requireContext(fragment), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(fragment, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull android.app.Fragment fragment,
                                           @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(fragment, clazz, null);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz, @Nullable Bundle args) {
        Intent intent = new Intent(view.getContext(), clazz);
        if (args != null) intent.putExtras(args);
        return safeStart(view, intent);
    }

    /**
     * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartByClass(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        return safeStartByClass(view, clazz, null);
    }

    /**
     * Get the declared field with the specified name from the specified class
     */
    @NonNull
    private static Field getDeclaredFieldRecursive(@NonNull Class<?> clazz, @NonNull String fieldName) throws NoSuchFieldException {
        Field field = null;

        Class currentClazz = clazz;
        while (field == null && currentClazz != null) {
            try {
                field = currentClazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
            }

            if (field == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (field == null) {
            throw new NoSuchFieldException(String.format("No such field by name '%s' in class '%s' and its parent class", fieldName, clazz.getName()));
        } else {
            return field;
        }
    }
    
    /**
     * Get the declared method with the specified name from the specified class
     */
    @NonNull
    private static Method getDeclaredMethodRecursive(@NonNull Class<?> clazz, @NonNull String methodName, @Nullable Class<?>... params) throws NoSuchMethodException {
        Method method = null;

        Class currentClazz = clazz;
        while (method == null && currentClazz != null) {
            try {
                //noinspection unchecked
                method = currentClazz.getDeclaredMethod(methodName, params);
            } catch (NoSuchMethodException ignored) {
            }

            if (method == null) {
                currentClazz = currentClazz.getSuperclass();
            }
        }

        if (method == null) {
            throw new NoSuchMethodException(String.format("No such method by name '%s' and params '%s' in class '%s' and its parent class", methodName, Arrays.toString(params), clazz.getName()));
        } else {
            return method;
        }
    }

    /**
     * Method of executing the specified name of the specified object
     */
    @Nullable
    private static <T> T callMethod(@NonNull Object object, @NonNull String methodName, Object... params) throws NoSuchMethodException {
        Class[] paramClazzArray = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramClazzArray[i] = params[i].getClass();
        }
        Method method = getDeclaredMethodRecursive(object.getClass(), methodName, paramClazzArray);
        return callMethod(object, method, params);
    }
    
    /**
     * Method of executing of the specified object
     */
    @Nullable
    private static <T> T callMethod(@NonNull Object object, @NonNull Method method, @Nullable Object... params) {
        method.setAccessible(true);
        try {
            //noinspection unchecked
            return (T) method.invoke(object, params);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Get the value of the specified field
     */
    @Nullable
    private static <T> T getFieldValue(@NonNull Object object, @NonNull Field field) {
        field.setAccessible(true);
        try {
            //noinspection unchecked
            return (T) field.get(object);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}