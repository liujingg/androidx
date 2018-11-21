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

package me.panpf.androidx.content.pm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.panpf.androidx.graphics.drawable.Drawablex;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Premisex;

public class Packagex {

    private Packagex() {
    }


    /**
     * Return true if the app with the specified packageName is installed
     */
    public static boolean isInstalled(@NonNull Context context, @NonNull String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }


    /**
     * Get the versionCode of the app for the specified packageName
     */
    public static int getVersionCode(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA).versionCode;
    }

    /**
     * Get the versionCode of the app for the specified packageName, return to defaultValue if not installed
     */
    public static int getVersionCodeOr(@NonNull Context context, @NonNull String packageName, int defaultValue) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }

        return packageInfo.versionCode;
    }


    /**
     * Get the versionName of the app for the specified packageName
     */
    @NonNull
    public static String getVersionName(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA).versionName;
    }

    /**
     * Get the versionName of the app for the specified packageName, return to defaultValue if not installed
     */
    @NonNull
    public static String getVersionNameOr(@NonNull Context context, @NonNull String packageName, @NotNull String defaultValue) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return defaultValue;
        }

        return packageInfo.versionName;
    }

    /**
     * Get the versionName of the app for the specified packageName, return to null if not installed
     */
    @Nullable
    public static String getVersionNameOrNull(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        return packageInfo.versionName;
    }


    /**
     * Get information about the app with the specified packageName
     */
    @NonNull
    public static AppPackage getPackage(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        return packageInfoToAppPackage(context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA), context.getPackageManager());
    }

    /**
     * Get information about the app with the specified packageName, return to null if not installed
     */
    @Nullable
    public static AppPackage getPackageOrNull(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        return packageInfoToAppPackage(packageInfo, packageManager);
    }


    /**
     * Return true if it is a system APP
     */
    public static boolean isSystemApp(@NonNull ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
    }

    /**
     * Return true if the app with the specified packageName is the system APP
     */
    public static boolean isSystemApp(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
    }

    /**
     * Return true if the app with the specified packageName is the system APP, return to defaultValue if not installed
     */
    public static boolean isSystemAppOr(@NonNull Context context, @NonNull String packageName, boolean defaultValue) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return defaultValue;
        }
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
    }


    /**
     * List the packageName and versionCode of all installed APPs
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     */
    @NonNull
    public static List<Pair<String, Integer>> listPackageNameAndVersionCode(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return new ArrayList<>(0);
        }

        List<Pair<String, Integer>> appsSet = new ArrayList<>(packageInfoList.size());
        for (PackageInfo packageInfo : packageInfoList) {
            if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo)) {
                continue;
            }

            appsSet.add(new Pair<>(packageInfo.packageName, packageInfo.versionCode));
        }
        return appsSet;
    }

    /**
     * Get the packageName and versionCode of all installed apps Map
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     */
    @NonNull
    public static android.support.v4.util.ArrayMap<String, Integer> listPackageNameAndVersionCodeMap(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return new android.support.v4.util.ArrayMap<>(0);
        }

        android.support.v4.util.ArrayMap<String, Integer> appsSet = new android.support.v4.util.ArrayMap<>();
        for (PackageInfo packageInfo : packageInfoList) {
            if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo)) {
                continue;
            }

            appsSet.put(packageInfo.packageName, packageInfo.versionCode);
        }
        return appsSet;
    }

    /**
     * List the packageName of all installed apps
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     */
    @NonNull
    public static List<String> listPackageName(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList != null && !packageInfoList.isEmpty()) {
            List<String> appsSet = new ArrayList<>();
            for (PackageInfo packageInfo : packageInfoList) {
                if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                    continue;
                }

                if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo)) {
                    continue;
                }

                appsSet.add(packageInfo.packageName);
            }
            return appsSet;
        } else {
            return new ArrayList<>(0);
        }
    }

    /**
     * List information for all installed apps
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     * @param size             How many apps to get. -1: all
     */
    @NonNull
    public static List<AppPackage> listPackage(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf, int size) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return new ArrayList<>(0);
        }

        ArrayList<AppPackage> packageArrayList = new ArrayList<>(size > 0 ? size : packageInfoList.size());
        int index = 0;
        for (PackageInfo packageInfo : packageInfoList) {
            if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo)) {
                continue;
            }

            AppPackage appPackage = packageInfoToAppPackage(packageInfo, packageManager);
            packageArrayList.add(appPackage);
            index++;
            if (size > 0 && index >= size) {
                break;
            }
        }
        return packageArrayList;
    }

    /**
     * List information for all installed apps
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     */
    @NonNull
    public static List<AppPackage> listPackage(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf) {
        return listPackage(context, excludeSystemApp, excludeSelf, -1);
    }


    /**
     * Get information about an app
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     */
    @Nullable
    public static AppPackage getOnePackage(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf) {
        List<AppPackage> appPackageList = listPackage(context, excludeSystemApp, excludeSelf, 1);
        return appPackageList.size() >= 1 ? appPackageList.get(0) : null;
    }


    /**
     * Get the number of installed apps
     *
     * @param excludeSystemApp If true, exclude yourself exclude system apps
     * @param excludeSelf      If true, exclude yourself
     */
    public static int count(@NonNull Context context, boolean excludeSystemApp, boolean excludeSelf) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager crashes internally on dazen X7 4.4.4 and Coolpad Y803-8 5.1 models
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (PackageInfo packageInfo : packageInfoList) {
            if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo)) {
                continue;
            }

            count++;
        }
        return count;
    }


    @NonNull
    @SuppressWarnings("WeakerAccess")
    public static AppPackage packageInfoToAppPackage(@NonNull PackageInfo packageInfo, @NonNull PackageManager packageManager) {
        ApplicationInfo applicationInfo = Premisex.checkNotNull(packageInfo.applicationInfo, "applicationInfo");
        CharSequence label = applicationInfo.loadLabel(packageManager);
        File packageFile = new File(applicationInfo.sourceDir);
        return new AppPackage(label.toString(), Stringx.orEmpty(applicationInfo.packageName), packageInfo.versionCode,
                Stringx.orEmpty(packageInfo.versionName), packageFile.getPath(), packageFile.length(),
                packageFile.lastModified(), isSystemApp(applicationInfo), applicationInfo.enabled);
    }


    /**
     * Get the apk file of the app with the specified packageName
     */
    @NonNull
    public static File getPackageApkFile(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        return new File(applicationInfo.sourceDir);
    }

    /**
     * Get the apk file of the app with the specified packageName, return to null if not installed
     */
    @Nullable
    public static File getPackageApkFileOrNull(@NonNull Context context, @NonNull String packageName) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return new File(applicationInfo.sourceDir);
    }


    /**
     * Get the signature data of the app with the specified packageName
     */
    @NonNull
    public static byte[] getAppSignatureBytes(@NonNull Context context, @NonNull String packageName) throws NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
        if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
            return packageInfo.signatures[0].toByteArray();
        } else {
            throw new IllegalArgumentException(packageName + " signatures is empty");
        }
    }

    /**
     * Get the signature data of the app with the specified packageName, return to null if not installed
     */
    @Nullable
    public static byte[] getAppSignatureBytesOrNull(@NonNull Context context, @NonNull String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                return packageInfo.signatures[0].toByteArray();
            } else {
                return null;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Get the icon Drawable of the app of the specified packageName
     *
     * @param versionCode App versionCode. Returns null if versionCode is inconsistent, -1: ignores versionCode
     */
    @Nullable
    public static Drawable getAppIconDrawable(@NonNull Context context, @NonNull String packageName, int versionCode) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        if (versionCode > -1 && packageInfo.versionCode != versionCode) {
            return null;
        }

        return packageInfo.applicationInfo.loadIcon(pm);
    }

    /**
     * Get the icon Bitmap of the app of the specified packageName
     *
     * @param versionCode App versionCode. Returns null if versionCode is inconsistent, -1: ignores versionCode
     */
    @Nullable
    public static Bitmap getAppIconBitmap(@NonNull Context context, @NonNull String packageName, int versionCode) {
        Drawable drawable = getAppIconDrawable(context, packageName, versionCode);
        return drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0 ? null
                : Drawablex.toBitmapWithIntrinsicSize(drawable);
    }


    /**
     * Get the icon Drawable of the specified apk file
     */
    @Nullable
    public static Drawable getApkIconDrawable(@NonNull Context context, @NonNull String apkFilePath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA);
        if (packageInfo == null) {
            return null;
        }

        packageInfo.applicationInfo.sourceDir = apkFilePath;
        packageInfo.applicationInfo.publicSourceDir = apkFilePath;
        return packageInfo.applicationInfo.loadIcon(pm);
    }

    /**
     * Get the icon Bitmap of the specified apk file
     */
    @Nullable
    public static Bitmap getApkIconBitmap(@NonNull Context context, @NonNull String apkFilePath) {
        Drawable drawable = getApkIconDrawable(context, apkFilePath);
        return drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0 ? null
                : Drawablex.toBitmapWithIntrinsicSize(drawable);
    }
}