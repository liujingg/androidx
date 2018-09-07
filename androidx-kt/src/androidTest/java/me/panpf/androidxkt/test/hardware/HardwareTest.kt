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

package me.panpf.androidxkt.test.hardware

import android.Manifest
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.isGrantPermissions
import me.panpf.androidxkt.hardware.*
import me.panpf.androidxkt.isAtLeastP
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HardwareTest {

    @Test
    fun testDeviceModel() {
        Assert.assertTrue(getDeviceModel().isNotEmpty())
    }

    @Test
    fun testDeviceName() {
        Assert.assertTrue(getDeviceName().isNotEmpty())
    }

    @Test
    fun testHardware() {
        Assert.assertTrue(getHardware().isNotEmpty())
    }

    @Test
    fun testSupportedAbis() {
        Assert.assertTrue(getSupportedAbis().isNotEmpty())
    }

    @Test
    fun testDeviceId() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
            Assert.assertTrue(context.getDeviceId().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertEquals(context.getDeviceId(), "PermissionDenied")
        }
    }

    @Test
    fun testAndroidId() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(context.getAndroidId().isNotEmpty())
    }

    @Test
    fun testSubscriberId() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
            Assert.assertTrue(context.getSubscriberId().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertEquals(context.getSubscriberId(), "PermissionDenied")
        }
    }

    @Test
    fun testSimSerialNumber() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
            Assert.assertTrue(context.getSimSerialNumber().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertEquals(context.getSimSerialNumber(), "PermissionDenied")
        }
    }

    @Test
    fun testSerial() {
        if (isAtLeastP()) {
            val context = InstrumentationRegistry.getContext()

            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                Assert.assertTrue(getSerial().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
            } else {
                Assert.assertEquals(getSerial(), "PermissionDenied")
            }
        } else {
            Assert.assertTrue(getSerial().isNotEmpty())
        }
    }

    @Test
    fun testMacAddress() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermissions(Manifest.permission.ACCESS_WIFI_STATE)) {
            Assert.assertTrue(context.getMacAddress().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertEquals(context.getMacAddress(), "PermissionDenied")
        }
    }
}