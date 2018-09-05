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

package me.panpf.androidx.test.hardware;

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.app.Permissionx;
import me.panpf.androidx.hardware.Hardwarex;
import me.panpf.androidx.os.Buildx;
import me.panpf.javax.util.Premisex;


@RunWith(AndroidJUnit4.class)
public class HardwareTest {

    @Test
    public final void testDeviceModel() {
        Assert.assertTrue(Hardwarex.getDeviceModel().length() > 0);
    }

    @Test
    public final void testDeviceName() {
        Assert.assertTrue(Hardwarex.getDeviceName().length() > 0);
    }

    @Test
    public final void testHardware() {
        Assert.assertTrue(Hardwarex.getHardware().length() > 0);
    }

    @Test
    public final void testSupportedAbis() {
        Assert.assertTrue(Hardwarex.getSupportedAbis().length != 0);
    }

    @Test
    public final void testDeviceId() {
        Context context = InstrumentationRegistry.getContext();
        if (Permissionx.isGrantPermission(context, Manifest.permission.READ_PHONE_STATE)) {
            String deviceId = Hardwarex.getDeviceId(context);
            Assert.assertTrue(deviceId.length() > 0 && !Premisex.areEqual(deviceId, "unknown") && !Premisex.areEqual(deviceId, "PermissionDenied"));
        } else {
            Assert.assertEquals(Hardwarex.getDeviceId(context), "PermissionDenied");
        }
    }

    @Test
    public final void testAndroidId() {
        Assert.assertTrue(Hardwarex.getAndroidId(InstrumentationRegistry.getContext()).length() > 0);
    }

    @Test
    public final void testSubscriberId() {
        Context context = InstrumentationRegistry.getContext();
        if (Permissionx.isGrantPermission(context, Manifest.permission.READ_PHONE_STATE)) {
            String subscriberId = Hardwarex.getSubscriberId(context);
            Assert.assertTrue(subscriberId.length() > 0 && !Premisex.areEqual(subscriberId, "unknown") && !Premisex.areEqual(subscriberId, "PermissionDenied"));
        } else {
            Assert.assertEquals(Hardwarex.getSubscriberId(context), "PermissionDenied");
        }

    }

    @Test
    public final void testSimSerialNumber() {
        Context context = InstrumentationRegistry.getContext();
        if (Permissionx.isGrantPermission(context, Manifest.permission.READ_PHONE_STATE)) {
            String simSerialNumber = Hardwarex.getSimSerialNumber(context);
            Assert.assertTrue(simSerialNumber.length() > 0 && !Premisex.areEqual(simSerialNumber, "unknown") && !Premisex.areEqual(simSerialNumber, "PermissionDenied"));
        } else {
            Assert.assertEquals(Hardwarex.getSimSerialNumber(context), "PermissionDenied");
        }
    }

    @Test
    public final void testSerial() {
        if (Buildx.isAtLeastP()) {
            Context context = InstrumentationRegistry.getContext();
            if (Permissionx.isGrantPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                String serial = Hardwarex.getSerial();
                Assert.assertTrue(serial.length() > 0 && !Premisex.areEqual(serial, "unknown") && !Premisex.areEqual(serial, "PermissionDenied"));
            } else {
                Assert.assertEquals(Hardwarex.getSerial(), "PermissionDenied");
            }
        } else {
            Assert.assertTrue(Hardwarex.getSerial().length() > 0);
        }
    }

    @Test
    public final void testMacAddress() {
        Context context = InstrumentationRegistry.getContext();
        if (Permissionx.isGrantPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
            String macAddress = Hardwarex.getMacAddress(context);
            Assert.assertTrue(macAddress.length() > 0 && !Premisex.areEqual(macAddress, "unknown") && !Premisex.areEqual(macAddress, "PermissionDenied"));
        } else {
            Assert.assertEquals(Hardwarex.getMacAddress(context), "PermissionDenied");
        }
    }
}
