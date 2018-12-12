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

package me.panpf.androidxkt.test.content

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.content.FileProvider
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.content.*
import me.panpf.androidxkt.content.pm.findSelfProviderInfoByName
import me.panpf.androidxkt.content.pm.getPackageApkFile
import me.panpf.androidxkt.os.storage.getAppExternalFilesDir
import me.panpf.androidxkt.test.BuildConfig
import me.panpf.javax.io.UnableCreateDirException
import me.panpf.javax.io.UnableCreateFileException
import me.panpf.javaxkt.io.closeQuietly
import me.panpf.javaxkt.io.createNewFileOrThrow
import me.panpf.javaxkt.util.requireNotNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class IntentxTest {

    @Test
    @Throws(IOException::class)
    fun testGetShareFileUri() {
        val context = InstrumentationRegistry.getContext()
        val file = File(context.getAppExternalFilesDir(), "testGetShareFileUri.txt")
        try {
            file.writeText("testGetShareFileUri")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val authority = BuildConfig.APPLICATION_ID + ".file.provider"
                val uri = context.getShareFileUri(file, authority)
                Assert.assertEquals("content://$authority/files/testGetShareFileUri.txt", uri.toString())

                try {
                    context.getShareFileUri(file, BuildConfig.APPLICATION_ID + ".file.provider.test")
                    Assert.fail()
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

            } else {
                val uri = context.getShareFileUri(file, "")
                Assert.assertEquals(Uri.fromFile(file).toString(), uri.toString())
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val uri = context.getShareFileUri(file)
                val fileProviderInfo = context.findSelfProviderInfoByName(FileProvider::class.java.name)
                Assert.assertEquals("content://" + (if (fileProviderInfo != null) fileProviderInfo.authority else "") + "/files/testGetShareFileUri.txt", uri.toString())
            } else {
                val uri = context.getShareFileUri(file)
                Assert.assertEquals(Uri.fromFile(file).toString(), uri.toString())
            }
        } finally {
            file.deleteRecursively()
        }
    }

//    @Test
//    fun testCreateRecordingIntent() {
//        val recordingIntent = Intentx.createRecordingIntent()
//        Assert.assertEquals(Intent.ACTION_GET_CONTENT, recordingIntent.action)
//        Assert.assertEquals("audio/amr", recordingIntent.type)
//    }

    @Test
    fun testCreateLaunchDialingIntent() {
        val recordingIntent = "18801287688".createLaunchDialingIntent()
        Assert.assertEquals(Intent.ACTION_DIAL, recordingIntent.action)
        Assert.assertEquals("tel:18801287688", recordingIntent.data.requireNotNull().toString())

        //        Activityx.safeStart(InstrumentationRegistry.getContext(), recordingIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateCallPhoneIntent() {
        val callPhoneIntent = "18801287688".createCallPhoneIntent()
        Assert.assertEquals(Intent.ACTION_CALL, callPhoneIntent.action)
        Assert.assertEquals("tel:18801287688", callPhoneIntent.data.requireNotNull().toString())

        //        Activityx.safeStart(InstrumentationRegistry.getContext(), callPhoneIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateLaunchSendSmsIntent() {
        val sendSmsIntent = "18801287688".createLaunchSendSmsIntent("好好学习吧，天天向上")
        Assert.assertEquals(Intent.ACTION_SENDTO, sendSmsIntent.action)
        Assert.assertEquals("smsto:18801287688", sendSmsIntent.data.requireNotNull().toString())
        Assert.assertEquals("好好学习吧，天天向上", sendSmsIntent.getStringExtra("sms_body"))

        //        Activityx.safeStart(InstrumentationRegistry.getContext(), sendSmsIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateLaunchWebBrowserIntent() {
        val webIntent = "https://github.com".createLaunchWebBrowserIntent()
        Assert.assertEquals(Intent.ACTION_VIEW, webIntent.action)
        Assert.assertEquals("https://github.com", webIntent.data.requireNotNull().toString())

        //        Activityx.safeStart(InstrumentationRegistry.getContext(), webIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateScanFileBroadcastIntent() {
        val context = InstrumentationRegistry.getContext()
        val file = File(context.getAppExternalFilesDir(), "testCreateScanFileBroadcastIntent.jpg")
        //        try {
        //            InputStream inputStream = context.getResources().openRawResource(me.panpf.androidx.test.R.drawable.rect);
        //            try {
        //                byte[] bytes = Streamx.readBytes(inputStream);
        //                file.writeBytes(, bytes);
        //            } finally {
        //                Streamx.closeQuietly(inputStream);
        //            }

        val scanFileIntent1 = context.getShareFileUri(file).createScanFileBroadcastIntent()
        Assert.assertEquals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, scanFileIntent1.action)
        Assert.assertEquals(context.getShareFileUri(file).toString(),
                scanFileIntent1.data.requireNotNull().toString())
        Assert.assertTrue(scanFileIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

        val scanFileIntent2 = context.createScanFileBroadcastIntent(file)
        Assert.assertEquals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, scanFileIntent2.action)
        Assert.assertEquals(context.getShareFileUri(file).toString(),
                scanFileIntent2.data.requireNotNull().toString())
        Assert.assertTrue(scanFileIntent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)
        //
        //            context.sendBroadcast(scanFileIntent);
        //            try {
        //                Thread.sleep(5000);
        //            } catch (InterruptedException e) {
        //                e.printStackTrace();
        //            }
        //        } finally {
        //            file.deleteRecursively();
        //        }
    }

    @Test
    fun testCreateInstallAppIntent() {
        val context = InstrumentationRegistry.getContext()

        val file = context.getPackageApkFile(context.packageName)

        val installAppIntent1 = context.getShareFileUri(file).createInstallAppIntent()
        Assert.assertEquals(Intent.ACTION_VIEW, installAppIntent1.action)
        Assert.assertEquals(Intent.CATEGORY_DEFAULT, installAppIntent1.categories.find { s -> s == Intent.CATEGORY_DEFAULT })
        Assert.assertEquals(context.getShareFileUri(file).toString(),
                installAppIntent1.data.requireNotNull().toString())
        Assert.assertEquals("application/vnd.android.package-archive", installAppIntent1.type)
        Assert.assertTrue(installAppIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

        val installAppIntent2 = context.createInstallAppIntent(file)
        Assert.assertEquals(Intent.ACTION_VIEW, installAppIntent2.action)
        Assert.assertNotNull(installAppIntent2.categories.find { s -> s == Intent.CATEGORY_DEFAULT })
        Assert.assertEquals(context.getShareFileUri(file).toString(),
                installAppIntent2.data.requireNotNull().toString())
        Assert.assertEquals("application/vnd.android.package-archive", installAppIntent2.type)
        Assert.assertTrue(installAppIntent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

        //        Activityx.safeStart(context, installAppIntent2);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateUninstallAppIntent() {
        val context = InstrumentationRegistry.getContext()

        val uninstallAppIntent = context.packageName.createUninstallAppIntent()
        Assert.assertEquals(Intent.ACTION_DELETE, uninstallAppIntent.action)
        Assert.assertEquals(Uri.fromParts("package", context.packageName, null).toString(),
                uninstallAppIntent.data.requireNotNull().toString())

        //        Activityx.safeStart(context, uninstallAppIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateLaunchAppIntent() {
        val context = InstrumentationRegistry.getContext()

        val webIntent = "https://github.com".createLaunchWebBrowserIntent()
        val resolveInfos = context.packageManager.queryIntentActivities(webIntent, 0)
        val resolveInfo = resolveInfos.firstOrNull().requireNotNull()
        val webBrowserPackageName = resolveInfo.activityInfo.packageName

        val launchAppIntent = context.createLaunchAppIntent(webBrowserPackageName).requireNotNull()
        Assert.assertEquals(Intent.ACTION_MAIN, launchAppIntent.action)
        Assert.assertNotNull(launchAppIntent.categories.find { s -> s == Intent.CATEGORY_LAUNCHER })
        Assert.assertEquals(webBrowserPackageName, launchAppIntent.getPackage())

        //        Activityx.safeStart(context, launchAppIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateAppDetailInSystemIntent() {
        val context = InstrumentationRegistry.getContext()

        val webIntent = "https://github.com".createLaunchWebBrowserIntent()
        val resolveInfos = context.packageManager.queryIntentActivities(webIntent, 0)
        val resolveInfo = resolveInfos.firstOrNull().requireNotNull()
        val webBrowserPackageName = resolveInfo.activityInfo.packageName

        val appDetailInSystemIntent = webBrowserPackageName.createAppDetailInSystemIntent()
        Assert.assertEquals(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, appDetailInSystemIntent.action)
        Assert.assertEquals(Uri.fromParts("package", webBrowserPackageName, null).toString(),
                appDetailInSystemIntent.data.requireNotNull().toString())

        //        Activityx.safeStart(context, appDetailInSystemIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    fun testCreateActivityIntentByResolveInfo() {
        val context = InstrumentationRegistry.getContext()

        val sendIntent = "https://github.com".createSendTextIntent()
        Assert.assertNull(sendIntent.component)

        val resolveInfos = context.packageManager.queryIntentActivities(sendIntent, 0)
        val resolveInfo = resolveInfos.firstOrNull().requireNotNull()

        val finalIntent = sendIntent.createActivityIntentByResolveInfo(resolveInfo)
        Assert.assertEquals(sendIntent.action, finalIntent.action)
        Assert.assertNotNull(finalIntent.component)

        //        Activityx.safeStart(context, finalIntent);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    @Throws(UnableCreateFileException::class, UnableCreateDirException::class)
    fun testCreateTakePhotoIntent() {
        val context = InstrumentationRegistry.getContext()

        val file = File(context.getAppExternalFilesDir(), "testCreateTakePhotoIntent_temp.jpg")
        try {
            file.createNewFileOrThrow()

            val takePhotoIntent1 = context.getShareFileUri(file).createTakePhotoIntent()
            Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, takePhotoIntent1.action)
            Assert.assertEquals(context.getShareFileUri(file).toString(), takePhotoIntent1.getParcelableExtra<Parcelable>(MediaStore.EXTRA_OUTPUT).toString())
            Assert.assertTrue(takePhotoIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val takePhotoIntent2 = context.createTakePhotoIntent(file)
            Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, takePhotoIntent2.action)
            Assert.assertEquals(context.getShareFileUri(file).toString(), takePhotoIntent2.getParcelableExtra<Parcelable>(MediaStore.EXTRA_OUTPUT).toString())
            Assert.assertTrue(takePhotoIntent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            //            Activityx.safeStart(context, takePhotoIntent2);
            //            try {
            //                Thread.sleep(5000);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
        } finally {
            file.deleteRecursively()
        }
    }

//    @Test
//    fun testCreatePickImageIntent() {
//        val pickImageIntent = Intentx.createPickImageIntent()
//        Assert.assertEquals(Intent.ACTION_PICK, pickImageIntent.action)
//        Assert.assertEquals("image/*", pickImageIntent.type)
//
//        //        Activityx.safeStart(InstrumentationRegistry.getContext(), pickImageIntent);
//        //        try {
//        //            Thread.sleep(5000);
//        //        } catch (InterruptedException e) {
//        //            e.printStackTrace();
//        //        }
//    }

    @Test
    @Throws(IOException::class)
    fun testCreateCropImageIntent() {
        val context = InstrumentationRegistry.getContext()
        val sourceFile = File(context.getAppExternalFilesDir(), "testCreateCropImageIntent.jpg")
        val saveFile = File(context.getAppExternalFilesDir(), "testCreateCropImageIntent_temp.jpg")
        try {
            saveFile.createNewFileOrThrow()

            val inputStream = context.resources.openRawResource(me.panpf.androidxkt.test.R.drawable.rect)
            try {
                val bytes = inputStream.readBytes()
                sourceFile.writeBytes(bytes)
            } finally {
                inputStream.closeQuietly()
            }

            val sourceFileUri = context.getShareFileUri(sourceFile)
            val saveFileUri = context.getShareFileUri(saveFile)

            val cropImageIntent1 = sourceFileUri.createCropImageIntent(50, 100, saveFileUri)
            Assert.assertEquals("com.android.camera.action.CROP", cropImageIntent1.action)
            Assert.assertEquals(sourceFileUri.toString(), cropImageIntent1.data.requireNotNull().toString())
            Assert.assertEquals("image/*", cropImageIntent1.type)
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("aspectX", 0).toLong())
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("aspectY", 0).toLong())
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("outputX", 0).toLong())
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("outputY", 0).toLong())
            Assert.assertEquals(saveFileUri.toString(), cropImageIntent1.getParcelableExtra<Parcelable>("output").toString())
            Assert.assertTrue(cropImageIntent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val cropImageIntent2 = sourceFileUri.createCropImageIntent(50, 100, null)
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("return-data", false))
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("scale", false))

            //            Activityx.safeStart(InstrumentationRegistry.getContext(), cropImageIntent1);
            //            try {
            //                Thread.sleep(5000);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
        } finally {
            sourceFile.deleteRecursively()
            saveFile.deleteRecursively()
        }
    }

    @Test
    fun testCreateSendTextIntent() {
        val intent1 = "好好学习吧，天天向上".createSendTextIntent("座右铭")
        Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
        Assert.assertEquals("text/plain", intent1.type)
        Assert.assertEquals("好好学习吧，天天向上", intent1.getStringExtra(Intent.EXTRA_TEXT))
        Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT))

        val intent2 = "好好学习吧，天天向上".createSendTextIntent()
        Assert.assertEquals(Intent.ACTION_SEND, intent2.action)
        Assert.assertEquals("text/plain", intent2.type)
        Assert.assertEquals("好好学习吧，天天向上", intent2.getStringExtra(Intent.EXTRA_TEXT))
        Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT))

        //        Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
    }

    @Test
    @Throws(IOException::class)
    fun testCreateSendTextFileIntent() {
        val context = InstrumentationRegistry.getContext()
        val textFile = File(context.getAppExternalFilesDir(), "testCreateSendTextFileIntent.txt")
        try {
            textFile.createNewFileOrThrow()
            textFile.writeText("testCreateSendTextFileIntent")

            val intent1 = context.getShareFileUri(textFile).createSendTextFileIntent("座右铭")
            Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent1.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent1.type)
            Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT))
            Assert.assertTrue(intent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent2 = context.getShareFileUri(textFile).createSendTextFileIntent()
            Assert.assertEquals(Intent.ACTION_SEND, intent2.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent2.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent2.type)
            Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT))
            Assert.assertTrue(intent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent3 = context.createSendTextFileIntent(textFile, "座右铭")
            Assert.assertEquals(Intent.ACTION_SEND, intent3.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent3.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent3.type)
            Assert.assertEquals("座右铭", intent3.getStringExtra(Intent.EXTRA_SUBJECT))
            Assert.assertTrue(intent3.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent4 = context.createSendTextFileIntent(textFile)
            Assert.assertEquals(Intent.ACTION_SEND, intent4.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent4.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent4.type)
            Assert.assertNull(intent4.getStringExtra(Intent.EXTRA_SUBJECT))
            Assert.assertTrue(intent4.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            //            Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
            //            try {
            //                Thread.sleep(5000);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
        } finally {
            textFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testCreateSendImageFileIntent() {
        val context = InstrumentationRegistry.getContext()
        val imageFile = File(context.getAppExternalFilesDir(), "testCreateSendImageFileIntent.jpg")
        try {
            imageFile.createNewFileOrThrow()
            val inputStream = context.resources.openRawResource(me.panpf.androidxkt.test.R.drawable.rect)
            try {
                val bytes = inputStream.readBytes()
                imageFile.writeBytes(bytes)
            } finally {
                inputStream.closeQuietly()
            }

            val intent1 = context.getShareFileUri(imageFile).createSendImageFileIntent("bmp")
            Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
            Assert.assertEquals(context.getShareFileUri(imageFile).toString(),
                    intent1.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("image/bmp", intent1.type)
            Assert.assertTrue(intent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent2 = context.getShareFileUri(imageFile).createSendImageFileIntent()
            Assert.assertEquals(Intent.ACTION_SEND, intent2.action)
            Assert.assertEquals(context.getShareFileUri(imageFile).toString(),
                    intent2.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("image/*", intent2.type)
            Assert.assertTrue(intent2.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent3 = context.createSendImageFileIntent(imageFile, "bmp")
            Assert.assertEquals(Intent.ACTION_SEND, intent3.action)
            Assert.assertEquals(context.getShareFileUri(imageFile).toString(),
                    intent3.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("image/bmp", intent3.type)
            Assert.assertTrue(intent3.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent4 = context.createSendImageFileIntent(imageFile)
            Assert.assertEquals(Intent.ACTION_SEND, intent4.action)
            Assert.assertEquals(context.getShareFileUri(imageFile).toString(),
                    intent4.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("image/*", intent4.type)
            Assert.assertTrue(intent4.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            //            Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
            //            try {
            //                Thread.sleep(5000);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
        } finally {
            imageFile.deleteRecursively()
        }
    }

    @Test
    @Throws(IOException::class)
    fun testCreateSendFileIntent() {
        val context = InstrumentationRegistry.getContext()
        val textFile = File(context.getAppExternalFilesDir(), "testCreateSendFileIntent.txt")
        try {
            textFile.createNewFileOrThrow()
            textFile.writeText("testCreateSendFileIntent")

            val intent1 = context.getShareFileUri(textFile).createSendFileIntent("text/plain")
            Assert.assertEquals(Intent.ACTION_SEND, intent1.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent1.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent1.type)
            Assert.assertTrue(intent1.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            val intent3 = context.createSendFileIntent(textFile, "text/plain")
            Assert.assertEquals(Intent.ACTION_SEND, intent3.action)
            Assert.assertEquals(context.getShareFileUri(textFile).toString(),
                    intent3.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
            Assert.assertEquals("text/plain", intent3.type)
            Assert.assertTrue(intent3.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0)

            //            Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
            //            try {
            //                Thread.sleep(5000);
            //            } catch (InterruptedException e) {
            //                e.printStackTrace();
            //            }
        } finally {
            textFile.deleteRecursively()
        }
    }
}
