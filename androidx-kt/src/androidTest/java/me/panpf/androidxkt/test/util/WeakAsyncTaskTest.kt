package me.panpf.androidxkt.test.util

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import me.panpf.androidxkt.util.WeakAsyncTask
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeakAsyncTaskTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(WeakAsyncTaskTestActivity::class.java)

    @Test
    fun testError() {
        var result: String

        result = try {
            InnerTestWeakAsyncTask(this)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            "failed"
        }
        Assert.assertEquals(result, "failed")

        result = try {
            TestWeakAsyncTask(this)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            "failed"
        }
        Assert.assertEquals(result, "success")

        result = try {
            TestWeakAsyncTask2(this)
            "success"
        } catch (e: Exception) {
            e.printStackTrace()
            "failed"
        }
        Assert.assertEquals(result, "success")
    }

    @Test
    fun testDestroyed() {
        val activity = activityTestRule.activity

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        activityTestRule.finishActivity()

        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertEquals(activity.result, "None")
    }

    @Test
    fun testNormal() {
        val activity = activityTestRule.activity

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Assert.assertEquals(activity.result, "onPostExecute")
    }

    private class TestWeakAsyncTask(weakAsyncTaskTest: WeakAsyncTaskTest) : WeakAsyncTask<WeakAsyncTaskTest, Int, Int, Int>(weakAsyncTaskTest) {

        override fun doInBackground(page: WeakAsyncTaskTest, params: Array<Int>): Int? {
            return null
        }
    }

    private inner class InnerTestWeakAsyncTask(weakAsyncTaskTest: WeakAsyncTaskTest) : WeakAsyncTask<WeakAsyncTaskTest, Int, Int, Int>(weakAsyncTaskTest) {

        override fun doInBackground(page: WeakAsyncTaskTest, params: Array<Int>): Int? {
            return null
        }
    }
}
