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

package me.panpf.androidxkt.os.storage

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.storage.StorageVolume
import me.panpf.javaxkt.lang.callMethod

import java.io.File

class StorageVolumeCompat(private val storageVolume: StorageVolume) {

    /**
     * Returns the mount path for the volume.
     *
     * @return the mount path
     */
    val path: String?
        get() {
            return try {
                storageVolume.callMethod("getPath") as String?
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
                null
            }
        }

    val pathFile: File?
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    return storageVolume.callMethod("getPathFile") as File?
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                }
            }

            return path?.let { File(it) }
        }

    /**
     * Returns true if the volume is the primary shared/external storage, which is the volume
     * backed by [Environment.getExternalStorageDirectory].
     */
    val isPrimary: Boolean
        get() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return storageVolume.isPrimary
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    try {
                        return storageVolume.callMethod("isPrimary") as Boolean
                    } catch (e: NoSuchMethodException) {
                        e.printStackTrace()
                    }
                }

                return path?.let { it == Environment.getExternalStorageDirectory().path } ?: false
            }
        }

    /**
     * Returns true if the volume is removable.
     *
     * @return is removable
     */
    val isRemovable: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            storageVolume.isRemovable
        } else {
            try {
                storageVolume.callMethod("isRemovable") as Boolean
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
                false
            }
        }

    /**
     * Returns true if the volume is emulated.
     *
     * @return is removable
     */
    val isEmulated: Boolean
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            storageVolume.isEmulated
        } else {
            try {
                storageVolume.callMethod("isEmulated") as Boolean
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
                false
            }
        }

    /**
     * Returns maximum file size for the volume, or zero if it is unbounded.
     *
     * @return maximum file size
     */
    val maxFileSize: Long
        get() {
            return try {
                storageVolume.callMethod("getMaxFileSize") as Long
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
                -1
            }
        }

    /**
     * Returns true if this volume can be shared via USB mass storage.
     *
     * @return whether mass storage is allowed
     */
    fun allowMassStorage(): Boolean {
        return try {
            storageVolume.callMethod("allowMassStorage") as Boolean
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Returns the current state of the volume.
     *
     * @return one of [Environment.MEDIA_UNKNOWN], [Environment.MEDIA_REMOVED],
     * [Environment.MEDIA_UNMOUNTED], [Environment.MEDIA_CHECKING],
     * [Environment.MEDIA_NOFS], [Environment.MEDIA_MOUNTED],
     * [Environment.MEDIA_MOUNTED_READ_ONLY], [Environment.MEDIA_SHARED],
     * [Environment.MEDIA_BAD_REMOVAL], or [Environment.MEDIA_UNMOUNTABLE].
     */
    fun getState(context: Context): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return storageVolume.state
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    return storageVolume.callMethod("getState")?.toString() ?: "unknown"
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                }
            }

            return path?.let { StorageManagerCompat(context).getVolumeState(it) } ?: "unknown"
        }
    }

    override fun equals(other: Any?): Boolean {
        return storageVolume == other
    }

    override fun hashCode(): Int {
        return storageVolume.hashCode()
    }

    override fun toString(): String {
        return storageVolume.toString()
    }
}
