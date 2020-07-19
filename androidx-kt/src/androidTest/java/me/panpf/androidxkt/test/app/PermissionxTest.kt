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

package me.panpf.androidxkt.test.app

import android.Manifest
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidx.Androidx
import me.panpf.androidxkt.app.filterDeniedPermissions
import me.panpf.androidxkt.app.isGrantPermissions
import me.panpf.androidxkt.hardware.getDeviceIdOr
import me.panpf.androidxkt.hardware.getMacAddress
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PermissionxTest {

    @Test
    fun testSinglePermission() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertTrue(context.isGrantPermissions(Manifest.permission.INTERNET))
    }

    @Test
    fun testSingleNoPermission() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeastM()) {
            val result = context.getDeviceIdOr("defaultValue")
            if ("defaultValue" == result) {
                Assert.assertFalse(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE))
            } else {
                Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE))
            }
        } else {
            Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE))
        }
    }

    @Test
    fun testMultiPermission() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertTrue(context.filterDeniedPermissions(Manifest.permission.INTERNET, Manifest.permission.VIBRATE).isEmpty())
    }

    @Test
    fun testMultiNoPermission() {
        val context = InstrumentationRegistry.getContext()
        if (Androidx.isAtLeastM()) {
            val deviceIdResult = context.getDeviceIdOr("defaultValue")
            val macAddressResult = context.getMacAddress()
            if ("defaultValue" == deviceIdResult || "defaultValue" == macAddressResult) {
                Assert.assertFalse(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE))
            } else {
                Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE))
            }
        } else {
            Assert.assertTrue(context.isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE))
        }
    }
}