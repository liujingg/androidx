@file:Suppress("unused")

package me.panpf.androidx.content.pm

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.support.annotation.WorkerThread
import android.support.v4.util.ArrayMap
import android.support.v4.util.ArraySet
import kotlinx.android.parcel.Parcelize
import java.io.File
import java.util.*

/*
 * APP Package related extension methods or properties
 */

@Parcelize
class AppPackage(val name: String, val packageName: String, val versionCode: Int, val versionName: String,
                 val packageFilePath: String, val packageSize: Long, val packageLastModifiedTime: Long,
                 val systemApp: Boolean, val enabled: Boolean) : Parcelable

/**
 * Whether the app with the specified package name is installed
 *
 * @param packageName App package name
 */
fun Context.isPackageInstalled(packageName: String): Boolean = try {
    packageManager.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES)
    true
} catch (e: NameNotFoundException) {
    false
}

/**
 * Get the version number of the installed APP
 *
 * @param packageName App package name
 * @return -1: Not Installed
 */
fun Context.getPackageVersionCode(packageName: String): Int {
    val packageManager = packageManager
    val packageInfo: PackageInfo
    try {
        packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
    } catch (e: NameNotFoundException) {
        return -1
    }

    return packageInfo.versionCode
}

/**
 * Get the version name of the installed APP
 *
 * @param packageName App package name
 */
fun Context.getPackageVersionName(packageName: String): String? {
    val packageManager = packageManager
    val packageInfo: PackageInfo
    try {
        packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
    } catch (e: NameNotFoundException) {
        return null
    }

    return packageInfo.versionName
}

/**
 * Get information of the installed APP
 *
 * @param packageName App package name
 * @return null：Not Installed
 */
fun Context.getPackage(packageName: String): AppPackage? {
    val packageManager = packageManager
    val packageInfo: PackageInfo
    try {
        packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
    } catch (e: NameNotFoundException) {
        return null
    }

    return assembleAppPackage(packageManager, packageInfo)
}

/**
 * 根据标记信息判断是否是系统 app
 *
 * @param appFlags app 标记信息，来自 [ApplicationInfo]
 */
fun isSystemApp(appFlags: Int): Boolean {
    return appFlags and ApplicationInfo.FLAG_SYSTEM == 1
}

/**
 * 是否是系统 app
 */
fun ApplicationInfo.isSystemApp(): Boolean {
    return isSystemApp(flags)
}

/**
 * 判断指定包名的已安装 app 是否是系统 app
 *
 * @param packageName    app 包名
 * @return null: 未安装
 */
fun PackageManager.isSystemApp(packageName: String): Boolean? {
    val applicationInfo: ApplicationInfo
    try {
        applicationInfo = getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    } catch (e: NameNotFoundException) {
        return null
    }

    return applicationInfo.isSystemApp()
}


/**
 * 判断指定包名的已安装 app 是否是系统 app
 *
 * @param packageName app 包名
 * @return null: 未安装
 */
fun Context.isSystemApp(packageName: String): Boolean? {
    return packageManager.isSystemApp(packageName)
}


/**
 * 获取所有已安装 app 的包名和版本号 Map
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 所有已安装 app 的包名和版本号集合
 */
@WorkerThread
fun Context.getAllAppIdAndVersionCodeMap(excludeSystemApp: Boolean, excludeSelf: Boolean): ArrayMap<String, Int>? {
    var packageInfoList: List<PackageInfo>? = null
    try {
        packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
    } catch (e: NullPointerException) {
        e.printStackTrace()
        // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
    }

    if (packageInfoList != null && !packageInfoList.isEmpty()) {
        val appsSet = ArrayMap<String, Int>()
        for (packageInfo in packageInfoList) {
            if (excludeSelf && packageName == packageInfo.packageName) {
                continue
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                continue
            }

            appsSet[packageInfo.packageName] = packageInfo.versionCode
        }
        return appsSet
    } else {
        return null
    }
}

/**
 * 获取所有已安装 app 的包名和版本号集合
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 所有已安装 app 的包名和版本号集合
 */
