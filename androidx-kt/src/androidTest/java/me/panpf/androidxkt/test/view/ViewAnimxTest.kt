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

package me.panpf.androidxkt.test.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidx.view.ViewAnimx
import me.panpf.androidxkt.runInUI
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewAnimxTest {

    private val activityRule = ActivityTestRule(TestActivity::class.java)

    @Rule
    fun getActivityRule(): ActivityTestRule<*> {
        return this.activityRule
    }

    @Test
    @Throws(InterruptedException::class)
    fun testAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.animAlpha(1.0f, 0.0f, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f, invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f, true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f, 500)
        }
        Thread.sleep(500)

        runInUI {
            view.visibility = View.VISIBLE
            view.animAlpha(1.0f, 0.0f)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testAnimTranslate() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.animTranslate(0f, 300f, 0f, 300f, 0f, ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, 500)
        }
        Thread.sleep(500)

        runInUI {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f, 3f)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.animTranslate(0f, 300f, 0f, 300f)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testShakeLandscape() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.shakeLandscape(10f, 7f, 700, true, invisibleListener) }
        Thread.sleep(700)

        runInUI { view.shakeLandscape(invisibleListener) }
        Thread.sleep(700)

        runInUI { view.shakeLandscape(true) }
        Thread.sleep(700)

        runInUI { view.shakeLandscape(500L) }
        Thread.sleep(500L)

        runInUI { view.shakeLandscape(15f) }
        Thread.sleep(700)

        runInUI { view.shakeLandscape() }
        Thread.sleep(700)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testShakePortrait() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.shakePortrait(10f, 7f, 700, true, invisibleListener) }
        Thread.sleep(700)

        runInUI { view.shakePortrait(invisibleListener) }
        Thread.sleep(700)

        runInUI { view.shakePortrait(true) }
        Thread.sleep(700)

        runInUI { view.shakePortrait(500L) }
        Thread.sleep(500L)

        runInUI { view.shakePortrait(15f) }
        Thread.sleep(700)

        runInUI { view.shakePortrait() }
        Thread.sleep(700)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testStartAnimFromRes() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.startAnimFromRes(R.anim.view_anim_test, true, invisibleListener) }
        Thread.sleep(1000)

        runInUI {
            view.visibility = View.VISIBLE
            view.startAnimFromRes(R.anim.view_anim_test, invisibleListener)
        }
        Thread.sleep(1000)

        runInUI {
            view.visibility = View.VISIBLE
            view.startAnimFromRes(R.anim.view_anim_test, true)
        }
        Thread.sleep(1000)

        runInUI {
            view.visibility = View.VISIBLE
            view.startAnimFromRes(R.anim.view_anim_test)
        }
        Thread.sleep(1000)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testInvisibleByAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.invisibleByAnimAlpha(ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha(invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha(true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha(500)
        }
        Thread.sleep(500)

        runInUI {
            view.visibility = View.VISIBLE
            view.invisibleByAnimAlpha()
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testGoneByAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.goneByAnimAlpha(ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha(invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha(true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha(500)
        }
        Thread.sleep(500)

        runInUI {
            view.visibility = View.VISIBLE
            view.goneByAnimAlpha()
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testVisibleByAnimAlpha() {
        val view = activityRule.activity.view

        val invisibleListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        }

        runInUI { view.visibleByAnimAlpha(ViewAnimx.DEFAULT_ANIMATION_DURATION, true, invisibleListener) }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha(invisibleListener)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha(true)
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)

        runInUI {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha(500)
        }
        Thread.sleep(500)

        runInUI {
            view.visibility = View.VISIBLE
            view.visibleByAnimAlpha()
        }
        Thread.sleep(ViewAnimx.DEFAULT_ANIMATION_DURATION)
    }

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val view: View
            get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val viewGroup = findViewById<ViewGroup>(android.R.id.content)
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.rect)
            viewGroup.addView(imageView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        }

        //        public ViewGroup getContentView() {
        //            return (ViewGroup) findViewById(android.R.id.content);
        //        }
    }
}
