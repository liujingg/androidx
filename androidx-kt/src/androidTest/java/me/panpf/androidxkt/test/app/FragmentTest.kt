package me.panpf.androidxkt.test.app

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.isDestroyedCompat

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(FragmentTestActivity::class.java)

    @get:Rule
    val fragmentActivityTestRule = ActivityTestRule(FragmentTestFragmentActivity::class.java)

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

        Assert.assertTrue(fragment.isDestroyedCompat())
    }

    @Test
    fun testFragmentActivityNormal() {
        val fragment = fragmentActivityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content)

        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertFalse(fragment.isDestroyedCompat())
    }
}
