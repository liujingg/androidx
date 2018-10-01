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
import android.os.Build;
import android.view.ViewConfiguration;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

@SuppressWarnings("WeakerAccess")
public class Windowx {

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


    private static int getInternalDimensionSize(@NotNull Resources res, @NotNull String resName) {
        int result = 0;
        int resourceId = res.getIdentifier(resName, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 是否拥有导航条
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
     * Get the height of the system status bar.
     *
     * @return The height of the status bar (in pixels).
     */
    public static int getStatusBarHeight(@NotNull Context context) {
        return getInternalDimensionSize(context.getResources(), "status_bar_height");
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
