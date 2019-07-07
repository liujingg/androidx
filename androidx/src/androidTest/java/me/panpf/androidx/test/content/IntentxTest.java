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

package me.panpf.androidx.test.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.core.content.FileProvider;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.panpf.androidx.BuildConfig;
import me.panpf.androidx.content.Intentx;
import me.panpf.androidx.content.pm.PackageInfox;
import me.panpf.androidx.content.pm.Packagex;
import me.panpf.androidx.os.storage.Storagex;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.io.Filex;
import me.panpf.javax.io.Streamx;
import me.panpf.javax.io.UnableCreateDirException;
import me.panpf.javax.io.UnableCreateFileException;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Predicate;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class IntentxTest {

    @Test
    public void testGetShareFileUri() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File file = new File(Storagex.getAppExternalFilesDir(context), "testGetShareFileUri.txt");
        try {
            Filex.writeText(file, "testGetShareFileUri");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String authority = BuildConfig.APPLICATION_ID + ".file.provider";
                Uri uri = Intentx.getShareFileUri(context, file, authority);
                Assert.assertEquals("content://" + authority + "/files/testGetShareFileUri.txt", uri.toString());

                try {
                    Intentx.getShareFileUri(context, file, BuildConfig.APPLICATION_ID + ".file.provider.test");
                    Assert.fail();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                Uri uri = Intentx.getShareFileUri(context, file, "");
                Assert.assertEquals(Uri.fromFile(file).toString(), uri.toString());
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri uri = Intentx.getShareFileUri(context, file);
                ProviderInfo fileProviderInfo = PackageInfox.findSelfProviderInfoByName(context, FileProvider.class.getName());
                Assert.assertEquals("content://" + (fileProviderInfo != null ? fileProviderInfo.authority : "") + "/files/testGetShareFileUri.txt", uri.toString());
            } else {
                Uri uri = Intentx.getShareFileUri(context, file);
                Assert.assertEquals(Uri.fromFile(file).toString(), uri.toString());
            }
        } finally {
            Filex.deleteRecursively(file);
        }
    }

    @Test
    public void testCreateRecordingIntent() {
        Intent recordingIntent = Intentx.createRecordingIntent();
        Assert.assertEquals(Intent.ACTION_GET_CONTENT, recordingIntent.getAction());
        Assert.assertEquals("audio/amr", recordingIntent.getType());
    }

    @Test
    public void testCreateLaunchDialingIntent() {
        Intent recordingIntent = Intentx.createLaunchDialingIntent("18801287688");
        Assert.assertEquals(Intent.ACTION_DIAL, recordingIntent.getAction());
        Assert.assertEquals("tel:18801287688", Premisex.requireNotNull(recordingIntent.getData()).toString());

//        Activityx.safeStart(InstrumentationRegistry.getContext(), recordingIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateCallPhoneIntent() {
        Intent callPhoneIntent = Intentx.createCallPhoneIntent("18801287688");
        Assert.assertEquals(Intent.ACTION_CALL, callPhoneIntent.getAction());
        Assert.assertEquals("tel:18801287688", Premisex.requireNotNull(callPhoneIntent.getData()).toString());

//        Activityx.safeStart(InstrumentationRegistry.getContext(), callPhoneIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateLaunchSendSmsIntent() {
        Intent sendSmsIntent = Intentx.createLaunchSendSmsIntent("18801287688", "好好学习吧，天天向上");
        Assert.assertEquals(Intent.ACTION_SENDTO, sendSmsIntent.getAction());
        Assert.assertEquals("smsto:18801287688", Premisex.requireNotNull(sendSmsIntent.getData()).toString());
        Assert.assertEquals("好好学习吧，天天向上", sendSmsIntent.getStringExtra("sms_body"));

//        Activityx.safeStart(InstrumentationRegistry.getContext(), sendSmsIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateLaunchWebBrowserIntent() {
        Intent webIntent = Intentx.createLaunchWebBrowserIntent("https://github.com");
        Assert.assertEquals(Intent.ACTION_VIEW, webIntent.getAction());
        Assert.assertEquals("https://github.com", Premisex.requireNotNull(webIntent.getData()).toString());

//        Activityx.safeStart(InstrumentationRegistry.getContext(), webIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateScanFileBroadcastIntent() {
        Context context = InstrumentationRegistry.getContext();
        File file = new File(Storagex.getAppExternalFilesDir(context), "testCreateScanFileBroadcastIntent.jpg");
//        try {
//            InputStream inputStream = context.getResources().openRawResource(me.panpf.androidx.test.R.drawable.rect);
//            try {
//                byte[] bytes = Streamx.readBytes(inputStream);
//                Filex.writeBytes(file, bytes);
//            } finally {
//                Streamx.closeQuietly(inputStream);
//            }

        Intent scanFileIntent1 = Intentx.createScanFileBroadcastIntent(Intentx.getShareFileUri(context, file));
        Assert.assertEquals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, scanFileIntent1.getAction());
        Assert.assertEquals(Intentx.getShareFileUri(context, file).toString(),
                Premisex.requireNotNull(scanFileIntent1.getData()).toString());
        Assert.assertTrue((scanFileIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

        Intent scanFileIntent2 = Intentx.createScanFileBroadcastIntent(context, file);
        Assert.assertEquals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, scanFileIntent2.getAction());
        Assert.assertEquals(Intentx.getShareFileUri(context, file).toString(),
                Premisex.requireNotNull(scanFileIntent2.getData()).toString());
        Assert.assertTrue((scanFileIntent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);
//
//            context.sendBroadcast(scanFileIntent);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } finally {
//            Filex.deleteRecursively(file);
//        }
    }

    @Test
    public void testCreateInstallAppIntent() throws PackageManager.NameNotFoundException {
        Context context = InstrumentationRegistry.getContext();

        File file = Packagex.getPackageApkFile(context, context.getPackageName());

        Intent installAppIntent1 = Intentx.createInstallAppIntent(Intentx.getShareFileUri(context, file));
        Assert.assertEquals(Intent.ACTION_VIEW, installAppIntent1.getAction());
        Assert.assertEquals(Intent.CATEGORY_DEFAULT, Collectionx.find(installAppIntent1.getCategories(), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String s) {
                return Stringx.equals(s, Intent.CATEGORY_DEFAULT);
            }
        }));
        Assert.assertEquals(Intentx.getShareFileUri(context, file).toString(),
                Premisex.requireNotNull(installAppIntent1.getData()).toString());
        Assert.assertEquals("application/vnd.android.package-archive", installAppIntent1.getType());
        Assert.assertTrue((installAppIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

        Intent installAppIntent2 = Intentx.createInstallAppIntent(context, file);
        Assert.assertEquals(Intent.ACTION_VIEW, installAppIntent2.getAction());
        Assert.assertNotNull(Collectionx.find(installAppIntent2.getCategories(), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String s) {
                return Stringx.equals(s, Intent.CATEGORY_DEFAULT);
            }
        }));
        Assert.assertEquals(Intentx.getShareFileUri(context, file).toString(),
                Premisex.requireNotNull(installAppIntent2.getData()).toString());
        Assert.assertEquals("application/vnd.android.package-archive", installAppIntent2.getType());
        Assert.assertTrue((installAppIntent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

//        Activityx.safeStart(context, installAppIntent2);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateUninstallAppIntent() {
        Context context = InstrumentationRegistry.getContext();

        Intent uninstallAppIntent = Intentx.createUninstallAppIntent(context.getPackageName());
        Assert.assertEquals(Intent.ACTION_DELETE, uninstallAppIntent.getAction());
        Assert.assertEquals(Uri.fromParts("package", context.getPackageName(), null).toString(),
                Premisex.requireNotNull(uninstallAppIntent.getData()).toString());

//        Activityx.safeStart(context, uninstallAppIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateLaunchAppIntent() {
        Context context = InstrumentationRegistry.getContext();

        Intent webIntent = Intentx.createLaunchWebBrowserIntent("https://github.com");
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(webIntent, 0);
        ResolveInfo resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos));
        String webBrowserPackageName = resolveInfo.activityInfo.packageName;

        Intent launchAppIntent = Premisex.requireNotNull(Intentx.createLaunchAppIntent(context, webBrowserPackageName));
        Assert.assertEquals(Intent.ACTION_MAIN, launchAppIntent.getAction());
        Assert.assertNotNull(Collectionx.find(launchAppIntent.getCategories(), new Predicate<String>() {
            @Override
            public boolean accept(@NotNull String s) {
                return Stringx.equals(s, Intent.CATEGORY_LAUNCHER);
            }
        }));
        Assert.assertEquals(webBrowserPackageName, launchAppIntent.getPackage());

