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

package me.panpf.androidx.test.content;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Pair;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;
import java.util.Map;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import me.panpf.androidx.content.pm.AppPackage;
import me.panpf.androidx.content.pm.Packagex;
import me.panpf.androidx.test.BuildConfig;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.util.Premisex;
import me.panpf.javax.util.Transformer;

@RunWith(AndroidJUnit4.class)
public class PackagexTest {

    @Test
    public void testIsInstalled() {
        Context context = InstrumentationRegistry.getContext();

        Assert.assertTrue(Packagex.isInstalled(context, context.getPackageName()));
        Assert.assertFalse(Packagex.isInstalled(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetVersionCode() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        AppPackage appPackage = Premisex.requireNotNull(Packagex.getOnePackage(context, false, true));

        Assert.assertTrue("versionCode: " + appPackage.versionCode, appPackage.versionCode > 0);
        Assert.assertEquals(appPackage.versionCode, Packagex.getVersionCode(context, appPackage.packageName));
        Assert.assertEquals(-1, Packagex.getVersionCodeOr(context, appPackage.packageName + "_nonono", -1));
    }

    @Test
    public void testGetVersionName() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        AppPackage appPackage = Premisex.requireNotNull(Packagex.getOnePackage(context, false, true));

        Assert.assertTrue("versionName: " + appPackage.versionName, Stringx.isSafe(appPackage.versionName));
        Assert.assertEquals(appPackage.versionName, Packagex.getVersionName(context, appPackage.packageName));
        Assert.assertEquals("unknown", Packagex.getVersionNameOr(context, appPackage.packageName + "_nonono", "unknown"));
        Assert.assertNull(Packagex.getVersionNameOrNull(context, appPackage.packageName + "_nonono"));
    }

    @Test
    public void testGetPackage() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        AppPackage selfAppPackage = Premisex.requireNotNull(Packagex.getPackage(context, context.getPackageName()));

        Assert.assertEquals(BuildConfig.APPLICATION_ID, selfAppPackage.packageName);
        Assert.assertTrue("name: " + selfAppPackage.name, Stringx.isSafe(selfAppPackage.name));
        Assert.assertNotNull(selfAppPackage.versionName);
        Assert.assertTrue("versionCode: " + selfAppPackage.versionCode, selfAppPackage.versionCode >= 0);
        Assert.assertTrue("packageFilePath: " + selfAppPackage.packageFilePath, Stringx.isSafe(selfAppPackage.packageFilePath));
        Assert.assertTrue("packageSize: " + selfAppPackage.packageSize, selfAppPackage.packageSize >= 0);
        Assert.assertTrue("packageLastModifiedTime: " + selfAppPackage.packageLastModifiedTime, selfAppPackage.packageLastModifiedTime >= 0);
        Assert.assertFalse(selfAppPackage.systemApp);
        Assert.assertTrue(selfAppPackage.enabled);

        AppPackage systemAppPackage = Premisex.requireNotNull(Packagex.getPackage(context, Premisex.requireNotNull(Collectionx.find(Packagex.listPackage(context, false, true), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NotNull AppPackage appPackage) {
                return appPackage.systemApp;
            }
        })).packageName));
        Assert.assertTrue(systemAppPackage.systemApp);

        Assert.assertNull(Packagex.getPackageOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testIsSystemApp() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        String systemAppPackageName = Premisex.requireNotNull(Collectionx.find(Packagex.listPackage(context, false, true), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NotNull AppPackage appPackage) {
                return appPackage.systemApp;
            }
        })).packageName;

        Assert.assertTrue(Packagex.isSystemApp(context.getPackageManager().getApplicationInfo(systemAppPackageName, 0)));
        Assert.assertFalse(Packagex.isSystemApp(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0)));

        Assert.assertTrue(Packagex.isSystemApp(context, systemAppPackageName));
        Assert.assertTrue(Packagex.isSystemAppOr(context, context.getPackageName() + "_nonono", true));
    }

    @Test
    public void testListPackageNameAndVersion() {
        final Context context = InstrumentationRegistry.getContext();

        me.panpf.javax.util.Pair<List<android.util.Pair<String, Integer>>, List<android.util.Pair<String, Integer>>> listPairPair = Collectionx.partition(Packagex.listPackageNameAndVersionCode(context, false, false), new Predicate<android.util.Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull android.util.Pair<String, Integer> stringIntegerPair) {
                return Packagex.isSystemAppOr(context, stringIntegerPair.first, false);
            }
        });
        Assert.assertTrue(!listPairPair.first.isEmpty());
        Assert.assertTrue(!listPairPair.second.isEmpty());

        me.panpf.javax.util.Pair<List<android.util.Pair<String, Integer>>, List<android.util.Pair<String, Integer>>> listPairPairWithoutSystem = Collectionx.partition(Packagex.listPackageNameAndVersionCode(context, true, false), new Predicate<android.util.Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull android.util.Pair<String, Integer> stringIntegerPair) {
                return Packagex.isSystemAppOr(context, stringIntegerPair.first, false);
            }
        });
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty());
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty());

        Assert.assertNotNull(Collectionx.find(Packagex.listPackageNameAndVersionCode(context, false, false), new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull Pair<String, Integer> stringIntegerPair) {
                return stringIntegerPair.first.equals(context.getPackageName());
            }
        }));

        Assert.assertNull(Collectionx.find(Packagex.listPackageNameAndVersionCode(context, false, true), new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull Pair<String, Integer> stringIntegerPair) {
                return stringIntegerPair.first.equals(context.getPackageName());
            }
        }));
    }

    @Test
    public void testListPackageNameAndVersionMap() {
        final Context context = InstrumentationRegistry.getContext();

        me.panpf.javax.util.Pair<List<android.util.Pair<String, Integer>>, List<android.util.Pair<String, Integer>>> listPairPair = Collectionx.partition(Collectionx.map(Packagex.listPackageNameAndVersionCodeMap(context, false, false).entrySet(), new Transformer<Map.Entry<String, Integer>, Pair<String, Integer>>() {
            @NotNull
            @Override
            public Pair<String, Integer> transform(@NotNull Map.Entry<String, Integer> stringIntegerEntry) {
                return new Pair<>(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            }
        }), new Predicate<android.util.Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull android.util.Pair<String, Integer> stringIntegerPair) {
                return Packagex.isSystemAppOr(context, stringIntegerPair.first, false);
            }
        });
        Assert.assertTrue(!listPairPair.first.isEmpty());
        Assert.assertTrue(!listPairPair.second.isEmpty());

        me.panpf.javax.util.Pair<List<android.util.Pair<String, Integer>>, List<android.util.Pair<String, Integer>>> listPairPairWithoutSystem = Collectionx.partition(Collectionx.map(Packagex.listPackageNameAndVersionCodeMap(context, true, false).entrySet(), new Transformer<Map.Entry<String, Integer>, Pair<String, Integer>>() {
            @NotNull
            @Override
            public Pair<String, Integer> transform(@NotNull Map.Entry<String, Integer> stringIntegerEntry) {
                return new Pair<>(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            }
        }), new Predicate<android.util.Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull android.util.Pair<String, Integer> stringIntegerPair) {
                return Packagex.isSystemAppOr(context, stringIntegerPair.first, false);
            }
        });
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty());
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty());

        Assert.assertNotNull(Collectionx.find(Collectionx.map(Packagex.listPackageNameAndVersionCodeMap(context, false, false).entrySet(), new Transformer<Map.Entry<String, Integer>, Pair<String, Integer>>() {
            @NotNull
            @Override
            public Pair<String, Integer> transform(@NotNull Map.Entry<String, Integer> stringIntegerEntry) {
                return new Pair<>(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            }
        }), new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull Pair<String, Integer> stringIntegerPair) {
                return stringIntegerPair.first.equals(context.getPackageName());
            }
        }));

        Assert.assertNull(Collectionx.find(Collectionx.map(Packagex.listPackageNameAndVersionCodeMap(context, false, true).entrySet(), new Transformer<Map.Entry<String, Integer>, Pair<String, Integer>>() {
            @NotNull
            @Override
            public Pair<String, Integer> transform(@NotNull Map.Entry<String, Integer> stringIntegerEntry) {
                return new Pair<>(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            }
        }), new Predicate<Pair<String, Integer>>() {
            @Override
            public boolean accept(@NotNull Pair<String, Integer> stringIntegerPair) {
                return stringIntegerPair.first.equals(context.getPackageName());
            }
        }));
    }

    @Test
    public void testListPackageName() {
        final Context context = InstrumentationRegistry.getContext();

        me.panpf.javax.util.Pair<List<String>, List<String>> listPairPair = Collectionx.partition(Packagex.listPackageName(context, false, false), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String packageName) {
                return Packagex.isSystemAppOr(context, packageName, false);
            }
        });
        Assert.assertTrue(!listPairPair.first.isEmpty());
        Assert.assertTrue(!listPairPair.second.isEmpty());

        me.panpf.javax.util.Pair<List<String>, List<String>> listPairPairWithoutSystem = Collectionx.partition(Packagex.listPackageName(context, true, false), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String packageName) {
                return Packagex.isSystemAppOr(context, packageName, false);
            }
        });
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty());
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty());

        Assert.assertNotNull(Collectionx.find(Packagex.listPackageName(context, false, false), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String packageName) {
                return packageName.equals(context.getPackageName());
            }
        }));

        Assert.assertNull(Collectionx.find(Packagex.listPackageName(context, false, true), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String packageName) {
                return packageName.equals(context.getPackageName());
            }
        }));
    }

    @Test
    public void testListPackage() {
        final Context context = InstrumentationRegistry.getContext();

        me.panpf.javax.util.Pair<List<AppPackage>, List<AppPackage>> listPairPair = Collectionx.partition(Packagex.listPackage(context, false, false), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NotNull AppPackage appPackage) {
                return Packagex.isSystemAppOr(context, appPackage.packageName, false);
            }
        });
        Assert.assertTrue(!listPairPair.first.isEmpty());
        Assert.assertTrue(!listPairPair.second.isEmpty());

        me.panpf.javax.util.Pair<List<AppPackage>, List<AppPackage>> listPairPairWithoutSystem = Collectionx.partition(Packagex.listPackage(context, true, false), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NotNull AppPackage appPackage) {
                return Packagex.isSystemAppOr(context, appPackage.packageName, false);
            }
        });
        Assert.assertTrue(listPairPairWithoutSystem.first.isEmpty());
        Assert.assertTrue(!listPairPairWithoutSystem.second.isEmpty());

        Assert.assertNotNull(Collectionx.find(Packagex.listPackage(context, false, false), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NotNull AppPackage appPackage) {
                return appPackage.packageName.equals(context.getPackageName());
            }
        }));

        Assert.assertNull(Collectionx.find(Packagex.listPackage(context, false, true), new Predicate<AppPackage>() {
            @Override
            public boolean accept(@NotNull AppPackage appPackage) {
                return appPackage.packageName.equals(context.getPackageName());
            }
        }));

        Assert.assertEquals(3, Packagex.listPackage(context, false, false, 3).size());
    }

    @Test
    public void testGetOnePackage() {
        final Context context = InstrumentationRegistry.getContext();

        AppPackage allAppPackage = Packagex.getOnePackage(context, false, false);
        Assert.assertNotNull(allAppPackage);

        AppPackage notSelfAppPackage = Packagex.getOnePackage(context, false, true);
        Assert.assertNotNull(notSelfAppPackage);
        Assert.assertNotEquals(context.getPackageName(), notSelfAppPackage.packageName);

        AppPackage notSystemAppPackage = Packagex.getOnePackage(context, true, false);
        Assert.assertNotNull(notSystemAppPackage);
        Assert.assertFalse(Packagex.isSystemAppOr(context, notSystemAppPackage.packageName, false));

        AppPackage notSystemNotSelfAppPackage = Packagex.getOnePackage(context, true, true);
        Assert.assertNotNull(notSystemNotSelfAppPackage);
        Assert.assertFalse(Packagex.isSystemAppOr(context, notSystemNotSelfAppPackage.packageName, false));
        Assert.assertNotEquals(context.getPackageName(), notSystemNotSelfAppPackage.packageName);
    }

    @Test
    public void testCount() {
        final Context context = InstrumentationRegistry.getContext();

        int allCount = Packagex.count(context, false, false);
        int notSelfCount = Packagex.count(context, false, true);

        int notSystemCount = Packagex.count(context, true, false);
        int notSystemNotSelfCount = Packagex.count(context, true, true);

        Assert.assertTrue(allCount > 0);
        Assert.assertEquals(allCount - 1, notSelfCount);
        Assert.assertEquals(notSystemCount - 1, notSystemNotSelfCount);
    }

    @Test
    public void testGetPackageFile() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();

        File packageFile = Packagex.getPackageApkFile(context, context.getPackageName());
        Assert.assertTrue(packageFile.getPath(), packageFile.exists());

        Assert.assertNull(Packagex.getPackageApkFileOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetAppSignatureBytes() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();

        Assert.assertNotNull(Packagex.getAppSignatureBytes(context, context.getPackageName()));
        Assert.assertNull(Packagex.getAppSignatureBytesOrNull(context, context.getPackageName() + "_nonono"));
    }

    @Test
    public void testGetAppIcon() {
        final Context context = InstrumentationRegistry.getContext();

        Assert.assertNotNull(Packagex.getAppIconDrawable(context, context.getPackageName(), 0));
        Assert.assertNull(Packagex.getAppIconDrawable(context, context.getPackageName() + "_nonono", 0));
        Assert.assertNull(Packagex.getAppIconDrawable(context, context.getPackageName(), 1));

        Bitmap bitmap = Packagex.getAppIconBitmap(context, context.getPackageName(), 0);
        try {
            Assert.assertNotNull(bitmap);
        } finally {
            if (bitmap != null) bitmap.recycle();
        }
        Assert.assertNull(Packagex.getAppIconBitmap(context, context.getPackageName() + "_nonono", 0));
        Assert.assertNull(Packagex.getAppIconBitmap(context, context.getPackageName(), 1));
    }

    @Test
    public void testGetApkIcon() throws PackageManager.NameNotFoundException {
        final Context context = InstrumentationRegistry.getContext();

        String selfApkFilePath = Packagex.getPackageApkFile(context, context.getPackageName()).getPath();

        Assert.assertNotNull(Packagex.getApkIconDrawable(context, selfApkFilePath));
        Assert.assertNull(Packagex.getApkIconDrawable(context, selfApkFilePath + "_nonono"));

        Bitmap bitmap = Packagex.getApkIconBitmap(context, selfApkFilePath);
        try {
            Assert.assertNotNull(bitmap);
        } finally {
            if (bitmap != null) bitmap.recycle();
        }
        Assert.assertNull(Packagex.getApkIconBitmap(context, selfApkFilePath + "_nonono"));
    }
}
