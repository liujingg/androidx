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
        Assert.assertTrue(InstrumentationRegistry.getContext().isGrantPermission(Manifest.permission.INTERNET))
    }

    @Test
    fun testSingleNoPermission() {
        if (isAtLeastM()) {
            Assert.assertFalse(InstrumentationRegistry.getContext().isGrantPermission(Manifest.permission.READ_PHONE_STATE))
        } else {
            Assert.assertTrue(InstrumentationRegistry.getContext().isGrantPermission(Manifest.permission.READ_PHONE_STATE))
        }
    }

    @Test
    fun testMultiPermission() {
        Assert.assertTrue(InstrumentationRegistry.getContext().isGrantPermissions(Manifest.permission.INTERNET, Manifest.permission.VIBRATE))
    }

    @Test
    fun testMultiNoPermission() {
        if (isAtLeastM()) {
            Assert.assertFalse(InstrumentationRegistry.getContext().isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA))
        } else {
            Assert.assertTrue(InstrumentationRegistry.getContext().isGrantPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA))
        }
    }
}