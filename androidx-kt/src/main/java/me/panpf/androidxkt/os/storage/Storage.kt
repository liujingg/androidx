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
import android.os.StatFs
import android.support.annotation.WorkerThread
import me.panpf.androidxkt.os.availableBytesCompat
import me.panpf.androidxkt.os.freeBytesCompat
import me.panpf.androidxkt.os.totalBytesCompat
import me.panpf.javaxkt.io.cleanDir
import me.panpf.javaxkt.io.lengthRecursively
import me.panpf.javaxkt.io.mkdirsCheck
import java.io.File
import java.util.*

/*
 * Storage related extension methods or properties
 */

/* ******************************************* Bytes *******************************************/


/**
 * Get the number of free bytes of the given path
 */
fun File.getFreeBytes(): Long {
    return StatFs(this.path).freeBytesCompat
}

/**
 * Get the number of total bytes of the given path
 */
fun File.getTotalBytes(): Long {
    return StatFs(this.path).totalBytesCompat
}

/**
 * Get the number of available bytes of the given path
 */
fun File.getAvailableBytes(): Long {
    return StatFs(this.path).availableBytesCompat
}

/**
 * Get the number of free bytes for the primary shared/external storage media
 */
fun getExternalStorageFreeBytes(): Long {
    return getExternalStorageDirectory().getFreeBytes()
}

/**
 * Get the number of total bytes for the primary shared/external storage media
 */
fun getExternalStorageTotalBytes(): Long {
    return getExternalStorageDirectory().getTotalBytes()
}

/**
 * Get the number of available bytes for the primary shared/external storage media
 */
fun getExternalStorageAvailableBytes(): Long {
    return getExternalStorageDirectory().getAvailableBytes()
}


/* ******************************************* Volume *******************************************/

/**
 * Returns volume state for given path
 */
fun Context.getVolumeState(path: File): String {
    val volumeCompat = this.getStorageVolume(path)
    return volumeCompat?.getState(this) ?: "unknown"
}

/**
 * Returns true if the state of the volume at which the given path is mounted
 */
fun Context.isVolumeMounted(path: File): Boolean {
    return Environment.MEDIA_MOUNTED == this.getVolumeState(path)
}


/**
 * Return true if the volume of the given path is the primary volume
 */
fun Context.isPrimaryVolume(path: File): Boolean {
    val volumeCompat = this.getStorageVolume(path)
    return volumeCompat != null && volumeCompat.isPrimary
}

/**
 * Return true if the volume of the given path is the emulated volume
 */
fun Context.isVolumeEmulated(path: File): Boolean {
    val volumeCompat = this.getStorageVolume(path)
    return volumeCompat != null && volumeCompat.isEmulated
}

/**
 * Return true if the volume of the given path is the removable volume
 */
fun Context.isVolumeRemovable(path: File): Boolean {
    val volumeCompat = this.getStorageVolume(path)
    return volumeCompat != null && volumeCompat.isRemovable
}


/**
 * Returns list of path for all volumes.
 */
fun Context.getVolumePaths(): Array<String> {
    return StorageManagerCompat(this).getVolumePaths()
}

/**
 * Returns list of path for all mounted volumes.
 */
fun Context.getMountedVolumePaths(): Array<String> {
    return this.getVolumePaths().filter { this.isVolumeMounted(File(it)) }.toTypedArray()
}


/**
 * Returns list of files for all volumes.
 */
fun Context.getVolumeFiles(): Array<File> {
    return this.getVolumePaths().map { File(it) }.toTypedArray()
}

/**
 * Returns list of files for all mounted volumes.
 */
fun Context.getMountedVolumeFiles(): Array<File> {
    return this.getVolumeFiles().filter { this.isVolumeMounted(it) }.toTypedArray()
}


/**
 * Returns list of StorageVolume for all volumes.
 */
fun Context.getVolumeList(): Array<StorageVolumeCompat> {
    return StorageManagerCompat(this).getVolumeList()
}

/**
 * Returns list of StorageVolume for all mounted volumes.
 */
fun Context.getMountedVolumeList(): Array<StorageVolumeCompat> {
    return this.getVolumeList().filter { Environment.MEDIA_MOUNTED == it.getState(this) }.toTypedArray()
}


