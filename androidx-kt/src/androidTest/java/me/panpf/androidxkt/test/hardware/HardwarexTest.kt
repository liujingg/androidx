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
import me.panpf.androidx.Androidx
import me.panpf.androidx.app.Permissionx
import me.panpf.androidx.hardware.Hardwarex
import me.panpf.androidxkt.app.isGrantPermissions
import me.panpf.androidxkt.hardware.*
import me.panpf.javax.lang.Stringx
import me.panpf.javax.util.Comparisonx
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HardwarexTest {

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
        if (Androidx.isAtLeast6_0()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                val deviceId = context.getDeviceId()
                Assert.assertTrue("deviceId: $deviceId", Stringx.isSafe(deviceId)
                        && !Comparisonx.areEqual(deviceId, "unknown")
                        && !Comparisonx.areEqual(deviceId, "PermissionDenied"))
            } else {
                val deviceId = context.getDeviceId()
                Assert.assertEquals("deviceId: $deviceId", deviceId, "PermissionDenied")
            }
        } else {
            val deviceId = context.getDeviceId()
            Assert.assertTrue("deviceId: $deviceId", Stringx.isSafe(deviceId))
        }
    }

    @Test
    fun testAndroidId() {
        val androidId = Hardwarex.getAndroidId(InstrumentationRegistry.getContext())
        Assert.assertTrue("androidId: $androidId", Stringx.isSafe(androidId))
    }

    @Test
    fun testSubscriberId() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val subscriberId = context.getSubscriberId()
                Assert.assertTrue("subscriberId: $subscriberId", Stringx.isSafe(subscriberId)
                        && !Comparisonx.areEqual(subscriberId, "unknown")
                        && !Comparisonx.areEqual(subscriberId, "PermissionDenied"))
            } else {
                val subscriberId = context.getSubscriberId()
                Assert.assertEquals("subscriberId: $subscriberId", subscriberId, "PermissionDenied")
            }
        } else {
            val subscriberId = context.getSubscriberId()
            Assert.assertTrue("subscriberId: $subscriberId", Stringx.isSafe(subscriberId))
        }
    }

    @Test
    fun testSimSerialNumber() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val simSerialNumber = context.getSimSerialNumber()
                Assert.assertTrue("simSerialNumber: $simSerialNumber", Stringx.isSafe(simSerialNumber)
                        && !Comparisonx.areEqual(simSerialNumber, "unknown")
                        && !Comparisonx.areEqual(simSerialNumber, "PermissionDenied"))
            } else {
                val simSerialNumber = context.getSimSerialNumber()
                Assert.assertEquals("simSerialNumber: $simSerialNumber", simSerialNumber, "PermissionDenied")
            }
        } else {
            val simSerialNumber = context.getSimSerialNumber()
            Assert.assertTrue("simSerialNumber: $simSerialNumber", Stringx.isSafe(simSerialNumber))
        }
    }

    @Test
    fun testSerial() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeastP()) {
            if (context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE)) {
                val serial = Hardwarex.getSerial()
                Assert.assertTrue("serial: $serial", Stringx.isSafe(serial)
                        && !Comparisonx.areEqual(serial, "unknown")
                        && !Comparisonx.areEqual(serial, "PermissionDenied"))
            } else {
                val serial = Hardwarex.getSerial()
                Assert.assertEquals("serial: $serial", serial, "PermissionDenied")
            }
        } else {
            val serial = Hardwarex.getSerial()
            Assert.assertTrue("serial: $serial", Stringx.isSafe(serial))
        }
    }

    @Test
    fun testMacAddress() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeast6_0()) {
            if (context.isGrantPermissions(Manifest.permission.ACCESS_WIFI_STATE)) {
                val macAddress = context.getMacAddress()
                Assert.assertTrue("macAddress: $macAddress", Stringx.isSafe(macAddress)
                        && !Comparisonx.areEqual(macAddress, "unknown")
                        && !Comparisonx.areEqual(macAddress, "PermissionDenied"))
            } else {
                val macAddress = context.getMacAddress()
                Assert.assertEquals("macAddress: $macAddress", macAddress, "PermissionDenied")
            }
        } else {
            val macAddress = context.getMacAddress()
            Assert.assertTrue("macAddress: $macAddress", Stringx.isSafe(macAddress))
        }
    }
}