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

@file:Suppress("MemberVisibilityCanBePrivate")

package me.panpf.androidxkt.os.storage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import me.panpf.javaxkt.lang.callMethod
import java.io.File
import java.io.IOException
import java.util.*

class StorageManagerCompat(context: Context) {

    private val manager: StorageManager = (context.getSystemService(Context.STORAGE_SERVICE)
            ?: throw IllegalStateException("StorageManager not found")) as StorageManager

    fun getVolumeList(): Array<StorageVolumeCompat> {
        return try {
            @Suppress("UNCHECKED_CAST")
            val storageVolumes = manager.callMethod("getVolumeList") as Array<StorageVolume>?
            storageVolumes?.map { StorageVolumeCompat(it) }?.toTypedArray()
                    ?: LinkedList<StorageVolumeCompat>().toTypedArray()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
            LinkedList<StorageVolumeCompat>().toTypedArray()
        }
    }

    fun getStorageVolumes(): List<StorageVolumeCompat> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            manager.storageVolumes.map { StorageVolumeCompat(it) }
        } else {
            getVolumeList().toList()
        }
    }

    fun getVolumePaths(): Array<String> {
        val volumePaths = LinkedList<String>()
        for (volumeCompat in getStorageVolumes()) {
            val path = volumeCompat.path
            if (path != null) {
                volumePaths.add(path)
            }
        }
        return volumePaths.toTypedArray()
    }

    /**
     * Gets the state of a volume via its mount point.
     *
     * @return one of [Environment.MEDIA_UNKNOWN], [Environment.MEDIA_REMOVED],
     * [Environment.MEDIA_UNMOUNTED], [Environment.MEDIA_CHECKING],
     * [Environment.MEDIA_NOFS], [Environment.MEDIA_MOUNTED],
     * [Environment.MEDIA_MOUNTED_READ_ONLY], [Environment.MEDIA_SHARED],
     * [Environment.MEDIA_BAD_REMOVAL], or [Environment.MEDIA_UNMOUNTABLE].
     */
    fun getVolumeState(mountPoint: String): String {
        var result: Any? = null
        try {
            result = manager.callMethod("getVolumeState", mountPoint)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        }

        return result?.toString() ?: "unknown"
    }

    /**
     * Return the [StorageVolume] that contains the given file, or `null` if none.
     */
    @SuppressLint("NewApi")
    fun getStorageVolume(file: File): StorageVolumeCompat? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val storageVolume = manager.getStorageVolume(file)
            return if (storageVolume != null) StorageVolumeCompat(storageVolume) else null
        } else {
            var volumeCompat: StorageVolumeCompat? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                var storageVolume: StorageVolume? = null
                try {
                    storageVolume = manager.callMethod("getStorageVolume", file) as StorageVolume?
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                }

                volumeCompat = if (storageVolume != null) StorageVolumeCompat(storageVolume) else null
            }
            if (volumeCompat == null) {
                volumeCompat = getStorageVolume(getVolumeList(), file)
            }
            return volumeCompat
        }
    }

    private fun getStorageVolume(volumes: Array<StorageVolumeCompat>, file: File?): StorageVolumeCompat? {
        if (file == null) return null
        val canonicalFile: File
        try {
            canonicalFile = file.canonicalFile
        } catch (ignored: IOException) {
            return null
        }

        // 在 4.1 版本内置 SD卡路径是 /mnt/sdcard 扩展 SD 卡路径是 /mnt/sdcard/external_sd，
        // 由于 volumes 始终 /mnt/sdcard 在第一位，如果 file 是 /mnt/sdcard/external_sd/download 将始终匹配到 /mnt/sdcard
        // 因此这里将 volumes 翻转一下就可解决这个问题
        for (volume in volumes.reversed()) {
            val volumeFile = volume.pathFile
            val canonicalVolumeFile: File?
            try {
                canonicalVolumeFile = volumeFile?.canonicalFile
            } catch (ignored: IOException) {
                continue
            }

            if (canonicalVolumeFile != null && contains(canonicalVolumeFile, canonicalFile)) {
                return volume
            }
        }
        return null
    }

    /**
     * Test if a file lives under the given directory, either as a direct child
     * or a distant grandchild.
     *
     *
     * Both files *must* have been resolved using
     * [File.getCanonicalFile] to avoid symlink or path traversal
     * attacks.
     */
    private fun contains(dir: File?, file: File?): Boolean {
        if (dir == null || file == null) return false

        var dirPath = dir.absolutePath
        val filePath = file.absolutePath

        if (dirPath == filePath) {
            return true
        }

        if (!dirPath.endsWith("/")) {
            dirPath += "/"
        }
        return filePath.startsWith(dirPath)
    }
}