/**
 * Returns StorageVolume for path.
 */
fun Context.getStorageVolume(path: File): StorageVolumeCompat? {
    return StorageManagerCompat(this).getStorageVolume(path)
}


/* ******************************************* External Storage *******************************************/


/**
 * Returns the current state of the shared/external storage media at the
 * given path.
 *
 * @return one of [Environment.MEDIA_UNKNOWN], [Environment.MEDIA_REMOVED],
 * [Environment.MEDIA_UNMOUNTED], [Environment.MEDIA_CHECKING],
 * [Environment.MEDIA_NOFS], [Environment.MEDIA_MOUNTED],
 * [Environment.MEDIA_MOUNTED_READ_ONLY], [Environment.MEDIA_SHARED],
 * [Environment.MEDIA_BAD_REMOVAL], or [Environment.MEDIA_UNMOUNTABLE].
 * @see .getExternalStorageDirectory
 */
fun Context.getExternalStorageState(path: File): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Environment.getExternalStorageState(path)
    } else {
        this.getVolumeState(path)
    }
}

/**
 * Return true if the state of the given path is MOUNTED
 */
fun Context.isExternalStorageMounted(path: File): Boolean {
    return Environment.MEDIA_MOUNTED == this.getExternalStorageState(path)
}

/**
 * Returns the current state of the primary shared/external storage media.
 *
 * @return one of [Environment.MEDIA_UNKNOWN], [Environment.MEDIA_REMOVED],
 * [Environment.MEDIA_UNMOUNTED], [Environment.MEDIA_CHECKING],
 * [Environment.MEDIA_NOFS], [Environment.MEDIA_MOUNTED],
 * [Environment.MEDIA_MOUNTED_READ_ONLY], [Environment.MEDIA_SHARED],
 * [Environment.MEDIA_BAD_REMOVAL], or [Environment.MEDIA_UNMOUNTABLE].
 * @see .getExternalStorageDirectory
 */
fun getExternalStorageState(): String {
    return Environment.getExternalStorageState()
}

/**
 * Return true if the state of the primary shared/external storage media is MOUNTED
 */
fun isExternalStorageMounted(): Boolean {
    return Environment.MEDIA_MOUNTED == getExternalStorageState()
}

/**
 * Return true if the path is the primary volume
 */
fun Context.isPrimaryExternalStorage(path: File): Boolean {
    return this.isPrimaryVolume(path)
}

/**
 * Return true if the volume of the given path is emulated
 */
fun Context.isExternalStorageEmulated(path: File): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Environment.isExternalStorageEmulated(path)
    } else {
        val storageVolume = StorageManagerCompat(this).getStorageVolume(path)
        storageVolume != null && storageVolume.isEmulated
    }
}

/**
 * Return true if the primary shared/external storage media is emulated
 */
fun isExternalStorageEmulated(): Boolean {
    return Environment.isExternalStorageEmulated()
}

/**
 * Return true if the state of the given path is REMOVED
 */
fun Context.isExternalStorageRemovable(path: File): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Environment.isExternalStorageRemovable(path)
    } else {
        val storageVolume = StorageManagerCompat(this).getStorageVolume(path)
        storageVolume != null && storageVolume.isRemovable
    }
}

/**
 * Return true if the state of the primary shared/external storage media is REMOVED
 */
fun isExternalStorageRemovable(): Boolean {
    return Environment.isExternalStorageRemovable()
}


/**
 * Return the primary shared/external storage directory.
 */
fun getExternalStorageDirectory(): File {
    return Environment.getExternalStorageDirectory()
}

/**
 * Return if the primary shared/external storage directory is mounted, otherwise return null
 */
fun Context.getMountedExternalStorageDirectory(): File? {
    val externalStorageDirectory = Environment.getExternalStorageDirectory()
    return if (this.isExternalStorageMounted(externalStorageDirectory)) externalStorageDirectory else null
}


