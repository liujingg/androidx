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

package me.panpf.androidxkt.content

import android.accounts.AccountManager
import android.app.ActivityManager
import android.app.DownloadManager
import android.app.NotificationManager
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ShortcutManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.fingerprint.FingerprintManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.print.PrintManager
import android.support.annotation.RequiresApi
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.inputmethod.InputMethodManager
import me.panpf.androidx.content.Contextx
import me.panpf.androidx.os.storage.StorageManagerCompat


/* ******************************************* SystemService *******************************************/


fun <T> Context.systemService(serviceName: String): T = Contextx.systemService(this, serviceName)

fun <T> Context.systemServiceOrNull(serviceName: String): T? = Contextx.systemServiceOrNull(this, serviceName)

fun Context.packageManager(): PackageManager = Contextx.packageManager(this)

fun Context.powerManager(): PowerManager = Contextx.powerManager(this)

fun Context.windowManager(): WindowManager = Contextx.windowManager(this)

fun Context.accountManager(): AccountManager = Contextx.accountManager(this)

fun Context.activityManager(): ActivityManager = Contextx.activityManager(this)

fun Context.layoutInflater(): LayoutInflater = Contextx.layoutInflater(this)

fun Context.notificationManager(): NotificationManager = Contextx.notificationManager(this)

fun Context.accessibilityManager(): AccessibilityManager = Contextx.accessibilityManager(this)

fun Context.sensorManager(): SensorManager = Contextx.sensorManager(this)

fun Context.storageManager(): StorageManager = Contextx.storageManager(this)

fun Context.storageManagerCompat(): StorageManagerCompat = Contextx.storageManagerCompat(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.storageStatsManager(): StorageStatsManager = Contextx.storageStatsManager(this)

fun Context.vibrator(): Vibrator = Contextx.vibrator(this)

fun Context.connectivityManager(): ConnectivityManager = Contextx.connectivityManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.networkStatsManager(): NetworkStatsManager = Contextx.networkStatsManager(this)

fun Context.wifiManager(): WifiManager = Contextx.wifiManager(this)

fun Context.wifiManagerOrNull(): WifiManager? = Contextx.wifiManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.wifiAwareManager(): WifiAwareManager = Contextx.wifiAwareManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.wifiAwareManagerOrNull(): WifiAwareManager? = Contextx.wifiAwareManagerOrNull(this)

fun Context.wifiP2pManager(): WifiP2pManager = Contextx.wifiP2pManager(this)

@RequiresApi(api = Build.VERSION_CODES.P)
fun Context.wifiRttManager(): WifiRttManager = Contextx.wifiRttManager(this)

fun Context.audioManager(): AudioManager = Contextx.audioManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.fingerprintManager(): FingerprintManager = Contextx.fingerprintManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.fingerprintManagerOrNull(): FingerprintManager? = Contextx.fingerprintManagerOrNull(this)

fun Context.telephonyManager(): TelephonyManager = Contextx.telephonyManager(this)

fun Context.telephonyManagerOrNull(): TelephonyManager? = Contextx.telephonyManagerOrNull(this)

fun Context.clipboardManager(): ClipboardManager = Contextx.clipboardManager(this)

fun Context.inputMethodManager(): InputMethodManager = Contextx.inputMethodManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.appWidgetManager(): AppWidgetManager = Contextx.appWidgetManager(this)

fun Context.dropBoxManager(): DropBoxManager = Contextx.dropBoxManager(this)

fun Context.devicePolicyManager(): DevicePolicyManager = Contextx.devicePolicyManager(this)

fun Context.downloadManager(): DownloadManager = Contextx.downloadManager(this)

fun Context.nfcManager(): NfcManager = Contextx.nfcManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Context.bluetoothManager(): BluetoothManager = Contextx.bluetoothManager(this)

fun Context.usbManager(): UsbManager = Contextx.usbManager(this)

fun Context.inputManager(): InputManager = Contextx.inputManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun Context.displayManager(): DisplayManager = Contextx.displayManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun Context.userManager(): UserManager = Contextx.userManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.cameraManager(): CameraManager = Contextx.cameraManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
fun Context.printManager(): PrintManager = Contextx.printManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
fun Context.usageStatsManager(): UsageStatsManager = Contextx.usageStatsManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.batteryManager(): BatteryManager = Contextx.batteryManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.jobScheduler(): JobScheduler = Contextx.jobScheduler(this)

@RequiresApi(api = Build.VERSION_CODES.N_MR1)
fun Context.shortcutManager(): ShortcutManager = Contextx.shortcutManager(this)