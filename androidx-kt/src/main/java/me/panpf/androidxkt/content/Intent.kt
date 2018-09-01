package me.panpf.androidxkt.content

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.support.annotation.RequiresPermission


/**
 * Create an Intent that opens the recording page
 */
fun makeRecordingIntent(): Intent {
    val intent = Intent(Intent.ACTION_GET_CONTENT)
    intent.type = "audio/amr"
    return intent
}

/**
 * Create an Intent that opens the dialing page and displays the specified phone number
 *
 * @param phoneNumber Target phone number
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
fun makeLaunchDialingIntent(phoneNumber: String): Intent {
    return Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
}

/**
 * Create an Intent that can call the specified phone number
 *
 * @param phoneNumber Target phone number
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
fun makeCallPhoneIntent(phoneNumber: String): Intent {
    return Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
}

/**
 * Create an Intent that can start sending SMS pages
 *
 * @param phoneNumber    Target phone number
 * @param messageContent SMS content
 */
fun makeLaunchSendSmsIntent(phoneNumber: String, messageContent: String): Intent {
    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber"))
    intent.putExtra("sms_body", messageContent)
    return intent
}

/**
 * Create an Intent that opens the specified web page
 *
 * @param url Web page url
 */
fun makeLaunchWebBrowserIntent(url: String): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse(url))
}

/**
 * Create a broadcast Intent that lets System Explorer scan the specified file
 */
fun makeScanFileBroadcastIntent(fileUri: Uri): Intent {
    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, fileUri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Prepare for FileProvider on Android N
    return intent
}


/**
 * Create an Intent that opens the specified app install page
 *
 * @param apkFileUri APK file uri
 */
fun makeInstallAppIntent(apkFileUri: Uri): Intent {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    intent.setDataAndType(apkFileUri, "application/vnd.android.package-archive")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Prepare for FileProvider on Android N
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    return intent
}

/**
 * Create an Intent that opens the specified app uninstall page
 *
 * @param packageName App package name
 */
fun makeUninstallAppIntent(packageName: String): Intent {
    return Intent(Intent.ACTION_DELETE, Uri.parse("package: $packageName"))
}

/**
 * Create an intent that opens the specified app
 *
 * @param packageName App package name
 */
fun Context.makeLaunchAppIntent(packageName: String): Intent? {
    try {
        var intent = this.packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent = intent.cloneFilter()
            // Instagram must add FLAG_ACTIVITY_NEW_TASK
            intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }

        val packageInfo = this.packageManager.getPackageInfo(packageName, 0)
        if (packageInfo != null) {
            if (packageInfo.activities != null && packageInfo.activities.size == 1) {
                intent = Intent(Intent.ACTION_MAIN)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setClassName(packageInfo.packageName, packageInfo.activities[0].name)
                return intent
            }
        }
    } catch (e: Exception) {
        return null
    }

    return null
}

/**
 * Create an Intent that opens the specified app details page
 *
 * @param packageName App package name
 */
fun makeAppDetailInSystemIntent(packageName: String): Intent {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    return intent
}

/**
 * Create an Intent based on the source Intent and the ResolveInfo found with it
 */
fun makeActivityIntentByResolveInfo(sourceIntent: Intent, resolveInfo: ResolveInfo): Intent {
    val resolveIntent = Intent()
    resolveIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
    resolveIntent.action = sourceIntent.action
    val bundle = sourceIntent.extras
    if (bundle != null) {
        resolveIntent.putExtras(bundle)
    }
    resolveIntent.type = sourceIntent.type
    resolveIntent.addFlags(resolveInfo.activityInfo.flags)
    return resolveIntent
}

/**
 * Test if you can start Activity
 */
fun Context.canStartActivity(intent: Intent): Boolean {
    if (this !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    val resolveInfoList = this.packageManager.queryIntentActivities(intent, 0)
    return resolveInfoList != null && resolveInfoList.size > 0
}

/**
 * Safely launch an Activity, catch ActivityNotFoundException and return false
 */
fun Context.safeStartActivity(intent: Intent): Boolean {
    if (this !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    return try {
        this.startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

/**
 * Create an Intent to take a photo with your camera
 *
 * @param saveFileUri Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
fun makeTakePhotoIntent(saveFileUri: Uri?): Intent {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (saveFileUri != null) {
        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileUri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Prepare for FileProvider on Android N
    }
    return intent
}

/**
 * Create an Intent that selects a picture from the system album, And then get the image uri from the returned Intent at onActivityResult,
 * for example: Uri imageUri = (Bitmap) intent.getData()
 */
fun makePickImageIntent(): Intent {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    return intent
}

/**
 * Create an intent that crops the image
 *
 * @param sourceFileUri Picture uri
 * @param targetWidth   Target width
 * @param targetHeight  Target height
 * @param saveFileUri   Save the image to the specified uri, If null, get the image from the returned Intent at onActivityResult,
 * for example: Bitmap bitmap = (Bitmap) intent.getExtras().get("data")
 */
fun makeCropImageIntent(sourceFileUri: Uri, targetWidth: Int, targetHeight: Int, saveFileUri: Uri?): Intent {
    val intent = Intent("com.android.camera.action.CROP")
    intent.setDataAndType(sourceFileUri, "image/*")
    intent.putExtra("crop", true)

    intent.putExtra("aspectX", targetWidth)
    intent.putExtra("aspectY", targetHeight)

    intent.putExtra("outputX", targetWidth)
    intent.putExtra("outputY", targetHeight)

    if (saveFileUri != null) {
        intent.putExtra("output", saveFileUri)
    } else {
        intent.putExtra("return-data", true)
        intent.putExtra("scale", true)
    }

    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Prepare for FileProvider on Android N
    return intent
}