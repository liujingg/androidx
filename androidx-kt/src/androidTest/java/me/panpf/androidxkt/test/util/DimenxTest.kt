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

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.util.*
import me.panpf.javax.util.Premisex
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DimenxTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(TestActivity::class.java)

    @Test
    fun testContext() {
        val context = InstrumentationRegistry.getContext()

        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), context.dp2px(10f).toLong())
        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), context.dp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.density + 0.5f, context.px2dp(100), 0f)

        Assert.assertEquals((10f * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), context.sp2px(10f).toLong())
        Assert.assertEquals((10.toFloat() * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), context.sp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.scaledDensity + 0.5f, context.px2sp(100), 0f)

        Assert.assertEquals(10f * context.resources.displayMetrics.scaledDensity, context.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
        Assert.assertEquals(10.toFloat() * context.resources.displayMetrics.scaledDensity, context.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)
    }

    @Test
    fun testFragment() {
        val context = InstrumentationRegistry.getContext()


        val originFragment = activityTestRule.activity.originFragment

        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), originFragment.dp2px(10f).toLong())
        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), originFragment.dp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.density + 0.5f, originFragment.px2dp(100), 0f)

        Assert.assertEquals((10f * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), originFragment.sp2px(10f).toLong())
        Assert.assertEquals((10.toFloat() * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), originFragment.sp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.scaledDensity + 0.5f, originFragment.px2sp(100), 0f)

        Assert.assertEquals(10f * context.resources.displayMetrics.scaledDensity, originFragment.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
        Assert.assertEquals(10.toFloat() * context.resources.displayMetrics.scaledDensity, originFragment.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)


        val supportFragment = activityTestRule.activity.supportFragment

        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), supportFragment.dp2px(10f).toLong())
        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), supportFragment.dp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.density + 0.5f, supportFragment.px2dp(100), 0f)

        Assert.assertEquals((10f * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), supportFragment.sp2px(10f).toLong())
        Assert.assertEquals((10.toFloat() * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), supportFragment.sp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.scaledDensity + 0.5f, supportFragment.px2sp(100), 0f)

        Assert.assertEquals(10f * context.resources.displayMetrics.scaledDensity, supportFragment.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
        Assert.assertEquals(10.toFloat() * context.resources.displayMetrics.scaledDensity, supportFragment.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)
    }

    @Test
    fun testView() {
        val context = InstrumentationRegistry.getContext()

        val view = activityTestRule.activity.view

        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), view.dp2px(10f).toLong())
        Assert.assertEquals((10f * context.resources.displayMetrics.density + 0.5f).toInt().toLong(), view.dp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.density + 0.5f, view.px2dp(100), 0f)

        Assert.assertEquals((10f * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), view.sp2px(10f).toLong())
        Assert.assertEquals((10.toFloat() * context.resources.displayMetrics.scaledDensity + 0.5f).toInt().toLong(), view.sp2px(10).toLong())

        Assert.assertEquals(100.toFloat() / context.resources.displayMetrics.scaledDensity + 0.5f, view.px2sp(100), 0f)

        Assert.assertEquals(10f * context.resources.displayMetrics.scaledDensity, view.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10f), 0f)
        Assert.assertEquals(10.toFloat() * context.resources.displayMetrics.scaledDensity, view.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10), 0f)
    }

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val originFragment: android.app.Fragment
            get() = fragmentManager.findFragmentById(R.id.multiFrameAt_frame1)

        val supportFragment: androidx.fragment.app.Fragment
            get() = Premisex.requireNotNull<androidx.fragment.app.Fragment>(supportFragmentManager.findFragmentById(R.id.multiFrameAt_frame2))

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_multi_frame)

            fragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, android.app.Fragment())
                    .commit()

            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, androidx.fragment.app.Fragment())
                    .commit()
        }
    }
}
