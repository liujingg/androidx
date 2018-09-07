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
import android.content.Intent
import android.os.Bundle
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

/**
 * Instantiate a Fragment and set arguments
 */
fun Class<out Fragment>.instance(arguments: Bundle? = null): Fragment {
    val fragment: Fragment

    try {
        fragment = this.newInstance()
    } catch (e: InstantiationException) {
        throw IllegalArgumentException(e)
    } catch (e: IllegalAccessException) {
        throw IllegalArgumentException(e)
    }

    if (arguments != null) {
        fragment.arguments = arguments
    }
    return fragment
}