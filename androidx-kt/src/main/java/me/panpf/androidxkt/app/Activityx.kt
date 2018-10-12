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

package me.panpf.androidxkt.app

import android.app.Activity
import android.support.v4.app.FragmentActivity
import me.panpf.androidx.app.Activityx

/**
 * Return true if the activity has been destroyed
 */
inline fun Activity.isDestroyedCompat(): Boolean = Activityx.isDestroyedCompat(this)

/**
 * Return true if the activity has been destroyed
 */
inline fun FragmentActivity.isDestroyedCompat(): Boolean = Activityx.isDestroyedCompat(this)

/**
 * Convert a translucent themed Activity
 * [android.R.attr.windowIsTranslucent] to a fullscreen opaque
 * Activity.
 *
 * Call this whenever the background of a translucent Activity has changed
 * to become opaque. Doing so will allow the [android.view.Surface] of
 * the Activity behind to be released.
 *
 * This call has no effect on non-translucent activities or on activities
 * with the [android.R.attr.windowIsFloating] attribute.
 */
inline fun Activity.convertFromTranslucentCompat(): Boolean = Activityx.convertFromTranslucent(this)

/**
 * Convert a translucent themed Activity
 * [android.R.attr.windowIsTranslucent] back from opaque to
 * translucent following a call to
 * [.convertActivityFromTranslucent] .
 *
 *
 * Calling this allows the Activity behind this one to be seen again. Once
 * all such Activities have been redrawn
 *
 *
 * This call has no effect on non-translucent activities or on activities
 * with the [android.R.attr.windowIsFloating] attribute.
 */
inline fun Activity.convertToTranslucentCompat(): Boolean = Activityx.convertToTranslucent(this)

/**
 * If the own or parent activity implements the specified [clazz], it returns its implementation.
 */
inline fun <T> Activity.getImplWithParent(clazz: Class<T>): T? = Activityx.getImplWithParent(this, clazz)