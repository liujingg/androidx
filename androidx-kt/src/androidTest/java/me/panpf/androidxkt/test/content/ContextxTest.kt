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

package me.panpf.androidxkt.test.content

import android.content.Context
import android.os.Build
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.accessibility.AccessibilityManager
import me.panpf.androidxkt.content.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContextxTest {

    @Test
    fun testSystemService() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertNotNull(context.systemService<AccessibilityManager>(Context.ACCESSIBILITY_SERVICE))
        context.systemServiceOrNull<AccessibilityManager>(Context.ACCESSIBILITY_SERVICE)

        Assert.assertNotNull(context.packageManager())
        Assert.assertNotNull(context.powerManager())
        Assert.assertNotNull(context.windowManager())
        Assert.assertNotNull(context.accountManager())
        Assert.assertNotNull(context.activityManager())
        Assert.assertNotNull(context.layoutInflater())
        Assert.assertNotNull(context.notificationManager())
        Assert.assertNotNull(context.accessibilityManager())
        Assert.assertNotNull(context.sensorManager())
        Assert.assertNotNull(context.storageManager())
        Assert.assertNotNull(context.storageManagerCompat())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Assert.assertNotNull(context.storageStatsManager())
        }
        Assert.assertNotNull(context.vibrator())
        Assert.assertNotNull(context.connectivityManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Assert.assertNotNull(context.networkStatsManager())
        }
        try {
            context.wifiManager()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        context.wifiManagerOrNull()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                context.wifiAwareManager()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            context.wifiAwareManagerOrNull()
        }
        Assert.assertNotNull(context.wifiP2pManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Assert.assertNotNull(context.wifiRttManager())
        }
        Assert.assertNotNull(context.audioManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                context.fingerprintManager()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            context.fingerprintManagerOrNull()
        }
        try {
            context.telephonyManager()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        context.telephonyManagerOrNull()
        Assert.assertNotNull(context.clipboardManager())
        Assert.assertNotNull(context.inputMethodManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.appWidgetManager())
        }
        Assert.assertNotNull(context.dropBoxManager())
        Assert.assertNotNull(context.devicePolicyManager())
        Assert.assertNotNull(context.downloadManager())
        Assert.assertNotNull(context.nfcManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Assert.assertNotNull(context.bluetoothManager())
        }
        Assert.assertNotNull(context.usbManager())
        Assert.assertNotNull(context.inputManager())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertNotNull(context.displayManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Assert.assertNotNull(context.userManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.cameraManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertNotNull(context.printManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Assert.assertNotNull(context.usageStatsManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.batteryManager())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Assert.assertNotNull(context.jobScheduler())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            Assert.assertNotNull(context.shortcutManager())
        }
    }
}
