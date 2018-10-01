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

package me.panpf.androidx.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import me.panpf.javax.util.LazyValue;
import me.panpf.javax.util.Premisex;

public class Displayx {

    private static String sNavBarOverride;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Method m = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                sNavBarOverride = null;
            }
        }
    }

    @NonNull
    public static Point getScreenSize(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point;
    }

    public static int getScreenWidth(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getScreenHeight(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point.y;
    }


    @NonNull
    public static DisplayMetrics getMetrics(@NonNull Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getDensity(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getDensityDpi(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }


    public static int getRotation(@NotNull Context context) {
        WindowManager windowManager = Premisex.requireNotNull((WindowManager) context.getSystemService(Context.WINDOW_SERVICE), "windowManager");
        switch (windowManager.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    public static int getRotation(@NonNull android.support.v4.app.Fragment fragment) {
        return getRotation(fragment.requireContext());
    }

    public static int getRotation(@NonNull final android.app.Fragment fragment) {
        return getRotation(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    public static int getRotation(@NonNull View view) {
        return getRotation(view.getContext());
    }


    public static boolean isOrientationPortrait(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean isOrientationPortrait(@NonNull android.support.v4.app.Fragment fragment) {
        return isOrientationPortrait(fragment.requireContext());
    }

    public static boolean isOrientationPortrait(@NonNull final android.app.Fragment fragment) {
        return isOrientationPortrait(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    public static boolean isOrientationPortrait(@NonNull View view) {
        return isOrientationPortrait(view.getContext());
    }


    public static boolean isOrientationLandscape(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isOrientationLandscape(@NonNull android.support.v4.app.Fragment fragment) {
        return isOrientationLandscape(fragment.requireContext());
    }

    public static boolean isOrientationLandscape(@NonNull final android.app.Fragment fragment) {
        return isOrientationLandscape(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    public static boolean isOrientationLandscape(@NonNull View view) {
        return isOrientationLandscape(view.getContext());
    }


    public static boolean isOrientationUndefined(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_UNDEFINED;
    }

    public static boolean isOrientationUndefined(@NonNull android.support.v4.app.Fragment fragment) {
        return isOrientationUndefined(fragment.requireContext());
    }

    public static boolean isOrientationUndefined(@NonNull final android.app.Fragment fragment) {
        return isOrientationUndefined(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NotNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    public static boolean isOrientationUndefined(@NonNull View view) {
        return isOrientationUndefined(view.getContext());
    }


    private static int getInternalDimensionSize(@NotNull Resources res, @NotNull String resName) {
        int result = 0;
        int resourceId = res.getIdentifier(resName, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * Get the height of the system status bar.
     *
     * @return The height of the status bar (in pixels).
     */
    public static int getStatusBarHeight(@NotNull Context context) {
        return getInternalDimensionSize(context.getResources(), "status_bar_height");
    }


    /**
     * Whether you have a navigation bar
     */
    public static boolean hasNavigationBar(@NotNull Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag (see static block)
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * Get the height of the navigation bar
     */
    public static int getNavigationBarHeight(@NotNull Context context) {
        Resources res = context.getResources();
        if (hasNavigationBar(context)) {
            if ((res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)) {
                return getInternalDimensionSize(res, "navigation_bar_height");
            } else {
                return getInternalDimensionSize(res, "navigation_bar_height_landscape");
            }
        }
        return 0;
    }

    /**
     * Get the width of the navigation bar
     */
    public static int getNavigationBarWidth(@NotNull Context context) {
        if (hasNavigationBar(context)) {
            return getInternalDimensionSize(context.getResources(), "navigation_bar_width");
        } else {
            return 0;
        }
    }
}
