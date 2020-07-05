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

package me.panpf.androidxkt.test.widget

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.view.inflateLayout
import me.panpf.androidxkt.widget.*
import me.panpf.javax.util.Premisex
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastxTest {

    @get:Rule
    val activityRule: ActivityTestRule<TestActivity> = ActivityTestRule<TestActivity>(TestActivity::class.java)

    @get:Rule
    val fragmentActivityRule: ActivityTestRule<TestFragmentActivity> = ActivityTestRule<TestFragmentActivity>(TestFragmentActivity::class.java)

    @Test
    fun testContextToast() {
        val activity = fragmentActivityRule.activity

        activity.showLongToast("今天是2018年10月18号")
        activity.showWithFormatLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        activity.showLongToast(R.string.toast_test)
        activity.showWithFormatLongToast(R.string.toast_test_tp, 2018, 10, 18)

        activity.showShortToast("今天是2018年10月18号")
        activity.showWithFormatShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        activity.showShortToast(R.string.toast_test)
        activity.showWithFormatShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testSupportFragmentToast() {
        val fragment = fragmentActivityRule.activity.fragment

        fragment.showLongToast("今天是2018年10月18号")
        fragment.showWithFormatLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showLongToast(R.string.toast_test)
        fragment.showWithFormatLongToast(R.string.toast_test_tp, 2018, 10, 18)

        fragment.showShortToast("今天是2018年10月18号")
        fragment.showWithFormatShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showShortToast(R.string.toast_test)
        fragment.showWithFormatShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testOriginFragmentToast() {
        val fragment = activityRule.activity.fragment

        fragment.showLongToast("今天是2018年10月18号")
        fragment.showWithFormatLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showLongToast(R.string.toast_test)
        fragment.showWithFormatLongToast(R.string.toast_test_tp, 2018, 10, 18)

        fragment.showShortToast("今天是2018年10月18号")
        fragment.showWithFormatShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showShortToast(R.string.toast_test)
        fragment.showWithFormatShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testViewToast() {
        val view = fragmentActivityRule.activity.view

        view.showLongToast("今天是2018年10月18号")
        view.showWithFormatLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        view.showLongToast(R.string.toast_test)
        view.showWithFormatLongToast(R.string.toast_test_tp, 2018, 10, 18)

        view.showShortToast("今天是2018年10月18号")
        view.showWithFormatShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        view.showShortToast(R.string.toast_test)
        view.showWithFormatShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testWithViewToast() {
        InstrumentationRegistry.getContext().inflateLayout(R.layout.view_toast).showLongToastWithSelf()

        InstrumentationRegistry.getContext().inflateLayout(R.layout.view_toast).showShortToastWithSelf()
    }

    class TestActivity : Activity() {

        val fragment: android.app.Fragment
            get() = Premisex.requireNotNull(fragmentManager.findFragmentById(android.R.id.content))

//        val view: View
//            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            this.fragmentManager.beginTransaction().replace(android.R.id.content, android.app.Fragment()).commit()
        }
    }

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {

        val fragment: androidx.fragment.app.Fragment
            get() = Premisex.requireNotNull(supportFragmentManager.findFragmentById(android.R.id.content))

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            this.supportFragmentManager.beginTransaction().replace(android.R.id.content, androidx.fragment.app.Fragment()).commit()
        }
    }
}