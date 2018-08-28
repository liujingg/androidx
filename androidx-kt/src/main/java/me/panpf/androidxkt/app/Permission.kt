@file:Suppress("unused")

package me.panpf.androidxkt.app

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/*
 * Permission related extension methods or properties
 */

/**
 * Return true if specified permissions have been granted, false otherwise
 */
fun Context.isGrantPermission(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

/**
 * Return true if all specified permissions have been granted, false otherwise
 */
fun Context.isGrantPermissions(vararg permissions: String): Boolean {
    return permissions.asSequence()
            .map { ActivityCompat.checkSelfPermission(this, it) }
            .find { it == PackageManager.PERMISSION_DENIED } == null
}