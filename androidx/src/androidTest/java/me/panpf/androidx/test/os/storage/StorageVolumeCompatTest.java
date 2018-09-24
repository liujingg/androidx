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

package me.panpf.androidx.test.os.storage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import me.panpf.androidx.os.storage.StorageManagerCompat;
import me.panpf.androidx.os.storage.StorageVolumeCompat;

@RunWith(AndroidJUnit4.class)
public class StorageVolumeCompatTest {

    @Test
    public void testGetPath() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        StorageVolumeCompat volumeCompat = managerCompat.getStorageVolume(new File(managerCompat.getVolumePaths()[0]));
        Assert.assertNotNull(volumeCompat != null ? volumeCompat.getPath() : null);
    }

    @Test
    public void testGetPathFile() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        StorageVolumeCompat volumeCompat = managerCompat.getStorageVolume(new File(managerCompat.getVolumePaths()[0]));
        Assert.assertNotNull(volumeCompat != null ? volumeCompat.getPathFile() : null);
    }

    @Test
    public void testIsPrimary() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        String[] volumePaths = managerCompat.getVolumePaths();

        // 内置 sd 卡
        StorageVolumeCompat primaryVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[0]));
        Assert.assertNotNull(primaryVolumeCompat);
        Assert.assertTrue(primaryVolumeCompat.isPrimary());

        // 外置 sd 卡
        if (volumePaths.length > 1) {
            StorageVolumeCompat expansionVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[1]));
            Assert.assertNotNull(expansionVolumeCompat);
            Assert.assertFalse(expansionVolumeCompat.isPrimary());
        }
    }

    @Test
    public void testIsEmulated() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        String[] volumePaths = managerCompat.getVolumePaths();

        // 内置 sd 卡有可能不是虚拟的
        StorageVolumeCompat primaryVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[0]));
        Assert.assertNotNull(primaryVolumeCompat != null ? primaryVolumeCompat.isEmulated() : null);

        // 外置 sd 卡一定不是虚拟的
        if (volumePaths.length > 1) {
            StorageVolumeCompat expansionVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[1]));
            Assert.assertNotNull(expansionVolumeCompat);
            Assert.assertFalse(expansionVolumeCompat.isEmulated());
        }
    }

    @Test
    public void testIsRemovable() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        String[] volumePaths = managerCompat.getVolumePaths();

        // 内置 sd 卡，有可能是非虚拟，同时也是不可插拔的
        StorageVolumeCompat primaryVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[0]));
        Assert.assertNotNull(primaryVolumeCompat != null ? primaryVolumeCompat.isRemovable() : null);

        // 外置 sd 卡，非虚拟卡才可插拔
        if (volumePaths.length > 1) {
            StorageVolumeCompat expansionVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[1]));
            Assert.assertNotNull(expansionVolumeCompat);
            if (expansionVolumeCompat.isEmulated()) {
                Assert.assertFalse(expansionVolumeCompat.isRemovable());
            } else {
                Assert.assertTrue(expansionVolumeCompat.isRemovable());
            }
        }
    }

    @Test
    public void testAllowMassStorage() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        String[] volumePaths = managerCompat.getVolumePaths();

        // 内置 sd 卡
        StorageVolumeCompat primaryVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[0]));
        Assert.assertNotNull(primaryVolumeCompat != null ? primaryVolumeCompat.allowMassStorage() : null);

        // 外置 sd 卡
        if (volumePaths.length > 1) {
            StorageVolumeCompat expansionVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[1]));
            Assert.assertNotNull(expansionVolumeCompat != null ? expansionVolumeCompat.allowMassStorage() : null);
        }
    }

    @Test
    public void testGetMaxFileSize() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        String[] volumePaths = managerCompat.getVolumePaths();

        // 内置 sd 卡
        StorageVolumeCompat primaryVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[0]));
        Assert.assertNotNull(primaryVolumeCompat);
        Assert.assertTrue(primaryVolumeCompat.getMaxFileSize() >= 0);

        // 外置 sd 卡
        if (volumePaths.length > 1) {
            StorageVolumeCompat expansionVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[1]));
            Assert.assertNotNull(expansionVolumeCompat);
            Assert.assertTrue(expansionVolumeCompat.getMaxFileSize() >= 0);
        }
    }

    @Test
    public void testGetState() {
        Context context = InstrumentationRegistry.getContext();
        StorageManagerCompat managerCompat = new StorageManagerCompat(context);

        String[] volumePaths = managerCompat.getVolumePaths();

        // 内置 sd 卡
        StorageVolumeCompat primaryVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[0]));
        Assert.assertNotNull(primaryVolumeCompat);
        Assert.assertNotEquals(primaryVolumeCompat.getState(context), "unknown");

        // 外置 sd 卡
        if (volumePaths.length > 1) {
            StorageVolumeCompat expansionVolumeCompat = managerCompat.getStorageVolume(new File(volumePaths[1]));
            Assert.assertNotNull(expansionVolumeCompat);
            Assert.assertNotEquals(expansionVolumeCompat.getState(context), "unknown");
        }
    }
}
