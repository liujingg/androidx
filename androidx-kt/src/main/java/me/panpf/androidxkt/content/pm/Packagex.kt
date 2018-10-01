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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.content.pm

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.WorkerThread
import android.support.v4.util.ArrayMap
import android.util.Pair
import me.panpf.androidx.content.pm.AppPackage
import me.panpf.androidx.content.pm.Packagex
import java.io.File

/*
 * APP Package related extension methods or properties
 */

/**
 * Whether the app with the specified package name is installed
 *
 * @param packageName App package name
 */
inline fun Context.isPackageInstalled(packageName: String): Boolean = Packagex.isInstalled(this, packageName)

/**
 * Get the version number of the installed APP
 *
 * @param packageName App package name
 * @return -1: Not Installed
 */
inline fun Context.getPackageVersionCode(packageName: String): Int = Packagex.getVersionCode(this, packageName)

/**
 * Get the version name of the installed APP
 *
 * @param packageName App package name
 */
inline fun Context.getPackageVersionName(packageName: String): String? = Packagex.getVersionName(this, packageName)

/**
 * Get information of the installed APP
 *
 * @param packageName App package name
 * @return null：Not Installed
 */
inline fun Context.getPackage(packageName: String): AppPackage? = Packagex.getPackage(this, packageName)

/**
 * 是否是系统 app
 */
inline fun ApplicationInfo.isSystemApp(): Boolean = Packagex.isSystemApp(this)

/**
 * 判断指定包名的已安装 app 是否是系统 app
 *
 * @param packageName    app 包名
 * @return null: 未安装
 */
inline fun PackageManager.isSystemApp(packageName: String): Boolean? = Packagex.isSystemApp(this, packageName)


/**
 * 判断指定包名的已安装 app 是否是系统 app
 *
 * @param packageName app 包名
 * @return null: 未安装
 */
inline fun Context.isSystemApp(packageName: String): Boolean? = Packagex.isSystemApp(this, packageName)

/**
 * 获取所有已安装 app 的包名和版本号集合
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 所有已安装 app 的包名和版本号集合
 */
@WorkerThread
inline fun Context.listAppIdAndVersionCode(excludeSystemApp: Boolean, excludeSelf: Boolean): List<Pair<String, Int>>? = Packagex.listAppIdAndVersionCode(this, excludeSystemApp, excludeSelf)


/**
 * 获取所有已安装 app 的包名和版本号 Map
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 所有已安装 app 的包名和版本号集合
 */
@WorkerThread
inline fun Context.listAppIdAndVersionCodeToMap(excludeSystemApp: Boolean, excludeSelf: Boolean): ArrayMap<String, Int>? = Packagex.listAppIdAndVersionCodeToMap(this, excludeSystemApp, excludeSelf)

/**
 * 获取所有已安装 app 的包名
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 所有已安装 app 的包名集合
 */
@WorkerThread
inline fun Context.listAppId(excludeSystemApp: Boolean, excludeSelf: Boolean): List<String>? = Packagex.listAppId(this, excludeSystemApp, excludeSelf)

/**
 * Get all installed apps
 *
 * @param excludeSystemApp Exclude system apps
 * @param excludeSelf      Exclude yourself
 * @param size             最多取多少个应用
 * @return Installed app list
 */
@WorkerThread
inline fun Context.listPackage(excludeSystemApp: Boolean, excludeSelf: Boolean, size: Int): List<AppPackage>? = Packagex.listPackage(this, excludeSystemApp, excludeSelf, size)

/**
 * Get all installed apps
 *
 * @param excludeSystemApp Exclude system applications
 * @param excludeSelf      Exclude yourself
 * @return Installed app list
 */
@WorkerThread
inline fun Context.listPackage(excludeSystemApp: Boolean, excludeSelf: Boolean): List<AppPackage>? = Packagex.listPackage(this, excludeSystemApp, excludeSelf)

/**
 * Get information about the first app
 *
 * @param excludeSystemApp Exclude system applications
 * @param excludeSelf      Exclude yourself
 * @return null：Not Installed
 */
inline fun Context.getFirstPackage(excludeSystemApp: Boolean, excludeSelf: Boolean): AppPackage? = Packagex.getFirstPackage(this, excludeSystemApp, excludeSelf)

/**
 * 统计已安装 app 个数
 *
 * @param excludeSystemApp 是否排除系统应用
 * @param excludeSelf      是否排除自己
 * @return 已安装 app 个数
 */
@WorkerThread
inline fun Context.countPackage(excludeSystemApp: Boolean, excludeSelf: Boolean): Int = Packagex.count(this, excludeSystemApp, excludeSelf)

inline fun PackageInfo.packageInfoToAppPackage(packageManager: PackageManager): AppPackage = Packagex.packageInfoToAppPackage(this, packageManager)

/**
 * 获取指定 app 的安装包文件
 *
 * @param packageName app 包名
 * @return app 的安装包文件
 */
@WorkerThread
inline fun Context.getPackageFile(packageName: String): File? = Packagex.getPackageFile(this, packageName)

/**
 * 获取指定 app 的签名字节数组
 *
 * @param packageName app 包名
 * @return app 的签名字节数组
 */
@SuppressLint("PackageManagerGetSignatures")
@WorkerThread
inline fun Context.getAppSignatureBytes(packageName: String): ByteArray? = Packagex.getAppSignatureBytes(this, packageName)

/**
 * 获取已安装 app 图标的 Drawable 版本
 *
 * @param packageName app 包名
 * @param versionCode app 版本号，如果版本号小于等于 0 则不匹配版本，否则版本必须一致才能返回图标
 * @return app 图标
 */
@WorkerThread
inline fun Context.getAppIconDrawable(packageName: String, versionCode: Int): Drawable? = Packagex.getAppIconDrawable(this, packageName, versionCode)

/**
 * 获取已安装 app 图标的 bitmap 版本，如果图标不是 BitmapDrawable 则创建新的 bitmap
 *
 * @param packageName app 包名
 * @param versionCode app 版本号，如果版本号小于等于 0 则不匹配版本，否则版本必须一致才能返回图标
 * @return app 图标
 */
@WorkerThread
inline fun Context.getAppIconBitmap(packageName: String, versionCode: Int): Bitmap? = Packagex.getAppIconBitmap(this, packageName, versionCode)

/**
 * 获取指定 apk 文件的图标的 Drawable 版本
 *
 * @param apkFilePath apk 文件路径
 * @return apk 文件的图标
 */
@WorkerThread
inline fun Context.getApkIconDrawable(apkFilePath: String): Drawable? = Packagex.getApkIconDrawable(this, apkFilePath)

/**
 * 获取指定 apk 文件的图标的 bitmap 版本，如果图标不是 BitmapDrawable 则创建新的 bitmap
 *
 * @param apkFilePath apk 文件路径
 * @return apk 文件的图标
 */
@WorkerThread
inline fun Context.getApkIconBitmap(apkFilePath: String): Bitmap? = Packagex.getApkIconBitmap(this, apkFilePath)