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

package me.panpf.androidxkt

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import java.io.File

private object MainHandlerHolder {
    val mainHandler = Handler(Looper.getMainLooper())
}

/**
 * Get the main thread Handler
 */
fun getMainHandler(): Handler = MainHandlerHolder.mainHandler

/**
 * Execute the specified code block in the main thread
 */
fun runInUI(block: () -> Unit) {
    if (isMainThread()) {
        block()
    } else {
        getMainHandler().post { block() }
    }
}

/**
 * Return true if ROOT is already
 */
fun isRooted(): Boolean {
    return (File("/system/bin/su").exists() || File("/system/xbin/su").exists()
            || File("/sbin/su").exists() || File("/su").exists())
}


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


/**
 * Is it the main thread?
 */
fun isMainThread(): Boolean {
    return Looper.getMainLooper().thread === Thread.currentThread()
}

fun isAtLeastI() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
fun isAtLeast14() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
fun isAtLeast4_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH

fun isAtLeastIMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
fun isAtLeast15() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
fun isAtLeast4_0_3() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1

fun isAtLeastJ() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
fun isAtLeast16() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
fun isAtLeast4_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN

fun isAtLeastJMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
fun isAtLeast17() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
fun isAtLeast4_2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1

fun isAtLeastJMR2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
fun isAtLeast18() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
fun isAtLeast4_3() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2

fun isAtLeastK() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
fun isAtLeast19() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
fun isAtLeast4_4() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

fun isAtLeastKW() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH
fun isAtLeast20() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH
fun isAtLeast4_4_W() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH

fun isAtLeastL() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isAtLeast21() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
fun isAtLeast5_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isAtLeastLMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun isAtLeast22() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun isAtLeast5_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1

fun isAtLeastM() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isAtLeast23() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isAtLeast6_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isAtLeastN() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isAtLeast24() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isAtLeast7_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isAtLeastNMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isAtLeast25() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isAtLeast7_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

fun isAtLeastO() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isAtLeast26() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isAtLeast8_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isAtLeastOMR1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun isAtLeast27() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun isAtLeast8_1() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

fun isAtLeastP() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 + 1
fun isAtLeast28() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 + 1
fun isAtLeast9_0() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1 + 1

fun getSdkVersionName(sdkVersion: Int): String {
    return when (sdkVersion) {
        1 -> "1.0"
        2 -> "1.1"
        3 -> "1.5"
        4 -> "1.6"
        5 -> "2.0"
        6 -> "2.0.1"
        7 -> "2.1"
        8 -> "2.2"
        9 -> "2.3"
        10 -> "2.3.3"
        11 -> "3.0"
        12 -> "3.1"
        13 -> "3.2"
        14 -> "4.0"
        15 -> "4.0.3"
        16 -> "4.1"
        17 -> "4.2"
        18 -> "4.3"
        19 -> "4.4"
        20 -> "4.4W"
        21 -> "5.0"
        22 -> "5.1"
        23 -> "6.0"
        24 -> "7.0"
        25 -> "7.1.1"
        26 -> "8.0"
        27 -> "8.1"
        28 -> "9.0"
        else -> "Unknown($sdkVersion)"
    }
}