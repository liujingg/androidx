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

package me.panpf.androidxkt.test

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidx.Androidx
import me.panpf.androidx.util.NullableResultRunnable
import me.panpf.androidx.util.ResultRunnable
import me.panpf.androidxkt.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class AndroidxText {

    @Test
    fun testRunInUI() {
        val results = arrayOfNulls<String>(1)
        val countDownLatch = CountDownLatch(1)
        runInUI {
            results[0] = if (Androidx.isMainThread()) "MainThread1" else "NoMainThread1"
            countDownLatch.countDown()
        }
        try {
            countDownLatch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Assert.assertEquals(results[0], "MainThread1")
        val countDownLatch2 = CountDownLatch(1)
        runInUI(Runnable {
            results[0] = if (Androidx.isMainThread()) "MainThread1-" else "NoMainThread1-"
            countDownLatch2.countDown()
        })
        try {
            countDownLatch2.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Assert.assertEquals(results[0], "MainThread1-")

        waitRunInUI(Runnable { results[0] = if (Androidx.isMainThread()) "MainThread2-" else "NoMainThread2-" })
        Assert.assertEquals(results[0], "MainThread2-")

        waitRunInUI { results[0] = if (Androidx.isMainThread()) "MainThread2" else "NoMainThread2" }
        Assert.assertEquals(results[0], "MainThread2")

        results[0] = waitRunInUIResult(ResultRunnable { if (Androidx.isMainThread()) "MainThread3-" else "NoMainThread3-" })
        Assert.assertEquals(results[0], "MainThread3-")

        results[0] = waitRunInUIResult { if (Androidx.isMainThread()) "MainThread3" else "NoMainThread3" }
        Assert.assertEquals(results[0], "MainThread3")

        results[0] = waitRunInUINullableResult(NullableResultRunnable { if (Androidx.isMainThread()) "MainThread4-" else null })
        Assert.assertEquals(results[0], "MainThread4-")

        results[0] = waitRunInUINullableResult { if (Androidx.isMainThread()) "MainThread4" else null }
        Assert.assertEquals(results[0], "MainThread4")
    }

    @Test
    fun testIsMainProcess() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertTrue(context.isMainProcess())
    }

    @Test
    fun testInProcessName() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertNotNull(context.getInProcessName())
        Assert.assertEquals(context.getInProcessNameSuffix(), "")
    }
}
