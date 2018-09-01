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

package me.panpf.androidx.content;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Intent tool method
 */
@SuppressWarnings("WeakerAccess")
public class Intentx {

    /**
     * Create an Intent that opens the recording page
     */
    @NotNull
    public static Intent makeRecordingIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/amr");
        return intent;
    }

    /**
     * Create an Intent that opens the dialing page and displays the specified phone number
     *
     * @param phoneNumber Target phone number
     */
    @NotNull
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static Intent makeLaunchDialingIntent(@NotNull String phoneNumber) {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
    }

    /**
     * Create an Intent that can call the specified phone number
     *
     * @param phoneNumber Target phone number
     */
    @NotNull
    @RequiresPermission(Manifest.permission.CALL_PHONE)
    public static Intent makeCallPhoneIntent(@NotNull String phoneNumber) {
        return new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
    }

    /**
     * Create an Intent that can start sending SMS pages
     *
     * @param phoneNumber    Target phone number
     * @param messageContent SMS content
     */
    @NotNull
    public static Intent makeLaunchSendSmsIntent(@NotNull String phoneNumber, String messageContent) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
        intent.putExtra("sms_body", messageContent);
        return intent;
    }

    /**
     * Create an Intent that opens the specified web page
     *
     * @param url Web page url
     */
    @NotNull
    public static Intent makeLaunchWebBrowserIntent(String url) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

    /**
     * Create a broadcast Intent that lets System Explorer scan the specified file
     */
    @NotNull
    public static Intent makeScanFileBroadcastIntent(Uri fileUri) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
        return intent;
    }


    /**
     * Create an Intent that opens the specified app install page
     *
     * @param apkFileUri APK file uri
     */
    @NotNull
    public static Intent makeInstallAppIntent(@NotNull Uri apkFileUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(apkFileUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * Create an Intent that opens the specified app uninstall page
     *
     * @param packageName App package name
     */
    @NotNull
    public static Intent makeUninstallAppIntent(@NotNull String packageName) {
        return new Intent(Intent.ACTION_DELETE, Uri.parse("package: " + packageName));
    }

    /**
     * Create an intent that opens the specified app
     *
     * @param packageName App package name
     */
    @Nullable
    public static Intent makeLaunchAppIntent(@NotNull Context context, @NotNull String packageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent = intent.cloneFilter();
                // Instagram must add FLAG_ACTIVITY_NEW_TASK
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                return intent;
            }

            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                if (packageInfo.activities != null && packageInfo.activities.length == 1) {
                    intent = new Intent(Intent.ACTION_MAIN);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClassName(packageInfo.packageName, packageInfo.activities[0].name);
                    return intent;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * Create an Intent that opens the specified app details page
     *
     * @param packageName App package name
     */
    @NotNull
    public static Intent makeAppDetailInSystemIntent(@NotNull String packageName) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);
        return intent;
    }

    /**
     * Create an Intent based on the source Intent and the ResolveInfo found with it
     */
    @NotNull
    public static Intent makeActivityIntentByResolveInfo(@NotNull Intent sourceIntent, @NotNull ResolveInfo resolveInfo) {
        Intent resolveIntent = new Intent();
        resolveIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        resolveIntent.setAction(sourceIntent.getAction());
        Bundle bundle = sourceIntent.getExtras();
        if (bundle != null) {
            resolveIntent.putExtras(bundle);
        }
        resolveIntent.setType(sourceIntent.getType());
        resolveIntent.addFlags(resolveInfo.activityInfo.flags);
        return resolveIntent;
    }

    /**
     * Test if you can start Activity
     */
    public static boolean canStartActivity(@NotNull Context context, @NotNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfoList != null && resolveInfoList.size() > 0;
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStartActivity(@NotNull Context context, @NotNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create an Intent to take a photo with your camera
     *
     * @param saveFileUri Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
     *                    for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
     */
    @NotNull
    public static Intent makeTakePhotoIntent(@Nullable Uri saveFileUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (saveFileUri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
        }
        return intent;
    }

    /**
     * Create an Intent that selects a picture from the system album, And then get the image uri from the returned Intent at onActivityResult,
     * for example: Uri imageUri = (Bitmap) intent.getData()
     */
    @NotNull
    public static Intent makePickImageIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        return intent;
    }

    /**
     * Create an intent that crops the image
     *
     * @param sourceFileUri Picture uri
     * @param targetWidth   Target width
     * @param targetHeight  Target height
     * @param saveFileUri   Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
     *                      for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
     */
    @NotNull
    public static Intent makeCropImageIntent(@NotNull Uri sourceFileUri, int targetWidth, int targetHeight, @Nullable Uri saveFileUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(sourceFileUri, "image/*");
        intent.putExtra("crop", true);

        intent.putExtra("aspectX", targetWidth);
        intent.putExtra("aspectY", targetHeight);

        intent.putExtra("outputX", targetWidth);
        intent.putExtra("outputY", targetHeight);

        if (saveFileUri != null) {
            intent.putExtra("output", saveFileUri);
        } else {
            intent.putExtra("return-data", true);
            intent.putExtra("scale", true);
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Prepare for FileProvider on Android N
        return intent;
    }
}
