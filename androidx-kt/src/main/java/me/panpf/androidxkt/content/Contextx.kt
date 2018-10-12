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
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.CrossProfileApps
import android.content.pm.LauncherApps
import android.content.pm.PackageManager
import android.content.pm.ShortcutManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.fingerprint.FingerprintManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.IpSecManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.*
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.support.annotation.RequiresApi
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import me.panpf.androidx.content.Contextx
import me.panpf.androidx.os.storage.StorageManagerCompat


/* ******************************************* SystemService *******************************************/


fun <T> Context.systemService(serviceName: String): T = Contextx.systemService(this, serviceName)

fun <T> Context.systemServiceOrNull(serviceName: String): T? = Contextx.systemServiceOrNull(this, serviceName)

fun Context.packageManager(): PackageManager = Contextx.packageManager(this)

fun Context.powerManager(): PowerManager = Contextx.powerManager(this)

fun Context.windowManager(): WindowManager = Contextx.windowManager(this)

fun Context.layoutInflater(): LayoutInflater = Contextx.layoutInflater(this)

fun Context.accountManager(): AccountManager = Contextx.accountManager(this)

fun Context.activityManager(): ActivityManager = Contextx.activityManager(this)

fun Context.alarmManager(): AlarmManager = Contextx.alarmManager(this)

fun Context.notificationManager(): NotificationManager = Contextx.notificationManager(this)

fun Context.accessibilityManager(): AccessibilityManager = Contextx.accessibilityManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
fun Context.captioningManager(): CaptioningManager = Contextx.captioningManager(this)

fun Context.keyguardManager(): KeyguardManager = Contextx.keyguardManager(this)

fun Context.locationManager(): LocationManager = Contextx.locationManager(this)

fun Context.searchManager(): SearchManager = Contextx.searchManager(this)

fun Context.sensorManager(): SensorManager = Contextx.sensorManager(this)

fun Context.storageManager(): StorageManager = Contextx.storageManager(this)

fun Context.storageManagerCompat(): StorageManagerCompat = Contextx.storageManagerCompat(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.storageStatsManager(): StorageStatsManager = Contextx.storageStatsManager(this)

fun Context.wallpaperManager(): WallpaperManager = Contextx.wallpaperManager(this)

// TIME_ZONE_RULES_MANAGER_SERVICE

fun Context.vibrator(): Vibrator = Contextx.vibrator(this)

fun Context.connectivityManager(): ConnectivityManager = Contextx.connectivityManager(this)

@RequiresApi(api = Build.VERSION_CODES.P)
fun Context.ipSecManager(): IpSecManager = Contextx.ipSecManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.networkStatsManager(): NetworkStatsManager = Contextx.networkStatsManager(this)

fun Context.wifiManager(): WifiManager = Contextx.wifiManager(this)

fun Context.wifiManagerOrNull(): WifiManager? = Contextx.wifiManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.wifiAwareManager(): WifiAwareManager = Contextx.wifiAwareManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.wifiAwareManagerOrNull(): WifiAwareManager? = Contextx.wifiAwareManagerOrNull(this)

fun Context.wifiP2pManager(): WifiP2pManager = Contextx.wifiP2pManager(this)

// WIFI_SCANNING_SERVICE

@RequiresApi(api = Build.VERSION_CODES.P)
fun Context.wifiRttManager(): WifiRttManager = Contextx.wifiRttManager(this)

fun Context.nsdManager(): NsdManager = Contextx.nsdManager(this)

fun Context.audioManager(): AudioManager = Contextx.audioManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.fingerprintManager(): FingerprintManager = Contextx.fingerprintManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.fingerprintManagerOrNull(): FingerprintManager? = Contextx.fingerprintManagerOrNull(this)

fun Context.mediaRouter(): MediaRouter = Contextx.mediaRouter(this)

fun Context.telephonyManager(): TelephonyManager = Contextx.telephonyManager(this)

fun Context.telephonyManagerOrNull(): TelephonyManager? = Contextx.telephonyManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
fun Context.telephonySubscriptionManager(): SubscriptionManager = Contextx.telephonySubscriptionManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.carrierConfigManager(): CarrierConfigManager = Contextx.carrierConfigManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.telecomManager(): TelecomManager = Contextx.telecomManager(this)

fun Context.clipboardManager(): ClipboardManager = Contextx.clipboardManager(this)

fun Context.inputMethodManager(): InputMethodManager = Contextx.inputMethodManager(this)

fun Context.textServicesManager(): TextServicesManager = Contextx.textServicesManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.textClassificationManager(): TextClassificationManager = Contextx.textClassificationManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.appWidgetManager(): AppWidgetManager = Contextx.appWidgetManager(this)

fun Context.dropBoxManager(): DropBoxManager = Contextx.dropBoxManager(this)

fun Context.devicePolicyManager(): DevicePolicyManager = Contextx.devicePolicyManager(this)

fun Context.uiModeManager(): UiModeManager = Contextx.uiModeManager(this)

fun Context.downloadManager(): DownloadManager = Contextx.downloadManager(this)

fun Context.nfcManager(): NfcManager = Contextx.nfcManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
fun Context.bluetoothManager(): BluetoothManager = Contextx.bluetoothManager(this)

fun Context.usbManager(): UsbManager = Contextx.usbManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.launcherApps(): LauncherApps = Contextx.launcherApps(this)

fun Context.inputManager(): InputManager = Contextx.inputManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun Context.displayManager(): DisplayManager = Contextx.displayManager(this)

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun Context.userManager(): UserManager = Contextx.userManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.restrictionsManager(): RestrictionsManager = Contextx.restrictionsManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
fun Context.appOpsManager(): AppOpsManager = Contextx.appOpsManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.cameraManager(): CameraManager = Contextx.cameraManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
fun Context.printManager(): PrintManager = Contextx.printManager(this)

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
fun Context.consumerIrManager(): ConsumerIrManager = Contextx.consumerIrManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.tvInputManager(): TvInputManager = Contextx.tvInputManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.tvInputManagerOrNull(): TvInputManager? = Contextx.tvInputManagerOrNull(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
fun Context.usageStatsManager(): UsageStatsManager = Contextx.usageStatsManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.mediaSessionManager(): MediaSessionManager = Contextx.mediaSessionManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.batteryManager(): BatteryManager = Contextx.batteryManager(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.jobScheduler(): JobScheduler = Contextx.jobScheduler(this)

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
fun Context.mediaProjectionManager(): MediaProjectionManager = Contextx.mediaProjectionManager(this)

@RequiresApi(api = Build.VERSION_CODES.M)
fun Context.midiManager(): MidiManager = Contextx.midiManager(this)

// RADIO_SERVICE

@RequiresApi(api = Build.VERSION_CODES.N)
fun Context.hardwarePropertiesManager(): HardwarePropertiesManager = Contextx.hardwarePropertiesManager(this)

@RequiresApi(api = Build.VERSION_CODES.N_MR1)
fun Context.shortcutManager(): ShortcutManager = Contextx.shortcutManager(this)

@RequiresApi(api = Build.VERSION_CODES.N)
fun Context.systemHealthManager(): SystemHealthManager = Contextx.systemHealthManager(this)

@RequiresApi(api = Build.VERSION_CODES.O)
fun Context.companionDeviceManager(): CompanionDeviceManager = Contextx.companionDeviceManager(this)

@RequiresApi(api = Build.VERSION_CODES.P)
fun Context.crossProfileApps(): CrossProfileApps = Contextx.crossProfileApps(this)