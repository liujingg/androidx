package me.panpf.androidxkt.test.app

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.isDestroyedCompat

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(ActivityTestActivity::class.java)

    @get:Rule
    val fragmentActivityTestRule = ActivityTestRule(ActivityTestFragmentActivity::class.java)

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
}
