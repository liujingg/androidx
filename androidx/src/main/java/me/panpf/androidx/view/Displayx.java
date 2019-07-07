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
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Surface;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import me.panpf.androidx.content.Contextx;
import me.panpf.androidx.os.SystemPropertiesx;
import me.panpf.javax.util.LazyValue;
import me.panpf.javax.util.Premisex;

public class Displayx {

    private static String sNavBarOverride;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                sNavBarOverride = SystemPropertiesx.get("qemu.hw.mainkeys");
            } catch (Throwable e) {
                sNavBarOverride = null;
            }
        }
    }

    private Displayx() {
    }


    /**
     * Get screen size
     */
    @NonNull
    public static Point getScreenSize(@NonNull Context context) {
        Point point = new Point();
        Contextx.windowManager(context).getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * Get screen width
     */
    public static int getScreenWidth(@NonNull Context context) {
        Point point = new Point();
        Contextx.windowManager(context).getDefaultDisplay().getSize(point);
        return point.x;
    }

    /**
     * Get screen height
     */
    public static int getScreenHeight(@NonNull Context context) {
        Point point = new Point();
        Contextx.windowManager(context).getDefaultDisplay().getSize(point);
        return point.y;
    }

    /**
     * Get actionBar size
     */
    public static int getActionBarSize(@NonNull Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } else {
            return 0;
        }
    }


    /**
     * Get DisplayMetrics
     */
    @NonNull
    public static DisplayMetrics getMetrics(@NonNull Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * Get screen density
     */
    public static float getDensity(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * Get screen density dpi
     */
    public static int getDensityDpi(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * Get the screen rotation angle, 0, 90, 180, 270
     */
    public static int getRotation(@NonNull Context context) {
        switch (Contextx.windowManager(context).getDefaultDisplay().getRotation()) {
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

    /**
     * Get the screen rotation angle, 0, 90, 180, 270
     */
    public static int getRotation(@NonNull Fragment fragment) {
        return getRotation(fragment.requireContext());
    }

    /**
     * Get the screen rotation angle, 0, 90, 180, 270
     */
    public static int getRotation(@NonNull final android.app.Fragment fragment) {
        return getRotation(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NonNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    /**
     * Get the screen rotation angle, 0, 90, 180, 270
     */
    public static int getRotation(@NonNull View view) {
        return getRotation(view.getContext());
    }


    /**
     * Return true if the current screen orientation is portrait
     */
    public static boolean isOrientationPortrait(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * Return true if the current screen orientation is portrait
     */
    public static boolean isOrientationPortrait(@NonNull Fragment fragment) {
        return isOrientationPortrait(fragment.requireContext());
    }

    /**
     * Return true if the current screen orientation is portrait
     */
    public static boolean isOrientationPortrait(@NonNull final android.app.Fragment fragment) {
        return isOrientationPortrait(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NonNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    /**
     * Return true if the current screen orientation is portrait
     */
    public static boolean isOrientationPortrait(@NonNull View view) {
        return isOrientationPortrait(view.getContext());
    }


    /**
     * Return true if the current screen orientation is landscape
     */
    public static boolean isOrientationLandscape(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * Return true if the current screen orientation is landscape
     */
    public static boolean isOrientationLandscape(@NonNull Fragment fragment) {
        return isOrientationLandscape(fragment.requireContext());
    }

    /**
     * Return true if the current screen orientation is landscape
     */
    public static boolean isOrientationLandscape(@NonNull final android.app.Fragment fragment) {
        return isOrientationLandscape(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NonNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    /**
     * Return true if the current screen orientation is landscape
     */
    public static boolean isOrientationLandscape(@NonNull View view) {
        return isOrientationLandscape(view.getContext());
    }


    /**
     * Return true if the current screen orientation is undefined
     */
    public static boolean isOrientationUndefined(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_UNDEFINED;
    }

    /**
     * Return true if the current screen orientation is undefined
     */
    public static boolean isOrientationUndefined(@NonNull Fragment fragment) {
        return isOrientationUndefined(fragment.requireContext());
    }

    /**
     * Return true if the current screen orientation is undefined
     */
    public static boolean isOrientationUndefined(@NonNull final android.app.Fragment fragment) {
        return isOrientationUndefined(Premisex.checkNotNull(fragment.getActivity(), new LazyValue<String>() {
            @NonNull
            @Override
            public String get() {
                return "Fragment " + fragment + " not attached to a context.";
            }
        }));
    }

    /**
     * Return true if the current screen orientation is undefined
     */
    public static boolean isOrientationUndefined(@NonNull View view) {
        return isOrientationUndefined(view.getContext());
    }


    private static int getInternalDimensionSize(@NonNull Resources res, @NonNull String resName) {
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
    public static int getStatusBarHeight(@NonNull Context context) {
        return getInternalDimensionSize(context.getResources(), "status_bar_height");
    }


    /**
     * Whether you have a navigation bar
     */
    public static boolean hasNavigationBar(@NonNull Context context) {
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
    public static int getNavigationBarHeight(@NonNull Context context) {
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
    public static int getNavigationBarWidth(@NonNull Context context) {
        if (hasNavigationBar(context)) {
            return getInternalDimensionSize(context.getResources(), "navigation_bar_width");
        } else {
            return 0;
        }
    }

//    /**
//     * Get the height of the navigation bar from default display
//     */
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public static int getNavigationBarHeightFromDisplay(@NonNull Context context) {
//        Display display = Contextx.windowManager(context).getDefaultDisplay();
//
//        // Get the available sizes for the APP
//        Point appSize = new Point();
//        display.getSize(appSize);
//
//        // Get the size of the screen
//        Point logicalSize = new Point();
//        display.getRealSize(logicalSize);
//
//        return logicalSize.y - appSize.y;
//    }
}
