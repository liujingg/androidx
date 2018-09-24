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
import android.os.storage.StorageVolume;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

import me.panpf.javax.lang.Classx;

@SuppressWarnings("WeakerAccess")
public class StorageVolumeCompat {

    @NotNull
    private StorageVolume storageVolume;

    public StorageVolumeCompat(@NotNull StorageVolume storageVolume) {
        this.storageVolume = storageVolume;
    }

    /**
     * Returns the mount path for the volume.
     *
     * @return the mount path
     */
    @Nullable
    public String getPath() {
        try {
            return (String) Classx.callMethod(storageVolume, "getPath");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public File getPathFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                return (File) Classx.callMethod(storageVolume, "getPathFile");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        String path = getPath();
        return path != null ? new File(path) : null;
    }

    /**
     * Returns true if the volume is the primary shared/external storage, which is the volume
     * backed by {@link Environment#getExternalStorageDirectory()}.
     */
    public boolean isPrimary() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return storageVolume.isPrimary();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    return (boolean) Classx.callMethod(storageVolume, "isPrimary");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            String path = getPath();
            return path != null && path.equals(Environment.getExternalStorageDirectory().getPath());
        }
    }

    /**
     * Returns true if the volume is removable.
     *
     * @return is removable
     */
    public boolean isRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return storageVolume.isRemovable();
        } else {
            try {
                return (boolean) Classx.callMethod(storageVolume, "isRemovable");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Returns true if the volume is emulated.
     *
     * @return is removable
     */
    public boolean isEmulated() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return storageVolume.isEmulated();
        } else {
            try {
                return (boolean) Classx.callMethod(storageVolume, "isEmulated");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Returns true if this volume can be shared via USB mass storage.
     *
     * @return whether mass storage is allowed
     */
    public boolean allowMassStorage() {
        try {
            return (boolean) Classx.callMethod(storageVolume, "allowMassStorage");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns maximum file size for the volume, or zero if it is unbounded.
     *
     * @return maximum file size
     */
    public long getMaxFileSize() {
        try {
            return (long) Classx.callMethod(storageVolume, "getMaxFileSize");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Returns the current state of the volume.
     *
     * @return one of {@link Environment#MEDIA_UNKNOWN}, {@link Environment#MEDIA_REMOVED},
     * {@link Environment#MEDIA_UNMOUNTED}, {@link Environment#MEDIA_CHECKING},
     * {@link Environment#MEDIA_NOFS}, {@link Environment#MEDIA_MOUNTED},
     * {@link Environment#MEDIA_MOUNTED_READ_ONLY}, {@link Environment#MEDIA_SHARED},
     * {@link Environment#MEDIA_BAD_REMOVAL}, or {@link Environment#MEDIA_UNMOUNTABLE}.
     */
    @NotNull
    public String getState(@NotNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return storageVolume.getState();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    Object callResult = Classx.callMethod(storageVolume, "getState");
                    return callResult != null ? callResult.toString() : "unknown";
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            String path = getPath();
            return path != null ? new StorageManagerCompat(context).getVolumeState(path) : "unknown";
        }
    }

    @Override
    public boolean equals(Object obj) {
        return storageVolume.equals(obj);
    }

    @Override
    public int hashCode() {
        return storageVolume.hashCode();
    }

    @Override
    public String toString() {
        return storageVolume.toString();
    }
}