/**
 * Return the all shared/external storage directory.
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
fun Context.getExternalStorageDirectorys(ignorePrimary: Boolean): Array<File> {
    val dirs = LinkedList<File>()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val files = this.getExternalFilesDirs(null)
        if (files != null && files.isNotEmpty()) {
            val lowerCaseSuffix = "/Android/data/".toLowerCase()
            for (file in files) {
                val lowerCasePath = file.path.toLowerCase()
                val index = lowerCasePath.indexOf(lowerCaseSuffix)
                if (index != -1) {
                    dirs.add(File(file.path.substring(0, index)))
                }
            }
        }
    }

    if (dirs.isEmpty()) {
        val files = this.getVolumeFiles()
        if (files.isNotEmpty()) {
            dirs.addAll(files)
        }
    }

    val primaryExternalStorageDirectory = getExternalStorageDirectory()
    if (dirs.isEmpty()) {
        dirs.add(primaryExternalStorageDirectory)
    }

    return dirs.filter { !ignorePrimary || it.path != primaryExternalStorageDirectory.path }.toTypedArray()
}

/**
 * Returns the all shared/external storage directories that are mounted.
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
fun Context.getMountedExternalStorageDirectorys(ignorePrimary: Boolean): Array<File> {
    return this.getExternalStorageDirectorys(ignorePrimary).filter { this.isExternalStorageMounted(it) }.toTypedArray()
}


/**
 * Return the all shared/external storage directory.
 */
fun Context.getExternalStorageDirectorys(): Array<File> {
    return this.getExternalStorageDirectorys(false)
}

/**
 * Returns the all shared/external storage directories that are mounted.
 */
fun Context.getMountedExternalStorageDirectorys(): Array<File> {
    return this.getMountedExternalStorageDirectorys(false)
}


/**
 * Get the given path under all shared/external storage media
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
fun Context.getExternalStorageDirectorysWithPath(childPath: String, ignorePrimary: Boolean): Array<File> {
    return this.getExternalStorageDirectorys(ignorePrimary).map { File(it, childPath) }.toTypedArray()
}

/**
 * Get the given path under all mounted shared/external storage media
 *
 * @param ignorePrimary Ignore primary shared/external storage directory.
 */
fun Context.getMountedExternalStorageDirectorysWithPath(childPath: String, ignorePrimary: Boolean): Array<File> {
    return this.getMountedExternalStorageDirectorys(ignorePrimary).map { File(it, childPath) }.toTypedArray()
}


/**
 * Get the given path under all shared/external storage media
 */
fun Context.getExternalStorageDirectorysWithPath(childPath: String): Array<File> {
    return this.getExternalStorageDirectorysWithPath(childPath, false)
}

/**
 * Get the given path under all mounted shared/external storage media
 */
fun Context.getMountedExternalStorageDirectorysWithPath(childPath: String): Array<File> {
    return this.getMountedExternalStorageDirectorysWithPath(childPath, false)
}


/* ******************************************* App Cache Dir *******************************************/


/**
 * Get the app external cache directory
 */
fun Context.getAppExternalCacheDir(): File? {
    val file = this.externalCacheDir
    return if (file != null) {
        // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getExternalCacheDir() returns null
        file
    } else {
        val externalDir = this.getMountedExternalStorageDirectory()
        if (externalDir != null) File(externalDir, "Android/data/" + this.packageName + "/cache") else null
    }
}

/**
 * Get the external cache directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppExternalCacheDir(packageName: String): File? {
    val selfExternalCacheDir = this.getAppExternalCacheDir()
    return if (selfExternalCacheDir != null) File(selfExternalCacheDir.path.replace(this.packageName, packageName)) else null
}

/**
 * Get the all app external cache directory
 */
fun Context.getAppExternalCacheDirs(): Array<File> {
    var externalCacheDirs: Array<File>? = null
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
        externalCacheDirs = this.externalCacheDirs
    }
    return if (externalCacheDirs != null && externalCacheDirs.isNotEmpty()) {
        externalCacheDirs
    } else {
        this.getMountedExternalStorageDirectorys().map { File(it, "Android/data/" + this.packageName + "/cache") }.toTypedArray()
    }
}

/**
 * Get the all external cache directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppExternalCacheDirs(packageName: String): Array<File> {
    return this.getAppExternalCacheDirs().map { File(it.path.replace(this.packageName, packageName)) }.toTypedArray()
}


/**
 * Get the app internal cache directory
 */
