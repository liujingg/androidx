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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.view

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View
import me.panpf.androidx.view.Displayx


/*
 * Extension method related to screen display
 */


inline fun Context.getScreenSize(): Point = Displayx.getScreenSize(this)

inline fun Context.getScreenWidth(): Int = Displayx.getScreenWidth(this)

inline fun Context.getScreenHeight(): Int = Displayx.getScreenHeight(this)


inline fun Context.getDisplayMetrics(): DisplayMetrics = Displayx.getMetrics(this)

inline fun Context.getDisplayDensity(): Float = Displayx.getDensity(this)

inline fun Context.getDisplayDensityDpi(): Int = Displayx.getDensityDpi(this)


inline fun Context.getDisplayRotation(): Int = Displayx.getRotation(this)

inline fun android.support.v4.app.Fragment.getDisplayRotation(): Int = Displayx.getRotation(this)

inline fun android.app.Fragment.getDisplayRotation(): Int = Displayx.getRotation(this)

inline fun View.getDisplayRotation(): Int = Displayx.getRotation(this)


inline fun Context.isOrientationPortrait(): Boolean = Displayx.isOrientationPortrait(this)

inline fun android.app.Fragment.isOrientationPortrait(): Boolean = Displayx.isOrientationPortrait(this)

inline fun android.support.v4.app.Fragment.isOrientationPortrait(): Boolean = Displayx.isOrientationPortrait(this)

inline fun View.isOrientationPortrait(): Boolean = Displayx.isOrientationPortrait(this)


inline fun Context.isOrientationLandscape(): Boolean = Displayx.isOrientationLandscape(this)

inline fun android.app.Fragment.isOrientationLandscape(): Boolean = Displayx.isOrientationLandscape(this)

inline fun android.support.v4.app.Fragment.isOrientationLandscape(): Boolean = Displayx.isOrientationLandscape(this)

inline fun View.isOrientationLandscape(): Boolean = Displayx.isOrientationLandscape(this)


inline fun Context.isOrientationUndefined(): Boolean = Displayx.isOrientationUndefined(this)

inline fun android.app.Fragment.isOrientationUndefined(): Boolean = Displayx.isOrientationUndefined(this)

inline fun android.support.v4.app.Fragment.isOrientationUndefined(): Boolean = Displayx.isOrientationUndefined(this)

inline fun View.isOrientationUndefined(): Boolean = Displayx.isOrientationUndefined(this)


/**
 * Get the height of the system status bar.
 *
 * @return The height of the status bar (in pixels).
 */
inline fun Context.getStatusBarHeight(): Int = Displayx.getStatusBarHeight(this)


/**
 * Whether you have a navigation bar
 */
inline fun Context.hasNavigationBar(): Boolean = Displayx.hasNavigationBar(this)

/**
 * Get the height of the navigation bar
 */
inline fun Context.getNavigationBarHeight(): Int = Displayx.getNavigationBarHeight(this)

/**
 * Get the width of the navigation bar
 */
inline fun Context.getNavigationBarWidth(): Int = Displayx.getNavigationBarWidth(this)