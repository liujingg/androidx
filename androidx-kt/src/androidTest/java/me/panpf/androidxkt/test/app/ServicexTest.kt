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

package me.panpf.androidxkt.test.app

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidx.os.BundleBuilder
import me.panpf.androidxkt.app.*
import me.panpf.androidxkt.test.TestService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ServicexTest {

    @Test
    @Throws(InterruptedException::class)
    fun testIsRunning() {
        val context = InstrumentationRegistry.getContext()

        try {
            context.startService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertFalse(context.isServiceRunning(NoRegisterTestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStart() {
        val context = InstrumentationRegistry.getContext()

        try {
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))

            context.startService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStartExtras() {
        val context = InstrumentationRegistry.getContext()

        try {
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))

            context.startService(TestService::class.java, BundleBuilder().putString("SHARE_KEY", "testStartExtras").build())
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertEquals("testStartExtras", TestService.SHARE_KEY)
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStartIfNoRunning() {
        val context = InstrumentationRegistry.getContext()

        try {
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))

            Assert.assertTrue(context.startServiceIfNoRunning(TestService::class.java))
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertFalse(context.startServiceIfNoRunning(TestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStartIfNoRunningExtras() {
        val context = InstrumentationRegistry.getContext()

        try {
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))

            Assert.assertTrue(context.startServiceIfNoRunning(TestService::class.java, BundleBuilder().putString("SHARE_KEY", "testStartIfNoRunningExtras").build()))
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))
            Assert.assertEquals("testStartIfNoRunningExtras", TestService.SHARE_KEY)

            Assert.assertFalse(context.startServiceIfNoRunning(TestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStop() {
        val context = InstrumentationRegistry.getContext()

        try {
            context.startService(TestService::class.java)
            Thread.sleep(100)
            Assert.assertTrue(context.isServiceRunning(TestService::class.java))

            context.stopService(TestService::class.java)
            Thread.sleep(100)
            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStopIfRunning() {
        val context = InstrumentationRegistry.getContext()

        try {
            context.startService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertTrue(context.isServiceRunning(TestService::class.java))

            Assert.assertTrue(context.stopServiceIfRunning(TestService::class.java))
            Thread.sleep(100)

            Assert.assertFalse(context.stopServiceIfRunning(TestService::class.java))
        } finally {
            context.stopService(TestService::class.java)
            Thread.sleep(100)

            Assert.assertFalse(context.isServiceRunning(TestService::class.java))
        }
    }

    class NoRegisterTestService : Service() {

        override fun onBind(intent: Intent): IBinder? {
            return null
        }
    }
}