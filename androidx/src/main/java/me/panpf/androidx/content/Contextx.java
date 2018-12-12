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
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.companion.CompanionDeviceManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.pm.CrossProfileApps;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutManager;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.IpSecManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.rtt.WifiRttManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.DropBoxManager;
import android.os.HardwarePropertiesManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.health.SystemHealthManager;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import android.view.textservice.TextServicesManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import me.panpf.androidx.Androidx;
import me.panpf.androidx.os.storage.StorageManagerCompat;
import me.panpf.androidx.util.NullableResultRunnable;
import me.panpf.androidx.util.ResultRunnable;
import me.panpf.javax.util.Premisex;

@SuppressWarnings({"UnusedReturnValue"})
public class Contextx {

    private Contextx() {
    }

    /* ******************************************* Context *******************************************/


    /**
     * Get Application Context
     */
    @NonNull
    public static Context appContext(@NonNull Activity activity) {
        return activity.getApplicationContext();
    }


    /**
     * Get Context, Throw an exception if Fragment is dead
     */
    @NonNull
    public static Context requireContext(@NonNull Fragment fragment) {
        return fragment.requireContext();
    }

    /**
     * Get Context, Throw an exception if Fragment is dead
     */
    @NonNull
    public static Context requireContext(@NonNull android.app.Fragment fragment) {
        Context context = fragment.getActivity();
        if (context == null) {
            throw new IllegalStateException("Fragment " + fragment + " not attached to a context.");
        } else {
            return context;
        }
    }


    /**
     * Get Application Context, Throw an exception if Fragment is dead
     */
    @NonNull
    public static Context requireAppContext(@NonNull Fragment fragment) {
        return requireContext(fragment).getApplicationContext();
    }

    /**
     * Get Application Context, Throw an exception if Fragment is dead
     */
    @NonNull
    public static Context requireAppContext(@NonNull android.app.Fragment fragment) {
        return requireContext(fragment).getApplicationContext();
    }

    /**
     * Get Application Context
     */
    @NonNull
    public static Context appContext(@NonNull View view) {
        return view.getContext().getApplicationContext();
    }

    /* ******************************************* SystemService *******************************************/


    @NonNull
    public static <T> T systemService(@NonNull Context context, @NonNull String serviceName) {
        //noinspection unchecked
        return Premisex.requireNotNull((T) context.getApplicationContext().getSystemService(serviceName), serviceName);
    }

    /**
     * All Managers need to be created when they are first acquired, and some Managers create Handlers when they are created, so if the current environment is executed on a non-main thread, it involves the problem of creating a Handler on a non-main thread.
     */
    @NonNull
    public static <T> T systemServiceInUI(@NonNull final Context context, @NonNull final String serviceName) {
        if (Androidx.isMainThread()) {
            //noinspection unchecked
            return Premisex.requireNotNull((T) context.getApplicationContext().getSystemService(serviceName), serviceName);
        } else {
            final Context appContext = context.getApplicationContext();
            return Androidx.waitRunInUIResult(new ResultRunnable<T>() {
                @NonNull
                @Override
                public T run() {
                    //noinspection unchecked
                    return Premisex.requireNotNull((T) appContext.getApplicationContext().getSystemService(serviceName), serviceName);
                }
            });
        }
    }

    @Nullable
    public static <T> T systemServiceOrNull(@NonNull Context context, @NonNull String serviceName) {
        //noinspection unchecked
        return (T) context.getApplicationContext().getSystemService(serviceName);
    }

