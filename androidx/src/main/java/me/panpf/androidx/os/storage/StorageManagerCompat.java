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

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.panpf.javax.lang.Classx;
import me.panpf.javax.util.Arrayx;
import me.panpf.javax.util.Collectionx;
import me.panpf.javax.util.Transformer;

@SuppressWarnings("WeakerAccess")
public class StorageManagerCompat {

    @NotNull
    private StorageManager manager;

    public StorageManagerCompat(@NotNull Context context) {
        final StorageManager manager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        if (manager == null) throw new IllegalStateException("StorageManager not found");
        this.manager = manager;
    }

    @NonNull
    public List<StorageVolumeCompat> getVolumeList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Collectionx.map(manager.getStorageVolumes(), new Transformer<StorageVolume, StorageVolumeCompat>() {
                @NotNull
                @Override
                public StorageVolumeCompat transform(@NotNull StorageVolume storageVolume) {
                    return new StorageVolumeCompat(storageVolume);
                }
            });
        } else {
            StorageVolume[] storageVolumes = null;
            try {
                storageVolumes = (StorageVolume[]) Classx.callMethod(manager, "getVolumeList");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return storageVolumes != null ? Arrayx.map(storageVolumes, new Transformer<StorageVolume, StorageVolumeCompat>() {
                @NotNull
                @Override
                public StorageVolumeCompat transform(@NotNull StorageVolume storageVolume) {
                    return new StorageVolumeCompat(storageVolume);
                }
            }) : new ArrayList<StorageVolumeCompat>(0);
        }
    }

    @NonNull
    public StorageVolumeCompat[] getVolumes() {
        // 本来可以 直接 反射调用 getVolumeList 方法的，可是在 8.1 模拟器上发现 manager.getStorageVolumes() 和 反射调用 getVolumeList 方法返回的结果不一致
        // 为了一致性考虑才使用这种解决办法
        return getVolumeList().toArray(new StorageVolumeCompat[0]);
    }

    @NonNull
    public String[] getVolumePaths() {
        List<String> volumePaths = new LinkedList<>();
        for (StorageVolumeCompat volumeCompat : getVolumeList()) {
            String path = volumeCompat.getPath();
            if (path != null) {
                volumePaths.add(path);
            }
        }
        return volumePaths.toArray(new String[0]);
    }

    /**
     * Gets the state of a volume via its mount point.
     *
     * @return one of {@link Environment#MEDIA_UNKNOWN}, {@link Environment#MEDIA_REMOVED},
     * {@link Environment#MEDIA_UNMOUNTED}, {@link Environment#MEDIA_CHECKING},
     * {@link Environment#MEDIA_NOFS}, {@link Environment#MEDIA_MOUNTED},
     * {@link Environment#MEDIA_MOUNTED_READ_ONLY}, {@link Environment#MEDIA_SHARED},
     * {@link Environment#MEDIA_BAD_REMOVAL}, or {@link Environment#MEDIA_UNMOUNTABLE}.
     */
    @NotNull
    public String getVolumeState(@NotNull String mountPoint) {
        Object result = null;
        try {
            result = Classx.callMethod(manager, "getVolumeState", mountPoint);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result != null ? result.toString() : "unknown";
    }

    /**
     * Return the {@link StorageVolume} that contains the given file, or {@code null} if none.
     */
    @SuppressLint("NewApi")
    @Nullable
    public StorageVolumeCompat getVolume(@NotNull File file) {
        StorageVolume storageVolume = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Sometimes the file is right or the StorageVolume is not available.
            storageVolume = manager.getStorageVolume(file);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                storageVolume = (StorageVolume) Classx.callMethod(manager, "getStorageVolume", file);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (storageVolume != null) {
            return new StorageVolumeCompat(storageVolume);
        }

        return matchVolume(getVolumeList(), file);
    }

    @Nullable
    private StorageVolumeCompat matchVolume(@NotNull List<StorageVolumeCompat> volumes, @Nullable File file) {
        if (file == null) return null;
        File canonicalFile;
        try {
            canonicalFile = file.getCanonicalFile();
        } catch (IOException ignored) {
            return null;
        }
        // 在 4.1 版本内置 SD卡路径是 /mnt/sdcard 扩展 SD 卡路径是 /mnt/sdcard/external_sd，
        // 由于 volumes 始终 /mnt/sdcard 在第一位，如果 file 是 /mnt/sdcard/external_sd/download 将始终匹配到 /mnt/sdcard
        // 因此这里将 volumes 翻转一下就可解决这个问题
        for (StorageVolumeCompat volume : Collectionx.reversed(volumes)) {
            File volumeFile = volume.getPathFile();
            File canonicalVolumeFile;
            try {
                canonicalVolumeFile = volumeFile != null ? volumeFile.getCanonicalFile() : null;
            } catch (IOException ignored) {
                continue;
            }
            if (contains(canonicalVolumeFile, canonicalFile)) {
                return volume;
            }
        }
        return null;
    }

    /**
     * Test if a file lives under the given directory, either as a direct child
     * or a distant grandchild.
     * <p>
     * Both files <em>must</em> have been resolved using
     * {@link File#getCanonicalFile()} to avoid symlink or path traversal
     * attacks.
     */
    private boolean contains(File dir, File file) {
        if (dir == null || file == null) return false;

        String dirPath = dir.getAbsolutePath();
        String filePath = file.getAbsolutePath();

        if (dirPath.equals(filePath)) {
            return true;
        }

        if (!dirPath.endsWith("/")) {
            dirPath += "/";
        }
        return filePath.startsWith(dirPath);
    }
}
