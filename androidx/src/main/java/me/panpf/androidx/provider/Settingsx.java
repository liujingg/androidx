/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
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

package me.panpf.androidx.provider;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.view.Window;
import android.view.WindowManager;

/**
 * System setup tool method
 */
@SuppressWarnings("WeakerAccess")
public class Settingsx {

    /**
     * Return true if screen brightness auto mode is on
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean isScreenBrightnessModeAutomatic(@NonNull Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Turn on or off the screen brightness auto mode
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setScreenBrightnessModeAutomatic(@NonNull Context context, boolean automatic) {
        int newValue = automatic ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, newValue);
    }

    /**
     * Get system brightness, the range is 0-255
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static int getScreenBrightness(@NonNull Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Set the system brightness (only change the brightness attribute of the system, the current activity brightness does not change)
     *
     * @param brightness Brightness, the range is 0-255
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setScreenBrightness(Context context, int brightness) {
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    /**
     * Set the brightness of the Activity window (you can see the effect, the brightness of the system will not change)
     *
     * @param brightness Brightness, the range is 0-255
     */
    public static void setWindowBrightness(@NonNull Activity activity, float brightness) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.screenBrightness = brightness;
        window.setAttributes(params);
    }

    /**
     * Get screen off timeout in milliseconds
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static int getScreenOffTimeout(@NonNull Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Set screen off timeout in milliseconds
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setScreenOffTimeout(@NonNull Context context, int millis) {
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, millis);
    }

    /**
     * Return true if airplane mode is on
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean isAirplaneModeOn(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
        } else {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;
        }
    }

    /**
     * Turn airplane mode on or off
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setAirplaneModeOn(@NonNull Context context, boolean enabled) {
        boolean result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            result = Settings.Global.putInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, enabled ? 1 : 0);
        } else {
            result = Settings.System.putInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, enabled ? 1 : 0);
        }
        context.sendBroadcast(new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        return result;
    }

    /**
     * Return true if Bluetooth is on or is being turned on
     */
    @RequiresPermission(Manifest.permission.BLUETOOTH)
    public static boolean isBluetoothOn() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        int state = bluetoothAdapter != null ? bluetoothAdapter.getState() : -1;
        return state == BluetoothAdapter.STATE_ON || state == BluetoothAdapter.STATE_TURNING_ON;
    }

    /**
     * Turn Bluetooth on or off
     */
    @RequiresPermission(allOf = {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN})
    public static void setBluetoothOn(boolean enable) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (enable) {
                bluetoothAdapter.enable();
            } else {
                bluetoothAdapter.disable();
            }
        }
    }

    /**
     * Get the media volume, the value range is 0-15
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static int getMediaVolume(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager == null) throw new IllegalStateException("AudioManager not found");
            return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        } else {
            try {
                return Settings.System.getInt(context.getContentResolver(), "volume_music");
            } catch (Settings.SettingNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Set the media volume, the value range is 0-15
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setMediaVolume(@NonNull Context context, int mediaVolume) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager == null) throw new IllegalStateException("AudioManager not found");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVolume, 0);
            return true;
        } else {
            return Settings.System.putInt(context.getContentResolver(), "volume_music", mediaVolume);
        }
    }

    /**
     * Get the ringer volume, the range is 0-7
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static int getRingVolume(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager == null) throw new IllegalStateException("AudioManager not found");
            return audioManager.getStreamVolume(AudioManager.STREAM_RING);
        } else {
            try {
                return Settings.System.getInt(context.getContentResolver(), "volume_ring");
            } catch (Settings.SettingNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Set the ringer volume, the range is 0-7
     */
    @RequiresPermission(Manifest.permission.WRITE_SETTINGS)
    public static boolean setRingVolume(@NonNull Context context, int ringVolume) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager == null) throw new IllegalStateException("AudioManager not found");
            audioManager.setStreamVolume(AudioManager.STREAM_RING, ringVolume, 0);
            return true;
        } else {
            return Settings.System.putInt(context.getContentResolver(), "volume_ring", ringVolume);
        }
    }
}
