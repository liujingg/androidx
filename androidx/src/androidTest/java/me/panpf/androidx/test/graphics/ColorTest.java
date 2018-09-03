package me.panpf.androidx.test.graphics;

import android.graphics.Color;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.graphics.Colorx;

@RunWith(AndroidJUnit4.class)
public class ColorTest {

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
        Assert.assertEquals((int) (Colorx.getHSVSaturation(COLOR) * 100), 89);
        Assert.assertEquals((int) (Colorx.getHSVSaturation(Colorx.setHSVSaturation(COLOR, 0.34f)) * 100), 34);
        Assert.assertEquals((int) (Colorx.getHSVSaturation(Colorx.addHSVSaturation(COLOR, 0.34f)) * 100), 31);
    }

    @Test
    public void testHSVValue() {
        Assert.assertEquals((int) (Colorx.getHSVValue(COLOR) * 100), 73);
        Assert.assertEquals((int) (Colorx.getHSVValue(Colorx.setHSVValue(COLOR, 0.21f)) * 100), 20);
        Assert.assertEquals((int) (Colorx.getHSVValue(Colorx.addHSVValue(COLOR, 0.21f)) * 100), 15);
    }

    @Test
    public void testLight() {
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#FFFFFF")));
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#C0C0C0")));
        Assert.assertTrue(Colorx.isLight(Color.parseColor("#808080")));

        Assert.assertFalse(Colorx.isLight(Color.parseColor("#000000")));
    }
}
