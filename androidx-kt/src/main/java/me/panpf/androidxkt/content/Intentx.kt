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
import androidx.annotation.RequiresPermission
import me.panpf.androidx.content.Intentx
import java.io.File


/**
 * Get the shared file uri
 *
 * @param authority FileProvider authority
 */
inline fun Context.getShareFileUri(file: File, authority: String): Uri = Intentx.getShareFileUri(this, file, authority)

/**
 * Get the shared file uri. Read the authority of provider 'androidx.fragment.app.Fragment' from AndroidManifest to generate file uri
 */
inline fun Context.getShareFileUri(file: File): Uri = Intentx.getShareFileUri(this, file)


/**
 * Create an Intent that opens the dialing page and displays the specified phone number
 *
 * @receiver Target phone number
 */
inline fun String.createLaunchDialingIntent(): Intent = Intentx.createLaunchDialingIntent(this)

/**
 * Create an Intent that can call the specified phone number
 *
 * @receiver Target phone number
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
inline fun String.createCallPhoneIntent(): Intent = Intentx.createCallPhoneIntent(this)

/**
 * Create an Intent that can start sending SMS pages
 *
 * @receiver    Target phone number
 * @param messageContent SMS content
 */
inline fun String.createLaunchSendSmsIntent(messageContent: String): Intent = Intentx.createLaunchSendSmsIntent(this, messageContent)

/**
 * Create an Intent that opens the specified web page
 *
 * @receiver Web page url
 */
inline fun String.createLaunchWebBrowserIntent(): Intent = Intentx.createLaunchWebBrowserIntent(this)

/**
 * Create a broadcast Intent that lets System Explorer scan the specified file uri
 */
inline fun Uri.createScanFileBroadcastIntent(): Intent = Intentx.createScanFileBroadcastIntent(this)

/**
 * Create a broadcast Intent that lets System Explorer scan the specified file uri
 */
inline fun Context.createScanFileBroadcastIntent(file: File): Intent = Intentx.createScanFileBroadcastIntent(this, file)


/**
 * Create an Intent that opens the specified app install page
 *
 * @receiver APK file uri
 */
inline fun Uri.createInstallAppIntent(): Intent = Intentx.createInstallAppIntent(this)

/**
 * Create an Intent that opens the specified app install page
 */
inline fun Context.createInstallAppIntent(apkFile: File): Intent = Intentx.createInstallAppIntent(this, apkFile)

/**
 * Create an Intent that opens the specified app uninstall page
 *
 * @receiver App package name
 */
inline fun String.createUninstallAppIntent(): Intent = Intentx.createUninstallAppIntent(this)

/**
 * Create an intent that opens the specified app
 *
 * @param packageName App package name
 */
inline fun Context.createLaunchAppIntent(packageName: String): Intent? = Intentx.createLaunchAppIntent(this, packageName)

/**
 * Create an Intent that opens the specified app details page
 *
 * @receiver App package name
 */
inline fun String.createAppDetailInSystemIntent(): Intent = Intentx.createAppDetailInSystemIntent(this)

/**
 * Create an Intent based on the source Intent and the ResolveInfo found with it
 */
inline fun Intent.createActivityIntentByResolveInfo(resolveInfo: ResolveInfo): Intent =
        Intentx.createActivityIntentByResolveInfo(this, resolveInfo)


/**
 * Create an Intent to take a photo with your camera
 *
 * @receiver Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
inline fun Uri?.createTakePhotoIntent(): Intent = Intentx.createTakePhotoIntent(this)

/**
 * Create an Intent to take a photo with your camera
 *
 * @param saveFile Save the image to file, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
inline fun Context.createTakePhotoIntent(saveFile: File?): Intent = Intentx.createTakePhotoIntent(this, saveFile)

/**
 * Create an intent that crops the image
 *
 * @receiver Picture uri
 * @param targetWidth   Target width
 * @param targetHeight  Target height
 * @param saveFileUri   Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
inline fun Uri.createCropImageIntent(targetWidth: Int, targetHeight: Int, saveFileUri: Uri?): Intent =
        Intentx.createCropImageIntent(this, targetWidth, targetHeight, saveFileUri)


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
 * Create an Intent to send text
 */
inline fun Context.createSendTextFileIntent(textFile: File, subject: String): Intent = Intentx.createSendTextFileIntent(this, textFile, subject)

/**
 * Create an Intent to send text
 */
inline fun Context.createSendTextFileIntent(textFile: File): Intent = Intentx.createSendTextFileIntent(this, textFile)


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
inline fun Context.createSendImageFileIntent(imageFile: File, subType: String): Intent = Intentx.createSendImageFileIntent(this, imageFile, subType)

/**
 * Create an Intent to send image
 */
inline fun Context.createSendImageFileIntent(imageFile: File): Intent = Intentx.createSendImageFileIntent(this, imageFile)


/**
 * Create an Intent to send image
 */
inline fun Uri.createSendFileIntent(mimeType: String): Intent = Intentx.createSendFileIntent(this, mimeType)

/**
 * Create an Intent to send image
 */
inline fun Context.createSendFileIntent(file: File, mimeType: String): Intent = Intentx.createSendFileIntent(this, file, mimeType)