//        Activityx.safeStart(context, launchAppIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateAppDetailInSystemIntent() {
        Context context = InstrumentationRegistry.getContext();

        Intent webIntent = Intentx.createLaunchWebBrowserIntent("https://github.com");
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(webIntent, 0);
        ResolveInfo resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos));
        String webBrowserPackageName = resolveInfo.activityInfo.packageName;

        Intent appDetailInSystemIntent = Intentx.createAppDetailInSystemIntent(webBrowserPackageName);
        Assert.assertEquals(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, appDetailInSystemIntent.getAction());
        Assert.assertEquals(Uri.fromParts("package", webBrowserPackageName, null).toString(),
                Premisex.requireNotNull(appDetailInSystemIntent.getData()).toString());

//        Activityx.safeStart(context, appDetailInSystemIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateActivityIntentByResolveInfo() {
        Context context = InstrumentationRegistry.getContext();

        Intent sendIntent = Intentx.createSendTextIntent("https://github.com");
        Assert.assertNull(sendIntent.getComponent());

        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(sendIntent, 0);
        ResolveInfo resolveInfo = Premisex.requireNotNull(Collectionx.firstOrNull(resolveInfos));

        Intent finalIntent = Intentx.createActivityIntentByResolveInfo(sendIntent, resolveInfo);
        Assert.assertEquals(sendIntent.getAction(), finalIntent.getAction());
        Assert.assertNotNull(finalIntent.getComponent());

