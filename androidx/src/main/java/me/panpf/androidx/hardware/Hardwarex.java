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

package me.panpf.androidx.hardware;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

import org.jetbrains.annotations.NotNull;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Collectionx;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.util.Transformer;

@SuppressWarnings("WeakerAccess")
public class Hardwarex {

    public static String getDeviceModel() {
        return Stringx.orEmpty(Build.MODEL);
    }

    public static String getDeviceName() {
        return Stringx.orEmpty(Build.DEVICE);
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String[] getSupportedAbis() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return Build.SUPPORTED_ABIS;
            } else {
                return Collectionx.filter(Collectionx.listOf(Build.CPU_ABI, Build.CPU_ABI2), new Predicate<String>() {
                    @Override
                    public boolean predicate(@NotNull String s) {
                        return Stringx.isNotEmpty(s) && !"unknown".equals(s);
                    }
                }).toArray(new String[0]);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(allOf = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS})
    public static String getPhoneNumber(@NotNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return Stringx.orEmpty(manager != null ? manager.getLine1Number() : null);
        } catch (Throwable e) {
            e.printStackTrace();
            return Stringx.orEmpty(e instanceof SecurityException ? "PermissionDenied" : null);
        }
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceId(@NotNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return Stringx.orEmpty(manager != null ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? manager.getImei() : manager.getDeviceId()) : null);
        } catch (Throwable e) {
            e.printStackTrace();
            return Stringx.orEmpty(e instanceof SecurityException ? "PermissionDenied" : null);
        }
    }

    @SuppressLint("HardwareIds")
    public static String getAndroidId(@NotNull Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取国际移动用户识别码
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSubscriberId(@NotNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return Stringx.orEmpty(manager != null ? manager.getSubscriberId() : null);
        } catch (Throwable e) {
            e.printStackTrace();
            return Stringx.orEmpty(e instanceof SecurityException ? "PermissionDenied" : null);
        }
    }

    /**
     * 获取 SIM 卡序列号
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSimSerialNumber(@NotNull Context context) {
        try {
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return Stringx.orEmpty(manager != null ? manager.getSimSerialNumber() : null);
        } catch (Throwable e) {
            e.printStackTrace();
            return Stringx.orEmpty(e instanceof SecurityException ? "PermissionDenied" : null);
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerial() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Build.getSerial();
            } else {
                return Build.SERIAL;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return Stringx.orEmpty(e instanceof SecurityException ? "PermissionDenied" : null);
        }
    }

    /**
     * 获取国际移动设备身份码
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMEI(@NotNull Context context) {
        return getDeviceId(context);
    }

    /**
     * 获取国际移动用户识别码
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getIMSI(@NotNull Context context) {
        return getSubscriberId(context);
    }

    /**
     * 获取 MAC 地址
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getMacAddress(@NotNull Context context) {
        String macAddress = null;
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
                List<NetworkInterface> nis = interfaceEnumeration != null ? Collections.list(interfaceEnumeration) : null;
                if (nis != null && nis.size() > 0) {
                    NetworkInterface networkInterface = Collectionx.find(nis, new Predicate<NetworkInterface>() {
                        @Override
                        public boolean predicate(@NotNull NetworkInterface networkInterface) {
                            return networkInterface.getName().equalsIgnoreCase("wlan0");
                        }
                    });
                    byte[] address = networkInterface != null ? networkInterface.getHardwareAddress() : null;
                    if (address != null) {
                        List<Byte> byteList = new LinkedList<>();
                        for (byte by : address) {
                            byteList.add(by);
                        }
                        macAddress = Collectionx.joinToString(byteList, ":", null, null, -1, null, new Transformer<Byte, CharSequence>() {
                            @NotNull
                            @Override
                            public CharSequence transform(@NotNull Byte by) {
                                return Integer.toHexString(by & 0xFF);
                            }
                        });
                    }
                } else {
                    WifiInfo wifiInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                    macAddress = wifiInfo != null ? wifiInfo.getMacAddress() : null;
                }
            } else {
                WifiInfo wifiInfo = wifiManager != null ? wifiManager.getConnectionInfo() : null;
                macAddress = wifiInfo != null ? wifiInfo.getMacAddress() : null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            macAddress = e instanceof SecurityException ? "PermissionDenied" : null;
        }
        return macAddress != null ? macAddress : "02:00:00:00:00:00";
    }
}