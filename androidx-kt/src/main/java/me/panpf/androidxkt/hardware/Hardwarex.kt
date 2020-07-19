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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.hardware

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.RequiresPermission
import me.panpf.androidx.hardware.Hardwarex

/*
 * 设备硬件相关的扩展方法或属性
 */


@SuppressLint("HardwareIds", "InlinedApi")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumberOr(defaultValue: String): String = Hardwarex.getPhoneNumberOr(this, defaultValue)

@SuppressLint("HardwareIds", "InlinedApi")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumberOrThrow(): String = Hardwarex.getPhoneNumberOrThrow(this)

@SuppressLint("HardwareIds", "InlinedApi")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumberOrNull(): String? = Hardwarex.getPhoneNumberOrNull(this)


@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceIdOr(defaultValue: String): String = Hardwarex.getDeviceIdOr(this, defaultValue)

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceIdOrThrow(): String = Hardwarex.getDeviceIdOrThrow(this)

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceIdOrNull(): String? = Hardwarex.getDeviceIdOrNull(this)


@SuppressLint("HardwareIds")
inline fun Context.getAndroidIdOr(defaultValue: String): String = Hardwarex.getAndroidIdOr(this, defaultValue)

@SuppressLint("HardwareIds")
inline fun Context.getAndroidIdOrThrow(): String = Hardwarex.getAndroidIdOrThrow(this)

@SuppressLint("HardwareIds")
inline fun Context.getAndroidIdOrNull(): String? = Hardwarex.getAndroidIdOrNull(this)


/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberIdOr(defaultValue: String): String = Hardwarex.getSubscriberIdOr(this, defaultValue)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberIdOrThrow(): String = Hardwarex.getSubscriberIdOrThrow(this)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberIdOrNull(): String? = Hardwarex.getSubscriberIdOrNull(this)


/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumberOr(defaultValue: String): String = Hardwarex.getSimSerialNumberOr(this, defaultValue)

/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumberOrThrow(): String = Hardwarex.getSimSerialNumberOrThrow(this)

/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumberOrNull(): String? = Hardwarex.getSimSerialNumberOrNull(this)


/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEIOr(defaultValue: String): String = Hardwarex.getIMEIOr(this, defaultValue)

/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEIOrThrow(): String = Hardwarex.getIMEIOrThrow(this)

/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEIOrNull(): String? = Hardwarex.getIMEIOrNull(this)


/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSIOr(defaultValue: String): String = Hardwarex.getIMSIOr(this, defaultValue)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSIOrThrow(): String = Hardwarex.getIMSIOrThrow(this)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSIOrNull(): String? = Hardwarex.getIMSIOrNull(this)


/**
 * 获取 MAC 地址
 */
@SuppressLint("HardwareIds")
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getMacAddressOr(defaultValue: String): String = Hardwarex.getMacAddressOr(this, defaultValue)

/**
 * 获取 MAC 地址
 */
@SuppressLint("HardwareIds")
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getMacAddress(): String = Hardwarex.getMacAddress(this)