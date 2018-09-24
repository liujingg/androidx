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
class StorageManagerCompatTest {

    @Test
    fun testGetVolumeList() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)
        Assert.assertTrue(managerCompat.getVolumeList().isNotEmpty())
    }

    @Test
    fun testGetStorageVolumes() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)
        Assert.assertTrue(managerCompat.getStorageVolumes().isNotEmpty())
    }

    @Test
    fun testGetVolumePaths() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)
        Assert.assertTrue(managerCompat.getVolumePaths().isNotEmpty())
    }

    @Test
    fun testGetStorageVolume() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)
        Assert.assertNotNull(managerCompat.getStorageVolume(File(managerCompat.getVolumePaths()[0])))
    }

    @Test
    fun testGetVolumeState() {
        val context = InstrumentationRegistry.getContext()
        val managerCompat = StorageManagerCompat(context)
        Assert.assertNotEquals(managerCompat.getVolumeState(managerCompat.getVolumePaths()[0]), "unknown")
    }
}