@WorkerThread
fun Context.getAllAppIdAndVersionCodeSet(excludeSystemApp: Boolean, excludeSelf: Boolean): ArraySet<AbstractMap.SimpleEntry<String, Int>>? {
    var packageInfoList: List<PackageInfo>? = null
    try {
        packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
    } catch (e: NullPointerException) {
        e.printStackTrace()
        // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
    }

    if (packageInfoList != null && !packageInfoList.isEmpty()) {
        val appsSet = ArraySet<AbstractMap.SimpleEntry<String, Int>>()
        for (packageInfo in packageInfoList) {
            if (excludeSelf && packageName == packageInfo.packageName) {
                continue
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                continue
            }

            appsSet.add(AbstractMap.SimpleEntry(packageInfo.packageName, packageInfo.versionCode))
        }
        return appsSet
    } else {
        return null
    }
}

/**
 * 获取所有已安装 app 的包名
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 所有已安装 app 的包名集合
 */
@WorkerThread
fun Context.getAllAppId(excludeSystemApp: Boolean, excludeSelf: Boolean): Set<String>? {
    var packageInfoList: List<PackageInfo>? = null
    try {
        packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
    } catch (e: NullPointerException) {
        e.printStackTrace()
        // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
    }

    if (packageInfoList != null && !packageInfoList.isEmpty()) {
        val appsSet = ArraySet<String>()
        for (packageInfo in packageInfoList) {
            if (excludeSelf && packageName == packageInfo.packageName) {
                continue
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                continue
            }

            appsSet.add(packageInfo.packageName)
        }
        return appsSet
    } else {
        return null
    }
}

/**
 * Get all installed apps
 *
 * @param excludeSystemApp Exclude system apps
 * @param excludeSelf      Exclude yourself
 * @param size             最多取多少个应用
 * @return Installed app list
 */
@WorkerThread
fun Context.getAllApp(excludeSystemApp: Boolean, excludeSelf: Boolean, size: Int): List<AppPackage>? {
    val packageManager = packageManager
    var packageInfoList: List<PackageInfo>? = null
    try {
        packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
    } catch (e: NullPointerException) {
        e.printStackTrace()
        // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
    }

    if (packageInfoList == null || packageInfoList.isEmpty()) {
        return null
    }

    val packageArrayList = ArrayList<AppPackage>(if (size > 0) size else packageInfoList.size)
    var index = 0
    for (packageInfo in packageInfoList) {
        if (excludeSelf && packageName == packageInfo.packageName) {
            continue
        }

        if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
            continue
        }

        val appPackage = assembleAppPackage(packageManager, packageInfo)
        if (appPackage != null) {
            packageArrayList.add(appPackage)
            index++
        }
        if (size in 1..index) {
            break
        }
    }
    return packageArrayList
}

/**
 * Get all installed apps
 *
 * @param excludeSystemApp Exclude system applications
 * @param excludeSelf      Exclude yourself
 * @return Installed app list
 */
@WorkerThread
fun Context.getAllApp(excludeSystemApp: Boolean, excludeSelf: Boolean): List<AppPackage>? {
    return getAllApp(excludeSystemApp, excludeSelf, -1)
}

/**
 * Get information about an APP
 *
 * @param excludeSystemApp Exclude system applications
 * @param excludeSelf      Exclude yourself
 * @return null：Not Installed
 */
fun Context.getOnePackage(excludeSystemApp: Boolean, excludeSelf: Boolean): AppPackage? {
    val appPackageList = getAllApp(excludeSystemApp, excludeSelf, 1)
    return if (appPackageList != null && appPackageList.isNotEmpty()) appPackageList[0] else null
}

/**
 * 统计已安装 app 个数
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 已安装 app 个数
 */
@WorkerThread
fun Context.countPackage(excludeSystemApp: Boolean, excludeSelf: Boolean): Int {
    val packageManager = packageManager
    var packageInfoList: List<PackageInfo>? = null
    try {
        packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
    } catch (e: NullPointerException) {
        e.printStackTrace()
        // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
    }

    if (packageInfoList == null || packageInfoList.isEmpty()) {
        return 0
    }

    var count = 0
    for (packageInfo in packageInfoList) {
        if (excludeSelf && packageName == packageInfo.packageName) {
            continue
        }

        if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
            continue
        }

        count++
    }
    return count
}

