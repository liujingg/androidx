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

package me.panpf.androidxkt.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.view.Surface
import android.view.ViewConfiguration

/**
 * Get the current window direction
 */
fun Activity.getDisplayRotation(): Int {
    return when (this.windowManager.defaultDisplay.rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        Surface.ROTATION_270 -> 270
        else -> 0
    }
}

/**
 * Return true if the current direction is landscape
 */
fun Context.isOrientationLandscape(): Boolean {
    return this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

/**
 * Return true if the current direction is portrait
 */
fun Context.isOrientationPortrait(): Boolean {
    return this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}


private object NavBarOverrideHolder {
    internal var sNavBarOverride: String? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                val m = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String::class.java)
                m.isAccessible = true
                sNavBarOverride = m.invoke(null, "qemu.hw.mainkeys").toString()
            } catch (e: Throwable) {
                sNavBarOverride = null
            }
        }
    }
}


private fun getInternalDimensionSize(res: Resources, resName: String): Int {
    var result = 0
    val resourceId = res.getIdentifier(resName, "dimen", "android")
    if (resourceId > 0) {
        result = res.getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * 是否拥有导航条
 */
fun Context.hasNavigationBar(): Boolean {
    val res = this.resources
    val resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android")
    return if (resourceId != 0) {
        var hasNav = res.getBoolean(resourceId)
        // check override flag (see static block)
        if ("1" == NavBarOverrideHolder.sNavBarOverride) {
            hasNav = false
        } else if ("0" == NavBarOverrideHolder.sNavBarOverride) {
            hasNav = true
        }
        hasNav
    } else { // fallback
        !ViewConfiguration.get(this).hasPermanentMenuKey()
    }
}


/**
 * Get the height of the system status bar.
 *
 * @return The height of the status bar (in pixels).
 */
fun Context.getStatusBarHeight(): Int {
    return getInternalDimensionSize(this.resources, "status_bar_height")
}

/**
 * Get the height of the navigation bar
 */
fun Context.getNavigationBarHeight(): Int {
    val res = this.resources
    return if (this.hasNavigationBar()) {
        if (res.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getInternalDimensionSize(res, "navigation_bar_height")
        } else {
            getInternalDimensionSize(res, "navigation_bar_height_landscape")
        }
    } else 0
}

/**
 * Get the width of the navigation bar
 */
fun Context.getNavigationBarWidth(): Int {
    return if (this.hasNavigationBar()) {
        getInternalDimensionSize(this.resources, "navigation_bar_width")
    } else {
        0
    }
}
