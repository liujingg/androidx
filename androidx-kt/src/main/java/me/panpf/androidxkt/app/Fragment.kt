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
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
fun <T> android.support.v4.app.Fragment.getImplWithParent(clazz: Class<T>): T? {
    var parent: android.support.v4.app.Fragment? = this
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
 * Instantiate a Fragment and set arguments
 */
fun Class<out android.support.v4.app.Fragment>.instance(arguments: Bundle? = null): Fragment {
    val fragment: android.support.v4.app.Fragment

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

/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
fun <T> android.app.Fragment.getImplWithParent(clazz: Class<T>): T? {
    var parent: android.app.Fragment? = this
    while (parent != null) {
        if (clazz.isAssignableFrom(parent.javaClass)) {
            @Suppress("UNCHECKED_CAST")
            return clazz as T
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                parent = parent.parentFragment
            } else {
                parent = null
            }
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
 * Instantiate a Fragment and set arguments
 */
fun Class<out android.app.Fragment>.instanceOrigin(arguments: Bundle? = null): android.app.Fragment {
    val fragment: android.app.Fragment

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