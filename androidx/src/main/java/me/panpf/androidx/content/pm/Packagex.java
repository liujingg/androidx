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
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class Packagex {

    /**
     * 是否已安装指定包名的 app
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     */
    public static boolean isInstalled(@NonNull Context context, @NonNull String packageName) {
        //noinspection ConstantConditions
        if (TextUtils.isEmpty(packageName) || context == null) {
            return false;
        }

        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 获取已安装版本号
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @return -1: 未安装
     */
    public static int getVersionCode(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return -1;
        }

        return packageInfo.versionCode;
    }

    /**
     * 获取已安装版本名称
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     */
    @Nullable
    public static String getVersionName(@NonNull Context context, @NonNull String packageName) {
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
     * 获取已安装的指定包名 app 的信息
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @return null：未安装
     */
    @Nullable
    @SuppressWarnings("WeakerAccess")
    public static AppPackage get(@NonNull Context context, @NonNull String packageName) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            return null;
        }

        return assembleAppPackage(packageManager, packageInfo);
    }

    /**
     * 根据标记信息判断是否是系统 app
     *
     * @param appFlags app 标记信息，来自 {@link ApplicationInfo}
     */
    public static boolean isSystemApp(int appFlags) {
        return (appFlags & ApplicationInfo.FLAG_SYSTEM) == 1;
    }

    /**
     * 是否是系统 app
     *
     * @param appInfo {@link ApplicationInfo}
     */
    public static boolean isSystemApp(@NonNull ApplicationInfo appInfo) {
        return isSystemApp(appInfo.flags);
    }

    /**
     * 判断指定包名的已安装 app 是否是系统 app
     *
     * @param packageManager {@link PackageManager}
     * @param packageName    app 包名
     * @return false: 未安装或不是系统 app
     */
    public static boolean isSystemApp(@NonNull PackageManager packageManager, @NonNull String packageName) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return isSystemApp(applicationInfo.flags);
    }

    /**
     * 判断指定包名的已安装 app 是否是系统 app
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @return false: 未安装或不是系统 app
     */
    public static boolean isSystemApp(@NonNull Context context, @NonNull String packageName) {
        return isSystemApp(context.getPackageManager(), packageName);
    }


    /**
     * 获取所有已安装 app 的包名和版本号 Map
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @return 所有已安装 app 的包名和版本号集合
     */
    @Nullable
    @WorkerThread
    public static Map<String, Integer> getAllAppIdAndVersionCodeMap(Context context, boolean excludeSystemApp, boolean excludeSelf) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
        }
        if (packageInfoList != null && !packageInfoList.isEmpty()) {
            Map<String, Integer> appsSet = new android.support.v4.util.ArrayMap<>();
            for (PackageInfo packageInfo : packageInfoList) {
                if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                    continue;
                }

                if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                    continue;
                }

                appsSet.put(packageInfo.packageName, packageInfo.versionCode);
            }
            return appsSet;
        } else {
            return null;
        }
    }

    /**
     * 获取所有已安装 app 的包名和版本号集合
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @return 所有已安装 app 的包名和版本号集合
     */
    @Nullable
    @WorkerThread
    public static Set<Map.Entry<String, Integer>> getAllAppIdAndVersionCodeSet(Context context, boolean excludeSystemApp, boolean excludeSelf) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
        }
        if (packageInfoList != null && !packageInfoList.isEmpty()) {
            Set<Map.Entry<String, Integer>> appsSet = new android.support.v4.util.ArraySet<>();
            for (PackageInfo packageInfo : packageInfoList) {
                if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                    continue;
                }

                if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                    continue;
                }

                appsSet.add(new AbstractMap.SimpleEntry<>(packageInfo.packageName, packageInfo.versionCode));
            }
            return appsSet;
        } else {
            return null;
        }
    }

    /**
     * 获取所有已安装 app 的包名
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @return 所有已安装 app 的包名集合
     */
    @Nullable
    @WorkerThread
    public static Set<String> getAllAppId(Context context, @SuppressWarnings("SameParameterValue") boolean excludeSystemApp, boolean excludeSelf) {
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
        }
        if (packageInfoList != null && !packageInfoList.isEmpty()) {
            Set<String> appsSet = new android.support.v4.util.ArraySet<>();
            for (PackageInfo packageInfo : packageInfoList) {
                if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                    continue;
                }

                if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                    continue;
                }

                appsSet.add(packageInfo.packageName);
            }
            return appsSet;
        } else {
            return null;
        }
    }

    /**
     * 获取所有已安装 app
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @param size             最多取多少个应用
     * @return 所有已安装 app 列表
     */
    @SuppressWarnings("WeakerAccess")
    @Nullable
    @WorkerThread
    public static List<AppPackage> getAllApp(@NonNull Context context, @SuppressWarnings("SameParameterValue") boolean excludeSystemApp, boolean excludeSelf, int size) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return null;
        }

        ArrayList<AppPackage> packageArrayList = new ArrayList<>(size > 0 ? size : packageInfoList.size());
        int index = 0;
        for (PackageInfo packageInfo : packageInfoList) {
            if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                continue;
            }

            AppPackage appPackage = assembleAppPackage(packageManager, packageInfo);
            if (appPackage != null) {
                packageArrayList.add(appPackage);
                index++;
            }
            if (size > 0 && index >= size) {
                break;
            }
        }
        return packageArrayList;
    }

    /**
     * 获取所有已安装 app
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @return 所有已安装 app 列表
     */
    @Nullable
    @WorkerThread
    public static List<AppPackage> getAllApp(@NonNull Context context, @SuppressWarnings("SameParameterValue") boolean excludeSystemApp, boolean excludeSelf) {
        return getAllApp(context, excludeSystemApp, excludeSelf, -1);
    }

    /**
     * 获取已安装的指定包名 app 的信息
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @return null：未安装
     */
    @Nullable
    @SuppressWarnings("WeakerAccess")
    public static AppPackage getOne(@NonNull Context context, @SuppressWarnings("SameParameterValue") boolean excludeSystemApp, boolean excludeSelf) {
        List<AppPackage> appPackageList = getAllApp(context, excludeSystemApp, excludeSelf, 1);
        return appPackageList != null && appPackageList.size() >= 1 ? appPackageList.get(0) : null;
    }

    /**
     * 统计已安装 app 个数
     *
     * @param context          {@link Context}
     * @param excludeSystemApp 是否排除系统应用
     * @param excludeSelf      是否排除自己
     * @return 已安装 app 个数
     */
    @WorkerThread
    public static int count(@NonNull Context context, @SuppressWarnings("SameParameterValue") boolean excludeSystemApp, boolean excludeSelf) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = null;
        try {
            packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);
        } catch (NullPointerException e) {
            e.printStackTrace();
            // ApplicationPackageManager 内部在 dazen X7 4.4.4 和 Coolpad Y803-8 5.1 机型上会崩溃
        }
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (PackageInfo packageInfo : packageInfoList) {
            if (excludeSelf && context.getPackageName().equals(packageInfo.packageName)) {
                continue;
            }

            if (excludeSystemApp && isSystemApp(packageInfo.applicationInfo.flags)) {
                continue;
            }

            count++;
        }
        return count;
    }

    private static AppPackage assembleAppPackage(@NonNull PackageManager packageManager, @NonNull PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) {
            return null;
        }

        AppPackage appPackage = new AppPackage();

        CharSequence label = applicationInfo.loadLabel(packageManager);
        appPackage.name = label != null ? label.toString() : "";
        appPackage.packageName = applicationInfo.packageName;
        appPackage.versionCode = packageInfo.versionCode;
        appPackage.versionName = packageInfo.versionName;

        appPackage.packageFilePath = applicationInfo.sourceDir;
        File packageFile = new File(applicationInfo.sourceDir);
        appPackage.packageSize = packageFile.length();
        appPackage.packageLastModifiedTime = packageFile.lastModified();

        appPackage.systemApp = isSystemApp(applicationInfo.flags);
        appPackage.enabled = applicationInfo.enabled;
        return appPackage;
    }

    /**
     * 获取指定 app 的安装包文件
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @return app 的安装包文件
     */
    @Nullable
    @WorkerThread
    public static File getAppPackageFile(@NonNull Context context, @NonNull String packageName) {
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
     * 获取指定 app 的签名字节数组
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @return app 的签名字节数组
     */
    @Nullable
    @WorkerThread
    public static byte[] getAppSignatureBytes(@NonNull Context context, @NonNull String packageName) {
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
     * 获取已安装 app 图标的 Drawable 版本
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @param versionCode app 版本号，如果版本号小于等于 0 则不匹配版本，否则版本必须一致才能返回图标
     * @return app 图标
     */
    @Nullable
    @WorkerThread
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
     * 获取已安装 app 图标的 bitmap 版本，如果图标不是 BitmapDrawable 则创建新的 bitmap
     *
     * @param context     {@link Context}
     * @param packageName app 包名
     * @param versionCode app 版本号，如果版本号小于等于 0 则不匹配版本，否则版本必须一致才能返回图标
     * @return app 图标
     */
    @Nullable
    @WorkerThread
    public static Bitmap getAppIconBitmap(@NonNull Context context, @NonNull String packageName, int versionCode) {
        Drawable drawable = getAppIconDrawable(context, packageName, versionCode);
        if (drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 获取指定 apk 文件的图标的 Drawable 版本
     *
     * @param context     {@link Context}
     * @param apkFilePath apk 文件路径
     * @return apk 文件的图标
     */
    @Nullable
    @WorkerThread
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
     * 获取指定 apk 文件的图标的 bitmap 版本，如果图标不是 BitmapDrawable 则创建新的 bitmap
     *
     * @param context     {@link Context}
     * @param apkFilePath apk 文件路径
     * @return apk 文件的图标
     */
    @Nullable
    @WorkerThread
    public static Bitmap getApkIconBitmap(@NonNull Context context, @NonNull String apkFilePath) {
        Drawable drawable = getApkIconDrawable(context, apkFilePath);
        if (drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }

    public static class AppPackage implements Parcelable {
        public static final Creator<AppPackage> CREATOR = new Creator<AppPackage>() {
            @Override
            public AppPackage createFromParcel(Parcel in) {
                return new AppPackage(in);
            }

            @Override
            public AppPackage[] newArray(int size) {
                return new AppPackage[size];
            }
        };
        /**
         * app 名称
         */
        @SuppressWarnings("WeakerAccess")
        public String name;
        /**
         * app 包名
         */
        @SuppressWarnings("WeakerAccess")
        public String packageName;
        /**
         * app 版本号
         */
        @SuppressWarnings("WeakerAccess")
        public int versionCode;
        /**
         * app 版本名称
         */
        @SuppressWarnings("WeakerAccess")
        public String versionName;
        /**
         * app 安装包路径
         */
        @SuppressWarnings("WeakerAccess")
        public String packageFilePath;
        /**
         * app 安装包大小
         */
        @SuppressWarnings("WeakerAccess")
        public long packageSize;
        /**
         * app 安装包最后修改时间
         */
        @SuppressWarnings("WeakerAccess")
        public long packageLastModifiedTime;
        /**
         * app 是否是系统应用
         */
        @SuppressWarnings("WeakerAccess")
        public boolean systemApp;
        /**
         * app 未被禁用（通常只有系统自带 app 可以被禁用）
         */
        @SuppressWarnings("WeakerAccess")
        public boolean enabled;

        public AppPackage() {
        }

        protected AppPackage(Parcel in) {
            name = in.readString();
            packageName = in.readString();
            versionCode = in.readInt();
            versionName = in.readString();
            packageFilePath = in.readString();
            packageSize = in.readLong();
            packageLastModifiedTime = in.readLong();
            systemApp = in.readByte() != 0;
            enabled = in.readByte() != 0;
        }

        @Override
        public String toString() {
            return "AppPackage{" +
                    "name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", versionCode=" + versionCode +
                    ", versionName='" + versionName + '\'' +
                    ", packageFilePath='" + packageFilePath + '\'' +
                    ", packageSize=" + packageSize +
                    ", packageLastModifiedTime=" + packageLastModifiedTime +
                    ", systemApp=" + systemApp +
                    ", enabled=" + enabled +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(packageName);
            dest.writeInt(versionCode);
            dest.writeString(versionName);
            dest.writeString(packageFilePath);
            dest.writeLong(packageSize);
            dest.writeLong(packageLastModifiedTime);
            dest.writeByte((byte) (systemApp ? 1 : 0));
            dest.writeByte((byte) (enabled ? 1 : 0));
        }
    }
}