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

package me.panpf.androidxkt.test.provider

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidx.Androidx
import me.panpf.androidx.widget.Toastx
import me.panpf.androidxkt.provider.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.ref.WeakReference
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class SettingsxTest {

    @get:Rule
    val requestPermissionActivityRule = ActivityTestRule(RequestPermissionTestActivity::class.java)

    @get:Rule
    val requestNotificationPolicyActivityRule = ActivityTestRule(RequestNotificationPolicyTestActivity::class.java)

    @Test
    fun testScreenBrightnessMode() {
        val context = InstrumentationRegistry.getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val isAutomatic = context.isScreenBrightnessModeAutomatic()
        try {
            val newAutomaticValue = !isAutomatic
            Assert.assertTrue(context.setScreenBrightnessModeAutomatic(newAutomaticValue))
            val newAutomaticValueFromSettings = context.isScreenBrightnessModeAutomatic()
            Assert.assertEquals(newAutomaticValue, newAutomaticValueFromSettings)
        } finally {
            context.setScreenBrightnessModeAutomatic(isAutomatic)
        }
    }

    @Test
    fun testScreenBrightness() {
        val context = InstrumentationRegistry.getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val screenBrightness = context.getScreenBrightness()
        try {
            val newScreenBrightnessValue = 255 - screenBrightness
            Assert.assertTrue(context.setScreenBrightness(newScreenBrightnessValue))
            val newScreenBrightnessValueFromSettings = context.getScreenBrightness()
            Assert.assertEquals(newScreenBrightnessValue.toLong(), newScreenBrightnessValueFromSettings.toLong())
        } finally {
            context.setScreenBrightness(screenBrightness)
        }
    }

    @Test
    fun testScreenOffTimeout() {
        val context = InstrumentationRegistry.getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val screenOffTimeout = context.getScreenOffTimeout()
        try {
            val newScreenOffTimeoutValue = screenOffTimeout + 100
            Assert.assertTrue(context.setScreenOffTimeout(newScreenOffTimeoutValue))
            val newScreenOffTimeoutValueFromSettings = context.getScreenOffTimeout()
            Assert.assertEquals(newScreenOffTimeoutValue.toLong(), newScreenOffTimeoutValueFromSettings.toLong())
        } finally {
            context.setScreenOffTimeout(screenOffTimeout)
        }
    }

    @Test
    fun testAirplaneModeOn() {
        if (Androidx.isAtLeast17()) return

        val context = InstrumentationRegistry.getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val isAirplaneModeOn = context.isAirplaneModeOn()
        try {
            val newAirplaneModeOnValue = !isAirplaneModeOn
            Assert.assertTrue(context.setAirplaneModeOn(newAirplaneModeOnValue))
            val newAirplaneModeOnValueFromSettings = context.isAirplaneModeOn()
            Assert.assertEquals(newAirplaneModeOnValue, newAirplaneModeOnValueFromSettings)
        } finally {
            context.setAirplaneModeOn(isAirplaneModeOn)
        }
    }

    @Test
    fun testMediaVolume() {
        val context = InstrumentationRegistry.getContext()
        if (!context.canWrite()) {
            val activity = requestPermissionActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.canWrite()) {
                Assert.fail("No write settings permission")
            }
        }

        val mediaVolume = context.getMediaVolume()
        try {
            val newMediaVolumeValue = 15 - mediaVolume
            Assert.assertTrue(context.setMediaVolume(newMediaVolumeValue))
            val newMediaVolumeValueFromSettings = context.getMediaVolume()
            Assert.assertEquals(newMediaVolumeValue.toLong(), newMediaVolumeValueFromSettings.toLong())
        } finally {
            context.setMediaVolume(mediaVolume)
        }
    }

    @Test
    fun testRingVolume() {
        val context = InstrumentationRegistry.getContext()
        if (!context.isNotificationPolicyAccessGranted()) {
            val activity = requestNotificationPolicyActivityRule.activity
            try {
                activity.countDownLatch.await()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            if (!context.isNotificationPolicyAccessGranted()) {
                Assert.fail("No NotificationPolicy access permission")
            }
        }

        val mediaVolume = context.getRingVolume()
        try {
            val newRingVolumeValue = 7 - mediaVolume
            Assert.assertTrue(context.setRingVolume(newRingVolumeValue))
            val newRingVolumeValueFromSettings = context.getRingVolume()
            Assert.assertEquals(newRingVolumeValue.toLong(), newRingVolumeValueFromSettings.toLong())
        } finally {
            context.setRingVolume(mediaVolume)
        }
    }

    class RequestPermissionTestActivity : Activity() {

        val countDownLatch = CountDownLatch(1)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (!canWrite()) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                startActivityForResult(intent, 101)

                Toastx.showLong(this, "请允许修改系统设置并关闭此页面")
                Androidx.getMainHandler().postDelayed(FinishTask(WeakReference(this)), (10 * 1000).toLong())
            } else {
                finish()
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }

        override fun onDestroy() {
            super.onDestroy()
            countDownLatch.countDown()
        }

        private class FinishTask internal constructor(private val activityWeakReference: WeakReference<Activity>) : Runnable {

            override fun run() {
                val activity = activityWeakReference.get()
                activity?.finish()
            }
        }
    }

    class RequestNotificationPolicyTestActivity : Activity() {

        val countDownLatch = CountDownLatch(1)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            if (!isNotificationPolicyAccessGranted()) {
                val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                startActivityForResult(intent, 1)

                Toastx.showLong(this, "请允许修改请勿打扰状态并关闭此页面")
                Androidx.getMainHandler().postDelayed(FinishTask(WeakReference(this)), (10 * 1000).toLong())
            } else {
                finish()
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            super.onActivityResult(requestCode, resultCode, data)
            finish()
        }

        override fun onDestroy() {
            super.onDestroy()
            countDownLatch.countDown()
        }

        private class FinishTask internal constructor(private val activityWeakReference: WeakReference<Activity>) : Runnable {

            override fun run() {
                val activity = activityWeakReference.get()
                activity?.finish()
            }
        }
    }
}
