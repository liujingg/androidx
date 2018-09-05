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

package me.panpf.androidxkt.app

import android.app.Activity
import android.app.ActivityOptions
import android.os.Build
import me.panpf.javaxkt.lang.callMethod

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
fun Activity.convertActivityFromTranslucent() {
    this.callMethod("convertFromTranslucent")
}

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
fun Activity.convertActivityToTranslucent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        try {
            val options = this.callMethod("getActivityOptions")

            val classes = Activity::class.java.declaredClasses
            var translucentConversionListenerClazz: Class<*>? = null
            for (clazz in classes) {
                if (clazz.simpleName.contains("TranslucentConversionListener")) {
                    translucentConversionListenerClazz = clazz
                }
            }
            val method = Activity::class.java.getDeclaredMethod("convertToTranslucent",
                    translucentConversionListenerClazz, ActivityOptions::class.java)
            this.callMethod(method, arrayOf(null, options))
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    } else {
        try {
            val classes = Activity::class.java.declaredClasses
            var translucentConversionListenerClazz: Class<*>? = null
            for (clazz in classes) {
                if (clazz.simpleName.contains("TranslucentConversionListener")) {
                    translucentConversionListenerClazz = clazz
                }
            }
            val method = Activity::class.java.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz)
            this.callMethod(method, arrayOf<Any?>(null))
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}

/**
 * If the own or parent activity implements the specified [clazz], it returns its implementation.
 */
fun <T> Activity.getImplWithParent(clazz: Class<T>): T? {
    var parent: Activity? = this
    while (parent != null) {
        if (clazz.isAssignableFrom(parent.javaClass)) {
            @Suppress("UNCHECKED_CAST")
            return clazz as T
        } else {
            parent = parent.parent
        }
    }
    return null
}