fun Context.getAppInternalCacheDir(): File {
    return this.cacheDir
}

/**
 * Get the internal cache directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppInternalCacheDir(packageName: String): File {
    return File(this.getAppInternalCacheDir().path.replace(this.packageName, packageName))
}


/**
 * Get all app cache directories
 */
fun Context.getAppCacheDirs(): Array<File> {
    val fileList = LinkedList<File>()
    val externalCacheDirs = this.getAppExternalCacheDirs()
    Collections.addAll(fileList, *externalCacheDirs)
    fileList.add(this.getAppInternalCacheDir())
    return fileList.toTypedArray()
}

/**
 * Get all cache directories of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppCacheDirs(packageName: String): Array<File> {
    val fileList = LinkedList<File>()
    val externalCacheDirs = this.getAppExternalCacheDirs(packageName)
    Collections.addAll(fileList, *externalCacheDirs)
    fileList.add(this.getAppInternalCacheDir(packageName))
    return fileList.toTypedArray()
}


/**
 * Count the size of all APP cache directories
 */
@WorkerThread
fun Context.lengthAppCacheDirs(): Long {
    var sum: Long = 0
    for (file in this.getAppCacheDirs()) {
        sum += file.lengthRecursively()
    }
    return sum
}

/**
 * Count the size of all APP cache directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
fun Context.lengthAppCacheDirs(packageName: String): Long {
    var sum: Long = 0
    for (file in this.getAppCacheDirs(packageName)) {
        sum += file.lengthRecursively()
    }
    return sum
}

/**
 * Clean up all app cache directories
 */
@WorkerThread
fun Context.cleanAppCacheDirs() {
    for (file in this.getAppCacheDirs()) {
        file.cleanDir()
    }
}

/**
 * Clean up all cache directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
fun Context.cleanAppCacheDirs(packageName: String) {
    for (file in this.getAppCacheDirs(packageName)) {
        file.cleanDir()
    }
}


/* ******************************************* App Files Dir *******************************************/


/**
 * Get the app external files directory
 */
fun Context.getAppExternalFilesDir(): File? {
    val file = this.getExternalFilesDir(null)
    return if (file != null) {
        // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getExternalFilesDir() returns null
        file
    } else {
        val externalDir = this.getMountedExternalStorageDirectory()
        if (externalDir != null) File(externalDir, "Android/data/" + this.packageName + "/files") else null
    }
}

/**
 * Get the external files directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppExternalFilesDir(packageName: String): File? {
    val selfExternalFilesDir = this.getAppExternalFilesDir()
    return if (selfExternalFilesDir != null) File(selfExternalFilesDir.path.replace(this.packageName, packageName)) else null
}

/**
 * Get the all app external files directory
 */
fun Context.getAppExternalFilesDirs(): Array<File> {
    var externalFilesDirs: Array<File>? = null
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
        externalFilesDirs = this.getExternalFilesDirs(null)
    }
    return if (externalFilesDirs != null && externalFilesDirs.isNotEmpty()) {
        externalFilesDirs
    } else {
        this.getMountedExternalStorageDirectorys().map { File(it, "Android/data/" + this.packageName + "/files") }.toTypedArray()
    }
}

/**
 * Get the all external files directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppExternalFilesDirs(packageName: String): Array<File> {
    return this.getAppExternalFilesDirs().map { File(it.path.replace(this.packageName, packageName)) }.toTypedArray()
}


/**
 * Get the app internal files directory
 */
fun Context.getAppInternalFilesDir(): File {
    return this.filesDir
}

/**
 * Get the internal files directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppInternalFilesDir(packageName: String): File {
    return File(this.getAppInternalFilesDir().path.replace(this.packageName, packageName))
}


/**
 * Get all app files directories
 */
fun Context.getAppFilesDirs(): Array<File> {
    val fileList = LinkedList<File>()
    val externalFilesDirs = this.getAppExternalFilesDirs()
    Collections.addAll(fileList, *externalFilesDirs)
    fileList.add(this.getAppInternalFilesDir())
    return fileList.toTypedArray()
}

