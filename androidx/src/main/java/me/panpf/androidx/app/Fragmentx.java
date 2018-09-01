package me.panpf.androidx.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.panpf.androidx.content.Intentx;

public class Fragmentx {

    /**
     * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public <T> T getImplWithParent(@NotNull Fragment fragment, @NotNull Class<T> clazz) {
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
    public static boolean canStartActivity(@NotNull Fragment fragment, @NotNull Intent intent) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Fragment " + fragment + " not attached to Activity");
        }

        return Intentx.canStartActivity(activity, intent);
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartActivity(@NotNull Fragment fragment, @NotNull Intent intent) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Fragment " + fragment + " not attached to Activity");
        }

        return Intentx.safeStartActivity(activity, intent);
    }
}
