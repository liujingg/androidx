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
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.panpf.javax.collections.Arrayx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Pair;
import me.panpf.javax.util.Predicate;

public class PackageInfox {

    private PackageInfox() {
    }


    @Nullable
    public static ActivityInfo findActivityInfo(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<ActivityInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        return Arrayx.find(info.activities, predicate);
    }

    @Nullable
    public static ActivityInfo findSelfActivityInfo(@NonNull Context context, @NonNull Predicate<ActivityInfo> predicate) {
        try {
            return findActivityInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ActivityInfo findActivityInfoByName(@NonNull Context context, @NonNull String packageName, @NonNull final String activityName) throws PackageManager.NameNotFoundException {
        return findActivityInfo(context, packageName, new Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo activityInfo) {
                return Stringx.equals(activityInfo.name, activityName);
            }
        });
    }

    @Nullable
    public static ActivityInfo findSelfActivityInfoByName(@NonNull Context context, @NonNull final String activityName) {
        try {
            return findActivityInfoByName(context, context.getPackageName(), activityName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static ServiceInfo findServiceInfo(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<ServiceInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SERVICES);
        return Arrayx.find(info.services, predicate);
    }

    @Nullable
    public static ServiceInfo findSelfServiceInfo(@NonNull Context context, @NonNull Predicate<ServiceInfo> predicate) {
        try {
            return findServiceInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ServiceInfo findServiceInfoByName(@NonNull Context context, @NonNull String packageName, @NonNull final String serviceName) throws PackageManager.NameNotFoundException {
        return findServiceInfo(context, packageName, new Predicate<ServiceInfo>() {
            @Override
            public boolean accept(@NonNull ServiceInfo serviceInfo) {
                return Stringx.equals(serviceInfo.name, serviceName);
            }
        });
    }

    @Nullable
    public static ServiceInfo findSelfServiceInfoByName(@NonNull Context context, @NonNull final String serviceName) {
        try {
            return findServiceInfoByName(context, context.getPackageName(), serviceName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static ActivityInfo findReceiverInfo(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<ActivityInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_RECEIVERS);
        return Arrayx.find(info.receivers, predicate);
    }

    @Nullable
    public static ActivityInfo findSelfReceiverInfo(@NonNull Context context, @NonNull Predicate<ActivityInfo> predicate) {
        try {
            return findReceiverInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ActivityInfo findReceiverInfoByName(@NonNull Context context, @NonNull String packageName, @NonNull final String receiverName) throws PackageManager.NameNotFoundException {
        return findReceiverInfo(context, packageName, new Predicate<ActivityInfo>() {
            @Override
            public boolean accept(@NonNull ActivityInfo activityInfo) {
                return Stringx.equals(activityInfo.name, receiverName);
            }
        });
    }

    @Nullable
    public static ActivityInfo findSelfReceiverInfoByName(@NonNull Context context, @NonNull final String receiverName) {
        try {
            return findReceiverInfoByName(context, context.getPackageName(), receiverName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static ProviderInfo findProviderInfo(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<ProviderInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PROVIDERS);
        return Arrayx.find(info.providers, predicate);
    }

    @Nullable
    public static ProviderInfo findSelfProviderInfo(@NonNull Context context, @NonNull Predicate<ProviderInfo> predicate) {
        try {
            return findProviderInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static ProviderInfo findProviderInfoByName(@NonNull Context context, @NonNull String packageName, @NonNull final String providerName) throws PackageManager.NameNotFoundException {
        return findProviderInfo(context, packageName, new Predicate<ProviderInfo>() {
            @Override
            public boolean accept(@NonNull ProviderInfo providerInfo) {
                return Stringx.equals(providerInfo.name, providerName);
            }
        });
    }

    @Nullable
    public static ProviderInfo findSelfProviderInfoByName(@NonNull Context context, @NonNull final String providerName) {
        try {
            return findProviderInfoByName(context, context.getPackageName(), providerName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static PermissionInfo findPermissionInfo(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<PermissionInfo> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        return Arrayx.find(info.permissions, predicate);
    }

    @Nullable
    public static PermissionInfo findSelfPermissionInfo(@NonNull Context context, @NonNull Predicate<PermissionInfo> predicate) {
        try {
            return findPermissionInfo(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static PermissionInfo findPermissionInfoByName(@NonNull Context context, @NonNull String packageName, @NonNull final String permissionName) throws PackageManager.NameNotFoundException {
        return findPermissionInfo(context, packageName, new Predicate<PermissionInfo>() {
            @Override
            public boolean accept(@NonNull PermissionInfo permissionInfo) {
                return Stringx.equals(permissionInfo.name, permissionName);
            }
        });
    }

    @Nullable
    public static PermissionInfo findSelfPermissionInfoByName(@NonNull Context context, @NonNull final String permissionName) {
        try {
            return findPermissionInfoByName(context, context.getPackageName(), permissionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static String findRequestedPermission(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<String> predicate) throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        return Arrayx.find(info.requestedPermissions, predicate);
    }

    @Nullable
    public static String findSelfRequestedPermission(@NonNull Context context, @NonNull Predicate<String> predicate) {
        try {
            return findRequestedPermission(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }

    @Nullable
    public static String findRequestedPermissionByName(@NonNull Context context, @NonNull String packageName, @NonNull final String permissionName) throws PackageManager.NameNotFoundException {
        return findRequestedPermission(context, packageName, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String requestedPermission) {
                return Stringx.equals(requestedPermission, permissionName);
            }
        });
    }

    @Nullable
    public static String findSelfRequestedPermissionByName(@NonNull Context context, @NonNull final String permissionName) {
        try {
            return findRequestedPermissionByName(context, context.getPackageName(), permissionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self PackageInfo", e);
        }
    }


    @Nullable
    public static Pair<String, String> findMetaDataWithName(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<String> predicate) throws PackageManager.NameNotFoundException {
        ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        Bundle metaData = info.metaData;
        if (metaData != null) {
            for (String key : metaData.keySet()) {
                if (predicate.accept(key)) {
                    Object value = metaData.get(key);
                    String valueString = value != null ? value.toString() : "";
                    return new Pair<>(key, valueString);
                }
            }
        }
        return null;
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataWithName(@NonNull Context context, @NonNull Predicate<String> predicate) {
        try {
            return findMetaDataWithName(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }

    @Nullable
    public static Pair<String, String> findMetaDataByName(@NonNull Context context, @NonNull String packageName, @NonNull final String metaDataName) throws PackageManager.NameNotFoundException {
        return findMetaDataWithName(context, packageName, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Stringx.equals(s, metaDataName);
            }
        });
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataByName(@NonNull Context context, @NonNull String metaDataName) {
        try {
            return findMetaDataByName(context, context.getPackageName(), metaDataName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }


    @Nullable
    public static Pair<String, String> findMetaDataWithValue(@NonNull Context context, @NonNull String packageName, @NonNull Predicate<String> predicate) throws PackageManager.NameNotFoundException {
        ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        Bundle metaData = info.metaData;
        if (metaData != null) {
            for (String key : metaData.keySet()) {
                Object value = metaData.get(key);
                String valueString = value != null ? value.toString() : "";
                if (predicate.accept(valueString)) {
                    return new Pair<>(key, valueString);
                }
            }
        }
        return null;
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataWithValue(@NonNull Context context, @NonNull Predicate<String> predicate) {
        try {
            return findMetaDataWithValue(context, context.getPackageName(), predicate);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }

    @Nullable
    public static Pair<String, String> findMetaDataByValue(@NonNull Context context, @NonNull String packageName, @NonNull final String metaDataValue) throws PackageManager.NameNotFoundException {
        return findMetaDataWithValue(context, packageName, new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String s) {
                return Stringx.equals(s, metaDataValue);
            }
        });
    }

    @Nullable
    public static Pair<String, String> findSelfMetaDataByValue(@NonNull Context context, @NonNull final String metaDataValue) {
        try {
            return findMetaDataByValue(context, context.getPackageName(), metaDataValue);
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Not found self ApplicationInfo", e);
        }
    }
}
