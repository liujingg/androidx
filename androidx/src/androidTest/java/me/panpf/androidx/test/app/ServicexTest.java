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

package me.panpf.androidx.test.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.Nullable;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import me.panpf.androidx.app.Servicex;
import me.panpf.androidx.os.BundleBuilder;
import me.panpf.androidx.test.TestService;

@RunWith(AndroidJUnit4.class)
public final class ServicexTest {

    @Test
    public void testIsRunning() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Servicex.start(context, TestService.class);
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertFalse(Servicex.isRunning(context, NoRegisterTestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStart() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));

            Servicex.start(context, TestService.class);
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStartExtras() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));

            Servicex.start(context, TestService.class, new BundleBuilder().putString("SHARE_KEY", "testStartExtras").build());
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertEquals("testStartExtras", TestService.SHARE_KEY);
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStartIfNoRunning() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));

            Assert.assertTrue(Servicex.startIfNoRunning(context, TestService.class));
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertFalse(Servicex.startIfNoRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStartIfNoRunningExtras() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));

            Assert.assertTrue(Servicex.startIfNoRunning(context, TestService.class, new BundleBuilder().putString("SHARE_KEY", "testStartIfNoRunningExtras").build()));
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));
            Assert.assertEquals("testStartIfNoRunningExtras", TestService.SHARE_KEY);

            Assert.assertFalse(Servicex.startIfNoRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStop() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Servicex.start(context, TestService.class);
            Thread.sleep(100);
            Assert.assertTrue(Servicex.isRunning(context, TestService.class));

            Servicex.stop(context, TestService.class);
            Thread.sleep(100);
            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    @Test
    public void testStopIfRunning() throws InterruptedException {
        Context context = InstrumentationRegistry.getContext();

        try {
            Servicex.start(context, TestService.class);
            Thread.sleep(100);

            Assert.assertTrue(Servicex.isRunning(context, TestService.class));

            Assert.assertTrue(Servicex.stopIfRunning(context, TestService.class));
            Thread.sleep(100);

            Assert.assertFalse(Servicex.stopIfRunning(context, TestService.class));
        } finally {
            Servicex.stop(context, TestService.class);
            Thread.sleep(100);

            Assert.assertFalse(Servicex.isRunning(context, TestService.class));
        }
    }

    public static class NoRegisterTestService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
