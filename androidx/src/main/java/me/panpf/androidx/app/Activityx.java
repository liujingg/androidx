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
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Method;

import me.panpf.javax.lang.Classx;

@SuppressWarnings("WeakerAccess")
public class Activityx {
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
    public static void convertActivityFromTranslucent(@NonNull Activity activity) {
        try {
            Classx.callMethod(activity, "convertFromTranslucent");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
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
    public static void convertActivityToTranslucent(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                ActivityOptions options = (ActivityOptions) Classx.callMethod(activity, "getActivityOptions");

                Class<?>[] classes = activity.getClass().getDeclaredClasses();
                Class<?> translucentConversionListenerClazz = null;
                for (Class<?> clazz : classes) {
                    if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
                        translucentConversionListenerClazz = clazz;
                    }
                }
                Method method = activity.getClass().getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class);
                Classx.callMethod(activity, method, null, options);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            try {
                Class<?>[] classes = activity.getClass().getDeclaredClasses();
                Class<?> translucentConversionListenerClazz = null;
                for (Class<?> clazz : classes) {
                    if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
                        translucentConversionListenerClazz = clazz;
                    }
                }
                Method method = activity.getClass().getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz);
                Classx.callMethod(activity, method, new Object[]{null});
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * If the own or parent activity implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public <T> T getImplWithParent(@NonNull Activity activity, @NonNull Class<T> clazz) {
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
}