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

package me.panpf.androidxkt.test.view

import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.FragmentActivity
import android.text.Selection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import me.panpf.androidxkt.runInUI
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.view.inputmethod.*
import me.panpf.androidxkt.view.isOrientationPortrait
import me.panpf.javaxkt.util.requireNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputMethodxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    @Throws(InterruptedException::class)
    fun testShowSoftInput() {
        val activity = activityRule.activity
        val originEditText = activity.originFragmentEditTxt

        // show
        if (activity.isOrientationPortrait()) {
            if (activity.isSoftInputShowing()) {
                runInUI { activity.hideSoftInput() }
                Thread.sleep(500)
                Assert.assertFalse(activity.isSoftInputShowing())
            }
        }
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.text).toLong())
        runInUI { originEditText.showSoftInput() }
        Thread.sleep(500)
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.text).toLong())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDelayShowSoftInput() {
        val activity = activityRule.activity
        val supportEditText = activity.originFragmentEditTxt

        // show
        if (activity.isOrientationPortrait()) {
            if (activity.isSoftInputShowing()) {
                runInUI { activity.hideSoftInput() }
                Thread.sleep(500)
                Assert.assertFalse(activity.isSoftInputShowing())
            }
        }
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.text).toLong())
        runInUI { supportEditText.delayShowSoftInput() }
        Thread.sleep(500)
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.text).toLong())

        // hide
        runInUI {
            activity.hideSoftInput()
            supportEditText.moveCursorToStart()
        }
        Thread.sleep(500)

        // show
        if (activity.isOrientationPortrait()) Assert.assertFalse(activity.isSoftInputShowing())
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.text).toLong())
        runInUI { supportEditText.delayShowSoftInput(500) }
        Thread.sleep((500 + 500).toLong())
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.text).toLong())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testHideSoftInput() {
        val activity = activityRule.activity
        val originEditText = activity.originFragmentEditTxt
        val supportEditText = activity.supportFragmentEditTxt

        // show
        if (activity.isOrientationPortrait()) {
            if (activity.isSoftInputShowing()) {
                runInUI { activity.hideSoftInput() }
                Thread.sleep(500)
                Assert.assertFalse(activity.isSoftInputShowing())
            }
        }
        runInUI { originEditText.showSoftInput() }
        Thread.sleep(500)

        // hide
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        runInUI { activity.hideSoftInput() }
        Thread.sleep(500)

        // show
        if (activity.isOrientationPortrait()) Assert.assertFalse(activity.isSoftInputShowing())
        runInUI { originEditText.showSoftInput() }
        Thread.sleep(500)

        // hide
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        runInUI { activity.originFragment.hideSoftInput() }
        Thread.sleep(500)

        // show
        if (activity.isOrientationPortrait()) Assert.assertFalse(activity.isSoftInputShowing())
        runInUI { supportEditText.showSoftInput() }
        Thread.sleep(500)

        // hide
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        runInUI { activity.supportFragment.hideSoftInput() }
        Thread.sleep(500)

        // show
        if (activity.isOrientationPortrait()) Assert.assertFalse(activity.isSoftInputShowing())
        runInUI { supportEditText.showSoftInput() }
        Thread.sleep(500)

        // hide
        if (activity.isOrientationPortrait()) Assert.assertTrue(activity.isSoftInputShowing())
        runInUI { supportEditText.hideSoftInput() }
        Thread.sleep(500)
        if (activity.isOrientationPortrait()) Assert.assertFalse(activity.isSoftInputShowing())
    }

    @Test
    @Throws(InterruptedException::class)
    fun testMoveCursor() {
        val activity = activityRule.activity
        val originEditText = activity.originFragmentEditTxt

        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.text).toLong())
        runInUI { originEditText.moveCursorToEnd() }
        Thread.sleep(100)
        Assert.assertEquals(originEditText.length().toLong(), Selection.getSelectionEnd(originEditText.text).toLong())

        runInUI { originEditText.moveCursorToStart() }
        Thread.sleep(100)
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.text).toLong())

        runInUI { originEditText.moveCursorTo(originEditText.length() / 2) }
        Thread.sleep(100)
        Assert.assertEquals((originEditText.length() / 2).toLong(), Selection.getSelectionEnd(originEditText.text).toLong())
    }

    class TestActivity : FragmentActivity() {

        val originFragment: android.app.Fragment
            get() = fragmentManager.findFragmentById(R.id.multiFrameAt_frame1)

        val originFragmentEditTxt: EditText
            get() = originFragment.view as EditText

        val supportFragment: android.support.v4.app.Fragment
            get() = supportFragmentManager.findFragmentById(R.id.multiFrameAt_frame2).requireNotNull()

        val supportFragmentEditTxt: EditText
            get() = supportFragment.view as EditText

        val view: View
            get() = findViewById(android.R.id.content)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.at_multi_frame)

            fragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, EditOriginFragment())
                    .commit()

            supportFragmentManager.beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, EditSupportFragment())
                    .commit()
        }
    }

    class EditOriginFragment : android.app.Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            val editText = EditText(activity)
            editText.setText("0123456789")
            return editText
        }
    }

    class EditSupportFragment : android.support.v4.app.Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            val editText = EditText(activity)
            editText.setText("0123456789")
            return editText
        }
    }
}
