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

import android.Manifest
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Pair
import androidx.annotation.RequiresPermission
import me.panpf.androidx.content.pm.AppPackage
import me.panpf.androidx.content.pm.Packagex
import java.io.File


/*
 * APP Package related extension methods or properties
 */


/**
 * Request to install apk
 * @return false: Request failed
 */
@RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
inline fun Context.requestInstallPackage(apkFileUri: Uri): Boolean = Packagex.requestInstall(this, apkFileUri)

/**
 * Request to install apk
 * @return false: Request failed
 */
@RequiresPermission(value = Manifest.permission.REQUEST_INSTALL_PACKAGES, conditional = true)
inline fun Context.requestInstallPackage(apkFile: File): Boolean = Packagex.requestInstall(this, apkFile)


/**
 * Request to uninstall app
 * @return false: Request failed
 */
@RequiresPermission(value = Manifest.permission.REQUEST_DELETE_PACKAGES, conditional = true)
inline fun Context.requestUninstallPackage(packageName: String): Boolean = Packagex.requestUninstall(this, packageName)


/**
 * Return true if the app with the specified packageName is installed
 */
inline fun Context.isPackageInstalled(packageName: String): Boolean = Packagex.isInstalled(this, packageName)


/**
 * Get the versionCode of the app for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionCode(packageName: String): Int = Packagex.getVersionCode(this, packageName)

/**
 * Get the versionCode of the app for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageVersionCodeOr(packageName: String, defaultValue: Int): Int = Packagex.getVersionCodeOr(this, packageName, defaultValue)


/**
 * Get the versionName of the app for the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageVersionName(packageName: String): String = Packagex.getVersionName(this, packageName)

/**
 * Get the versionName of the app for the specified packageName, return to defaultValue if not installed
 */
inline fun Context.getPackageVersionNameOr(packageName: String, defaultValue: String): String = Packagex.getVersionNameOr(this, packageName, defaultValue)

/**
 * Get the versionName of the app for the specified packageName, return to null if not installed
 */
inline fun Context.getPackageVersionNameOrNull(packageName: String): String? = Packagex.getVersionNameOrNull(this, packageName)


/**
 * Get information about the app with the specified packageName
 */
inline fun Context.getPackage(packageName: String): AppPackage = Packagex.get(this, packageName)

/**
 * Get information about the app with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageOrNull(packageName: String): AppPackage? = Packagex.getOrNull(this, packageName)


/**
 * Return true if it is a system APP
 */
inline fun ApplicationInfo.isSystemApp(): Boolean = Packagex.isSystemApp(this)

/**
 * Return true if the app with the specified packageName is the system APP
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.isSystemApp(packageName: String): Boolean = Packagex.isSystemApp(this, packageName)

/**
 * Return true if the app with the specified packageName is the system APP, return to defaultValue if not installed
 */
inline fun Context.isSystemAppOr(packageName: String, defaultValue: Boolean): Boolean = Packagex.isSystemAppOr(this, packageName, defaultValue)


/**
 * List the packageName and versionCode of all installed APPs
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 */
inline fun Context.listPackageVersionCodePair(excludeSystemApp: Boolean, excludeSelf: Boolean): List<Pair<String, Int>> = Packagex.listVersionCodePair(this, excludeSystemApp, excludeSelf)


/**
 * Get the packageName and versionCode of all installed apps Map
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 */
inline fun Context.listPackageVersionCodeMap(excludeSystemApp: Boolean, excludeSelf: Boolean): androidx.collection.ArrayMap<String, Int> = Packagex.listVersionCodeMap(this, excludeSystemApp, excludeSelf)

/**
 * List the packageName of all installed apps
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 */
inline fun Context.listPackageName(excludeSystemApp: Boolean, excludeSelf: Boolean): List<String> = Packagex.listPackageName(this, excludeSystemApp, excludeSelf)

/**
 * List information for all installed apps
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 * @param size             How many apps to get. -1: all
 */
inline fun Context.listPackage(excludeSystemApp: Boolean, excludeSelf: Boolean, size: Int): List<AppPackage> = Packagex.list(this, excludeSystemApp, excludeSelf, size)

/**
 * List information for all installed apps
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 */
inline fun Context.listPackage(excludeSystemApp: Boolean, excludeSelf: Boolean): List<AppPackage> = Packagex.list(this, excludeSystemApp, excludeSelf)


/**
 * Get information about an app
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 */
inline fun Context.getOnePackage(excludeSystemApp: Boolean, excludeSelf: Boolean): AppPackage? = Packagex.getOne(this, excludeSystemApp, excludeSelf)


/**
 * Get the number of installed apps
 *
 * @param excludeSystemApp If true, exclude yourself exclude system apps
 * @param excludeSelf      If true, exclude yourself
 */
inline fun Context.countPackage(excludeSystemApp: Boolean, excludeSelf: Boolean): Int = Packagex.count(this, excludeSystemApp, excludeSelf)


/**
 * Get the apk file of the app with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getPackageApkFile(packageName: String): File = Packagex.getPackageApkFile(this, packageName)

/**
 * Get the apk file of the app with the specified packageName, return to null if not installed
 */
inline fun Context.getPackageApkFileOrNull(packageName: String): File? = Packagex.getPackageApkFileOrNull(this, packageName)


/**
 * Get the signature data of the app with the specified packageName
 */
@Throws(PackageManager.NameNotFoundException::class)
inline fun Context.getAppSignatureBytes(packageName: String): ByteArray = Packagex.getAppSignatureBytes(this, packageName)

/**
 * Get the signature data of the app with the specified packageName, return to null if not installed
 */
inline fun Context.getAppSignatureBytesOrNull(packageName: String): ByteArray? = Packagex.getAppSignatureBytesOrNull(this, packageName)


/**
 * Get the icon Drawable of the app of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, -1: ignores versionCode
 */
inline fun Context.getAppIconDrawable(packageName: String, versionCode: Int): Drawable? = Packagex.getAppIconDrawable(this, packageName, versionCode)

/**
 * Get the icon Bitmap of the app of the specified packageName
 *
 * @param versionCode App versionCode. Returns null if versionCode is inconsistent, -1: ignores versionCode
 */
inline fun Context.getAppIconBitmap(packageName: String, versionCode: Int): Bitmap? = Packagex.getAppIconBitmap(this, packageName, versionCode)


/**
 * Get the icon Drawable of the specified apk file
 */
inline fun Context.getApkIconDrawable(apkFilePath: String): Drawable? = Packagex.getApkIconDrawable(this, apkFilePath)

/**
 * Get the icon Bitmap of the specified apk file
 */
inline fun Context.getApkIconBitmap(apkFilePath: String): Bitmap? = Packagex.getApkIconBitmap(this, apkFilePath)