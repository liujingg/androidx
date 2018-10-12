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

import android.arch.lifecycle.ViewModelStoreOwner
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.convertFromTranslucentCompat
import me.panpf.androidxkt.app.convertToTranslucentCompat
import me.panpf.androidxkt.app.getImplWithParent
import me.panpf.androidxkt.app.isDestroyedCompat
import me.panpf.androidxkt.waitRunInUI
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityxTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(ActivityxTestActivity::class.java)

    @get:Rule
    val fragmentActivityTestRule = ActivityTestRule(ActivityxTestFragmentActivity::class.java)

    @Test
    fun testActivityDestroyed() {
        val activity = activityTestRule.activity

        activityTestRule.finishActivity()

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertTrue(activity.isDestroyedCompat())
    }

    @Test
    fun testActivityNormal() {
        val activity = activityTestRule.activity

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertFalse(activity.isDestroyedCompat())
    }

    @Test
    fun testFragmentActivityDestroyed() {
        val activity = fragmentActivityTestRule.activity

        fragmentActivityTestRule.finishActivity()

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertTrue(activity.isDestroyedCompat())
    }

    @Test
    fun testFragmentActivityNormal() {
        val activity = fragmentActivityTestRule.activity

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertFalse(activity.isDestroyedCompat())
    }

    @Test
    fun testConvertTranslucent() {
        val activity = activityTestRule.activity

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        waitRunInUI { activity.convertToTranslucentCompat() }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        waitRunInUI { activity.convertFromTranslucentCompat() }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    @Test
    fun testImpl() {
        val activity = activityTestRule.activity
        Assert.assertNotNull(activity.getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNull(activity.getImplWithParent(ViewModelStoreOwner::class.java))

        val activity2 = fragmentActivityTestRule.activity
        Assert.assertNull(activity2.getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNotNull(activity2.getImplWithParent(ViewModelStoreOwner::class.java))
    }
}
