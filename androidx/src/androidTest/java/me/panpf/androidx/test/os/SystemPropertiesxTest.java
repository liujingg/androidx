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

package me.panpf.androidx.test.os;

import android.os.Build;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.runner.AndroidJUnit4;
import me.panpf.androidx.hardware.Hardwarex;
import me.panpf.androidx.os.SystemPropertiesx;

@RunWith(AndroidJUnit4.class)
public class SystemPropertiesxTest {

    @Test
    public void testGet() {
        Assert.assertEquals(SystemPropertiesx.get("ro.product.brand"), Hardwarex.getBrand());
        Assert.assertEquals(SystemPropertiesx.get("ro.wifi.channels"), "");
        Assert.assertEquals(SystemPropertiesx.get("custom"), "");
    }

    @Test
    public void testGetDef() {
        Assert.assertEquals(SystemPropertiesx.get("ro.wifi.channels", "unknown"), "unknown");
        Assert.assertEquals(SystemPropertiesx.get("custom", "customValue"), "customValue");
    }

    @Test
    public void testGetInt() {
        Assert.assertEquals(SystemPropertiesx.getInt("ro.build.version.sdk", -1), Build.VERSION.SDK_INT);
        Assert.assertEquals(SystemPropertiesx.getInt("custom", -1), -1);
    }

    @Test
    public void testGetLong() {
        Assert.assertEquals(SystemPropertiesx.getLong("ro.build.version.sdk", (long) -1), (long) Build.VERSION.SDK_INT);
        Assert.assertEquals(SystemPropertiesx.getLong("custom", (long) -1), (long) -1);
    }

    @Test
    public void testGetBoolean() {
        Assert.assertNotNull(String.valueOf(SystemPropertiesx.getBoolean("gsm.operator.isroaming", true)));
        Assert.assertTrue(SystemPropertiesx.getBoolean("custom", true));
    }
}
