package me.panpf.androidxkt.app

import android.Manifest
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.os.isAtLeastM
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PermissionTest {

    @Test
    fun testSinglePermission() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertTrue(context.isGrantPermission(Manifest.permission.INTERNET))
    }

    @Test
    fun testSingleNoPermission() {
        val context = InstrumentationRegistry.getContext()
        if (isAtLeastM()) {
            Assert.assertFalse(context.isGrantPermission(Manifest.permission.READ_PHONE_STATE))
        } else {
            Assert.assertTrue(context.isGrantPermission(Manifest.permission.READ_PHONE_STATE))
        }
    }

    @Test
    fun testMultiPermission() {
        val context = InstrumentationRegistry.getContext()
        Assert.assertTrue(context.isGrantPermissions(
                Manifest.permission.INTERNET, Manifest.permission.VIBRATE))
    }

    @Test
    fun testMultiNoPermission() {
        val context = InstrumentationRegistry.getContext()
        if (isAtLeastM()) {
            Assert.assertFalse(context.isGrantPermissions(
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA))
        } else {
            Assert.assertTrue(context.isGrantPermissions(
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA))
        }
    }
}