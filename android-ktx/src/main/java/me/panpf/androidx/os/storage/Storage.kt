@file:Suppress("unused")

package me.panpf.androidx.os.storage

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.support.annotation.WorkerThread
import me.panpf.androidx.os.compatAvailableBytes
import me.panpf.androidx.os.compatFreeBytes
import me.panpf.androidx.os.compatTotalBytes
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.util.*

/*
 * 存储相关的扩展方法或属性
 */


fun File.getDirFreeBytes(): Long = StatFs(path).compatFreeBytes

fun File.getDirTotalBytes(): Long = StatFs(path).compatTotalBytes

fun File.getDirAvailableBytes(): Long = StatFs(path).compatAvailableBytes


fun isSDCardMounted(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

fun getSDCardFreeBytes(): Long = Environment.getExternalStorageDirectory().getDirFreeBytes()

fun getSDCardTotalBytes(): Long = Environment.getExternalStorageDirectory().getDirTotalBytes()

fun getSDCardAvailableBytes(): Long = Environment.getExternalStorageDirectory().getDirAvailableBytes()


fun Context.getAllSdcardPath(): Array<String>? {
    val paths: Array<String>?
    val getVolumePathsMethod: Method
    try {
        getVolumePathsMethod = StorageManager::class.java.getMethod("getVolumePaths")
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            arrayOf(Environment.getExternalStorageDirectory().path)
        } else {
            null
        }
    }

    val sm = getSystemService(Context.STORAGE_SERVICE) as StorageManager
    try {
        @Suppress("UNCHECKED_CAST")
        paths = getVolumePathsMethod.invoke(sm) as Array<String>
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
        return null
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
        return null
    }

    if (paths.isEmpty()) return null

    // 去掉不可用的存储器
    val storagePathList = LinkedList<String>()
    Collections.addAll(storagePathList, *paths)
    val storagePathIterator = storagePathList.iterator()

    var path: String
    var getVolumeStateMethod: Method? = null
    while (storagePathIterator.hasNext()) {
        path = storagePathIterator.next()
        if (getVolumeStateMethod == null) {
            try {
                getVolumeStateMethod = StorageManager::class.java.getMethod("getVolumeState", String::class.java)
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
                return null
            }
        }
        val status: String
        try {
            status = getVolumeStateMethod!!.invoke(sm, path) as String
        } catch (e: Exception) {
            e.printStackTrace()
            storagePathIterator.remove()
            continue
        }

        if (!(Environment.MEDIA_MOUNTED == status || Environment.MEDIA_MOUNTED_READ_ONLY == status)) {
            storagePathIterator.remove()
        }
    }
    return storagePathList.toTypedArray()
}

fun Context.getAllSdcardChildFiles(childPath: String): Array<File>? {
    return getAllSdcardPath()?.map { File(it, childPath) }?.toTypedArray()
}

/**
 * 寻找空间足够的 SD 卡
 *
 * @param needBytes 需要的空间，单位字节
 * @param childDir File(sdcardPath, childDir) 在指定子目录下检查空间
 * @param ignorePrimary 忽略主 sdcard
 */
fun Context.getSdcard(needBytes: Long, childDir: String? = null, ignorePrimary: Boolean = false): String? {
    val defaultStoragePath = Environment.getExternalStorageDirectory()?.path
    return getAllSdcardPath()?.find {
        (ignorePrimary && it != defaultStoragePath)
                && (if (childDir != null) File(it, childDir) else File(it)).getDirAvailableBytes() >= needBytes
    }
}

/**
 * 遍历指定的目录列表，检查目录剩余空间，并返回文件（不创建）
 *
 * @param dirs         目录列表
 * @param fileName     文件名称
 * @param needBytes    最低可用空间
 * @param cleanOldFile 在判断空间之前是否删除旧文件
 */
fun getFileFromDirs(dirs: Array<File>, fileName: String, needBytes: Long, cleanOldFile: Boolean = false): File? {
    return dirs.find {
        if (it.isDirectory) {
            it.mkdirs()
            if (cleanOldFile) File(it, fileName).delete()
            it.getDirAvailableBytes() >= needBytes
        } else {
            false
        }
    }?.let { File(it, fileName) }
}

/**
 * 获取所有 app 缓存目录
 */
fun Context.getAllAppCacheDirs(ignoreInternal: Boolean = false): Array<File> = mutableListOf<File>().apply {
    if (!ignoreInternal) add(this@getAllAppCacheDirs.cacheDir)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        addAll(this@getAllAppCacheDirs.externalCacheDirs)
    } else {
        add(this@getAllAppCacheDirs.externalCacheDir)
    }
}.toTypedArray()

/**
 * 获取所有 app 缓存目录
 */
fun Context.getAllAppFilesDirs(ignoreInternal: Boolean = false): Array<File> = mutableListOf<File>().apply {
    if (!ignoreInternal) add(this@getAllAppFilesDirs.filesDir)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        addAll(this@getAllAppFilesDirs.getExternalFilesDirs(null))
    } else {
        add(this@getAllAppFilesDirs.getExternalFilesDir(null))
    }
}.toTypedArray()

fun Context.getFileFromAppCaches(fileName: String, needBytes: Long = 0): File? =
        getFileFromDirs(getAllAppCacheDirs(), fileName, needBytes, false)

fun Context.getFileFromAppFiles(fileName: String, needBytes: Long): File? =
        getFileFromDirs(getAllAppFilesDirs(), fileName, needBytes, false)

/**
 * 清理所有 app 缓存目录
 */
fun Context.cleanAppCacheDirs() {
    getAllAppCacheDirs().forEach { cacheDir ->
        cacheDir.listFiles()?.forEach { childFile ->
            childFile.deleteRecursively()
        }
    }
}

/**
 * 获取指定 app 的 obb 目录
 *
 * @param packageName app 包名
 * @return app 的 obb 目录
 */
@WorkerThread
fun getAppObbDir(packageName: String): File = File(Environment.getExternalStorageDirectory(), "Android/obb/$packageName")

/**
 * 获取指定 app 的 data 目录
 *
 * @param packageName app 包名
 * @return app 的 data 目录
 */
@WorkerThread
fun getAppDataDir(packageName: String): File = File(Environment.getExternalStorageDirectory(), "Android/data/$packageName")
