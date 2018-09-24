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

package me.panpf.androidxkt.test.os.storage

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.os.storage.StorageManagerCompat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class StorageVolumeCompatTest {

    @Test
    fun testGetPath() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        Assert.assertNotNull(managerCompat.getStorageVolume(File(managerCompat.getVolumePaths()[0]))?.path)
    }

    @Test
    fun testGetPathFile() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        Assert.assertNotNull(managerCompat.getStorageVolume(File(managerCompat.getVolumePaths()[0]))?.pathFile)
    }

    @Test
    fun testIsPrimary() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        val volumePaths = managerCompat.getVolumePaths()

        // 内置 sd 卡
        val primaryVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[0]))
        Assert.assertNotNull(primaryVolumeCompat)
        Assert.assertTrue(primaryVolumeCompat?.isPrimary ?: false)

        // 外置 sd 卡
        if (volumePaths.size > 1) {
            val expansionVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[1]))
            Assert.assertNotNull(expansionVolumeCompat)
            Assert.assertFalse(expansionVolumeCompat?.isPrimary ?: true)
        }
    }

    @Test
    fun testIsEmulated() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        val volumePaths = managerCompat.getVolumePaths()

        // 内置 sd 卡有可能不是虚拟的
        Assert.assertNotNull(managerCompat.getStorageVolume(File(volumePaths[0]))?.isEmulated)

        // 外置 sd 卡一定不是虚拟的
        if (volumePaths.size > 1) {
            val expansionVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[1]))
            Assert.assertNotNull(expansionVolumeCompat)
            Assert.assertFalse(expansionVolumeCompat?.isEmulated ?: true)
        }
    }

    @Test
    fun testIsRemovable() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        val volumePaths = managerCompat.getVolumePaths()

        // 内置 sd 卡，有可能是非虚拟，同时也是不可插拔的
        Assert.assertNotNull(managerCompat.getStorageVolume(File(volumePaths[0]))?.isRemovable)

        // 外置 sd 卡，非虚拟卡才可插拔
        if (volumePaths.size > 1) {
            val expansionVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[1]))
            Assert.assertNotNull(expansionVolumeCompat)
            if (expansionVolumeCompat?.isEmulated == true) {
                Assert.assertFalse(expansionVolumeCompat.isRemovable)
            } else {
                Assert.assertTrue(expansionVolumeCompat?.isRemovable ?: false)
            }
        }
    }

    @Test
    fun testAllowMassStorage() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        val volumePaths = managerCompat.getVolumePaths()

        // 内置 sd 卡
        Assert.assertNotNull(managerCompat.getStorageVolume(File(volumePaths[0]))?.allowMassStorage())

        // 外置 sd 卡
        if (volumePaths.size > 1) {
            Assert.assertNotNull(managerCompat.getStorageVolume(File(volumePaths[1]))?.allowMassStorage())
        }
    }

    @Test
    fun testGetMaxFileSize() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        val volumePaths = managerCompat.getVolumePaths()

        // 内置 sd 卡
        val primaryVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[0]))
        Assert.assertNotNull(primaryVolumeCompat)
        Assert.assertTrue(primaryVolumeCompat?.maxFileSize ?: -1 >= 0)

        // 外置 sd 卡
        if (volumePaths.size > 1) {
            val expansionVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[1]))
            Assert.assertNotNull(expansionVolumeCompat)
            Assert.assertTrue(expansionVolumeCompat?.maxFileSize ?: -1 >= 0)
        }
    }

    @Test
    fun testGetState() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)

        val volumePaths = managerCompat.getVolumePaths()

        // 内置 sd 卡
        val primaryVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[0]))
        Assert.assertNotNull(primaryVolumeCompat)
        Assert.assertNotEquals(primaryVolumeCompat?.getState(context), "unknown")

        // 外置 sd 卡
        if (volumePaths.size > 1) {
            val expansionVolumeCompat = managerCompat.getStorageVolume(File(volumePaths[1]))
            Assert.assertNotNull(expansionVolumeCompat)
            Assert.assertNotEquals(expansionVolumeCompat?.getState(context), "unknown")
        }
    }
}
