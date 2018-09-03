package me.panpf.androidxkt.test.graphics

import android.graphics.Color
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.graphics.*

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ColorTest {

    companion object {
        private val COLOR = Color.parseColor("#89BB8713")
    }

    @Test
    fun testAlpha() {
        Assert.assertEquals(COLOR.getColorAlpha(), 137)
        Assert.assertEquals(COLOR.setColorAlpha( 211).getColorAlpha(), 211)
        Assert.assertEquals(COLOR.addColorAlpha(0.56f).getColorAlpha(), 76)
    }

    @Test
    fun testHue() {
        Assert.assertEquals((COLOR.getColorHSVHue().toInt()).toLong(), 41)
        Assert.assertEquals((COLOR.setColorHSVHue(111f).getColorHSVHue().toInt()).toLong(), 111)
    }

    @Test
    fun testSaturation() {
        Assert.assertEquals(((COLOR.getColorHSVSaturation() * 100).toInt()).toLong(), 89)
        Assert.assertEquals(((COLOR.setColorHSVSaturation(0.34f).getColorHSVSaturation() * 100).toInt()).toLong(), 34)
        Assert.assertEquals(((COLOR.addColorHSVSaturation(0.34f).getColorHSVSaturation() * 100).toInt()).toLong(), 31)
    }

    @Test
    fun testHSVValue() {
        Assert.assertEquals(((COLOR.getColorHSVValue() * 100).toInt()).toLong(), 73)
        Assert.assertEquals(((COLOR.setColorHSVValue(0.21f).getColorHSVValue() * 100).toInt()).toLong(), 20)
        Assert.assertEquals(((COLOR.addColorHSVValue(0.21f).getColorHSVValue() * 100).toInt()).toLong(), 15)
    }

    @Test
    fun testLight() {
        Assert.assertTrue(Color.parseColor("#FFFFFF").isLightColor())
        Assert.assertTrue(Color.parseColor("#C0C0C0").isLightColor())
        Assert.assertTrue(Color.parseColor("#808080").isLightColor())

        Assert.assertFalse(Color.parseColor("#000000").isLightColor())
    }
}
