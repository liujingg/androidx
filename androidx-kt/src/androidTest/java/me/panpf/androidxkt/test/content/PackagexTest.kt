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

package me.panpf.androidxkt.test.content

import android.content.pm.PackageManager
import android.util.Pair
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidx.content.pm.AppPackage
import me.panpf.androidx.content.pm.AcceptPackageType
import me.panpf.androidxkt.content.pm.*
import me.panpf.androidxkt.test.BuildConfig
import me.panpf.javax.collections.Collectionx
import me.panpf.javax.util.Predicate
import me.panpf.javaxkt.lang.isSafe
import me.panpf.javaxkt.util.requireNotNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PackagexTest {

    @Test
    fun testIsInstalled() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(context.isPackageInstalled(context.packageName))
        Assert.assertFalse(context.isPackageInstalled(context.packageName + "_nonono"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetVersionCode() {
        val context = InstrumentationRegistry.getContext()

        val appPackage = context.getOnePackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF).requireNotNull()

        Assert.assertTrue("versionCode: " + appPackage.versionCode, appPackage.versionCode > 0)
        Assert.assertEquals(appPackage.versionCode.toLong(), context.getPackageVersionCode(appPackage.packageName).toLong())
        Assert.assertEquals(-1, context.getPackageVersionCodeOr(appPackage.packageName + "_nonono", -1).toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetVersionName() {
        val context = InstrumentationRegistry.getContext()

        val appPackage = context.getOnePackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF).requireNotNull()

        Assert.assertTrue("versionName: " + appPackage.versionName, appPackage.versionName.isSafe())
        Assert.assertEquals(appPackage.versionName, context.getPackageVersionName(appPackage.packageName))
        Assert.assertEquals("unknown", context.getPackageVersionNameOr(appPackage.packageName + "_nonono", "unknown"))
        Assert.assertNull(context.getPackageVersionNameOrNull(appPackage.packageName + "_nonono"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetPackage() {
        val context = InstrumentationRegistry.getContext()

        val selfAppPackage = context.getPackage(context.packageName).requireNotNull()

        Assert.assertEquals(BuildConfig.APPLICATION_ID, selfAppPackage.packageName)
        Assert.assertTrue("name: " + selfAppPackage.name, selfAppPackage.name.isSafe())
        Assert.assertNotNull(selfAppPackage.versionName)
        Assert.assertTrue("versionCode: " + selfAppPackage.versionCode, selfAppPackage.versionCode >= 0)
        Assert.assertTrue("packageFilePath: " + selfAppPackage.packageFilePath, selfAppPackage.packageFilePath.isSafe())
        Assert.assertTrue("packageSize: " + selfAppPackage.packageSize, selfAppPackage.packageSize >= 0)
        Assert.assertTrue("packageLastModifiedTime: " + selfAppPackage.packageLastModifiedTime, selfAppPackage.packageLastModifiedTime >= 0)
        Assert.assertFalse(selfAppPackage.systemApp)
        Assert.assertTrue(selfAppPackage.enabled)

        val systemAppPackage = context.getPackage(context.listPackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF).find { appPackage -> appPackage.systemApp }.requireNotNull().packageName).requireNotNull()
        Assert.assertTrue(systemAppPackage.systemApp)

        Assert.assertNull(context.getPackageOrNull(context.packageName + "_nonono"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testIsSystemApp() {
        val context = InstrumentationRegistry.getContext()

        val systemAppPackageName = context.listPackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF).find { appPackage -> appPackage.systemApp }.requireNotNull().packageName

        Assert.assertTrue(context.packageManager.getApplicationInfo(systemAppPackageName, 0).isSystemApp())
        Assert.assertFalse(context.packageManager.getApplicationInfo(context.packageName, 0).isSystemApp())

        Assert.assertTrue(context.isSystemApp(systemAppPackageName))
        Assert.assertTrue(context.isSystemAppOr(context.packageName + "_nonono", true))
    }

    @Test
    fun testListPackageVersionCodePair() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val selfPredicate = Predicate<Pair<String, Int>> { stringIntegerPair -> stringIntegerPair.first == selfPackageName }
        val systemAppPredicate = Predicate<Pair<String, Int>> { stringIntegerPair -> context.isSystemAppOr(stringIntegerPair.first, false) }
        val userAppPredicate = Predicate<Pair<String, Int>> { stringIntegerPair -> !context.isSystemAppOr(stringIntegerPair.first, false) }

        /*
         * ALL
         */
        val allApps = context.listPackageVersionCodePair(AcceptPackageType.ALL)
        val systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate)
        Assert.assertTrue(systemAppsInAllSize > 0)
        val userAppsInAllSize = Collectionx.count(allApps, userAppPredicate)
        Assert.assertTrue(userAppsInAllSize > 0)
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate))

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        val allAndExcludeSelfApps = context.listPackageVersionCodePair(AcceptPackageType.ALL_AND_EXCLUDE_SELF)
        Assert.assertEquals(allApps.size.toLong(), (allAndExcludeSelfApps.size + 1).toLong())
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate))


        /*
         * USER
         */
        val userApps = context.listPackageVersionCodePair(AcceptPackageType.USER)
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate))
        Assert.assertEquals(userAppsInAllSize.toLong(), userApps.size.toLong())
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate))
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        val userAndExcludeSelfApps = context.listPackageVersionCodePair(AcceptPackageType.USER_AND_EXCLUDE_SELF)
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate))
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(userApps.size.toLong(), (userAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(userApps.size.toLong(), userAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate))


        /*
         * SYSTEM
         */
        val systemApps = context.listPackageVersionCodePair(AcceptPackageType.SYSTEM)
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate))
        Assert.assertEquals(systemAppsInAllSize.toLong(), systemApps.size.toLong())
        Assert.assertEquals(allApps.size.toLong(), (userApps.size + systemApps.size).toLong())
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate))
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        val systemAndExcludeSelfApps = context.listPackageVersionCodePair(AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate))
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(systemApps.size.toLong(), (systemAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(systemApps.size.toLong(), systemAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate))
        Assert.assertEquals(allAndExcludeSelfApps.size.toLong(), (userAndExcludeSelfApps.size + systemAndExcludeSelfApps.size).toLong())
    }

    @Test
    fun testGetPackageVersionCodeMap() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val selfPredicate = Predicate<MutableMap.MutableEntry<String, Int>> { stringIntegerPair -> stringIntegerPair.key == selfPackageName }
        val systemAppPredicate = Predicate<MutableMap.MutableEntry<String, Int>> { stringIntegerPair -> context.isSystemAppOr(stringIntegerPair.key, false) }
        val userAppPredicate = Predicate<MutableMap.MutableEntry<String, Int>> { stringIntegerPair -> !context.isSystemAppOr(stringIntegerPair.key, false) }

        /*
         * ALL
         */
        val allApps = context.getPackageVersionCodeMap(AcceptPackageType.ALL).entries
        val systemAppsInAllSize = Collectionx.count<MutableMap.MutableEntry<String, Int>>(allApps, systemAppPredicate)
        Assert.assertTrue(systemAppsInAllSize > 0)
        val userAppsInAllSize = Collectionx.count<MutableMap.MutableEntry<String, Int>>(allApps, userAppPredicate)
        Assert.assertTrue(userAppsInAllSize > 0)
        Assert.assertNotNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(allApps, selfPredicate))

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        val allAndExcludeSelfApps = context.getPackageVersionCodeMap(AcceptPackageType.ALL_AND_EXCLUDE_SELF).entries
        Assert.assertEquals(allApps.size.toLong(), (allAndExcludeSelfApps.size + 1).toLong())
        Assert.assertNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(allAndExcludeSelfApps, selfPredicate))


        /*
         * USER
         */
        val userApps = context.getPackageVersionCodeMap(AcceptPackageType.USER).entries
        Assert.assertTrue(Collectionx.all<MutableMap.MutableEntry<String, Int>>(userApps, userAppPredicate))
        Assert.assertEquals(userAppsInAllSize.toLong(), userApps.size.toLong())
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(userApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(userApps, selfPredicate))
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        val userAndExcludeSelfApps = context.getPackageVersionCodeMap(AcceptPackageType.USER_AND_EXCLUDE_SELF).entries
        Assert.assertTrue(Collectionx.all<MutableMap.MutableEntry<String, Int>>(userAndExcludeSelfApps, userAppPredicate))
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(userApps.size.toLong(), (userAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(userApps.size.toLong(), userAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(userAndExcludeSelfApps, selfPredicate))


        /*
         * SYSTEM
         */
        val systemApps = context.getPackageVersionCodeMap(AcceptPackageType.SYSTEM).entries
        Assert.assertTrue(Collectionx.all<MutableMap.MutableEntry<String, Int>>(systemApps, systemAppPredicate))
        Assert.assertEquals(systemAppsInAllSize.toLong(), systemApps.size.toLong())
        Assert.assertEquals(allApps.size.toLong(), (userApps.size + systemApps.size).toLong())
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(systemApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(systemApps, selfPredicate))
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        val systemAndExcludeSelfApps = context.getPackageVersionCodeMap(AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF).entries
        Assert.assertTrue(Collectionx.all<MutableMap.MutableEntry<String, Int>>(systemAndExcludeSelfApps, systemAppPredicate))
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(systemApps.size.toLong(), (systemAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(systemApps.size.toLong(), systemAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find<MutableMap.MutableEntry<String, Int>>(systemAndExcludeSelfApps, selfPredicate))
        Assert.assertEquals(allAndExcludeSelfApps.size.toLong(), (userAndExcludeSelfApps.size + systemAndExcludeSelfApps.size).toLong())
    }

    @Test
    fun testListPackageName() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val selfPredicate = Predicate<String> { string -> string == selfPackageName }
        val systemAppPredicate = Predicate<String> { string -> context.isSystemAppOr(string, false) }
        val userAppPredicate = Predicate<String> { string -> !context.isSystemAppOr(string, false) }

        /*
         * ALL
         */
        val allApps = context.listPackageName(AcceptPackageType.ALL)
        val systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate)
        Assert.assertTrue(systemAppsInAllSize > 0)
        val userAppsInAllSize = Collectionx.count(allApps, userAppPredicate)
        Assert.assertTrue(userAppsInAllSize > 0)
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate))

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        val allAndExcludeSelfApps = context.listPackageName(AcceptPackageType.ALL_AND_EXCLUDE_SELF)
        Assert.assertEquals(allApps.size.toLong(), (allAndExcludeSelfApps.size + 1).toLong())
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate))


        /*
         * USER
         */
        val userApps = context.listPackageName(AcceptPackageType.USER)
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate))
        Assert.assertEquals(userAppsInAllSize.toLong(), userApps.size.toLong())
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate))
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        val userAndExcludeSelfApps = context.listPackageName(AcceptPackageType.USER_AND_EXCLUDE_SELF)
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate))
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(userApps.size.toLong(), (userAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(userApps.size.toLong(), userAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate))


        /*
         * SYSTEM
         */
        val systemApps = context.listPackageName(AcceptPackageType.SYSTEM)
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate))
        Assert.assertEquals(systemAppsInAllSize.toLong(), systemApps.size.toLong())
        Assert.assertEquals(allApps.size.toLong(), (userApps.size + systemApps.size).toLong())
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate))
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        val systemAndExcludeSelfApps = context.listPackageName(AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate))
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(systemApps.size.toLong(), (systemAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(systemApps.size.toLong(), systemAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate))
        Assert.assertEquals(allAndExcludeSelfApps.size.toLong(), (userAndExcludeSelfApps.size + systemAndExcludeSelfApps.size).toLong())
    }

    @Test
    fun testListPackage() {
        val context = InstrumentationRegistry.getContext()
        val selfPackageName = context.packageName
        val selfPredicate = Predicate<AppPackage> { appPackage -> appPackage.packageName == selfPackageName }
        val systemAppPredicate = Predicate<AppPackage> { appPackage -> context.isSystemAppOr(appPackage.packageName, false) }
        val userAppPredicate = Predicate<AppPackage> { appPackage -> !context.isSystemAppOr(appPackage.packageName, false) }

        /*
         * ALL
         */
        val allApps = context.listPackage(AcceptPackageType.ALL)
        val systemAppsInAllSize = Collectionx.count(allApps, systemAppPredicate)
        Assert.assertTrue(systemAppsInAllSize > 0)
        val userAppsInAllSize = Collectionx.count(allApps, userAppPredicate)
        Assert.assertTrue(userAppsInAllSize > 0)
        Assert.assertNotNull(Collectionx.find(allApps, selfPredicate))

        /*
         * ALL_AND_EXCLUDE_SELF
         */
        val allAndExcludeSelfApps = context.listPackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF)
        Assert.assertEquals(allApps.size.toLong(), (allAndExcludeSelfApps.size + 1).toLong())
        Assert.assertNull(Collectionx.find(allAndExcludeSelfApps, selfPredicate))


        /*
         * USER
         */
        val userApps = context.listPackage(AcceptPackageType.USER)
        Assert.assertTrue(Collectionx.all(userApps, userAppPredicate))
        Assert.assertEquals(userAppsInAllSize.toLong(), userApps.size.toLong())
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(userApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find(userApps, selfPredicate))
        }

        /*
         * USER_AND_EXCLUDE_SELF
         */
        val userAndExcludeSelfApps = context.listPackage(AcceptPackageType.USER_AND_EXCLUDE_SELF)
        Assert.assertTrue(Collectionx.all(userAndExcludeSelfApps, userAppPredicate))
        if (!context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(userApps.size.toLong(), (userAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(userApps.size.toLong(), userAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find(userAndExcludeSelfApps, selfPredicate))


        /*
         * SYSTEM
         */
        val systemApps = context.listPackage(AcceptPackageType.SYSTEM)
        Assert.assertTrue(Collectionx.all(systemApps, systemAppPredicate))
        Assert.assertEquals(systemAppsInAllSize.toLong(), systemApps.size.toLong())
        Assert.assertEquals(allApps.size.toLong(), (userApps.size + systemApps.size).toLong())
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertNotNull(Collectionx.find(systemApps, selfPredicate))
        } else {
            Assert.assertNull(Collectionx.find(systemApps, selfPredicate))
        }

        /*
         * SYSTEM_AND_EXCLUDE_SELF
         */
        val systemAndExcludeSelfApps = context.listPackage(AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
        Assert.assertTrue(Collectionx.all(systemAndExcludeSelfApps, systemAppPredicate))
        if (context.isSystemApp(selfPackageName)) {
            Assert.assertEquals(systemApps.size.toLong(), (systemAndExcludeSelfApps.size + 1).toLong())
        } else {
            Assert.assertEquals(systemApps.size.toLong(), systemAndExcludeSelfApps.size.toLong())
        }
        Assert.assertNull(Collectionx.find(systemAndExcludeSelfApps, selfPredicate))
        Assert.assertEquals(allAndExcludeSelfApps.size.toLong(), (userAndExcludeSelfApps.size + systemAndExcludeSelfApps.size).toLong())
    }

    @Test
    fun testGetOnePackage() {
        val context = InstrumentationRegistry.getContext()

        val allAppPackage = context.getOnePackage(AcceptPackageType.ALL)
        Assert.assertNotNull(allAppPackage)

        val allAndExcludeSelfAppPackage = context.getOnePackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF)
        Assert.assertNotNull(allAndExcludeSelfAppPackage)
        Assert.assertNotEquals(context.packageName, allAndExcludeSelfAppPackage!!.packageName)

        val userAppPackage = context.getOnePackage(AcceptPackageType.USER)
        Assert.assertNotNull(userAppPackage)
        Assert.assertFalse(context.isSystemAppOr(userAppPackage!!.packageName, false))
        Assert.assertFalse(context.isSystemAppOr(userAppPackage.packageName, false))

        val userAndExcludeSelfAppPackage = context.getOnePackage(AcceptPackageType.USER_AND_EXCLUDE_SELF)
        Assert.assertNotNull(userAndExcludeSelfAppPackage)
        Assert.assertFalse(context.isSystemAppOr(userAndExcludeSelfAppPackage!!.packageName, false))
        Assert.assertNotEquals(context.packageName, userAndExcludeSelfAppPackage.packageName)

        val systemAppPackage = context.getOnePackage(AcceptPackageType.SYSTEM)
        Assert.assertNotNull(systemAppPackage)
        Assert.assertTrue(context.isSystemAppOr(systemAppPackage!!.packageName, false))

        val systemAndExcludeSelfAppPackage = context.getOnePackage(AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)
        Assert.assertNotNull(systemAndExcludeSelfAppPackage)
        Assert.assertTrue(context.isSystemAppOr(systemAndExcludeSelfAppPackage!!.packageName, false))
        Assert.assertNotEquals(context.packageName, systemAndExcludeSelfAppPackage.packageName)
    }

    @Test
    fun testCount() {
        val context = InstrumentationRegistry.getContext()

        val allCount = context.countPackage(AcceptPackageType.ALL)
        val allAndExcludeSelfCount = context.countPackage(AcceptPackageType.ALL_AND_EXCLUDE_SELF)

        val userCount = context.countPackage(AcceptPackageType.USER)
        val userAndExcludeSelfCount = context.countPackage(AcceptPackageType.USER_AND_EXCLUDE_SELF)

        val systemCount = context.countPackage(AcceptPackageType.SYSTEM)
        val systemAndExcludeSelfCount = context.countPackage(AcceptPackageType.SYSTEM_AND_EXCLUDE_SELF)

        Assert.assertTrue(allCount > 0)
        Assert.assertEquals((allCount - 1).toLong(), allAndExcludeSelfCount.toLong())
        Assert.assertEquals((userCount - 1).toLong(), userAndExcludeSelfCount.toLong())
        Assert.assertEquals(systemCount.toLong(), systemAndExcludeSelfCount.toLong())
        Assert.assertEquals(allCount.toLong(), (systemCount + userCount).toLong())
        Assert.assertEquals(allAndExcludeSelfCount.toLong(), (systemAndExcludeSelfCount + userAndExcludeSelfCount).toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetPackageFile() {
        val context = InstrumentationRegistry.getContext()

        val packageFile = context.getPackageApkFile(context.packageName)
        Assert.assertTrue(packageFile.path, packageFile.exists())

        Assert.assertNull(context.getPackageApkFileOrNull(context.packageName + "_nonono"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetAppSignatureBytes() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertNotNull(context.getAppSignatureBytes(context.packageName))
        Assert.assertNull(context.getAppSignatureBytesOrNull(context.packageName + "_nonono"))
    }

    @Test
    fun testGetAppIcon() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertNotNull(context.getAppIconDrawable(context.packageName, 0))
        Assert.assertNull(context.getAppIconDrawable(context.packageName + "_nonono", 0))
        Assert.assertNull(context.getAppIconDrawable(context.packageName, 1))

        val bitmap = context.getAppIconBitmap(context.packageName, 0)
        try {
            Assert.assertNotNull(bitmap)
        } finally {
            bitmap?.recycle()
        }
        Assert.assertNull(context.getAppIconBitmap(context.packageName + "_nonono", 0))
        Assert.assertNull(context.getAppIconBitmap(context.packageName, 1))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetApkIcon() {
        val context = InstrumentationRegistry.getContext()

        val selfApkFilePath = context.getPackageApkFile(context.packageName).path

        Assert.assertNotNull(context.getApkIconDrawable(selfApkFilePath))
        Assert.assertNull(context.getApkIconDrawable(selfApkFilePath + "_nonono"))

        val bitmap = context.getApkIconBitmap(selfApkFilePath)
        try {
            Assert.assertNotNull(bitmap)
        } finally {
            bitmap?.recycle()
        }
        Assert.assertNull(context.getApkIconBitmap(selfApkFilePath + "_nonono"))
    }
}