//        Activityx.safeStart(context, finalIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateTakePhotoIntent() throws UnableCreateFileException, UnableCreateDirException {
        Context context = InstrumentationRegistry.getContext();

        File file = new File(Storagex.getAppExternalFilesDir(context), "testCreateTakePhotoIntent_temp.jpg");
        try {
            Filex.createNewFileOrThrow(file);

            Intent takePhotoIntent1 = Intentx.createTakePhotoIntent(Intentx.getShareFileUri(context, file));
            Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, takePhotoIntent1.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, file).toString(), takePhotoIntent1.getParcelableExtra(MediaStore.EXTRA_OUTPUT).toString());
            Assert.assertTrue((takePhotoIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent takePhotoIntent2 = Intentx.createTakePhotoIntent(context, file);
            Assert.assertEquals(MediaStore.ACTION_IMAGE_CAPTURE, takePhotoIntent2.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, file).toString(), takePhotoIntent2.getParcelableExtra(MediaStore.EXTRA_OUTPUT).toString());
            Assert.assertTrue((takePhotoIntent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

//            Activityx.safeStart(context, takePhotoIntent2);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } finally {
            Filex.deleteRecursively(file);
        }
    }

    @Test
    public void testCreatePickImageIntent() {
        Intent pickImageIntent = Intentx.createPickImageIntent();
        Assert.assertEquals(Intent.ACTION_PICK, pickImageIntent.getAction());
        Assert.assertEquals("image/*", pickImageIntent.getType());

//        Activityx.safeStart(InstrumentationRegistry.getContext(), pickImageIntent);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateCropImageIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File sourceFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateCropImageIntent.jpg");
        File saveFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateCropImageIntent_temp.jpg");
        try {
            Filex.createNewFileOrThrow(saveFile);

            InputStream inputStream = context.getResources().openRawResource(me.panpf.androidx.test.R.drawable.rect);
            try {
                byte[] bytes = Streamx.readBytes(inputStream);
                Filex.writeBytes(sourceFile, bytes);
            } finally {
                Streamx.closeQuietly(inputStream);
            }

            Uri sourceFileUri = Intentx.getShareFileUri(context, sourceFile);
            Uri saveFileUri = Intentx.getShareFileUri(context, saveFile);

            Intent cropImageIntent1 = Intentx.createCropImageIntent(sourceFileUri, 50, 100, saveFileUri);
            Assert.assertEquals("com.android.camera.action.CROP", cropImageIntent1.getAction());
            Assert.assertEquals(sourceFileUri.toString(), Premisex.requireNotNull(cropImageIntent1.getData()).toString());
            Assert.assertEquals("image/*", cropImageIntent1.getType());
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("aspectX", 0));
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("aspectY", 0));
            Assert.assertEquals(50, cropImageIntent1.getIntExtra("outputX", 0));
            Assert.assertEquals(100, cropImageIntent1.getIntExtra("outputY", 0));
            Assert.assertEquals(saveFileUri.toString(), cropImageIntent1.getParcelableExtra("output").toString());
            Assert.assertTrue((cropImageIntent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent cropImageIntent2 = Intentx.createCropImageIntent(sourceFileUri, 50, 100, null);
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("return-data", false));
            Assert.assertTrue(cropImageIntent2.getBooleanExtra("scale", false));

//            Activityx.safeStart(InstrumentationRegistry.getContext(), cropImageIntent1);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } finally {
            Filex.deleteRecursively(sourceFile);
            Filex.deleteRecursively(saveFile);
        }
    }

    @Test
    public void testCreateSendTextIntent() {
        Intent intent1 = Intentx.createSendTextIntent("好好学习吧，天天向上", "座右铭");
        Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
        Assert.assertEquals("text/plain", intent1.getType());
        Assert.assertEquals("好好学习吧，天天向上", intent1.getStringExtra(Intent.EXTRA_TEXT));
        Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT));

        Intent intent2 = Intentx.createSendTextIntent("好好学习吧，天天向上");
        Assert.assertEquals(Intent.ACTION_SEND, intent2.getAction());
        Assert.assertEquals("text/plain", intent2.getType());
        Assert.assertEquals("好好学习吧，天天向上", intent2.getStringExtra(Intent.EXTRA_TEXT));
        Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT));

