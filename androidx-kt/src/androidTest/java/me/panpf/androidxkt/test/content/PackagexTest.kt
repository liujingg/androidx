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
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.content.pm.*
import me.panpf.androidxkt.test.BuildConfig
import me.panpf.javax.collections.Collectionx
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

        val appPackage = context.getOnePackage(false, true).requireNotNull()

        Assert.assertTrue("versionCode: " + appPackage.versionCode, appPackage.versionCode > 0)
        Assert.assertEquals(appPackage.versionCode.toLong(), context.getPackageVersionCode(appPackage.packageName).toLong())
        Assert.assertEquals(-1, context.getPackageVersionCodeOr(appPackage.packageName + "_nonono", -1).toLong())
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testGetVersionName() {
        val context = InstrumentationRegistry.getContext()

        val appPackage = context.getOnePackage(false, true).requireNotNull()

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

        val systemAppPackage = context.getPackage(context.listPackage(false, true).find { appPackage -> appPackage.systemApp }.requireNotNull().packageName).requireNotNull()
        Assert.assertTrue(systemAppPackage.systemApp)

        Assert.assertNull(context.getPackageOrNull(context.packageName + "_nonono"))
    }

    @Test
    @Throws(PackageManager.NameNotFoundException::class)
    fun testIsSystemApp() {
        val context = InstrumentationRegistry.getContext()

        val systemAppPackageName = context.listPackage(false, true).find { appPackage -> appPackage.systemApp }.requireNotNull().packageName

        Assert.assertTrue(context.packageManager.getApplicationInfo(systemAppPackageName, 0).isSystemApp())
        Assert.assertFalse(context.packageManager.getApplicationInfo(context.packageName, 0).isSystemApp())

        Assert.assertTrue(context.isSystemApp(systemAppPackageName))
        Assert.assertTrue(context.isSystemAppOr(context.packageName + "_nonono", true))
    }

    @Test
    fun testListPackageNameAndVersion() {
        val context = InstrumentationRegistry.getContext()

        val listPairPair = Collectionx.partition(context.listPackageNameAndVersionCode(false, false)) { stringIntegerPair -> context.isSystemAppOr(stringIntegerPair.first, false) }
        Assert.assertTrue(!listPairPair.first.isEmpty())
        Assert.assertTrue(!listPairPair.second.isEmpty())

        val listPairPairWithoutSystem = Collectionx.partition(context.listPackageNameAndVersionCode(true, false)) { stringIntegerPair -> context.isSystemAppOr(stringIntegerPair.first, false) }
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty())
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty())

        Assert.assertNotNull(Collectionx.find(context.listPackageNameAndVersionCode(false, false)) { stringIntegerPair -> stringIntegerPair.first == context.packageName })

        Assert.assertNull(Collectionx.find(context.listPackageNameAndVersionCode(false, true)) { stringIntegerPair -> stringIntegerPair.first == context.packageName })
    }

    @Test
    fun testListPackageNameAndVersionMap() {
        val context = InstrumentationRegistry.getContext()

        val listPairPair = context.listPackageNameAndVersionCodeMap(false, false).toList().partition { context.isSystemAppOr(it.first, false) }
        Assert.assertTrue(!listPairPair.first.isEmpty())
        Assert.assertTrue(!listPairPair.second.isEmpty())

        val listPairPairWithoutSystem = context.listPackageNameAndVersionCodeMap(true, false).toList().partition { context.isSystemAppOr(it.first, false) }
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty())
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty())

        Assert.assertNotNull(context.listPackageNameAndVersionCodeMap(false, false).toList().find { it.first == context.packageName })
        Assert.assertNull(context.listPackageNameAndVersionCodeMap(false, true).toList().find { it.first == context.packageName })
    }

    @Test
    fun testListPackageName() {
        val context = InstrumentationRegistry.getContext()

        val listPairPair = context.listPackageName(false, false).partition { packageName -> context.isSystemAppOr(packageName, false) }
        Assert.assertTrue(!listPairPair.first.isEmpty())
        Assert.assertTrue(!listPairPair.second.isEmpty())

        val listPairPairWithoutSystem = context.listPackageName(true, false).partition { packageName -> context.isSystemAppOr(packageName, false) }
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty())
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty())

        Assert.assertNotNull(context.listPackageName(false, false).find { packageName -> packageName == context.packageName })

        Assert.assertNull(context.listPackageName(false, true).find { packageName -> packageName == context.packageName })
    }

    @Test
    fun testListPackage() {
        val context = InstrumentationRegistry.getContext()

        val listPairPair = context.listPackage(false, false).partition { appPackage -> context.isSystemAppOr(appPackage.packageName, false) }
        Assert.assertTrue(!listPairPair.first.isEmpty())
        Assert.assertTrue(!listPairPair.second.isEmpty())

        val listPairPairWithoutSystem = context.listPackage(true, false).partition { appPackage -> context.isSystemAppOr(appPackage.packageName, false) }
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty())
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty())

        Assert.assertNotNull(context.listPackage(false, false).find { appPackage -> appPackage.packageName == context.packageName })

        Assert.assertNull(context.listPackage(false, true).find { appPackage -> appPackage.packageName == context.packageName })

        Assert.assertEquals(3, context.listPackage(false, false, 3).size.toLong())
    }

    @Test
    fun testGetOnePackage() {
        val context = InstrumentationRegistry.getContext()

        val allAppPackage = context.getOnePackage(false, false)
        Assert.assertNotNull(allAppPackage)

        val notSelfAppPackage = context.getOnePackage(false, true).requireNotNull()
        Assert.assertNotEquals(context.packageName, notSelfAppPackage.packageName)

        val notSystemAppPackage = context.getOnePackage(true, false).requireNotNull()
        Assert.assertFalse(context.isSystemAppOr(notSystemAppPackage.packageName, false))

        val notSystemNotSelfAppPackage = context.getOnePackage(true, true).requireNotNull()
        Assert.assertFalse(context.isSystemAppOr(notSystemNotSelfAppPackage.packageName, false))
        Assert.assertNotEquals(context.packageName, notSystemNotSelfAppPackage.packageName)
    }

    @Test
    fun testCount() {
        val context = InstrumentationRegistry.getContext()

        val allCount = context.countPackage(false, false)
        val notSelfCount = context.countPackage(false, true)

        val notSystemCount = context.countPackage(true, false)
        val notSystemNotSelfCount = context.countPackage(true, true)

        Assert.assertTrue(allCount > 0)
        Assert.assertEquals((allCount - 1).toLong(), notSelfCount.toLong())
        Assert.assertEquals((notSystemCount - 1).toLong(), notSystemNotSelfCount.toLong())
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
