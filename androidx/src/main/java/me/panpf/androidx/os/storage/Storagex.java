/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
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

package me.panpf.androidx.os.storage;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import me.panpf.androidx.os.StatFsx;
import me.panpf.javax.io.Filex;
import me.panpf.javax.lang.Classx;
import me.panpf.javax.util.Arrayx;
import me.panpf.javax.util.Collectionx;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.util.Premisex;
import me.panpf.javax.util.Transformer;

/**
 * Storage related tool methods
 */
@SuppressWarnings("WeakerAccess")
public class Storagex {

    /**
     * Get the number of free bytes remaining in the current directory
     */
    public static long getDirFreeBytes(@NonNull File $receiver) {
        return StatFsx.getCompatFreeBytes(new StatFs($receiver.getPath()));
    }

    /**
     * Get the number of total bytes in the current directory
     */
    public static long getDirTotalBytes(@NonNull File $receiver) {
        return StatFsx.getCompatTotalBytes(new StatFs($receiver.getPath()));
    }

    /**
     * Get the number of bytes remaining in the current directory that are available for the current application
     */
    public static long getDirAvailableBytes(@NonNull File $receiver) {
        return StatFsx.getCompatAvailableBytes(new StatFs($receiver.getPath()));
    }

    /**
     * Return true if the SD card is ready
     */
    public static boolean isSDCardMounted() {
        return Premisex.areEqual(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED);
    }

    /**
     * Get the number of free bytes remaining in the sdcard
     */
    public static long getSDCardFreeBytes() {
        return getDirFreeBytes(Environment.getExternalStorageDirectory());
    }

    /**
     * Get the number of total bytes in the sdcard
     */
    public static long getSDCardTotalBytes() {
        return getDirTotalBytes(Environment.getExternalStorageDirectory());
    }

    /**
     * Get the number of bytes remaining in the sdcard that are available for the current application
     */
    public static long getSDCardAvailableBytes() {
        return getDirAvailableBytes(Environment.getExternalStorageDirectory());
    }

