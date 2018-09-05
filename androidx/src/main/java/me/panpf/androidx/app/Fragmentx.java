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
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.panpf.androidx.content.Intentx;

public class Fragmentx {

    /**
     * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public <T> T getImplWithParent(@NonNull Fragment fragment, @NonNull Class<T> clazz) {
        Fragment parent = fragment;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parent = parent.getParentFragment();
            }
        }
        Activity parentActivity = fragment.getActivity();
        while (parentActivity != null) {
            if (clazz.isAssignableFrom(parentActivity.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parentActivity = parentActivity.getParent();
            }
        }
        return null;
    }

    /**
     * Test if you can start Activity
     */
    public static boolean canStartActivity(@NonNull Fragment fragment, @NonNull Intent intent) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Fragment " + fragment + " not attached to Activity");
        }

        return Intentx.canStartActivity(activity, intent);
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartActivity(@NonNull Fragment fragment, @NonNull Intent intent) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Fragment " + fragment + " not attached to Activity");
        }

        return Intentx.safeStartActivity(activity, intent);
    }
}