    /**
     * All Managers need to be created when they are first acquired, and some Managers create Handlers when they are created, so if the current environment is executed on a non-main thread, it involves the problem of creating a Handler on a non-main thread.
     */
    @Nullable
    public static <T> T systemServiceOrNullInUI(@NonNull final Context context, @NonNull final String serviceName) {
        if (Androidx.isMainThread()) {
            //noinspection unchecked
            return (T) context.getApplicationContext().getSystemService(serviceName);
        } else {
            final Context appContext = context.getApplicationContext();
            return Androidx.waitRunInUINullableResult(new NullableResultRunnable<T>() {
                @Nullable
                @Override
                public T run() {
                    //noinspection unchecked
                    return (T) appContext.getApplicationContext().getSystemService(serviceName);
                }
            });
        }
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
    public static LayoutInflater layoutInflater(@NonNull Context context) {
        return systemService(context, Context.LAYOUT_INFLATER_SERVICE);
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
    public static AlarmManager alarmManager(@NonNull Context context) {
        return systemService(context, Context.ALARM_SERVICE);
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static CaptioningManager captioningManager(@NonNull Context context) {
        return systemServiceInUI(context, Context.CAPTIONING_SERVICE);
    }

    @NonNull
    public static KeyguardManager keyguardManager(@NonNull Context context) {
        return systemService(context, Context.KEYGUARD_SERVICE);
    }

    @NonNull
    public static LocationManager locationManager(@NonNull Context context) {
        return systemService(context, Context.LOCATION_SERVICE);
    }

    @NonNull
    public static SearchManager searchManager(@NonNull Context context) {
        return systemService(context, Context.SEARCH_SERVICE);
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
    public static WallpaperManager wallpaperManager(@NonNull Context context) {
        return systemService(context, Context.WALLPAPER_SERVICE);
    }

    // TIME_ZONE_RULES_MANAGER_SERVICE

    @NonNull
    public static Vibrator vibrator(@NonNull Context context) {
        return systemService(context, Context.VIBRATOR_SERVICE);
    }

    @NonNull
    public static ConnectivityManager connectivityManager(@NonNull Context context) {
        return systemService(context, Context.CONNECTIVITY_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static IpSecManager ipSecManager(@NonNull Context context) {
        return systemService(context, Context.IPSEC_SERVICE);
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

    // WIFI_SCANNING_SERVICE

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static WifiRttManager wifiRttManager(@NonNull Context context) {
        return systemService(context, Context.WIFI_RTT_RANGING_SERVICE);
    }

    @NonNull
    public static NsdManager nsdManager(@NonNull Context context) {
        return systemService(context, Context.NSD_SERVICE);
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
    public static MediaRouter mediaRouter(@NonNull Context context) {
        return systemService(context, Context.MEDIA_ROUTER_SERVICE);
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static SubscriptionManager telephonySubscriptionManager(@NonNull Context context) {
        return systemService(context, Context.TELEPHONY_SUBSCRIPTION_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static CarrierConfigManager carrierConfigManager(@NonNull Context context) {
        return systemService(context, Context.CARRIER_CONFIG_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static TelecomManager telecomManager(@NonNull Context context) {
        return systemService(context, Context.TELECOM_SERVICE);
    }

    @NonNull
    public static ClipboardManager clipboardManager(@NonNull Context context) {
        return systemServiceInUI(context, Context.CLIPBOARD_SERVICE);
    }

    @NonNull
    public static InputMethodManager inputMethodManager(@NonNull Context context) {
        return systemService(context, Context.INPUT_METHOD_SERVICE);
    }

    @NonNull
    public static TextServicesManager textServicesManager(@NonNull Context context) {
        return systemService(context, Context.TEXT_SERVICES_MANAGER_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static TextClassificationManager textClassificationManager(@NonNull Context context) {
        return systemService(context, Context.TEXT_CLASSIFICATION_SERVICE);
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
    public static UiModeManager uiModeManager(@NonNull Context context) {
        return systemService(context, Context.UI_MODE_SERVICE);
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static LauncherApps launcherApps(@NonNull Context context) {
        return systemService(context, Context.LAUNCHER_APPS_SERVICE);
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
    public static RestrictionsManager restrictionsManager(@NonNull Context context) {
        return systemService(context, Context.RESTRICTIONS_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static AppOpsManager appOpsManager(@NonNull Context context) {
        return systemService(context, Context.APP_OPS_SERVICE);
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ConsumerIrManager consumerIrManager(@NonNull Context context) {
        return systemService(context, Context.CONSUMER_IR_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static TvInputManager tvInputManager(@NonNull Context context) {
        return systemService(context, Context.TV_INPUT_SERVICE);
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static TvInputManager tvInputManagerOrNull(@NonNull Context context) {
        return systemServiceOrNull(context, Context.TV_INPUT_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static UsageStatsManager usageStatsManager(@NonNull Context context) {
        return systemService(context, Context.USAGE_STATS_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static MediaSessionManager mediaSessionManager(@NonNull Context context) {
        return systemService(context, Context.MEDIA_SESSION_SERVICE);
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static MediaProjectionManager mediaProjectionManager(@NonNull Context context) {
        return systemService(context, Context.MEDIA_PROJECTION_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static MidiManager midiManager(@NonNull Context context) {
        return systemService(context, Context.MIDI_SERVICE);
    }

    @Nullable
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static MidiManager midiManagerOrNull(@NonNull Context context) {
        return systemServiceOrNull(context, Context.MIDI_SERVICE);
    }

    // RADIO_SERVICE

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static HardwarePropertiesManager hardwarePropertiesManager(@NonNull Context context) {
        return systemService(context, Context.HARDWARE_PROPERTIES_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static ShortcutManager shortcutManager(@NonNull Context context) {
        return systemService(context, Context.SHORTCUT_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static SystemHealthManager systemHealthManager(@NonNull Context context) {
        return systemService(context, Context.SYSTEM_HEALTH_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static CompanionDeviceManager companionDeviceManager(@NonNull Context context) {
        return systemService(context, Context.COMPANION_DEVICE_SERVICE);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static CrossProfileApps crossProfileApps(@NonNull Context context) {
        return systemService(context, Context.CROSS_PROFILE_APPS_SERVICE);
    }
}
