package me.panpf.androidxkt.hardware

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HardwareTest {

    @Test
    fun test() {
        println("HardwareTest -> deviceModel: $deviceModel")
        println("HardwareTest -> deviceName: $deviceName")
        println("HardwareTest -> hardware: $hardware")
        println("HardwareTest -> supportedAbis: ${supportedAbis.joinToString()}")
        println("HardwareTest -> deviceId: ${InstrumentationRegistry.getContext().getDeviceId()}")
        println("HardwareTest -> androidId: ${InstrumentationRegistry.getContext().getAndroidId()}")
        println("HardwareTest -> subscriberId: ${InstrumentationRegistry.getContext().getSubscriberId()}")
        println("HardwareTest -> simSerialNumber: ${InstrumentationRegistry.getContext().getSimSerialNumber()}")
        println("HardwareTest -> serialNumber: ${getSerial()}")
        println("HardwareTest -> macAddress: ${InstrumentationRegistry.getContext().getMacAddress()}")
    }
}