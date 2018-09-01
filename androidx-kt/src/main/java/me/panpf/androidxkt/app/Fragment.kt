package me.panpf.androidxkt.app

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import me.panpf.androidxkt.content.canStartActivity
import me.panpf.androidxkt.content.safeStartActivity

/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
fun <T> Fragment.getImplWithParent(clazz: Class<T>): T? {
    var parent: Fragment? = this
    while (parent != null) {
        if (clazz.isAssignableFrom(parent.javaClass)) {
            @Suppress("UNCHECKED_CAST")
            return clazz as T
        } else {
            parent = parent.parentFragment
        }
    }
    var parentActivity: Activity? = this.activity
    while (parentActivity != null) {
        if (clazz.isAssignableFrom(parentActivity.javaClass)) {
            @Suppress("UNCHECKED_CAST")
            return clazz as T
        } else {
            parentActivity = parentActivity.parent
        }
    }
    return null
}

/**
 * Test if you can start Activity
 */
fun Fragment.canStartActivity(intent: Intent): Boolean {
    val activity = this.activity
            ?: throw IllegalStateException("Fragment $this not attached to Activity")

    return activity.canStartActivity(intent)
}

/**
 * Safely launch an Activity, catch ActivityNotFoundException and return false
 */
fun Fragment.safeStartActivity(intent: Intent): Boolean {
    val activity = this.activity
            ?: throw IllegalStateException("Fragment $this not attached to Activity")

    return activity.safeStartActivity(intent)
}