    /**
     * Get the path to the SD card
     */
    @NonNull
    public static String getSdcardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * Get the file to the SD card
     */
    @NonNull
    public static File getSdcardFile() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * Get the path to all available SD cards
     *
     * @param ignorePrimary Ignore the main sdcard
     */
    // todo 测试是否兼容 android 9
    @NonNull
    public static String[] getAllSdcardPath(@NonNull Context context, final boolean ignorePrimary) {
        final StorageManager manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        if (manager == null) throw new IllegalStateException("StorageManager not found");

        String[] volumePaths = null;
        try {
            volumePaths = (String[]) Classx.callMethod(manager, "getVolumePaths");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String primarySdcardPath = getSdcardPath();

        List<String> paths = new LinkedList<>();
        if (volumePaths != null && volumePaths.length > 0) {
            Collections.addAll(paths, volumePaths);
        } else {
            paths.add(primarySdcardPath);
        }

        return Collectionx.filter(paths, new Predicate<String>() {
            @Override
            public boolean predicate(@NonNull String s) {
                if (ignorePrimary && primarySdcardPath.equals(s)) {
                    return false;
                } else {
                    String volumeState = null;
                    try {
                        volumeState = (String) Classx.callMethod(manager, "getVolumeState", s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return (Environment.MEDIA_MOUNTED.equals(volumeState) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(volumeState));
                }
            }
        }).toArray(new String[0]);
    }

    /**
     * Get the path to all available SD cards
     *
     * @param ignorePrimary Ignore the main sdcard
     */
    @NonNull
    public static File[] getAllSdcardFile(@NonNull Context context, boolean ignorePrimary) {
        return Arrayx.map(getAllSdcardPath(context, ignorePrimary), new Transformer<String, File>() {
            @NonNull
            @Override
            public File transform(@NonNull String s) {
                return new File(s);
            }
        }).toArray(new File[0]);
    }

    /**
     * Get all available SD cards and splicing them back on the specified path
     *
     * @param ignorePrimary Ignore the main sdcard
     */
    @NonNull
    public static File[] getAllSdcardWithPathFile(@NonNull Context context, @NonNull final String childPath, boolean ignorePrimary) {
        return Arrayx.map(getAllSdcardPath(context, ignorePrimary), new Transformer<String, File>() {
            @NonNull
            @Override
            public File transform(@NonNull String s) {
                return new File(s, childPath);
            }
        }).toArray(new File[0]);
    }

    /**
     * Find an SD card with a space no less than the specified number of bytes
     *
     * @param minBytes      Minimum number of bytes
     * @param childPath     Check the space under File(sdcardPath, childDir) and return this file
     * @param ignorePrimary Ignore the main sdcard
     */
    @Nullable
    public static File findSdcardBySpace(@NonNull Context context, final long minBytes, @Nullable String childPath, boolean ignorePrimary) {
        File[] allDir = childPath != null ? getAllSdcardWithPathFile(context, childPath, ignorePrimary) : getAllSdcardFile(context, ignorePrimary);
        return Collectionx.find(Arrays.asList(allDir), new Predicate<File>() {
            @Override
            public boolean predicate(@NonNull File file) {
                return getDirAvailableBytes(file) >= minBytes;
            }
        });
    }

    /**
     * Traverse the directory list, check the remaining space of the directory, and return the file
     *
     * @param dirs          Directory list
     * @param childFileName This file will be deleted from the directory before the space is checked, and will eventually be returned.
     * @param minBytes      Minimum number of bytes
     * @param deleteFile    Whether to delete old files before judging the space
     */
    @Nullable
    public static File getChildFileBySpaceFromDirs(File[] dirs, final String childFileName, final long minBytes, final boolean deleteFile) {
        File dir = Collectionx.find(Arrays.asList(dirs), new Predicate<File>() {
            @Override
            public boolean predicate(@NonNull File file) {
                if (file.isDirectory()) {
                    Filex.mkdirsCheck(file);
                    if (deleteFile) Filex.deleteRecursively(new File(file, childFileName));
                    return getDirAvailableBytes(file) >= minBytes;
                } else {
                    return false;
                }
            }
        });
        return dir != null ? new File(dir, childFileName) : null;
    }

    /**
     * Get all app cache directories
     */
    @NonNull
    public static File[] getAllAppCacheDirs(@NonNull Context context, boolean ignoreInternal) {
        List<File> fileList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 19) {
            Collections.addAll(fileList, context.getExternalCacheDirs());
        } else {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                fileList.add(externalCacheDir);
            }
        }
        if (!ignoreInternal) {
            fileList.add(context.getCacheDir());
        }
        return fileList.toArray(new File[0]);
    }

    /**
     * Get all app file directories
     */
    @NonNull
    public static File[] getAllAppFilesDirs(@NonNull Context context, boolean ignoreInternal) {
        List<File> fileList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 19) {
            Collections.addAll(fileList, context.getExternalFilesDirs(null));
        } else {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                fileList.add(externalFilesDir);
            }
        }
        if (!ignoreInternal) {
            fileList.add(context.getFilesDir());
        }
        return fileList.toArray(new File[0]);
    }

    /**
     * Get a file from the APP cache directory, external storage takes precedence
     */
    @NonNull
    public static File getFileFromAppCacheDirs(@NonNull Context context, @NonNull String fileName) {
        return new File(getAllAppCacheDirs(context, false)[0], fileName);
    }

    /**
     * Get a file from the APP files directory, external storage takes precedence
     */
    @NonNull
    public static File getFileFromAppFilesDirs(@NonNull Context context, @NonNull String fileName) {
        return new File(getAllAppFilesDirs(context, false)[0], fileName);
    }

    /**
     * Get a file from the app's cache directory and check the remaining space
     */
    @Nullable
    public static File getFileFromAppCacheDirsBySpace(@NonNull Context context, @NonNull String fileName, long minBytes) {
        return getChildFileBySpaceFromDirs(getAllAppCacheDirs(context, false), fileName, minBytes, false);
    }

    /**
     * Get a file from the app's files directory and check the remaining space
     */
    @Nullable
    public static File getFileFromAppFilesDirsBySpace(@NonNull Context context, @NonNull String fileName, long minBytes) {
        return getChildFileBySpaceFromDirs(getAllAppFilesDirs(context, false), fileName, minBytes, false);
    }

    /**
     * Clean up all app cache directories
     */
    public static void cleanAppCacheDirs(@NonNull Context context) {
        for (File a$receiver$iv : getAllAppCacheDirs(context, false)) {
            Filex.cleanDir(a$receiver$iv);
        }
    }

    /**
     * Count the size of all APP cache directories
     */
    @WorkerThread
    public static long lengthAppCacheDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAllAppFilesDirs(context, false)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP files directories
     */
    @WorkerThread
    public static long lengthAppFilesDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAllAppCacheDirs(context, false)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Get the obb directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File getAppObbDir(@NonNull String packageName) {
        return new File(Environment.getExternalStorageDirectory(), "Android/obb/" + packageName);
    }

    /**
     * Get the data directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File getAppDataDir(@NonNull String packageName) {
        return new File(Environment.getExternalStorageDirectory(), "Android/data/" + packageName);
    }
}
