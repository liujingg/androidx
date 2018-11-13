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
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.panpf.androidx.Androidx
import me.panpf.androidx.app.Argsx
import me.panpf.androidx.os.BundleBuilder
import me.panpf.androidxkt.app.*
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.waitRunInUI
import me.panpf.androidxkt.waitRunInUIResult
import me.panpf.javaxkt.util.requireNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentxTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(TestActivity::class.java)

    @get:Rule
    val findUserVisibleChildActivityRule = ActivityTestRule(TestFindUserVisibleChildActivity::class.java)

    @Test
    fun testDestroyed() {
        val originFragment = activityTestRule.activity.getOriginFragment()
        val supportFragment = activityTestRule.activity.getSupportFragment()

        Assert.assertFalse(originFragment.isDestroyedCompat())
        Assert.assertFalse(supportFragment.isDestroyedCompat())

        activityTestRule.finishActivity()
        Thread.sleep(2000)

        Assert.assertTrue(originFragment.isDestroyedCompat())
        Assert.assertTrue(supportFragment.isDestroyedCompat())
    }

    @Test
    fun testGetImplWithParent() {
        val originFragment = activityTestRule.activity.getOriginFragment() as TestImplOriginFragment
        val supportFragment = activityTestRule.activity.getSupportFragment() as TestImplSupportFragment

        Assert.assertNotNull(originFragment.getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNotNull(supportFragment.getImplWithParent(ImplTestInterface::class.java))

        if (Androidx.isAtLeast17()) {
            Assert.assertNotNull(originFragment.getChildFragment().getImplWithParent(ImplTestInterface::class.java))
        }
        Assert.assertNotNull(supportFragment.getChildFragment().getImplWithParent(ImplTestInterface::class.java))

        waitRunInUI {
            activityTestRule.activity.convertChildFragment()
        }

        val originFragment2 = waitRunInUIResult { activityTestRule.activity.getOriginFragment() as TestImplOriginFragment2 }
        val supportFragment2 = waitRunInUIResult { activityTestRule.activity.getSupportFragment() as TestImplSupportFragment2 }

        Assert.assertNotNull(originFragment2.getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNotNull(supportFragment2.getImplWithParent(ImplTestInterface::class.java))

        Assert.assertNull(TestImplOriginFragment2().getImplWithParent(ImplTestInterface::class.java))
        Assert.assertNull(TestImplSupportFragment2().getImplWithParent(ImplTestInterface::class.java))
    }

    @Test
    fun testInstantiate() {
        val originFragment = android.app.Fragment::class.java.instantiateOrigin(Bundle().apply {
            putString("key", "testInstantiate")
        })
        Assert.assertEquals(android.app.Fragment::class.java.name, originFragment::class.java.name)
        Assert.assertEquals("testInstantiate", Argsx.readStringArg(originFragment, "key"))

        val supportFragment = android.support.v4.app.Fragment::class.java.instantiate(Bundle().apply {
            putString("key", "testInstantiate")
        })
        Assert.assertEquals(android.support.v4.app.Fragment::class.java.name, supportFragment::class.java.name)
        Assert.assertEquals("testInstantiate", Argsx.readStringArg(supportFragment, "key"))

        val originFragment2 = android.app.Fragment::class.java.instantiateOrigin()
        Assert.assertEquals(android.app.Fragment::class.java.name, originFragment2::class.java.name)

        val supportFragment2 = android.support.v4.app.Fragment::class.java.instantiate()
        Assert.assertEquals(android.support.v4.app.Fragment::class.java.name, supportFragment2::class.java.name)
    }

    @Test
    fun testFindUserVisibleChildFragment() {
        val activity = findUserVisibleChildActivityRule.activity
        // 定义多少个 ActivityTestRule 测试方法执行的时候就会启动多少个 ActivityTestRule 为了让 findUserVisibleChildActivityRule 处于 resumed 状态
        activityTestRule.finishActivity()

        val fragmentFromActivity = activity.findUserVisibleChildFragment().requireNotNull()
        Assert.assertEquals(TestFindUserVisibleChildFragment::class.java.name, fragmentFromActivity::class.java.name)

        val fragmentFromList = activity.supportFragmentManager.fragments.findUserVisibleChildFragment().requireNotNull()
        Assert.assertEquals(TestFindUserVisibleChildFragment::class.java.name, fragmentFromList::class.java.name)

        val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleChildFragment
        val fragmentFromChildFragment = fragmentFromActivity2.findUserVisibleChildFragment().requireNotNull()
        Assert.assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
    }

    @Test
    fun testFindFragmentByViewPagerCurrentItem() {
        val activity = findUserVisibleChildActivityRule.activity
        // 定义多少个 ActivityTestRule 测试方法执行的时候就会启动多少个 ActivityTestRule 为了让 findUserVisibleChildActivityRule 处于 resumed 状态
        activityTestRule.finishActivity()

        val fragmentFromActivity = activity.findFragmentByViewPagerCurrentItem(2).requireNotNull()
        Assert.assertEquals(TestFindUserVisibleChildFragment::class.java.name, fragmentFromActivity::class.java.name)
        Assert.assertTrue(fragmentFromActivity.tag, fragmentFromActivity.tag.orEmpty().startsWith("android:switcher") && fragmentFromActivity.tag.orEmpty().endsWith(":2"))

        val fragmentFromList = activity.supportFragmentManager.fragments.findFragmentByViewPagerCurrentItem(2).requireNotNull()
        Assert.assertEquals(TestFindUserVisibleChildFragment::class.java.name, fragmentFromList::class.java.name)
        Assert.assertTrue(fragmentFromList.tag, fragmentFromList.tag.orEmpty().startsWith("android:switcher") && fragmentFromList.tag.orEmpty().endsWith(":2"))

        val fragmentFromActivity2 = fragmentFromActivity as TestFindUserVisibleChildFragment
        val fragmentFromChildFragment = fragmentFromActivity2.findFragmentByViewPagerCurrentItem(3).requireNotNull()
        Assert.assertTrue(fragmentFromChildFragment.tag, fragmentFromChildFragment.tag.orEmpty().startsWith("android:switcher") && fragmentFromChildFragment.tag.orEmpty().endsWith(":3"))
    }

    @Test
    fun testContext() {
        val activity = activityTestRule.activity

        Assert.assertNotNull(activity.getOriginFragment().requireContext())

        Assert.assertNotNull(activity.getSupportFragment().requireAppContext())
        Assert.assertFalse(activity.getSupportFragment().requireAppContext() is Activity)

        Assert.assertNotNull(activity.getOriginFragment().requireAppContext())
        Assert.assertFalse(activity.getOriginFragment().requireAppContext() is Activity)

        activityTestRule.finishActivity()
        Thread.sleep(1000)

        try {
            activity.getOriginFragment().requireContext()
            Assert.fail()
        } catch (e: Exception) {
        }

        try {
            activity.getOriginFragment().requireAppContext()
            Assert.fail()
        } catch (e: Exception) {
        }

        try {
            activity.getSupportFragment().requireAppContext()
            Assert.fail()
        } catch (e: Exception) {
        }
    }

    class TestActivity : FragmentActivity(), ImplTestInterface {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_multi_frame)

            fragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, TestImplOriginFragment())
                    .commit()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, TestImplSupportFragment())
                    .commit()
        }

        fun getOriginFragment(): android.app.Fragment =
                fragmentManager.findFragmentById(R.id.multiFrameAt_frame1).requireNotNull()

        fun getSupportFragment(): android.support.v4.app.Fragment =
                supportFragmentManager.findFragmentById(R.id.multiFrameAt_frame2).requireNotNull()

        fun convertChildFragment() {
            fragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, TestImplOriginFragment2())
                    .commit()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, TestImplSupportFragment2())
                    .commit()
        }
    }

    interface ImplTestInterface

    class TestImplOriginFragment : android.app.Fragment(), ImplTestInterface {
        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater?.inflate(R.layout.at_test, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            if (Androidx.isAtLeast17()) {
                childFragmentManager.beginTransaction()
                        .replace(R.id.testAt_frame, TestImplOriginFragment2())
                        .commit()
            }
        }

        fun getChildFragment(): android.app.Fragment =
                childFragmentManager.findFragmentById(R.id.testAt_frame).requireNotNull()
    }

    class TestImplOriginFragment2 : android.app.Fragment()

    class TestImplSupportFragment : android.support.v4.app.Fragment(), ImplTestInterface {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_test, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            childFragmentManager.beginTransaction()
                    .replace(R.id.testAt_frame, TestImplSupportFragment2())
                    .commit()
        }

        fun getChildFragment(): android.support.v4.app.Fragment =
                childFragmentManager.findFragmentById(R.id.testAt_frame).requireNotNull()
    }

    class TestImplSupportFragment2 : android.support.v4.app.Fragment()

    class TestFindUserVisibleChildActivity : FragmentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_view_pager)

            val viewPager = findViewById<ViewPager>(R.id.viewPagerAt_viewPager)
            viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
                override fun getItem(p0: Int): Fragment {
                    return if (p0 == 2) {
                        TestFindUserVisibleChildFragment::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    } else {
                        TestFindUserVisibleChildFragment2::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())
                    }
                }

                override fun getCount(): Int = 5
            }
            viewPager.currentItem = 2
        }
    }

    class TestFindUserVisibleChildFragment : android.support.v4.app.Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.at_view_pager, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val viewPager = view.findViewById<ViewPager>(R.id.viewPagerAt_viewPager)
            viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager) {
                override fun getItem(p0: Int): android.support.v4.app.Fragment =
                        TestFindUserVisibleChildFragment2::class.java.instantiate(BundleBuilder().putString("position", p0.toString()).build())

                override fun getCount(): Int = 5
            }
            viewPager.currentItem = 3
        }
    }

    class TestFindUserVisibleChildFragment2 : android.support.v4.app.Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return TextView(context)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            (view as TextView).text = "position: ${readStringArg("position")}"
        }
    }
}
