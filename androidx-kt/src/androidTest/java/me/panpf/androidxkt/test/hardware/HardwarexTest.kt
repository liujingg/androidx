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
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidx.Androidx
import me.panpf.androidxkt.app.isGrantPermissions
import me.panpf.androidxkt.hardware.*
import me.panpf.javaxkt.lang.isSafe
import me.panpf.javaxkt.net.isMacAddress
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HardwarexTest {

    @Test
    fun testPhoneNumber() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertNotNull(context.getPhoneNumber())
    }

    @Test
    fun testDeviceId() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val deviceId = context.getDeviceId()
                Assert.assertTrue("deviceId: $deviceId", deviceId.isSafe()
                        && deviceId != "unknown"
                        && deviceId != "PermissionDenied")
            } else {
                val deviceId = context.getDeviceId()
                Assert.assertEquals("deviceId: $deviceId", deviceId, "PermissionDenied")
            }
        } else {
            val deviceId = context.getDeviceId()
            Assert.assertTrue("deviceId: $deviceId", deviceId.isSafe())
        }
    }

    @Test
    fun testAndroidId() {
        val androidId = InstrumentationRegistry.getContext().getAndroidId()
        Assert.assertTrue("androidId: $androidId", androidId.isSafe())
    }

    @Test
    fun testSubscriberId() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val subscriberId = context.getSubscriberId()
                Assert.assertTrue("subscriberId: $subscriberId", subscriberId.isSafe()
                        && subscriberId != "unknown"
                        && subscriberId != "PermissionDenied")
            } else {
                val subscriberId = context.getSubscriberId()
                Assert.assertEquals("subscriberId: $subscriberId", subscriberId, "PermissionDenied")
            }
        } else {
            val subscriberId = context.getSubscriberId()
            Assert.assertTrue("subscriberId: $subscriberId", subscriberId.isSafe())
        }
    }

    @Test
    fun testSimSerialNumber() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val simSerialNumber = context.getSimSerialNumber()
                Assert.assertTrue("simSerialNumber: $simSerialNumber", simSerialNumber.isSafe()
                        && simSerialNumber != "unknown"
                        && simSerialNumber != "PermissionDenied")
            } else {
                val simSerialNumber = context.getSimSerialNumber()
                Assert.assertEquals("simSerialNumber: $simSerialNumber", simSerialNumber, "PermissionDenied")
            }
        } else {
            val simSerialNumber = context.getSimSerialNumber()
            Assert.assertTrue("simSerialNumber: $simSerialNumber", simSerialNumber.isSafe())
        }
    }

    @Test
    fun testIMEI() {
        val context = InstrumentationRegistry.getContext()
        if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
            val imei = context.getIMEI()
            Assert.assertTrue("imei: $imei", imei.isSafe() && imei != "PermissionDenied")
        } else {
            val imei = context.getIMEI()
            Assert.assertEquals("imei: $imei", imei, "PermissionDenied")
        }
    }

    @Test
    fun testIMSI() {
        val context = InstrumentationRegistry.getContext()
        if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
            val imsi = context.getIMSI()
            Assert.assertTrue("imsi: $imsi", imsi.isSafe() && imsi != "PermissionDenied")
        } else {
            val imsi = context.getIMSI()
            Assert.assertEquals("imsi: $imsi", imsi, "PermissionDenied")
        }
    }

    @Test
    fun testMacAddress() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.ACCESS_WIFI_STATE)) {
                val macAddress = context.getMacAddress()
                Assert.assertTrue("macAddress: $macAddress", macAddress.isSafe()
                        && macAddress != "unknown"
                        && macAddress != "PermissionDenied")
            } else {
                val macAddress = context.getMacAddress()
                Assert.assertEquals("macAddress: $macAddress", macAddress, "PermissionDenied")
            }
        } else {
            val macAddress = context.getMacAddress()
            Assert.assertTrue("macAddress: $macAddress", macAddress.isMacAddress())
        }
    }
}