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

package me.panpf.androidx.content;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.rtt.WifiRttManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.os.storage.StorageManagerCompat;
import me.panpf.androidx.util.NullableResultRunnable;
import me.panpf.javax.util.Premisex;

@SuppressWarnings({"UnusedReturnValue", "WeakerAccess"})
public class Contextx {


    /* ******************************************* SystemService *******************************************/


    @NonNull
    public static <T> T systemService(@NonNull Context context, @NonNull String serviceName) {
        //noinspection unchecked
        return Premisex.requireNotNull((T) context.getApplicationContext().getSystemService(serviceName), serviceName);
    }

    @Nullable
    public static <T> T systemServiceOrNull(@NonNull Context context, @NonNull String serviceName) {
        //noinspection unchecked
        return (T) context.getApplicationContext().getSystemService(serviceName);
    }

    @NonNull
    public static PackageManager packageManager(@NonNull Context context) {
        return Premisex.requireNotNull(context.getApplicationContext().getPackageManager(), "PackageManager");
    }

    @NonNull
    public static PowerManager powerManager(@NonNull Context context) {
        return systemService(context, Context.POWER_SERVICE);
    }

    @NonNull
    public static WindowManager windowManager(@NonNull Context context) {
        return systemService(context, Context.WINDOW_SERVICE);
    }

    @NonNull
    public static AccountManager accountManager(@NonNull Context context) {
        return systemService(context, Context.ACCOUNT_SERVICE);
    }

    @NonNull
    public static ActivityManager activityManager(@NonNull Context context) {
        return systemService(context, Context.ACTIVITY_SERVICE);
    }

