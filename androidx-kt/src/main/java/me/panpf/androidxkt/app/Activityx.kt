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

@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION")

package me.panpf.androidxkt.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
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


inline fun Activity.appContext(): Context = Activityx.appContext(this)


/* ************************************* start ***************************************** */


/**
 * Test if you can start Activity
 */
inline fun Context.canStartActivity(intent: Intent): Boolean = Activityx.canStart(this, intent)


/**
 * Start the activity of the specified Class
 */
inline fun View.startActivity(intent: Intent) = Activityx.start(this, intent)


/**
 * Start the activity of the specified Class
 */
inline fun Context.startActivity(clazz: Class<out Activity>, args: Bundle?) = Activityx.start(this, clazz, args)

/**
 * Start the activity of the specified Class
 */
inline fun Context.startActivity(clazz: Class<out Activity>) = Activityx.start(this, clazz)

/**
 * Start the activity of the specified Class
 */
inline fun android.support.v4.app.Fragment.startActivity(clazz: Class<out Activity>, args: Bundle?) = Activityx.start(this, clazz, args)

/**
 * Start the activity of the specified Class
 */
inline fun android.support.v4.app.Fragment.startActivity(clazz: Class<out Activity>) = Activityx.start(this, clazz)

/**
 * Start the activity of the specified Class
 */
inline fun android.app.Fragment.startActivity(clazz: Class<out Activity>, args: Bundle?) = Activityx.start(this, clazz, args)

/**
 * Start the activity of the specified Class
 */
inline fun android.app.Fragment.startActivity(clazz: Class<out Activity>) = Activityx.start(this, clazz)

/**
 * Start the activity of the specified Class
 */
inline fun View.startActivity(clazz: Class<out Activity>, args: Bundle?) = Activityx.start(this, clazz, args)

/**
 * Start the activity of the specified Class
 */
inline fun View.startActivity(clazz: Class<out Activity>) = Activityx.start(this, clazz)


/* ************************************* safeStart ***************************************** */


/**
 * Safely launch an Activity, catch ActivityNotFoundException and return false
 */
inline fun Context.safeStartActivity(intent: Intent): Boolean = Activityx.safeStart(this, intent)

/**
 * Safely launch an Activity, catch ActivityNotFoundException and return false
 */
inline fun android.support.v4.app.Fragment.safeStartActivity(intent: Intent): Boolean = Activityx.safeStart(this, intent)

/**
 * Safely launch an Activity, catch ActivityNotFoundException and return false
 */
inline fun android.app.Fragment.safeStartActivity(intent: Intent): Boolean = Activityx.safeStart(this, intent)

/**
 * Safely launch an Activity, catch ActivityNotFoundException and return false
 */
inline fun View.safeStartActivity(intent: Intent): Boolean = Activityx.safeStart(this, intent)


/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun Context.safeStartActivity(clazz: Class<out Activity>, args: Bundle?): Boolean = Activityx.safeStart(this, clazz, args)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun Context.safeStartActivity(clazz: Class<out Activity>): Boolean = Activityx.safeStart(this, clazz)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun android.support.v4.app.Fragment.safeStartActivity(clazz: Class<out Activity>, args: Bundle?): Boolean = Activityx.safeStart(this, clazz, args)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun android.support.v4.app.Fragment.safeStartActivity(clazz: Class<out Activity>): Boolean = Activityx.safeStart(this, clazz)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun android.app.Fragment.safeStartActivity(clazz: Class<out Activity>, args: Bundle?): Boolean = Activityx.safeStart(this, clazz, args)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun android.app.Fragment.safeStartActivity(clazz: Class<out Activity>): Boolean = Activityx.safeStart(this, clazz)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun View.safeStartActivity(clazz: Class<out Activity>, args: Bundle?): Boolean = Activityx.safeStart(this, clazz, args)

/**
 * Safely launch the activity of the specified Class, catch ActivityNotFoundException and return false
 */
inline fun View.safeStartActivity(clazz: Class<out Activity>): Boolean = Activityx.safeStart(this, clazz)