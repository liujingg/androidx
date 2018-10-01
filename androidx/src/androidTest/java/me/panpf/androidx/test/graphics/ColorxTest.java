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

package me.panpf.androidx.test.graphics;

import android.graphics.Color;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.Colorx;
import me.panpf.javax.lang.Numberx;

@RunWith(AndroidJUnit4.class)
public class ColorxTest {

    private static final int COLOR = Color.parseColor("#89BB8713");

    @Test
    public void testAlpha() {
        Assert.assertEquals(Colorx.getAlpha(COLOR), 137);
        Assert.assertEquals(Colorx.getAlpha(Colorx.setAlpha(COLOR, 211)), 211);
        Assert.assertEquals(Colorx.getAlpha(Colorx.addAlpha(COLOR, 0.56f)), 76);
    }

    @Test
    public void testHue() {
        Assert.assertEquals((int) Colorx.getHSVHue(COLOR), 41);
        Assert.assertEquals((int) Colorx.getHSVHue(Colorx.setHSVHue(COLOR, 111f)), 111);
    }

    @Test
    public void testSaturation() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertEquals(Numberx.scale(Colorx.getHSVSaturation(COLOR), 2), 0.9f, 0.1f);
        Assert.assertEquals(Numberx.scale(Colorx.getHSVSaturation(Colorx.setHSVSaturation(COLOR, 0.34f)), 2), 0.34f, 0.1f);
        Assert.assertEquals(Numberx.scale(Colorx.getHSVSaturation(Colorx.addHSVSaturation(COLOR, 0.34f)), 2), 0.31f, 0.1f);
    }

    @Test
    public void testHSVValue() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertEquals(Numberx.scale(Colorx.getHSVValue(COLOR), 2), 0.73f, 0.1f);
        Assert.assertEquals(Numberx.scale(Colorx.getHSVValue(Colorx.setHSVValue(COLOR, 0.21f)), 2), 0.21f, 0.1f);
        Assert.assertEquals(Numberx.scale(Colorx.getHSVValue(Colorx.addHSVValue(COLOR, 0.21f)), 2), 0.15f, 0.1f);
    }

    @Test
    public void testLight() {
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#FFFFFF")));
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#C0C0C0")));
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#808080")));

        Assert.assertFalse(Colorx.isLight(Color.parseColor("#000000")));
    }

    @Test
    public void testArgbEvaluate() {
        // 不同设备存在色差，因此无法准确对比
        Assert.assertNotNull(String.valueOf(Colorx.argbEvaluate(Color.RED, Color.GREEN, 0.6f)));
    }
}
