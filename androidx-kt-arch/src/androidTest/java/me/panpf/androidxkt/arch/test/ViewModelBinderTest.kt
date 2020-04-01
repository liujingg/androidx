package me.panpf.androidxkt.arch.test

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.arch.bindViewModel
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
        val fragment = activityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content) as TestFragment
        Assert.assertNotNull(fragment.viewModel)
    }

    @Test
    fun testFragmentFactoryViewModel() {
        val fragment = activityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content) as TestFragment
        Assert.assertEquals(fragment.factoryViewModel.tag, "testFactoryViewModelFragment")
    }

    class TestViewModel(application: Application) : AndroidViewModel(application)

    class TestFactoryViewModel(val tag: String) : ViewModel()

    class TestFragment : androidx.fragment.app.Fragment(){

        val viewModel: TestViewModel  by bindViewModel(TestViewModel::class)

        val factoryViewModel: TestFactoryViewModel  by bindViewModel(TestFactoryViewModel::class, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(String::class.java).newInstance("testFactoryViewModelFragment")
            }
        })
    }

    class TestActivity : androidx.fragment.app.FragmentActivity() {

        val viewModel: TestViewModel  by bindViewModel(TestViewModel::class)

        val factoryViewModel: TestFactoryViewModel  by bindViewModel(TestFactoryViewModel::class, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(String::class.java).newInstance("testFactoryViewModel")
            }
        })

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            supportFragmentManager.beginTransaction().replace(android.R.id.content, TestFragment()).commit()
        }
    }
}