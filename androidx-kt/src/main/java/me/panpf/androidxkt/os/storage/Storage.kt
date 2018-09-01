package me.panpf.androidxkt.os.storage

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.support.annotation.WorkerThread
import me.panpf.androidxkt.os.compatAvailableBytes
import me.panpf.androidxkt.os.compatFreeBytes
import me.panpf.androidxkt.os.compatTotalBytes
import me.panpf.javaxkt.io.cleanDir
import me.panpf.javaxkt.io.lengthRecursively
import me.panpf.javaxkt.lang.callMethod
import java.io.File
import java.util.*

/*
 * Storage related extension methods or properties
 */

/**
 * Get the number of free bytes remaining in the current directory
 */
fun File.getDirFreeBytes(): Long = StatFs(path).compatFreeBytes

/**
 * Get the number of total bytes in the current directory
 */
fun File.getDirTotalBytes(): Long = StatFs(path).compatTotalBytes

/**
 * Get the number of bytes remaining in the current directory that are available for the current application
 */
fun File.getDirAvailableBytes(): Long = StatFs(path).compatAvailableBytes

/**
 * Return true if the SD card is ready
 */
fun isSDCardMounted(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * Get the number of free bytes remaining in the sdcard
 */
fun getSDCardFreeBytes(): Long = Environment.getExternalStorageDirectory().getDirFreeBytes()

/**
 * Get the number of total bytes in the sdcard
 */
fun getSDCardTotalBytes(): Long = Environment.getExternalStorageDirectory().getDirTotalBytes()

/**
 * Get the number of bytes remaining in the sdcard that are available for the current application
 */
fun getSDCardAvailableBytes(): Long = Environment.getExternalStorageDirectory().getDirAvailableBytes()

/**
 * Get the path to the SD card
 */
fun getSdcardPath(): String = Environment.getExternalStorageDirectory().path

/**
 * Get the file to the SD card
 */
fun getSdcardFile(): File = Environment.getExternalStorageDirectory()


/**
 * Get the path to all available SD cards
 * @param ignorePrimary Ignore the main sdcard
 */
// todo 测试是否兼容 android 9
fun Context.getAllSdcardPath(ignorePrimary: Boolean = false): Array<String> {
    val manager = (getSystemService(Context.STORAGE_SERVICE)
            ?: throw IllegalStateException("StorageManager not found")) as StorageManager

    @Suppress("UNCHECKED_CAST")
    val volumePaths: Array<String>? = try {
        manager.callMethod("getVolumePaths") as Array<String>?
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    val primarySdcardPath = Environment.getExternalStorageDirectory().path

    return LinkedList<String>().apply {
        if (volumePaths?.isNotEmpty() == true) {
            Collections.addAll(this, *volumePaths)
        } else {
            add(primarySdcardPath)
        }
    }.filter {
        if (ignorePrimary && primarySdcardPath == it) {
            false
        } else {
            val volumeState = try {
                manager.callMethod("getVolumeState", it)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            (Environment.MEDIA_MOUNTED == volumeState || Environment.MEDIA_MOUNTED_READ_ONLY == volumeState)
        }
    }.toTypedArray()
}

/**
 * Get the path to all available SD cards
 * @param ignorePrimary Ignore the main sdcard
 */
fun Context.getAllSdcardFile(ignorePrimary: Boolean = false): Array<File> {
    return getAllSdcardPath(ignorePrimary).map { File(it) }.toTypedArray()
}

/**
 * Get all available SD cards and splicing them back on the specified path
 * @param ignorePrimary Ignore the main sdcard
 */
fun Context.getAllSdcardWithPathFile(childPath: String, ignorePrimary: Boolean = false): Array<File> {
    return getAllSdcardPath(ignorePrimary).map { File(it, childPath) }.toTypedArray()
}

/**
 * Find an SD card with a space no less than the specified number of bytes
 * @param minBytes Minimum number of bytes
 * @param childPath Check the space under File(sdcardPath, childDir) and return this file
 * @param ignorePrimary Ignore the main sdcard
 */
fun Context.findSdcardBySpace(minBytes: Long, childPath: String? = null, ignorePrimary: Boolean = false): File? {
    val allDir = if (childPath != null) getAllSdcardWithPathFile(childPath, ignorePrimary) else getAllSdcardFile(ignorePrimary)
    return allDir.find { it.getDirAvailableBytes() >= minBytes }
}

/**
 * Traverse the directory list, check the remaining space of the directory, and return the file
 *
 * @receiver Directory list
 * @param childFileName     This file will be deleted from the directory before the space is checked, and will eventually be returned.
 * @param minBytes    Minimum number of bytes
 * @param deleteFile Whether to delete old files before judging the space
 */
fun Array<File>.getChildFileBySpaceFromDirs(childFileName: String, minBytes: Long, deleteFile: Boolean = false): File? {
    return this.find {
        if (it.isDirectory) {
            it.mkdirs()
            if (deleteFile) File(it, childFileName).deleteRecursively()
            it.getDirAvailableBytes() >= minBytes
        } else {
            false
        }
    }?.let { File(it, childFileName) }
}

/**
 * Get all app cache directories
 */
fun Context.getAllAppCacheDirs(ignoreInternal: Boolean = false): Array<File> = mutableListOf<File>().apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        addAll(this@getAllAppCacheDirs.externalCacheDirs)
    } else {
        this@getAllAppCacheDirs.externalCacheDir?.let { add(it) }
    }
    if (!ignoreInternal) add(this@getAllAppCacheDirs.cacheDir)
}.toTypedArray()

/**
 * Get all app file directories
 */
fun Context.getAllAppFilesDirs(ignoreInternal: Boolean = false): Array<File> = mutableListOf<File>().apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        addAll(this@getAllAppFilesDirs.getExternalFilesDirs(null))
    } else {
        this@getAllAppFilesDirs.getExternalFilesDir(null)?.let { add(it) }
    }
    if (!ignoreInternal) add(this@getAllAppFilesDirs.filesDir)
}.toTypedArray()

