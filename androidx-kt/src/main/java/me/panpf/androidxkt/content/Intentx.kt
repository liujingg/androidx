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

package me.panpf.androidxkt.content

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.support.annotation.RequiresPermission
import me.panpf.androidx.content.Intentx


/**
 * Create an Intent that opens the recording page
 */
inline fun createRecordingIntent(): Intent = Intentx.createRecordingIntent()

/**
 * Create an Intent that opens the dialing page and displays the specified phone number
 *
 * @param phoneNumber Target phone number
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
inline fun createLaunchDialingIntent(phoneNumber: String): Intent = Intentx.createLaunchDialingIntent(phoneNumber)

/**
 * Create an Intent that can call the specified phone number
 *
 * @param phoneNumber Target phone number
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
inline fun createCallPhoneIntent(phoneNumber: String): Intent = Intentx.createCallPhoneIntent(phoneNumber)

/**
 * Create an Intent that can start sending SMS pages
 *
 * @param phoneNumber    Target phone number
 * @param messageContent SMS content
 */
inline fun createLaunchSendSmsIntent(phoneNumber: String, messageContent: String): Intent = Intentx.createLaunchSendSmsIntent(phoneNumber, messageContent)

/**
 * Create an Intent that opens the specified web page
 *
 * @param url Web page url
 */
inline fun createLaunchWebBrowserIntent(url: String): Intent = Intentx.createLaunchWebBrowserIntent(url)

/**
 * Create a broadcast Intent that lets System Explorer scan the specified file uri
 */
inline fun createScanFileBroadcastIntent(fileUri: Uri): Intent = Intentx.createScanFileBroadcastIntent(fileUri)


/**
 * Create an Intent that opens the specified app install page
 *
 * @param apkFileUri APK file uri
 */
inline fun createInstallAppIntent(apkFileUri: Uri): Intent = Intentx.createInstallAppIntent(apkFileUri)

/**
 * Create an Intent that opens the specified app uninstall page
 *
 * @param packageName App package name
 */
inline fun createUninstallAppIntent(packageName: String): Intent = Intentx.createUninstallAppIntent(packageName)

/**
 * Create an intent that opens the specified app
 *
 * @param packageName App package name
 */
inline fun Context.createLaunchAppIntent(packageName: String): Intent? = Intentx.createLaunchAppIntent(this, packageName)

/**
 * Create an Intent that opens the specified app details page
 *
 * @param packageName App package name
 */
inline fun createAppDetailInSystemIntent(packageName: String): Intent = Intentx.createAppDetailInSystemIntent(packageName)

/**
 * Create an Intent based on the source Intent and the ResolveInfo found with it
 */
inline fun createActivityIntentByResolveInfo(sourceIntent: Intent, resolveInfo: ResolveInfo): Intent =
        Intentx.createActivityIntentByResolveInfo(sourceIntent, resolveInfo)

/**
 * Create an Intent to take a photo with your camera
 *
 * @param saveFileUri Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
inline fun createTakePhotoIntent(saveFileUri: Uri?): Intent = Intentx.createTakePhotoIntent(saveFileUri)

/**
 * Create an Intent that selects a picture from the system album, And then get the image uri from the returned Intent at onActivityResult,
 * for example: Uri imageUri = (Bitmap) intent.getData()
 */
inline fun createPickImageIntent(): Intent = Intentx.createPickImageIntent()

/**
 * Create an intent that crops the image
 *
 * @param sourceFileUri Picture uri
 * @param targetWidth   Target width
 * @param targetHeight  Target height
 * @param saveFileUri   Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
inline fun createCropImageIntent(sourceFileUri: Uri, targetWidth: Int, targetHeight: Int, saveFileUri: Uri?): Intent =
        Intentx.createCropImageIntent(sourceFileUri, targetWidth, targetHeight, saveFileUri)


/**
 * Create an Intent to send text
 */
inline fun String.createSendTextIntent(subject: String): Intent = Intentx.createSendTextIntent(this, subject)

/**
 * Create an Intent to send text
 */
inline fun String.createSendTextIntent(): Intent = Intentx.createSendTextIntent(this)

/**
 * Create an Intent to send text
 */
inline fun Uri.createSendTextFileIntent(subject: String): Intent = Intentx.createSendTextFileIntent(this, subject)

/**
 * Create an Intent to send text
 */
inline fun Uri.createSendTextFileIntent(): Intent = Intentx.createSendTextFileIntent(this)

/**
 * Create an Intent to send image
 */
inline fun Uri.createSendImageFileIntent(): Intent = Intentx.createSendImageFileIntent(this)

/**
 * Create an Intent to send image
 */
inline fun Uri.createSendImageFileIntent(subType: String): Intent = Intentx.createSendImageFileIntent(this, subType)

/**
 * Create an Intent to send image
 */
inline fun Uri.createSendFileIntent(mimeType: String): Intent = Intentx.createSendFileIntent(this, mimeType)