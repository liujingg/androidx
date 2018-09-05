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

package me.panpf.androidxkt.os

import android.app.ActivityManager
import android.content.Context

/*
 * Process related extension methods or properties
 */


/**
 * Get the name of the current process
 */
fun Context.getInProcessName(): String? {
    val myPid = android.os.Process.myPid()
    val activityManager = (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
    return activityManager.runningAppProcesses?.find { it.pid == myPid }?.processName
}

/**
 * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
 */
fun Context.getInProcessNameSuffix(): String? {
    val processName = getInProcessName() ?: return null
    val lastIndex = processName.lastIndexOf(packageName)
    return if (lastIndex != -1) processName.substring(lastIndex + packageName.length) else null
}

/**
 * Is in the main process?
 */
fun Context.isMainProcess(): Boolean = packageName == getInProcessName()
