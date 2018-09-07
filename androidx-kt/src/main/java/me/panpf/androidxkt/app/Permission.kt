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

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/*
 * Permission related extension methods or properties
 */

/**
 * Return true if all specified permissions have been granted, false otherwise
 */
fun Context.isGrantPermissions(vararg permissions: String): Boolean {
    return permissions.asSequence()
            .map { ActivityCompat.checkSelfPermission(this, it) }
            .find { it == PackageManager.PERMISSION_DENIED } == null
}

/**
 * Filter all denied permissions
 */
fun Context.filterDeniedPermissions(vararg permissions: String): Array<String> {
    return permissions.filter { permission ->
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED
    }.toTypedArray()
}