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
import android.os.Build;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        Assert.assertNotNull(Hardwarex.getPhoneNumberOr(context, "defaultValue"));
    }

    @Test
    public final void testAndroidId() {
        String androidId = Hardwarex.getAndroidIdOr(InstrumentationRegistry.getContext(), "defaultValue");
        Assert.assertTrue("androidId: " + androidId, Stringx.isSafe(androidId));
    }

    @Test
    public final void testDeviceId() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String deviceId = Hardwarex.getDeviceIdOr(context, "defaultValue");
            Assert.assertEquals("deviceId: " + deviceId, "defaultValue", deviceId);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String deviceId = Hardwarex.getDeviceIdOr(context, "defaultValue");
                Assert.assertTrue("deviceId: " + deviceId,
                        Stringx.isSafe(deviceId) && !Comparisonx.areEqual(deviceId, "defaultValue")
                );
            } else {
                String deviceId = Hardwarex.getDeviceIdOr(context, "defaultValue");
                Assert.assertEquals("deviceId: " + deviceId, "defaultValue", deviceId);
            }
        } else {
            String deviceId = Hardwarex.getDeviceIdOr(context, "defaultValue");
            Assert.assertTrue("deviceId: " + deviceId,
                    Stringx.isSafe(deviceId) && !Comparisonx.areEqual(deviceId, "defaultValue")
            );
        }
    }

    @Test
    public final void testSubscriberId() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String subscriberId = Hardwarex.getSubscriberIdOr(context, "defaultValue");
            Assert.assertEquals("subscriberId: " + subscriberId, "defaultValue", subscriberId);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String subscriberId = Hardwarex.getSubscriberIdOr(context, "defaultValue");
                Assert.assertTrue("subscriberId: " + subscriberId,
                        Stringx.isSafe(subscriberId) && !Comparisonx.areEqual(subscriberId, "defaultValue")
                );
            } else {
                String subscriberId = Hardwarex.getSubscriberIdOr(context, "defaultValue");
                Assert.assertEquals("subscriberId: " + subscriberId, "defaultValue", subscriberId);
            }
        } else {
            String subscriberId = Hardwarex.getSubscriberIdOr(context, "defaultValue");
            Assert.assertTrue("subscriberId: " + subscriberId,
                    Stringx.isSafe(subscriberId) && !Comparisonx.areEqual(subscriberId, "defaultValue")
            );
        }
    }

    @Test
    public final void testSimSerialNumber() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String simSerialNumber = Hardwarex.getSimSerialNumberOr(context, "defaultValue");
            Assert.assertEquals("simSerialNumber: " + simSerialNumber, "defaultValue", simSerialNumber);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String simSerialNumber = Hardwarex.getSimSerialNumberOr(context, "defaultValue");
                Assert.assertTrue("simSerialNumber: " + simSerialNumber,
                        Stringx.isSafe(simSerialNumber) && !Comparisonx.areEqual(simSerialNumber, "defaultValue"));
            } else {
                String simSerialNumber = Hardwarex.getSimSerialNumberOr(context, "defaultValue");
                Assert.assertEquals("simSerialNumber: " + simSerialNumber, "defaultValue", simSerialNumber);
            }
        } else {
            String simSerialNumber = Hardwarex.getSimSerialNumberOr(context, "defaultValue");
            Assert.assertTrue("simSerialNumber: " + simSerialNumber,
                    Stringx.isSafe(simSerialNumber) && !Comparisonx.areEqual(simSerialNumber, "defaultValue")
            );
        }
    }

    @Test
    public final void testSerial() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String serial = Hardwarex.getSerialOr("defaultValue");
            Assert.assertEquals("serial: " + serial, "defaultValue", serial);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String serial = Hardwarex.getSerialOr("defaultValue");
                Assert.assertTrue("serial: " + serial,
                        Stringx.isSafe(serial) && !Comparisonx.areEqual(serial, "defaultValue")
                );
            } else {
                String serial = Hardwarex.getSerialOr("defaultValue");
                Assert.assertEquals("serial: " + serial, "defaultValue", serial);
            }
        } else {
            String serial = Hardwarex.getSerialOr("defaultValue");
            Assert.assertTrue("serial: " + serial,
                    Stringx.isSafe(serial) && !Comparisonx.areEqual(serial, "defaultValue")
            );
        }
    }

    @Test
    public final void testIMEI() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String imei = Hardwarex.getIMEIOr(context, "defaultValue");
            Assert.assertEquals("imei: " + imei, "defaultValue", imei);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String imei = Hardwarex.getIMEIOr(context, "defaultValue");
                Assert.assertTrue("imei: " + imei,
                        Stringx.isSafe(imei) && !Comparisonx.areEqual(imei, "defaultValue")
                );
            } else {
                String imei = Hardwarex.getIMEIOr(context, "defaultValue");
                Assert.assertEquals("imei: " + imei, "defaultValue", imei);
            }
        } else {
            String imei = Hardwarex.getIMEIOr(context, "defaultValue");
            Assert.assertTrue("imei: " + imei,
                    Stringx.isSafe(imei) && !Comparisonx.areEqual(imei, "defaultValue")
            );
        }
    }

    @Test
    public final void testIMSI() {
        Context context = InstrumentationRegistry.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String imsi = Hardwarex.getIMSIOr(context, "defaultValue");
            Assert.assertEquals("imsi: " + imsi, "defaultValue", imsi);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
                String imsi = Hardwarex.getIMSIOr(context, "defaultValue");
                Assert.assertTrue("imsi: " + imsi,
                        Stringx.isSafe(imsi) && !Comparisonx.areEqual(imsi, "defaultValue")
                );
            } else {
                String imsi = Hardwarex.getIMSIOr(context, "defaultValue");
                Assert.assertEquals("imsi: " + imsi, "defaultValue", imsi);
            }
        } else {
            String imsi = Hardwarex.getIMSIOr(context, "defaultValue");
            Assert.assertTrue("imsi: " + imsi,
                    Stringx.isSafe(imsi) && !Comparisonx.areEqual(imsi, "defaultValue")
            );
        }
    }

    @Test
    public final void testMacAddress() {
        Context context = InstrumentationRegistry.getContext();
        if (Androidx.isAtLeast6_0()) {
            if (Permissionx.isGrantPermissions(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                String macAddress = Hardwarex.getMacAddressOr(context, "defaultValue");
                Assert.assertTrue("macAddress: " + macAddress,
                        Stringx.isSafe(macAddress) && !Comparisonx.areEqual(macAddress, "defaultValue")
                );
            } else {
                String macAddress = Hardwarex.getMacAddress(context);
                Assert.assertEquals("macAddress: " + macAddress, macAddress, "defaultValue");
            }
        } else {
            String macAddress = Hardwarex.getMacAddress(context);
            Assert.assertTrue("macAddress: " + macAddress, Netx.isMacAddress(macAddress));
        }
    }
}
