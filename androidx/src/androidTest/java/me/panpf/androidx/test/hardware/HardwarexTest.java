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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import me.panpf.androidx.Androidx;
import me.panpf.androidx.app.Permissionx;
import me.panpf.androidx.hardware.Hardwarex;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.net.Netx;
import me.panpf.javax.util.Comparisonx;


@RunWith(AndroidJUnit4.class)
public class HardwarexTest {

    @Test
    public final void testModel() {
        Assert.assertTrue(Stringx.isSafe(Hardwarex.getModel()));
    }

    @Test
    public final void testName() {
        Assert.assertTrue(Stringx.isSafe(Hardwarex.getDevice()));
    }

    @Test
    public final void testHardware() {
        Assert.assertTrue(Stringx.isSafe(Hardwarex.getHardware()));
    }

    @Test
    public final void testProduct() {
        Assert.assertTrue(Stringx.isSafe(Hardwarex.getProduct()));
    }

    @Test
    public final void testBrand() {
        Assert.assertTrue(Stringx.isSafe(Hardwarex.getBrand()));
    }

    @Test
    public final void testSupportedAbis() {
        Assert.assertTrue(Hardwarex.getSupportedAbis().length != 0);
    }

    @Test
    public final void testPhoneNumber() {
        Context context = InstrumentationRegistry.getContext();
        Assert.assertNotNull(Hardwarex.getPhoneNumber(context));
    }

    @Test
    public final void testDeviceId() {
        Context context = InstrumentationRegistry.getContext();
        if (Androidx.isAtLeast6_0()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String deviceId = Hardwarex.getDeviceId(context);
                Assert.assertTrue("deviceId: " + deviceId, Stringx.isSafe(deviceId)
                        && !Comparisonx.areEqual(deviceId, "unknown")
                        && !Comparisonx.areEqual(deviceId, "PermissionDenied"));
            } else {
                String deviceId = Hardwarex.getDeviceId(context);
                Assert.assertEquals("deviceId: " + deviceId, deviceId, "PermissionDenied");
            }
        } else {
            String deviceId = Hardwarex.getDeviceId(context);
            Assert.assertTrue("deviceId: " + deviceId, Stringx.isSafe(deviceId));
        }
    }

    @Test
    public final void testAndroidId() {
        String androidId = Hardwarex.getAndroidId(InstrumentationRegistry.getContext());
        Assert.assertTrue("androidId: " + androidId, Stringx.isSafe(androidId));
    }

    @Test
    public final void testSubscriberId() {
        Context context = InstrumentationRegistry.getContext();
        if (Androidx.isAtLeast6_0()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String subscriberId = Hardwarex.getSubscriberId(context);
                Assert.assertTrue("subscriberId: " + subscriberId, Stringx.isSafe(subscriberId)
                        && !Comparisonx.areEqual(subscriberId, "unknown")
                        && !Comparisonx.areEqual(subscriberId, "PermissionDenied"));
            } else {
                String subscriberId = Hardwarex.getSubscriberId(context);
                Assert.assertEquals("subscriberId: " + subscriberId, subscriberId, "PermissionDenied");
            }
        } else {
            String subscriberId = Hardwarex.getSubscriberId(context);
            Assert.assertTrue("subscriberId: " + subscriberId, Stringx.isSafe(subscriberId));
        }
    }

    @Test
    public final void testSimSerialNumber() {
        Context context = InstrumentationRegistry.getContext();
        if (Androidx.isAtLeast6_0()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String simSerialNumber = Hardwarex.getSimSerialNumber(context);
                Assert.assertTrue("simSerialNumber: " + simSerialNumber, Stringx.isSafe(simSerialNumber)
                        && !Comparisonx.areEqual(simSerialNumber, "unknown")
                        && !Comparisonx.areEqual(simSerialNumber, "PermissionDenied"));
            } else {
                String simSerialNumber = Hardwarex.getSimSerialNumber(context);
                Assert.assertEquals("simSerialNumber: " + simSerialNumber, simSerialNumber, "PermissionDenied");
            }
        } else {
            String simSerialNumber = Hardwarex.getSimSerialNumber(context);
            Assert.assertTrue("simSerialNumber: " + simSerialNumber, Stringx.isSafe(simSerialNumber));
        }
    }

    @Test
    public final void testSerial() {
        Context context = InstrumentationRegistry.getContext();
        if (Androidx.isAtLeastP()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String serial = Hardwarex.getSerial();
                Assert.assertTrue("serial: " + serial, Stringx.isSafe(serial)
                        && !Comparisonx.areEqual(serial, "unknown")
                        && !Comparisonx.areEqual(serial, "PermissionDenied"));
            } else {
                String serial = Hardwarex.getSerial();
                Assert.assertEquals("serial: " + serial, serial, "PermissionDenied");
            }
        } else {
            String serial = Hardwarex.getSerial();
            Assert.assertTrue("serial: " + serial, Stringx.isSafe(serial));
        }
    }

    @Test
    public final void testIMEI() {
        Context context = InstrumentationRegistry.getContext();
        if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
            String imei = Hardwarex.getIMEI(context);
            Assert.assertTrue("imei: " + imei, Stringx.isSafe(imei) && !Comparisonx.areEqual(imei, "PermissionDenied"));
        } else {
            String imei = Hardwarex.getIMEI(context);
            Assert.assertEquals("imei: " + imei, imei, "PermissionDenied");
        }
    }

    @Test
    public final void testIMSI() {
        Context context = InstrumentationRegistry.getContext();
        if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
            String imsi = Hardwarex.getIMSI(context);
            Assert.assertTrue("imsi: " + imsi, Stringx.isSafe(imsi) && !Comparisonx.areEqual(imsi, "PermissionDenied"));
        } else {
            String imsi = Hardwarex.getIMSI(context);
            Assert.assertEquals("imsi: " + imsi, imsi, "PermissionDenied");
        }
    }

    @Test
    public final void testMacAddress() {
        Context context = InstrumentationRegistry.getContext();
        if (Androidx.isAtLeast6_0()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                String macAddress = Hardwarex.getMacAddress(context);
                Assert.assertTrue("macAddress: " + macAddress, Stringx.isSafe(macAddress)
                        && !Comparisonx.areEqual(macAddress, "unknown")
                        && !Comparisonx.areEqual(macAddress, "PermissionDenied"));
            } else {
                String macAddress = Hardwarex.getMacAddress(context);
                Assert.assertEquals("macAddress: " + macAddress, macAddress, "PermissionDenied");
            }
        } else {
            String macAddress = Hardwarex.getMacAddress(context);
            Assert.assertTrue("macAddress: " + macAddress, Netx.isMacAddress(macAddress));
        }
    }
}
