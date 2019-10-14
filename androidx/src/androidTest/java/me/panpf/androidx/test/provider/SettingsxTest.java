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

package me.panpf.androidx.test.provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.provider.Settingsx;
import me.panpf.androidx.widget.Toastx;

@RunWith(AndroidJUnit4.class)
public class SettingsxTest {

    @NonNull
    private final ActivityTestRule<RequestPermissionTestActivity> requestPermissionActivityRule = new ActivityTestRule<>(RequestPermissionTestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getRequestPermissionActivityRule() {
        return this.requestPermissionActivityRule;
    }

    @NonNull
    private final ActivityTestRule<RequestNotificationPolicyTestActivity> requestNotificationPolicyActivityRule = new ActivityTestRule<>(RequestNotificationPolicyTestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getRequestNotificationPolicyActivityRule() {
        return this.requestNotificationPolicyActivityRule;
    }

    @Test
    public void testScreenBrightnessMode() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        boolean isAutomatic = Settingsx.isScreenBrightnessModeAutomatic(context);
        try {
            boolean newAutomaticValue = !isAutomatic;
            Assert.assertTrue(Settingsx.setScreenBrightnessModeAutomatic(context, newAutomaticValue));
            boolean newAutomaticValueFromSettings = Settingsx.isScreenBrightnessModeAutomatic(context);
            Assert.assertEquals(newAutomaticValue, newAutomaticValueFromSettings);
        } finally {
            Settingsx.setScreenBrightnessModeAutomatic(context, isAutomatic);
        }
    }

    @Test
    public void testScreenBrightness() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        int screenBrightness = Settingsx.getScreenBrightness(context);
        try {
            int newScreenBrightnessValue = 255 - screenBrightness;
            Assert.assertTrue(Settingsx.setScreenBrightness(context, newScreenBrightnessValue));
            int newScreenBrightnessValueFromSettings = Settingsx.getScreenBrightness(context);
            Assert.assertEquals(newScreenBrightnessValue, newScreenBrightnessValueFromSettings);
        } finally {
            Settingsx.setScreenBrightness(context, screenBrightness);
        }
    }

    @Test
    public void testScreenOffTimeout() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        int screenOffTimeout = Settingsx.getScreenOffTimeout(context);
        try {
            int newScreenOffTimeoutValue = screenOffTimeout + 100;
            Assert.assertTrue(Settingsx.setScreenOffTimeout(context, newScreenOffTimeoutValue));
            int newScreenOffTimeoutValueFromSettings = Settingsx.getScreenOffTimeout(context);
            Assert.assertEquals(newScreenOffTimeoutValue, newScreenOffTimeoutValueFromSettings);
        } finally {
            Settingsx.setScreenOffTimeout(context, screenOffTimeout);
        }
    }

    @Test
    public void testAirplaneModeOn() {
        if (Androidx.isAtLeast17()) return;

        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        boolean isAirplaneModeOn = Settingsx.isAirplaneModeOn(context);
        try {
            boolean newAirplaneModeOnValue = !isAirplaneModeOn;
            Assert.assertTrue(Settingsx.setAirplaneModeOn(context, newAirplaneModeOnValue));
            boolean newAirplaneModeOnValueFromSettings = Settingsx.isAirplaneModeOn(context);
            Assert.assertEquals(newAirplaneModeOnValue, newAirplaneModeOnValueFromSettings);
        } finally {
            Settingsx.setAirplaneModeOn(context, isAirplaneModeOn);
        }
    }

    @Test
    public void testBluetoothOn() {
        if (!Settingsx.haveBluetooth()) return;

        boolean isBluetoothOn = Settingsx.isBluetoothOn();
        try {
            boolean newBluetoothOnValue = !isBluetoothOn;
            Assert.assertTrue(Settingsx.setBluetoothOn(newBluetoothOnValue));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean newBluetoothOnValueFromSettings = Settingsx.isBluetoothOn();
            Assert.assertEquals(newBluetoothOnValue, newBluetoothOnValueFromSettings);
        } finally {
            Settingsx.setBluetoothOn(isBluetoothOn);
        }
    }

    @Test
    public void testMediaVolume() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.canWrite(context)) {
            RequestPermissionTestActivity activity = requestPermissionActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.canWrite(context)) {
                Assert.fail("No write settings permission");
            }
        }

        int mediaVolume = Settingsx.getMediaVolume(context);
        try {
            int newMediaVolumeValue = 15 - mediaVolume;
            Assert.assertTrue(Settingsx.setMediaVolume(context, newMediaVolumeValue));
            int newMediaVolumeValueFromSettings = Settingsx.getMediaVolume(context);
            Assert.assertEquals(newMediaVolumeValue, newMediaVolumeValueFromSettings);
        } finally {
            Settingsx.setMediaVolume(context, mediaVolume);
        }
    }

    @Test
    public void testRingVolume() {
        Context context = InstrumentationRegistry.getContext();
        if (!Settingsx.isNotificationPolicyAccessGranted(context)) {
            RequestNotificationPolicyTestActivity activity = requestNotificationPolicyActivityRule.getActivity();
            try {
                activity.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!Settingsx.isNotificationPolicyAccessGranted(context)) {
                Assert.fail("No NotificationPolicy access permission");
            }
        }

        int mediaVolume = Settingsx.getRingVolume(context);
        try {
            int newRingVolumeValue = 7 - mediaVolume;
            Assert.assertTrue(Settingsx.setRingVolume(context, newRingVolumeValue));
            int newRingVolumeValueFromSettings = Settingsx.getRingVolume(context);
            Assert.assertEquals(newRingVolumeValue, newRingVolumeValueFromSettings);
        } finally {
            Settingsx.setRingVolume(context, mediaVolume);
        }
    }

    public static class RequestPermissionTestActivity extends Activity {

        private CountDownLatch countDownLatch = new CountDownLatch(1);

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (!Settingsx.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 101);

                Toastx.showLong(this, "请允许修改系统设置并关闭此页面");
                Androidx.getMainHandler().postDelayed(new FinishTask(new WeakReference<Activity>(this)), 10 * 1000);
            } else {
                finish();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            finish();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            countDownLatch.countDown();
        }

        private static class FinishTask implements Runnable {
            private WeakReference<Activity> activityWeakReference;

            FinishTask(WeakReference<Activity> activityWeakReference) {
                this.activityWeakReference = activityWeakReference;
            }

            @Override
            public void run() {
                Activity activity = activityWeakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
    }

    public static class RequestNotificationPolicyTestActivity extends Activity {

        private CountDownLatch countDownLatch = new CountDownLatch(1);

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (!Settingsx.isNotificationPolicyAccessGranted(this)) {
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivityForResult(intent, 1);

                Toastx.showLong(this, "请允许修改请勿打扰状态并关闭此页面");
                Androidx.getMainHandler().postDelayed(new FinishTask(new WeakReference<Activity>(this)), 10 * 1000);
            } else {
                finish();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            finish();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            countDownLatch.countDown();
        }

        private static class FinishTask implements Runnable {
            private WeakReference<Activity> activityWeakReference;

            FinishTask(WeakReference<Activity> activityWeakReference) {
                this.activityWeakReference = activityWeakReference;
            }

            @Override
            public void run() {
                Activity activity = activityWeakReference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
    }
}
