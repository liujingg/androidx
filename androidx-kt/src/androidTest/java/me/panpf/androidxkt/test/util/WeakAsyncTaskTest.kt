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

package me.panpf.androidxkt.test.util

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.util.WeakAsyncTask
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeakAsyncTaskTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(WeakAsyncTaskTestActivity::class.java)

    @Test
    fun testError() {
        var result: String

        result = try {
            InnerTestWeakAsyncTask(this)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            "failed"
        }
        Assert.assertEquals(result, "failed")

        result = try {
            TestWeakAsyncTask(this)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            "failed"
        }
        Assert.assertEquals(result, "success")

        result = try {
            TestWeakAsyncTask2(this)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            "failed"
        }
        Assert.assertEquals(result, "success")
    }

    @Test
    fun testDestroyed() {
        val activity = activityTestRule.activity

        activityTestRule.finishActivity()

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertEquals(activity.result, "None")
    }

    @Test
    fun testNormal() {
        val activity = activityTestRule.activity

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertEquals(activity.result, "onPostExecute")
    }

    private class TestWeakAsyncTask(weakAsyncTaskTest: WeakAsyncTaskTest) : WeakAsyncTask<WeakAsyncTaskTest, Int, Int, Int>(weakAsyncTaskTest) {

        override fun doInBackground(page: WeakAsyncTaskTest, params: Array<Int>): Int? {
            return null
        }
    }

    private inner class InnerTestWeakAsyncTask(weakAsyncTaskTest: WeakAsyncTaskTest) : WeakAsyncTask<WeakAsyncTaskTest, Int, Int, Int>(weakAsyncTaskTest) {

        override fun doInBackground(page: WeakAsyncTaskTest, params: Array<Int>): Int? {
            return null
        }
    }
}
