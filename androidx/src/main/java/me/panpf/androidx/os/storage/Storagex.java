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

package me.panpf.androidx.os.storage;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import me.panpf.androidx.content.Contextx;
import me.panpf.androidx.os.StatFsx;
import me.panpf.javax.collections.Arrayx;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.io.Filex;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.util.Transformer;

/**
 * Storage related tool methods
 */
public class Storagex {

    private Storagex() {
    }

    /* ******************************************* Bytes *******************************************/


    /**
     * Get the number of free bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
     */
    public static long getFreeBytesOr(@NonNull File path, long defaultValue) {
        try {
            if (!path.exists() && !path.mkdirs()) {
                return defaultValue;
            }
            return StatFsx.getFreeBytesCompat(new StatFs(path.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get the number of total bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
     */
    public static long getTotalBytesOr(@NonNull File path, long defaultValue) {
        try {
            if (!path.exists() && !path.mkdirs()) {
                return defaultValue;
            }
            return StatFsx.getTotalBytesCompat(new StatFs(path.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get the number of available bytes of the given path. The directory does not exist and the creation is unsuccessful. [defaultValue]
     */
    public static long getAvailableBytesOr(@NonNull File path, long defaultValue) {
        try {
            if (!path.exists() && !path.mkdirs()) {
                return defaultValue;
            }
            return StatFsx.getAvailableBytesCompat(new StatFs(path.getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get the number of free bytes for the primary shared/external storage media
     */
    public static long getExternalStorageFreeBytes() {
        return getFreeBytesOr(getExternalStorageDirectory(), -1);
    }

    /**
     * Get the number of total bytes for the primary shared/external storage media
     */
    public static long getExternalStorageTotalBytes() {
        return getTotalBytesOr(getExternalStorageDirectory(), -1);
    }

    /**
     * Get the number of available bytes for the primary shared/external storage media
     */
    public static long getExternalStorageAvailableBytes() {
        return getAvailableBytesOr(getExternalStorageDirectory(), -1);
    }


    /* ******************************************* Volume *******************************************/

    /**
     * Returns volume state for given path
     */
    @NonNull
    public static String getVolumeState(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null ? volumeCompat.getState(context) : "unknown";
    }

    /**
     * Returns true if the state of the volume at which the given path is mounted
     */
    public static boolean isVolumeMounted(@NonNull Context context, @NonNull File path) {
        return Environment.MEDIA_MOUNTED.equals(getVolumeState(context, path));
    }


    /**
     * Return true if the volume of the given path is the primary volume
     */
    public static boolean isPrimaryVolume(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null && volumeCompat.isPrimary();
    }

    /**
     * Return true if the volume of the given path is the emulated volume
     */
    public static boolean isVolumeEmulated(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null && volumeCompat.isEmulated();
    }

    /**
     * Return true if the volume of the given path is the removable volume
     */
    public static boolean isVolumeRemovable(@NonNull Context context, @NonNull File path) {
        StorageVolumeCompat volumeCompat = getVolume(context, path);
        return volumeCompat != null && volumeCompat.isRemovable();
    }


    /**
     * Returns list of path for all volumes.
     */
    @NonNull
    public static String[] getVolumePaths(@NonNull Context context) {
        return Contextx.storageManagerCompat(context).getVolumePaths();
    }

    /**
     * Returns list of path for all mounted volumes.
     */
    @NonNull
    public static String[] getMountedVolumePaths(@NonNull final Context context) {
        return Arrayx.filter(getVolumePaths(context), new Predicate<String>() {
            @Override
            public boolean accept(@NonNull String path) {
                return isVolumeMounted(context, new File(path));
            }
        }).toArray(new String[0]);
    }


    /**
     * Returns list of files for all volumes.
     */
    @NonNull
    public static File[] getVolumeFiles(@NonNull Context context) {
        return Arrayx.map(getVolumePaths(context), new Transformer<String, File>() {
            @NonNull
            @Override
            public File transform(@NonNull String volumePath) {
                return new File(volumePath);
            }
        }).toArray(new File[0]);
    }

    /**
     * Returns list of files for all mounted volumes.
     */
    @NonNull
    public static File[] getMountedVolumeFiles(@NonNull final Context context) {
        return Arrayx.filter(getVolumeFiles(context), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return isVolumeMounted(context, file);
            }
        }).toArray(new File[0]);
    }


    /**
     * Returns list of StorageVolume for all volumes.
     */
    @NonNull
    public static List<StorageVolumeCompat> getVolumeList(@NonNull Context context) {
        return Contextx.storageManagerCompat(context).getVolumeList();
    }

    /**
     * Returns list of StorageVolume for all mounted volumes.
     */
    @NonNull
    public static List<StorageVolumeCompat> getMountedVolumeList(@NonNull final Context context) {
        return Collectionx.filter(getVolumeList(context), new Predicate<StorageVolumeCompat>() {
            @Override
            public boolean accept(@NonNull StorageVolumeCompat storageVolumeCompat) {
                return Environment.MEDIA_MOUNTED.equals(storageVolumeCompat.getState(context));
            }
        });
    }

    /**
     * Returns array of StorageVolume for all volumes.
     */
    @NonNull
    public static StorageVolumeCompat[] getVolumes(@NonNull Context context) {
        return Contextx.storageManagerCompat(context).getVolumes();
    }

    /**
     * Returns array of StorageVolume for all mounted volumes.
     */
    @NonNull
    public static StorageVolumeCompat[] getMountedVolumes(@NonNull final Context context) {
        return Arrayx.filter(getVolumes(context), new Predicate<StorageVolumeCompat>() {
            @Override
            public boolean accept(@NonNull StorageVolumeCompat storageVolumeCompat) {
                return Environment.MEDIA_MOUNTED.equals(storageVolumeCompat.getState(context));
            }
        }).toArray(new StorageVolumeCompat[0]);
    }


    /**
     * Returns StorageVolume for path.
     */
    @Nullable
    public static StorageVolumeCompat getVolume(@NonNull Context context, @NonNull File path) {
        return Contextx.storageManagerCompat(context).getVolume(path);
    }


    /* ******************************************* External Storage *******************************************/


    /**
     * Returns the current state of the shared/external storage media at the
     * given path.
     *
     * @return one of {@link Environment#MEDIA_UNKNOWN}, {@link Environment#MEDIA_REMOVED},
     * {@link Environment#MEDIA_UNMOUNTED}, {@link Environment#MEDIA_CHECKING},
     * {@link Environment#MEDIA_NOFS}, {@link Environment#MEDIA_MOUNTED},
     * {@link Environment#MEDIA_MOUNTED_READ_ONLY}, {@link Environment#MEDIA_SHARED},
     * {@link Environment#MEDIA_BAD_REMOVAL}, or {@link Environment#MEDIA_UNMOUNTABLE}.
     * @see #getExternalStorageDirectory()
     */
    @NonNull
    public static String getExternalStorageState(@NonNull Context context, @NonNull File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Environment.getExternalStorageState(path);
        } else {
            return getVolumeState(context, path);
        }
    }

    /**
     * Return true if the state of the given path is MOUNTED
     */
    public static boolean isExternalStorageMounted(@NonNull Context context, @NonNull File path) {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageState(context, path));
    }

    /**
     * Returns the current state of the primary shared/external storage media.
     *
     * @return one of {@link Environment#MEDIA_UNKNOWN}, {@link Environment#MEDIA_REMOVED},
     * {@link Environment#MEDIA_UNMOUNTED}, {@link Environment#MEDIA_CHECKING},
     * {@link Environment#MEDIA_NOFS}, {@link Environment#MEDIA_MOUNTED},
     * {@link Environment#MEDIA_MOUNTED_READ_ONLY}, {@link Environment#MEDIA_SHARED},
     * {@link Environment#MEDIA_BAD_REMOVAL}, or {@link Environment#MEDIA_UNMOUNTABLE}.
     * @see #getExternalStorageDirectory()
     */
    @NonNull
    public static String getExternalStorageState() {
        return Environment.getExternalStorageState();
    }

    /**
     * Return true if the state of the primary shared/external storage media is MOUNTED
     */
    public static boolean isExternalStorageMounted() {
        return Environment.MEDIA_MOUNTED.equals(getExternalStorageState());
    }

    /**
     * Return true if the path is the primary volume
     */
    public static boolean isPrimaryExternalStorage(@NonNull Context context, @NonNull File path) {
        return isPrimaryVolume(context, path);
    }

    /**
     * Return true if the volume of the given path is emulated
     */
    public static boolean isExternalStorageEmulated(@NonNull Context context, @NonNull File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Environment.isExternalStorageEmulated(path);
        } else {
            StorageVolumeCompat storageVolume = Contextx.storageManagerCompat(context).getVolume(path);
            return storageVolume != null && storageVolume.isEmulated();
        }
    }

    /**
     * Return true if the primary shared/external storage media is emulated
     */
    public static boolean isExternalStorageEmulated() {
        return Environment.isExternalStorageEmulated();
    }

    /**
     * Return true if the state of the given path is REMOVED
     */
    public static boolean isExternalStorageRemovable(@NonNull Context context, @NonNull File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Environment.isExternalStorageRemovable(path);
        } else {
            StorageVolumeCompat storageVolume = Contextx.storageManagerCompat(context).getVolume(path);
            return storageVolume != null && storageVolume.isRemovable();
        }
    }

    /**
     * Return true if the state of the primary shared/external storage media is REMOVED
     */
    public static boolean isExternalStorageRemovable() {
        return Environment.isExternalStorageRemovable();
    }


    /**
     * Return the primary shared/external storage directory.
     */
    @NonNull
    public static File getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * Return if the primary shared/external storage directory is mounted, otherwise return null
     */
    @Nullable
    public static File getMountedExternalStorageDirectory(@NonNull Context context) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        return isExternalStorageMounted(context, externalStorageDirectory) ? externalStorageDirectory : null;
    }


    /**
     * Return the all shared/external storage directory.
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getExternalStorageDirectorys(@NonNull Context context, final boolean ignorePrimary) {
        List<File> dirs = new LinkedList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File[] files = context.getExternalFilesDirs(null);
            if (files != null && files.length > 0) {
                String lowerCaseSuffix = "/Android/data/".toLowerCase();
                for (File file : files) {
                    if (file != null) {
                        String lowerCasePath = file.getPath().toLowerCase();
                        int index = lowerCasePath.indexOf(lowerCaseSuffix);
                        if (index != -1) {
                            dirs.add(new File(file.getPath().substring(0, index)));
                        }
                    }
                }
            }
        }

        if (dirs.isEmpty()) {
            File[] files = getVolumeFiles(context);
            if (files.length > 0) {
                Collectionx.addAll(dirs, files);
            }
        }

        final File primaryExternalStorageDirectory = getExternalStorageDirectory();
        if (dirs.isEmpty()) {
            dirs.add(primaryExternalStorageDirectory);
        }

        return Collectionx.filter(dirs, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return !ignorePrimary || !file.getPath().equals(primaryExternalStorageDirectory.getPath());
            }
        }).toArray(new File[0]);
    }

    /**
     * Returns the all shared/external storage directories that are mounted.
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorys(@NonNull final Context context, final boolean ignorePrimary) {
        return Arrayx.filter(getExternalStorageDirectorys(context, ignorePrimary), new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File file) {
                return isExternalStorageMounted(context, file);
            }
        }).toArray(new File[0]);
    }


    /**
     * Return the all shared/external storage directory.
     */
    @NonNull
    public static File[] getExternalStorageDirectorys(@NonNull Context context) {
        return getExternalStorageDirectorys(context, false);
    }

    /**
     * Returns the all shared/external storage directories that are mounted.
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorys(@NonNull final Context context) {
        return getMountedExternalStorageDirectorys(context, false);
    }


    /**
     * Get the given path under all shared/external storage media
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath, boolean ignorePrimary) {
        return Arrayx.map(getExternalStorageDirectorys(context, ignorePrimary), new Transformer<File, File>() {
            @NonNull
            @Override
            public File transform(@NonNull File file) {
                return new File(file, childPath);
            }
        }).toArray(new File[0]);
    }

    /**
     * Get the given path under all mounted shared/external storage media
     *
     * @param ignorePrimary Ignore primary shared/external storage directory.
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath, boolean ignorePrimary) {
        return Arrayx.map(getMountedExternalStorageDirectorys(context, ignorePrimary), new Transformer<File, File>() {
            @NonNull
            @Override
            public File transform(@NonNull File file) {
                return new File(file, childPath);
            }
        }).toArray(new File[0]);
    }


    /**
     * Get the given path under all shared/external storage media
     */
    @NonNull
    public static File[] getExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath) {
        return getExternalStorageDirectorysWithPath(context, childPath, false);
    }

    /**
     * Get the given path under all mounted shared/external storage media
     */
    @NonNull
    public static File[] getMountedExternalStorageDirectorysWithPath(@NonNull Context context, @NonNull final String childPath) {
        return getMountedExternalStorageDirectorysWithPath(context, childPath, false);
    }


    /* ******************************************* App Cache Dir *******************************************/


    /**
     * Get the app external cache directory
     */
    @Nullable
    public static File getAppExternalCacheDir(@NonNull Context context) {
        File file = context.getExternalCacheDir();
        if (file != null) {
            // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getExternalCacheDir() returns null
            return file;
        } else {
            File externalDir = getMountedExternalStorageDirectory(context);
            return externalDir != null ? new File(externalDir, "Android/data/" + context.getPackageName() + "/cache") : null;
        }
    }

    /**
     * Get the external cache directory of the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static File getAppExternalCacheDir(@NonNull Context context, @NonNull String packageName) {
        File selfExternalCacheDir = getAppExternalCacheDir(context);
        return selfExternalCacheDir != null ? new File(selfExternalCacheDir.getPath().replace(context.getPackageName(), packageName)) : null;
    }

    /**
     * Get the all app external cache directory
     */
    @NonNull
    public static File[] getAppExternalCacheDirs(@NonNull final Context context) {
        File[] externalCacheDirs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            externalCacheDirs = Arrayx.filter(context.getExternalCacheDirs(), new Predicate<File>() {
                @Override
                public boolean accept(@NonNull File file) {
                    //noinspection ConstantConditions
                    return file != null;
                }
            }).toArray(new File[0]);
        }
        if (externalCacheDirs != null && externalCacheDirs.length > 0) {
            return externalCacheDirs;
        } else {
            return Arrayx.map(getMountedExternalStorageDirectorys(context), new Transformer<File, File>() {
                @NonNull
                @Override
                public File transform(@NonNull File file) {
                    return new File(file, "Android/data/" + context.getPackageName() + "/cache");
                }
            }).toArray(new File[0]);
        }
    }

    /**
     * Get the all external cache directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppExternalCacheDirs(@NonNull final Context context, @NonNull final String packageName) {
        return Arrayx.map(getAppExternalCacheDirs(context), new Transformer<File, File>() {
            @NonNull
            @Override
            public File transform(@NonNull File file) {
                return new File(file.getPath().replace(context.getPackageName(), packageName));
            }
        }).toArray(new File[0]);
    }


    /**
     * Get the app internal cache directory
     */
    @NonNull
    public static File getAppInternalCacheDir(@NonNull Context context) {
        return context.getCacheDir();
    }

    /**
     * Get the internal cache directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File getAppInternalCacheDir(@NonNull Context context, @NonNull String packageName) {
        return new File(getAppInternalCacheDir(context).getPath().replace(context.getPackageName(), packageName));
    }


    /**
     * Get all app cache directories
     */
    @NonNull
    public static File[] getAppCacheDirs(@NonNull Context context) {
        List<File> fileList = new LinkedList<>();
        File[] externalCacheDirs = getAppExternalCacheDirs(context);
        Collections.addAll(fileList, externalCacheDirs);
        fileList.add(getAppInternalCacheDir(context));
        return fileList.toArray(new File[0]);
    }

    /**
     * Get all cache directories of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppCacheDirs(@NonNull final Context context, @NonNull final String packageName) {
        List<File> fileList = new LinkedList<>();
        File[] externalCacheDirs = getAppExternalCacheDirs(context, packageName);
        Collections.addAll(fileList, externalCacheDirs);
        fileList.add(getAppInternalCacheDir(context, packageName));
        return fileList.toArray(new File[0]);
    }


    /**
     * Count the size of all APP cache directories
     */
    @WorkerThread
    public static long lengthAppCacheDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAppCacheDirs(context)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP cache directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static long lengthAppCacheDirs(@NonNull Context context, @NonNull String packageName) {
        long sum = 0;
        for (File file : getAppCacheDirs(context, packageName)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Clean up all app cache directories
     */
    @WorkerThread
    public static void cleanAppCacheDirs(@NonNull Context context) {
        for (File file : getAppCacheDirs(context)) {
            Filex.cleanRecursively(file);
        }
    }

    /**
     * Clean up all cache directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static void cleanAppCacheDirs(@NonNull Context context, @NonNull String packageName) {
        for (File file : getAppCacheDirs(context, packageName)) {
            Filex.cleanRecursively(file);
        }
    }


    /* ******************************************* App Files Dir *******************************************/


    /**
     * Get the app external files directory
     */
    @Nullable
    public static File getAppExternalFilesDir(@NonNull Context context) {
        File file = context.getExternalFilesDir(null);
        if (file != null) {
            // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getExternalFilesDir() returns null
            return file;
        } else {
            File externalDir = getMountedExternalStorageDirectory(context);
            return externalDir != null ? new File(externalDir, "Android/data/" + context.getPackageName() + "/files") : null;
        }
    }

    /**
     * Get the external files directory of the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static File getAppExternalFilesDir(@NonNull Context context, @NonNull String packageName) {
        File selfExternalFilesDir = getAppExternalFilesDir(context);
        return selfExternalFilesDir != null ? new File(selfExternalFilesDir.getPath().replace(context.getPackageName(), packageName)) : null;
    }

    /**
     * Get the all app external files directory
     */
    @NonNull
    public static File[] getAppExternalFilesDirs(@NonNull final Context context) {
        File[] externalFilesDirs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            externalFilesDirs = Arrayx.filter(context.getExternalFilesDirs(null), new Predicate<File>() {
                @Override
                public boolean accept(@NonNull File file) {
                    //noinspection ConstantConditions
                    return file != null;
                }
            }).toArray(new File[0]);
        }
        if (externalFilesDirs != null && externalFilesDirs.length > 0) {
            return externalFilesDirs;
        } else {
            return Arrayx.map(getMountedExternalStorageDirectorys(context), new Transformer<File, File>() {
                @NonNull
                @Override
                public File transform(@NonNull File file) {
                    return new File(file, "Android/data/" + context.getPackageName() + "/files");
                }
            }).toArray(new File[0]);
        }
    }

    /**
     * Get the all external files directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppExternalFilesDirs(@NonNull final Context context, @NonNull final String packageName) {
        return Arrayx.map(getAppExternalFilesDirs(context), new Transformer<File, File>() {
            @NonNull
            @Override
            public File transform(@NonNull File file) {
                return new File(file.getPath().replace(context.getPackageName(), packageName));
            }
        }).toArray(new File[0]);
    }


    /**
     * Get the app internal files directory
     */
    @NonNull
    public static File getAppInternalFilesDir(@NonNull Context context) {
        return context.getFilesDir();
    }

    /**
     * Get the internal files directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File getAppInternalFilesDir(@NonNull Context context, @NonNull String packageName) {
        return new File(getAppInternalFilesDir(context).getPath().replace(context.getPackageName(), packageName));
    }


    /**
     * Get all app files directories
     */
    @NonNull
    public static File[] getAppFilesDirs(@NonNull Context context) {
        List<File> fileList = new LinkedList<>();
        File[] externalFilesDirs = getAppExternalFilesDirs(context);
        Collections.addAll(fileList, externalFilesDirs);
        fileList.add(getAppInternalFilesDir(context));
        return fileList.toArray(new File[0]);
    }

    /**
     * Get all files directories of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppFilesDirs(@NonNull final Context context, @NonNull final String packageName) {
        List<File> fileList = new LinkedList<>();
        File[] externalFilesDirs = getAppExternalFilesDirs(context, packageName);
        Collections.addAll(fileList, externalFilesDirs);
        fileList.add(getAppInternalFilesDir(context, packageName));
        return fileList.toArray(new File[0]);
    }


    /**
     * Count the size of all APP files directories
     */
    @WorkerThread
    public static long lengthAppFilesDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAppFilesDirs(context)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP files directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static long lengthAppFilesDirs(@NonNull Context context, @NonNull String packageName) {
        long sum = 0;
        for (File file : getAppFilesDirs(context, packageName)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Clean up all app files directories
     */
    @WorkerThread
    public static void cleanAppFilesDirs(@NonNull Context context) {
        for (File file : getAppFilesDirs(context)) {
            Filex.cleanRecursively(file);
        }
    }

    /**
     * Clean up all files directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static void cleanAppFilesDirs(@NonNull Context context, @NonNull String packageName) {
        for (File file : getAppFilesDirs(context, packageName)) {
            Filex.cleanRecursively(file);
        }
    }


    /* ******************************************* App Obb Dir *******************************************/


    /**
     * Get the app obb directory
     */
    @Nullable
    public static File getAppObbDir(@NonNull Context context) {
        File file = context.getObbDir();
        if (file != null) {
            // There is no WRITE_EXTERNAL_STORAGE permission on android 4.1. getObbDir() returns null
            return file;
        } else {
            File externalDir = getMountedExternalStorageDirectory(context);
            return externalDir != null ? new File(externalDir, "Android/obb/" + context.getPackageName()) : null;
        }
    }

    /**
     * Get the obb directory of the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static File getAppObbDir(@NonNull Context context, @NonNull String packageName) {
        File selfObbDir = getAppObbDir(context);
        return selfObbDir != null ? new File(selfObbDir.getPath().replace(context.getPackageName(), packageName)) : null;
    }

    /**
     * Get the all app obb directory
     */
    @NonNull
    public static File[] getAppObbDirs(@NonNull final Context context) {
        File[] obbDirs = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            obbDirs = Arrayx.filter(context.getObbDirs(), new Predicate<File>() {
                @Override
                public boolean accept(@NonNull File file) {
                    //noinspection ConstantConditions
                    return file != null;
                }
            }).toArray(new File[0]);
        }
        if (obbDirs != null && obbDirs.length > 0) {
            return obbDirs;
        } else {
            return Arrayx.map(getMountedExternalStorageDirectorys(context), new Transformer<File, File>() {
                @NonNull
                @Override
                public File transform(@NonNull File file) {
                    return new File(file, "Android/obb/" + context.getPackageName());
                }
            }).toArray(new File[0]);
        }
    }

    /**
     * Get the all obb directory of the specified app
     *
     * @param packageName App package name
     */
    @NonNull
    public static File[] getAppObbDirs(@NonNull final Context context, @NonNull final String packageName) {
        return Arrayx.map(getAppObbDirs(context), new Transformer<File, File>() {
            @NonNull
            @Override
            public File transform(@NonNull File file) {
                return new File(file.getPath().replace(context.getPackageName(), packageName));
            }
        }).toArray(new File[0]);
    }


    /**
     * Count the size of all APP obb directories
     */
    @WorkerThread
    public static long lengthAppObbDirs(@NonNull Context context) {
        long sum = 0;
        for (File file : getAppObbDirs(context)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Count the size of all APP obb directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static long lengthAppObbDirs(@NonNull Context context, @NonNull String packageName) {
        long sum = 0;
        for (File file : getAppObbDirs(context, packageName)) {
            sum += Filex.lengthRecursively(file);
        }
        return sum;
    }

    /**
     * Clean up all app obb directories
     */
    @WorkerThread
    public static void cleanAppObbDirs(@NonNull Context context) {
        for (File file : getAppObbDirs(context)) {
            Filex.cleanRecursively(file);
        }
    }

    /**
     * Clean up all obb directories of the specified app
     *
     * @param packageName App package name
     */
    @WorkerThread
    public static void cleanAppObbDirs(@NonNull Context context, @NonNull String packageName) {
        for (File file : getAppObbDirs(context, packageName)) {
            Filex.cleanRecursively(file);
        }
    }


    /* ******************************************* Other *******************************************/


    /**
     * Traverses the given directory collection, returning a directory with free space greater than or equal to the minimum number of bytes
     *
     * @param paths    Directory list
     * @param minBytes Minimum number of bytes
     */
    @Nullable
    public static File filterByMinBytes(@Nullable File[] paths, final long minBytes) {
        if (paths == null || paths.length == 0) return null;
        return Arrayx.find(paths, new Predicate<File>() {
            @Override
            public boolean accept(@NonNull File path1) {
                Filex.mkdirsOrCheck(path1);
                return path1.isDirectory() && getAvailableBytesOr(path1, 0) >= minBytes;
            }
        });
    }

    /**
     * Traverse the specified directory list, check the remaining space of the directory, and return the file (not created)
     *
     * @param fileName     file name
     * @param minBytes     Minimum available bytes
     * @param cleanOldFile Whether to delete old files before judging the space
     * @param dirs         Directory list
     */
    @Nullable
    public static File getFileIn(@Nullable File[] dirs, @NonNull String fileName, long minBytes, boolean cleanOldFile) {
        if (dirs != null && dirs.length > 0) {
            for (File dir : dirs) {
                if (dir == null || dir.isFile()) {
                    continue;
                }

                File newFile = new File(dir, fileName);
                if (cleanOldFile && newFile.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    newFile.delete();
                }

                if (Storagex.getAvailableBytesOr(dir, -1) >= minBytes) {
                    return newFile;
                }
            }
        }
        return null;
    }

    /**
     * Traverse the specified directory list, check the remaining space of the directory, and return the file (not created)
     *
     * @param fileName file name
     * @param minBytes Minimum available bytes
     * @param dirs     Directory list
     */
    @Nullable
    public static File getFileIn(@Nullable File[] dirs, @NonNull String fileName, long minBytes) {
        return getFileIn(dirs, fileName, minBytes, false);
    }
}