private fun assembleAppPackage(packageManager: PackageManager, packageInfo: PackageInfo): AppPackage? {
    val applicationInfo = packageInfo.applicationInfo ?: return null
    val packageFile = File(applicationInfo.sourceDir)
    return AppPackage(
            applicationInfo.loadLabel(packageManager)?.toString() ?: "",
            applicationInfo.packageName,
            packageInfo.versionCode,
            packageInfo.versionName,
            applicationInfo.sourceDir,
            packageFile.length(),
            packageFile.lastModified(),
            isSystemApp(applicationInfo.flags),
            applicationInfo.enabled)
}

/**
 * 获取指定 app 的安装包文件
 *
 * @param packageName app 包名
 * @return app 的安装包文件
 */
@WorkerThread
fun Context.getAppPackageFile(packageName: String): File? {
    val applicationInfo: ApplicationInfo
    try {
        applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    } catch (e: NameNotFoundException) {
        e.printStackTrace()
        return null
    }

    return File(applicationInfo.sourceDir)
}

/**
 * 获取指定 app 的签名字节数组
 *
 * @param packageName app 包名
 * @return app 的签名字节数组
 */
@SuppressLint("PackageManagerGetSignatures")
@WorkerThread
fun Context.getAppSignatureBytes(packageName: String): ByteArray? {
    return try {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        if (packageInfo.signatures != null && packageInfo.signatures.isNotEmpty()) {
            packageInfo.signatures[0].toByteArray()
        } else {
            null
        }
    } catch (e: NameNotFoundException) {
        e.printStackTrace()
        null
    }
}

/**
 * 获取已安装 app 图标的 Drawable 版本
 *
 * @param packageName app 包名
 * @param versionCode app 版本号，如果版本号小于等于 0 则不匹配版本，否则版本必须一致才能返回图标
 * @return app 图标
 */
@WorkerThread
fun Context.getAppIconDrawable(packageName: String, versionCode: Int): Drawable? {
    val pm = packageManager
    val packageInfo: PackageInfo
    try {
        packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_META_DATA)
    } catch (e: NameNotFoundException) {
        return null
    }

    return if (versionCode > -1 && packageInfo.versionCode != versionCode) {
        null
    } else packageInfo.applicationInfo.loadIcon(pm)

}

/**
 * 获取已安装 app 图标的 bitmap 版本，如果图标不是 BitmapDrawable 则创建新的 bitmap
 *
 * @param packageName app 包名
 * @param versionCode app 版本号，如果版本号小于等于 0 则不匹配版本，否则版本必须一致才能返回图标
 * @return app 图标
 */
@WorkerThread
fun Context.getAppIconBitmap(packageName: String, versionCode: Int): Bitmap? {
    val drawable = getAppIconDrawable(packageName, versionCode)
    if (drawable == null || drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        return null
    }

    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }

    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)

    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.draw(canvas)
    return bitmap
}

/**
 * 获取指定 apk 文件的图标的 Drawable 版本
 *
 * @param apkFilePath apk 文件路径
 * @return apk 文件的图标
 */
@WorkerThread
fun Context.getApkIconDrawable(apkFilePath: String): Drawable? {
    val pm = packageManager
    val packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA)
            ?: return null

    packageInfo.applicationInfo.sourceDir = apkFilePath
    packageInfo.applicationInfo.publicSourceDir = apkFilePath
    return packageInfo.applicationInfo.loadIcon(pm)
}

/**
 * 获取指定 apk 文件的图标的 bitmap 版本，如果图标不是 BitmapDrawable 则创建新的 bitmap
 *
 * @param apkFilePath apk 文件路径
 * @return apk 文件的图标
 */
@WorkerThread
fun Context.getApkIconBitmap(apkFilePath: String): Bitmap? {
    val drawable = getApkIconDrawable(apkFilePath)
    if (drawable == null || drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        return null
    }

    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }

    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)

    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.draw(canvas)
    return bitmap
}