/**
 * Get a file from the APP cache directory, external storage takes precedence
 */
fun Context.getFileFromAppCacheDirs(fileName: String): File = getAllAppCacheDirs().first().let { File(it, fileName) }

/**
 * Get a file from the APP files directory, external storage takes precedence
 */
fun Context.getFileFromAppFilesDirs(fileName: String): File = getAllAppFilesDirs().first().let { File(it, fileName) }

/**
 * Get a file from the app's cache directory and check the remaining space
 */
fun Context.getFileFromAppCacheDirsBySpace(fileName: String, minBytes: Long = 0): File? =
        getAllAppCacheDirs().getChildFileBySpaceFromDirs(fileName, minBytes, false)

/**
 * Get a file from the app's files directory and check the remaining space
 */
fun Context.getFileFromAppFilesDirsBySpace(fileName: String, minBytes: Long = 0): File? =
        getAllAppFilesDirs().getChildFileBySpaceFromDirs(fileName, minBytes, false)

/**
 * Clean up all app cache directories
 */
fun Context.cleanAppCacheDirs(): Unit = getAllAppCacheDirs().forEach { cacheDir -> cacheDir.cleanDir() }

/**
 * Count the size of all APP cache directories
 */
@WorkerThread
fun Context.lengthAppCacheDirs(): Long = getAllAppCacheDirs().map { it.lengthRecursively() }.sum()

/**
 * Count the size of all APP files directories
 */
@WorkerThread
fun Context.lengthAppFilesDirs(): Long = getAllAppFilesDirs().map { it.lengthRecursively() }.sum()


/**
 * Get the obb directory of the specified app
 *
 * @receiver App package name
 */
fun String.getAppObbDir(): File = File(Environment.getExternalStorageDirectory(), "Android/obb/$this")

/**
 * Get the data directory of the specified app
 *
 * @receiver App package name
 */
fun String.getAppDataDir(): File = File(Environment.getExternalStorageDirectory(), "Android/data/$this")
