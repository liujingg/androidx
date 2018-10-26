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

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.isDestroyedCompat
import me.panpf.androidxkt.test.app.fragment.FragmentxTestActivity
import me.panpf.androidxkt.test.app.fragment.FragmentxTestFragmentActivity

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentxTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(FragmentxTestActivity::class.java)

    @get:Rule
    val fragmentActivityTestRule = ActivityTestRule(FragmentxTestFragmentActivity::class.java)

    @Test
    fun testActivityDestroyed() {
        val fragment = activityTestRule.activity.fragmentManager.findFragmentById(android.R.id.content)

        activityTestRule.finishActivity()

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertTrue(fragment.isDestroyedCompat())
    }

    @Test
    fun testActivityNormal() {
        val fragment = activityTestRule.activity.fragmentManager.findFragmentById(android.R.id.content)

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertFalse(fragment.isDestroyedCompat())
    }

    @Test
    fun testFragmentActivityDestroyed() {
        val fragment = fragmentActivityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content)

        fragmentActivityTestRule.finishActivity()

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertTrue(fragment?.isDestroyedCompat() ?: false)
    }

    @Test
    fun testFragmentActivityNormal() {
        val fragment = fragmentActivityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content)

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertFalse(fragment?.isDestroyedCompat() ?: false)
    }
}
