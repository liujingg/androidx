@file:Suppress("unused")

package me.panpf.androidx.hardware

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresPermission
import android.telephony.TelephonyManager
import java.net.NetworkInterface

/*
 * 设备硬件相关的扩展方法或属性
 */


val deviceModel: String by lazy { Build.MODEL.orEmpty() }

val deviceName: String by lazy { Build.DEVICE.orEmpty() }

val hardware: String by lazy { Build.HARDWARE }

val supportedAbis: Array<out String> by lazy {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Build.SUPPORTED_ABIS
        } else {
            arrayListOf<String>(Build.CPU_ABI, Build.CPU_ABI2).filter { it.isNotEmpty() }.toTypedArray()
        }
    } catch (err: Throwable) {
        arrayOf<String>()
    }
}

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(allOf = [Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS])
fun Context.getPhoneNumber(): String = try {
    (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).line1Number
} catch (e: Throwable) {
    e.printStackTrace()
    if (e is SecurityException) "PermissionDenied" else null
} ?: ""

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun Context.getDeviceId(): String = try {
    (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) this.imei else this.deviceId
    }
} catch (e: Throwable) {
    e.printStackTrace()
    if (e is SecurityException) "PermissionDenied" else null
} ?: ""

@SuppressLint("HardwareIds")
fun Context.getAndroidId(): String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun Context.getSubscriberId(): String = try {
    (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).subscriberId
} catch (e: Throwable) {
    e.printStackTrace()
    if (e is SecurityException) "PermissionDenied" else null
} ?: ""

/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun Context.getSimSerialNumber(): String = try {
    (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simSerialNumber
} catch (e: Throwable) {
    e.printStackTrace()
    if (e is SecurityException) "PermissionDenied" else null
} ?: ""

@SuppressLint("MissingPermission", "HardwareIds")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun getSerial(): String = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Build.getSerial()
    } else {
        Build.SERIAL
    }
} catch (e: Throwable) {
    e.printStackTrace()
    if (e is SecurityException) "PermissionDenied" else null
} ?: ""

/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun Context.getIMEI(): String = getDeviceId()

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun Context.getIMSI(): String = getSubscriberId()

/**
 * 获取 MAC 地址
 */
@SuppressLint("HardwareIds")
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
fun Context.getMacAddress(): String {
    val macAddress = try {
        val wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nis = NetworkInterface.getNetworkInterfaces()?.toList()
            if (nis != null && nis.isNotEmpty()) {
                nis.find { it.name.equals("wlan0", ignoreCase = true) }?.hardwareAddress?.let {
                    it.joinToString(separator = ":") { b -> Integer.toHexString(b.toInt() and 0xFF) }
                }
            } else {
                wifiManager.connectionInfo?.macAddress
            }
        } else {
            wifiManager.connectionInfo?.macAddress
        }
    } catch (e: Exception) {
        e.printStackTrace()
        if (e is SecurityException) "PermissionDenied" else null
    }
    return macAddress ?: "02:00:00:00:00:00"
}