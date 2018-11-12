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

@file:Suppress("DEPRECATION")

package me.panpf.androidxkt.test.app

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.ViewModelStoreOwner
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.FragmentActivity
import android.view.View
import me.panpf.androidxkt.app.*
import me.panpf.androidxkt.waitRunInUIResult
import me.panpf.javax.lang.Throwablex
import me.panpf.javaxkt.util.requireNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityxTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(TestActivity::class.java)

    @get:Rule
    val fragmentActivityTestRule = ActivityTestRule(TestFragmentActivity::class.java)

    @Test
    fun testActivityDestroyed() {
        val activity = activityTestRule.activity

        Assert.assertFalse(activity.isDestroyedCompat())

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

        Assert.assertFalse(activity.isDestroyedCompat())

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

        Assert.assertFalse(activity.isDestroyedCompat())

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

        Assert.assertFalse(activity.isDestroyedCompat())

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

        var result = waitRunInUIResult { activity.convertToTranslucentCompat() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result)
        } else {
            Assert.assertFalse(result)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        result = waitRunInUIResult { activity.convertFromTranslucentCompat() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Assert.assertTrue(result)
        } else {
            Assert.assertFalse(result)
        }

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    @Test
    fun testGetImplWithParent() {
        val activity = activityTestRule.activity
        Assert.assertNotNull(activity.getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNull(activity.getImplWithParent(ViewModelStoreOwner::class.java))

        val activity2 = fragmentActivityTestRule.activity
        Assert.assertNull(activity2.getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNotNull(activity2.getImplWithParent(ViewModelStoreOwner::class.java))
    }

    @Test
    fun testAppContext() {
        val activity = activityTestRule.activity
        Assert.assertTrue(activity.appContext() is Application)
        Assert.assertFalse(activity.appContext() is Activity)
    }

    @Test
    fun testCanStart() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertFalse(context.canStartActivity(Intent(context, ActivityxTest::class.java)))
        Assert.assertTrue(context.canStartActivity(Intent(context, TestActivity::class.java)))
    }

    @Test
    fun testStartActivityByIntentActivity() {
        val activity = activityTestRule.activity

        try {
            activity.startActivity(Intent(activity, TestActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.appContext().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.startActivity(TestActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.startActivity(TestActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.appContext().startActivity(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testStartActivityByIntentSupportFragment() {
        val activity = fragmentActivityTestRule.activity

        try {
            activity.getFragment().startActivity(Intent(activity, TestActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.getFragment().startActivity(TestActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(TestActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Test
    fun testStartActivityByIntentOriginFragment() {
        val activity = activityTestRule.activity

        try {
            activity.getFragment().startActivity(Intent(activity, TestActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.getFragment().startActivity(TestActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(TestActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getFragment().startActivity(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Test
    fun testStartActivityByIntentView() {
        val activity = activityTestRule.activity

        try {
            activity.getView().startActivity(Intent(activity, TestActivity::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getView().startActivity(Intent(activity, ActivityxTest::class.java))
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            activity.getView().startActivity(TestActivity::class.java, null)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getView().startActivity(TestActivity::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail(Throwablex.stackTraceToString(e))
        }

        try {
            activity.getView().startActivity(NoRegisterTestActivity::class.java)
            Assert.fail()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testSafeStartActivityByIntentActivity() {
        val activity = activityTestRule.activity

        Assert.assertTrue(activity.safeStartActivity(Intent(activity, TestActivity::class.java)))
        Assert.assertFalse(activity.appContext().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.safeStartActivity(TestActivity::class.java, null))
        Assert.assertTrue(activity.safeStartActivity(TestActivity::class.java))
        Assert.assertFalse(activity.appContext().safeStartActivity(NoRegisterTestActivity::class.java))
    }

    @Test
    fun testSafeStartActivityByIntentSupportFragment() {
        val activity = fragmentActivityTestRule.activity

        Assert.assertTrue(activity.getFragment().safeStartActivity(Intent(activity, TestActivity::class.java)))
        Assert.assertFalse(activity.getFragment().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.getFragment().safeStartActivity(TestActivity::class.java, null))
        Assert.assertTrue(activity.getFragment().safeStartActivity(TestActivity::class.java))
        Assert.assertFalse(activity.getFragment().safeStartActivity(NoRegisterTestActivity::class.java))
    }

    @Test
    fun testSafeStartActivityByIntentOriginFragment() {
        val activity = activityTestRule.activity

        Assert.assertTrue(activity.getFragment().safeStartActivity(Intent(activity, TestActivity::class.java)))
        Assert.assertFalse(activity.getFragment().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.getFragment().safeStartActivity(TestActivity::class.java, null))
        Assert.assertTrue(activity.getFragment().safeStartActivity(TestActivity::class.java))
        Assert.assertFalse(activity.getFragment().safeStartActivity(NoRegisterTestActivity::class.java))
    }

    @Test
    fun testSafeStartActivityByIntentView() {
        val activity = activityTestRule.activity

        Assert.assertTrue(activity.getView().safeStartActivity(Intent(activity, TestActivity::class.java)))
        Assert.assertFalse(activity.getView().safeStartActivity(Intent(activity, ActivityxTest::class.java)))
        Assert.assertTrue(activity.getView().safeStartActivity(TestActivity::class.java, null))
        Assert.assertTrue(activity.getView().safeStartActivity(TestActivity::class.java))
        Assert.assertFalse(activity.getView().safeStartActivity(NoRegisterTestActivity::class.java))
    }

    interface ImplTestInterface

    class TestActivity : Activity(), ImplTestInterface {
        var finished: Boolean = false
        var finishedActivity: Boolean = false
        var finishedActivityFromChild: Boolean = false
        var destoryed: Boolean = false

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            fragmentManager.beginTransaction().replace(android.R.id.content, android.app.Fragment()).commit()
        }

        override fun finish() {
            super.finish()
            finished = true
        }

        override fun finishActivity(requestCode: Int) {
            super.finishActivity(requestCode)
            finishedActivity = true
        }

        override fun finishActivityFromChild(child: Activity, requestCode: Int) {
            super.finishActivityFromChild(child, requestCode)
            finishedActivityFromChild = true
        }

        override fun onDestroy() {
            super.onDestroy()
            destoryed = true
        }

        fun getFragment(): android.app.Fragment {
            return fragmentManager.findFragmentById(android.R.id.content)
        }

        fun getView(): View {
            return findViewById(android.R.id.content)
        }
    }

    class TestFragmentActivity : FragmentActivity(){
        var finished: Boolean = false
        var finishedActivity: Boolean = false
        var finishedActivityFromChild: Boolean = false
        var destoryed: Boolean = false

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportFragmentManager.beginTransaction().replace(android.R.id.content, android.support.v4.app.Fragment()).commit()
        }

        override fun finish() {
            super.finish()
            finished = true
        }

        override fun finishActivity(requestCode: Int) {
            super.finishActivity(requestCode)
            finishedActivity = true
        }

        override fun finishActivityFromChild(child: Activity, requestCode: Int) {
            super.finishActivityFromChild(child, requestCode)
            finishedActivityFromChild = true
        }

        override fun onDestroy() {
            super.onDestroy()
            destoryed = true
        }

        fun getFragment(): android.support.v4.app.Fragment {
            return supportFragmentManager.findFragmentById(android.R.id.content).requireNotNull()
        }

        fun getView(): View {
            return findViewById(android.R.id.content)
        }
    }

    class NoRegisterTestActivity : Activity(), ImplTestInterface {
        var finished: Boolean = false
        var finishedActivity: Boolean = false
        var finishedActivityFromChild: Boolean = false
        var destoryed: Boolean = false

        override fun finish() {
            super.finish()
            finished = true
        }

        override fun finishActivity(requestCode: Int) {
            super.finishActivity(requestCode)
            finishedActivity = true
        }

        override fun finishActivityFromChild(child: Activity, requestCode: Int) {
            super.finishActivityFromChild(child, requestCode)
            finishedActivityFromChild = true
        }

        override fun onDestroy() {
            super.onDestroy()
            destoryed = true
        }
    }
}