/**
 * Get all files directories of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppFilesDirs(packageName: String): Array<File> {
    val fileList = LinkedList<File>()
    val externalFilesDirs = this.getAppExternalFilesDirs(packageName)
    Collections.addAll(fileList, *externalFilesDirs)
    fileList.add(this.getAppInternalFilesDir(packageName))
    return fileList.toTypedArray()
}


/**
 * Count the size of all APP files directories
 */
@WorkerThread
fun Context.lengthAppFilesDirs(): Long {
    var sum: Long = 0
    for (file in this.getAppFilesDirs()) {
        sum += file.lengthRecursively()
    }
    return sum
}

/**
 * Count the size of all APP files directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
fun Context.lengthAppFilesDirs(packageName: String): Long {
    var sum: Long = 0
    for (file in this.getAppFilesDirs(packageName)) {
        sum += file.lengthRecursively()
    }
    return sum
}

/**
 * Clean up all app files directories
 */
@WorkerThread
fun Context.cleanAppFilesDirs() {
    for (file in this.getAppFilesDirs()) {
        file.cleanDir()
    }
}

/**
 * Clean up all files directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
fun Context.cleanAppFilesDirs(packageName: String) {
    for (file in this.getAppFilesDirs(packageName)) {
        file.cleanDir()
    }
}


/* ******************************************* App Obb Dir *******************************************/


/**
 * Get the app obb directory
 */
fun Context.getAppObbDir(): File? {
    val file = this.obbDir
    return if (file != null) {
        // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getObbDir() returns null
        file
    } else {
        val externalDir = this.getMountedExternalStorageDirectory()
        if (externalDir != null) File(externalDir, "Android/obb/" + this.packageName) else null
    }
}

/**
 * Get the obb directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppObbDir(packageName: String): File? {
    val selfObbDir = this.getAppObbDir()
    return if (selfObbDir != null) File(selfObbDir.path.replace(this.packageName, packageName)) else null
}

/**
 * Get the all app obb directory
 */
fun Context.getAppObbDirs(): Array<File> {
    var obbDirs: Array<File>? = null
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
        obbDirs = this.obbDirs
    }
    return if (obbDirs != null && obbDirs.isNotEmpty()) {
        obbDirs
    } else {
        this.getMountedExternalStorageDirectorys().map { File(it, "Android/obb/" + this.packageName) }.toTypedArray()
    }
}

/**
 * Get the all obb directory of the specified app
 *
 * @param packageName App package name
 */
fun Context.getAppObbDirs(packageName: String): Array<File> {
    return this.getAppObbDirs().map { File(it.path.replace(this.packageName, packageName)) }.toTypedArray()
}


/**
 * Count the size of all APP obb directories
 */
@WorkerThread
fun Context.lengthAppObbDirs(): Long {
    var sum: Long = 0
    for (file in this.getAppObbDirs()) {
        sum += file.lengthRecursively()
    }
    return sum
}

/**
 * Count the size of all APP obb directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
fun Context.lengthAppObbDirs(packageName: String): Long {
    var sum: Long = 0
    for (file in this.getAppObbDirs(packageName)) {
        sum += file.lengthRecursively()
    }
    return sum
}

/**
 * Clean up all app obb directories
 */
@WorkerThread
fun Context.cleanAppObbDirs() {
    for (file in this.getAppObbDirs()) {
        file.cleanDir()
    }
}

/**
 * Clean up all obb directories of the specified app
 *
 * @param packageName App package name
 */
@WorkerThread
fun Context.cleanAppObbDirs(packageName: String) {
    for (file in this.getAppObbDirs(packageName)) {
        file.cleanDir()
    }
}


/* ******************************************* Other *******************************************/


/**
 * Traverses the given directory collection, returning a directory with free space greater than or equal to the minimum number of bytes
 *
 * @param minBytes Minimum number of bytes
 */
fun Array<File>?.filterByMinBytes(minBytes: Long): File? {
    return if (this == null || this.isEmpty()) null else this.find {
        it.mkdirsCheck()
        it.isDirectory && it.getAvailableBytes() >= minBytes
    }
}
