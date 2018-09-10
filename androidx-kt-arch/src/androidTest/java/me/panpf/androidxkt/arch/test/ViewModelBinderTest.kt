package me.panpf.androidxkt.arch.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewModelBinderTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<TestActivity> = ActivityTestRule<TestActivity>(TestActivity::class.java)

    @Test
    fun testViewModel() {
        val activity = activityTestRule.activity
        Assert.assertNotNull(activity.viewModel)
    }

    @Test
    fun testFactoryViewModel() {
        val activity = activityTestRule.activity
        Assert.assertEquals(activity.factoryViewModel.tag, "testFactoryViewModel")
    }

    @Test
    fun testFragmentViewModel() {
        val fragment = activityTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.arch.test.R.id.testAt_frame) as TestFragment;
        Assert.assertNotNull(fragment.viewModel)
    }

    @Test
    fun testFragmentFactoryViewModel() {
        val fragment = activityTestRule.activity.supportFragmentManager.findFragmentById(me.panpf.androidxkt.arch.test.R.id.testAt_frame) as TestFragment;
        Assert.assertEquals(fragment.factoryViewModel.tag, "testFactoryViewModelFragment")
    }
}