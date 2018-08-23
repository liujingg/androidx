@file:Suppress("unused")

package me.panpf.androidx.os

import android.app.ActivityManager
import android.content.Context

/*
 * Process related extension methods or properties
 */


/**
 * Is in the main process?
 */
fun Context.isMainProcess(): Boolean = packageName == getInProcessName()

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