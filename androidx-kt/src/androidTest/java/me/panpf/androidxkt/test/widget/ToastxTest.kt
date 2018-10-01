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

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.view.inflateLayout
import me.panpf.androidxkt.widget.showLongToast
import me.panpf.androidxkt.widget.showLongToastWithSelf
import me.panpf.androidxkt.widget.showShortToast
import me.panpf.androidxkt.widget.showShortToastWithSelf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastxTest {

    @get:Rule
    val fragmentRuleToast: ActivityTestRule<ToastxTestActivity> = ActivityTestRule<ToastxTestActivity>(ToastxTestActivity::class.java)

    @Test
    fun testContextToast(){
        fragmentRuleToast.activity.showLongToast("今天是2018年10月18号")
        fragmentRuleToast.activity.showLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragmentRuleToast.activity.showLongToast(R.string.toast_test)
        fragmentRuleToast.activity.showLongToast(R.string.toast_test_tp, 2018, 10, 18)

        fragmentRuleToast.activity.showShortToast("今天是2018年10月18号")
        fragmentRuleToast.activity.showShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragmentRuleToast.activity.showShortToast(R.string.toast_test)
        fragmentRuleToast.activity.showShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testFragmentToast(){
        val fragment = fragmentRuleToast.activity.supportFragmentManager.findFragmentById(R.id.testAt_frame)

        fragment.showLongToast("今天是2018年10月18号")
        fragment.showLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showLongToast(R.string.toast_test)
        fragment.showLongToast(R.string.toast_test_tp, 2018, 10, 18)

        fragment.showShortToast("今天是2018年10月18号")
        fragment.showShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showShortToast(R.string.toast_test)
        fragment.showShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testViewToast(){
        val view = fragmentRuleToast.activity.findViewById<View>(R.id.testAt_frame)

        view.showLongToast("今天是2018年10月18号")
        view.showLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        view.showLongToast(R.string.toast_test)
        view.showLongToast(R.string.toast_test_tp, 2018, 10, 18)

        view.showShortToast("今天是2018年10月18号")
        view.showShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        view.showShortToast(R.string.toast_test)
        view.showShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testWithViewToast(){
        InstrumentationRegistry.getContext().inflateLayout(R.layout.view_toast).showLongToastWithSelf()

        InstrumentationRegistry.getContext().inflateLayout(R.layout.view_toast).showShortToastWithSelf()
    }
}