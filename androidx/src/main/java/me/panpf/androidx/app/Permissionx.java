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

package me.panpf.androidx.app;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import me.panpf.javax.collections.Arrayx;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.util.Predicate;

/**
 * Permission related tool methods
 */
public class Permissionx {

    private Permissionx() {
    }

    /**
     * Return true if all specified permissions have been granted, false otherwise
     */
    public static boolean isGrantPermissions(@NonNull Context context, @NonNull String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Filter all denied permissions
     */
    public static String[] filterDeniedPermissions(@NonNull final Context context, @NonNull String... permissions) {
        return Collectionx.filter(Arrayx.toList(permissions), new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String permission) {
                return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
            }
        }).toArray(new String[0]);
    }
}
