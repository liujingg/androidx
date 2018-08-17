@file:Suppress("unused")

package me.panpf.androidx.app

import android.app.Activity
import android.app.ActivityOptions
import android.os.Build

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
    try {
        val method = Activity::class.java.getDeclaredMethod("convertFromTranslucent")
        method.isAccessible = true
        method.invoke(this)
    } catch (t: Throwable) {
        t.printStackTrace()
    }
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
            val getActivityOptions = Activity::class.java.getDeclaredMethod("getActivityOptions")
            getActivityOptions.isAccessible = true
            val options = getActivityOptions.invoke(this)

            val classes = Activity::class.java.declaredClasses
            var translucentConversionListenerClazz: Class<*>? = null
            for (clazz in classes) {
                if (clazz.simpleName.contains("TranslucentConversionListener")) {
                    translucentConversionListenerClazz = clazz
                }
            }
            val convertToTranslucent = Activity::class.java.getDeclaredMethod("convertToTranslucent",
                    translucentConversionListenerClazz, ActivityOptions::class.java)
            convertToTranslucent.isAccessible = true
            convertToTranslucent.invoke(this, null, options)
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
            val method = Activity::class.java.getDeclaredMethod("convertToTranslucent",
                    translucentConversionListenerClazz)
            method.isAccessible = true
            method.invoke(this, arrayOf<Any?>(null))
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}