    @NonNull
    public static LayoutInflater layoutInflater(@NonNull Context context) {
        return systemService(context, Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    public static NotificationManager notificationManager(@NonNull Context context) {
        return systemService(context, Context.NOTIFICATION_SERVICE);
    }

    @NonNull
    public static AccessibilityManager accessibilityManager(@NonNull Context context) {
        return systemService(context, Context.ACCESSIBILITY_SERVICE);
    }

    @NonNull
    public static SensorManager sensorManager(@NonNull Context context) {
        return systemService(context, Context.SENSOR_SERVICE);
    }

    @NonNull
    public static StorageManager storageManager(@NonNull Context context) {
        return systemService(context, Context.STORAGE_SERVICE);
    }

    @NonNull
    public static StorageManagerCompat storageManagerCompat(@NonNull Context context) {
        return new StorageManagerCompat(storageManager(context));
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static StorageStatsManager storageStatsManager(@NonNull Context context) {
        return systemService(context, Context.STORAGE_STATS_SERVICE);
    }

    @NonNull
    public static Vibrator vibrator(@NonNull Context context) {
        return systemService(context, Context.VIBRATOR_SERVICE);
    }

    @NonNull
    public static ConnectivityManager connectivityManager(@NonNull Context context) {
        return systemService(context, Context.CONNECTIVITY_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static NetworkStatsManager networkStatsManager(@NonNull Context context) {
        return systemService(context, Context.NETWORK_STATS_SERVICE);
    }

    @NonNull
    public static WifiManager wifiManager(@NonNull Context context) {
        return systemService(context, Context.WIFI_SERVICE);
    }

    @Nullable
    public static WifiManager wifiManagerOrNull(@NonNull Context context) {
        return systemServiceOrNull(context, Context.WIFI_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static WifiAwareManager wifiAwareManager(@NonNull Context context) {
        return systemService(context, Context.WIFI_AWARE_SERVICE);
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static WifiAwareManager wifiAwareManagerOrNull(@NonNull Context context) {
        return systemServiceOrNull(context, Context.WIFI_AWARE_SERVICE);
    }

    @NonNull
    public static WifiP2pManager wifiP2pManager(@NonNull Context context) {
        return systemService(context, Context.WIFI_P2P_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static WifiRttManager wifiRttManager(@NonNull Context context) {
        return systemService(context, Context.WIFI_RTT_RANGING_SERVICE);
    }

    @NonNull
    public static AudioManager audioManager(@NonNull Context context) {
        return systemService(context, Context.AUDIO_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static FingerprintManager fingerprintManager(@NonNull Context context) {
        return systemService(context, Context.FINGERPRINT_SERVICE);
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static FingerprintManager fingerprintManagerOrNull(@NonNull Context context) {
        return systemServiceOrNull(context, Context.FINGERPRINT_SERVICE);
    }

    @NonNull
    public static TelephonyManager telephonyManager(@NonNull Context context) {
        return systemService(context, Context.TELEPHONY_SERVICE);
    }

    @Nullable
    public static TelephonyManager telephonyManagerOrNull(@NonNull Context context) {
        return systemServiceOrNull(context, Context.TELEPHONY_SERVICE);
    }

    @NonNull
    public static ClipboardManager clipboardManager(@NonNull Context context) {
        if (Androidx.isMainThread()) {
            return systemService(context, Context.CLIPBOARD_SERVICE);
        } else {
            /*
             * 首次获取 ClipboardManager 时会创建 ClipboardManager，并创建 Handler，
             * 如果当前环境是在非主线程执行的，这里就涉及到了在非主线程创建 Handler 的问题
             */
            final Context appContext = context.getApplicationContext();
            return Premisex.requireNotNull(Androidx.waitRunInUI(new NullableResultRunnable<ClipboardManager>() {
                @Nullable
                @Override
                public ClipboardManager run() {
                    return systemServiceOrNull(appContext, Context.CLIPBOARD_SERVICE);
                }
            }), Context.CLIPBOARD_SERVICE);
        }
    }

    @NonNull
    public static InputMethodManager inputMethodManager(@NonNull Context context) {
        return systemService(context, Context.INPUT_METHOD_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static AppWidgetManager appWidgetManager(@NonNull Context context) {
        return systemService(context, Context.APPWIDGET_SERVICE);
    }

    @NonNull
    public static DropBoxManager dropBoxManager(@NonNull Context context) {
        return systemService(context, Context.DROPBOX_SERVICE);
    }

    @NonNull
    public static DevicePolicyManager devicePolicyManager(@NonNull Context context) {
        return systemService(context, Context.DEVICE_POLICY_SERVICE);
    }

    @NonNull
    public static DownloadManager downloadManager(@NonNull Context context) {
        return systemService(context, Context.DOWNLOAD_SERVICE);
    }

    @NonNull
    public static NfcManager nfcManager(@NonNull Context context) {
        return systemService(context, Context.NFC_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static BluetoothManager bluetoothManager(@NonNull Context context) {
        return systemService(context, Context.BLUETOOTH_SERVICE);
    }

    @NonNull
    public static UsbManager usbManager(@NonNull Context context) {
        return systemService(context, Context.USB_SERVICE);
    }

    @NonNull
    public static InputManager inputManager(@NonNull Context context) {
        return systemService(context, Context.INPUT_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static DisplayManager displayManager(@NonNull Context context) {
        return systemService(context, Context.DISPLAY_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static UserManager userManager(@NonNull Context context) {
        return systemService(context, Context.USER_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static CameraManager cameraManager(@NonNull Context context) {
        return systemService(context, Context.CAMERA_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static PrintManager printManager(@NonNull Context context) {
        return systemService(context, Context.PRINT_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static UsageStatsManager usageStatsManager(@NonNull Context context) {
        return systemService(context, Context.USAGE_STATS_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static BatteryManager batteryManager(@NonNull Context context) {
        return systemService(context, Context.BATTERY_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static JobScheduler jobScheduler(@NonNull Context context) {
        return systemService(context, Context.JOB_SCHEDULER_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static ShortcutManager shortcutManager(@NonNull Context context) {
        return systemService(context, Context.SHORTCUT_SERVICE);
    }

    // TODO: 2018/10/1 补充剩余的 manager
}