//        Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreateSendTextFileIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File textFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateSendTextFileIntent.txt");
        try {
            Filex.createNewFileOrThrow(textFile);
            Filex.writeText(textFile, "testCreateSendTextFileIntent");

            Intent intent1 = Intentx.createSendTextFileIntent(Intentx.getShareFileUri(context, textFile), "座右铭");
            Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, textFile).toString(),
                    intent1.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent1.getType());
            Assert.assertEquals("座右铭", intent1.getStringExtra(Intent.EXTRA_SUBJECT));
            Assert.assertTrue((intent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent2 = Intentx.createSendTextFileIntent(Intentx.getShareFileUri(context, textFile));
            Assert.assertEquals(Intent.ACTION_SEND, intent2.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, textFile).toString(),
                    intent2.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent2.getType());
            Assert.assertNull(intent2.getStringExtra(Intent.EXTRA_SUBJECT));
            Assert.assertTrue((intent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent3 = Intentx.createSendTextFileIntent(context, textFile, "座右铭");
            Assert.assertEquals(Intent.ACTION_SEND, intent3.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, textFile).toString(),
                    intent3.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent3.getType());
            Assert.assertEquals("座右铭", intent3.getStringExtra(Intent.EXTRA_SUBJECT));
            Assert.assertTrue((intent3.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent4 = Intentx.createSendTextFileIntent(context, textFile);
            Assert.assertEquals(Intent.ACTION_SEND, intent4.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, textFile).toString(),
                    intent4.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent4.getType());
            Assert.assertNull(intent4.getStringExtra(Intent.EXTRA_SUBJECT));
            Assert.assertTrue((intent4.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

//            Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } finally {
            Filex.deleteRecursively(textFile);
        }
    }

    @Test
    public void testCreateSendImageFileIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File imageFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateSendImageFileIntent.jpg");
        try {
            Filex.createNewFileOrThrow(imageFile);
            InputStream inputStream = context.getResources().openRawResource(me.panpf.androidx.test.R.drawable.rect);
            try {
                byte[] bytes = Streamx.readBytes(inputStream);
                Filex.writeBytes(imageFile, bytes);
            } finally {
                Streamx.closeQuietly(inputStream);
            }

            Intent intent1 = Intentx.createSendImageFileIntent(Intentx.getShareFileUri(context, imageFile), "bmp");
            Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, imageFile).toString(),
                    intent1.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("image/bmp", intent1.getType());
            Assert.assertTrue((intent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent2 = Intentx.createSendImageFileIntent(Intentx.getShareFileUri(context, imageFile));
            Assert.assertEquals(Intent.ACTION_SEND, intent2.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, imageFile).toString(),
                    intent2.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("image/*", intent2.getType());
            Assert.assertTrue((intent2.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent3 = Intentx.createSendImageFileIntent(context, imageFile, "bmp");
            Assert.assertEquals(Intent.ACTION_SEND, intent3.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, imageFile).toString(),
                    intent3.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("image/bmp", intent3.getType());
            Assert.assertTrue((intent3.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent4 = Intentx.createSendImageFileIntent(context, imageFile);
            Assert.assertEquals(Intent.ACTION_SEND, intent4.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, imageFile).toString(),
                    intent4.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("image/*", intent4.getType());
            Assert.assertTrue((intent4.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

//            Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } finally {
            Filex.deleteRecursively(imageFile);
        }
    }

    @Test
    public void testCreateSendFileIntent() throws IOException {
        Context context = InstrumentationRegistry.getContext();
        File textFile = new File(Storagex.getAppExternalFilesDir(context), "testCreateSendFileIntent.txt");
        try {
            Filex.createNewFileOrThrow(textFile);
            Filex.writeText(textFile, "testCreateSendFileIntent");

            Intent intent1 = Intentx.createSendFileIntent(Intentx.getShareFileUri(context, textFile), "text/plain");
            Assert.assertEquals(Intent.ACTION_SEND, intent1.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, textFile).toString(),
                    intent1.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent1.getType());
            Assert.assertTrue((intent1.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

            Intent intent3 = Intentx.createSendFileIntent(context, textFile, "text/plain");
            Assert.assertEquals(Intent.ACTION_SEND, intent3.getAction());
            Assert.assertEquals(Intentx.getShareFileUri(context, textFile).toString(),
                    intent3.getParcelableExtra(Intent.EXTRA_STREAM).toString());
            Assert.assertEquals("text/plain", intent3.getType());
            Assert.assertTrue((intent3.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION) != 0);

//            Activityx.safeStart(InstrumentationRegistry.getContext(), intent1);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } finally {
            Filex.deleteRecursively(textFile);
        }
    }
}
