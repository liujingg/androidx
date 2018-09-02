package me.panpf.androidxkt.test.hardware

import android.Manifest
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.isGrantPermission
import me.panpf.androidxkt.hardware.*
import me.panpf.androidxkt.os.isAtLeastP
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HardwareTest {

    @Test
    fun testDeviceModel() {
        Assert.assertTrue(getDeviceModel().isNotEmpty())
    }

    @Test
    fun testDeviceName() {
        Assert.assertTrue(getDeviceName().isNotEmpty())
    }

    @Test
    fun testHardware() {
        Assert.assertTrue(getHardware().isNotEmpty())
    }

    @Test
    fun testSupportedAbis() {
        Assert.assertTrue(getSupportedAbis().isNotEmpty())
    }

    @Test
    fun testDeviceId() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermission(Manifest.permission.READ_PHONE_STATE)) {
            Assert.assertTrue(context.getDeviceId().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertTrue(context.getDeviceId() == "PermissionDenied")
        }
    }

    @Test
    fun testAndroidId() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(context.getAndroidId().isNotEmpty())
    }

    @Test
    fun testSubscriberId() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermission(Manifest.permission.READ_PHONE_STATE)) {
            Assert.assertTrue(context.getSubscriberId().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertTrue(context.getSubscriberId() == "PermissionDenied")
        }
    }

    @Test
    fun testSimSerialNumber() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermission(Manifest.permission.READ_PHONE_STATE)) {
            Assert.assertTrue(context.getSimSerialNumber().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertTrue(context.getSimSerialNumber() == "PermissionDenied")
        }
    }

    @Test
    fun testSerial() {
        if (isAtLeastP()) {
            val context = InstrumentationRegistry.getContext()

            if (context.isGrantPermission(Manifest.permission.READ_PHONE_STATE)) {
                Assert.assertTrue(getSerial().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
            } else {
                Assert.assertTrue(getSerial() == "PermissionDenied")
            }
        } else {
            Assert.assertTrue(getSerial().isNotEmpty())
        }
    }

    @Test
    fun testMacAddress() {
        val context = InstrumentationRegistry.getContext()

        if (context.isGrantPermission(Manifest.permission.ACCESS_WIFI_STATE)) {
            Assert.assertTrue(context.getMacAddress().let { it.isNotEmpty() && it != "unknown" && it != "PermissionDenied" })
        } else {
            Assert.assertTrue(context.getMacAddress() == "PermissionDenied")
        }
    }
}