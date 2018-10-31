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
import android.support.annotation.RequiresPermission
import me.panpf.androidx.hardware.Hardwarex

/*
 * 设备硬件相关的扩展方法或属性
 */


@SuppressLint("HardwareIds")
@RequiresPermission(anyOf = [(Manifest.permission.READ_PHONE_STATE), (Manifest.permission.READ_SMS), (Manifest.permission.READ_PHONE_NUMBERS)])
inline fun Context.getPhoneNumber(): String = Hardwarex.getPhoneNumber(this)

@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getDeviceId(): String = Hardwarex.getDeviceId(this)

@SuppressLint("HardwareIds")
inline fun Context.getAndroidId(): String = Hardwarex.getAndroidId(this)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSubscriberId(): String = Hardwarex.getSubscriberId(this)

/**
 * 获取 SIM 卡序列号
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getSimSerialNumber(): String = Hardwarex.getSimSerialNumber(this)

@SuppressLint("MissingPermission", "HardwareIds")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun getSerial(): String = Hardwarex.getSerial()

/**
 * 获取国际移动设备身份码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMEI(): String = Hardwarex.getIMEI(this)

/**
 * 获取国际移动用户识别码
 */
@SuppressLint("HardwareIds", "MissingPermission")
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
inline fun Context.getIMSI(): String = Hardwarex.getIMSI(this)

/**
 * 获取 MAC 地址
 */
@SuppressLint("HardwareIds")
@RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
inline fun Context.getMacAddress(): String = Hardwarex.getMacAddress(this)