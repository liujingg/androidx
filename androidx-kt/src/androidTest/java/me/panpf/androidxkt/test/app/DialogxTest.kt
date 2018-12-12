@file:Suppress("DEPRECATION")

package me.panpf.androidxkt.test.app

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import me.panpf.androidxkt.app.setClickButtonClosable
import me.panpf.androidxkt.app.showProgressDialog
import me.panpf.androidxkt.waitRunInUI
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DialogxTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(TestActivity::class.java)

    @get:Rule
    val mFragmentActivityTestRule = ActivityTestRule(TestFragmentActivity::class.java)

    @Test
    fun testSetClickButtonClosable() {
        waitRunInUI {
            val activity = mActivityTestRule.activity
            Assert.assertTrue(activity.dialog.setClickButtonClosable(true))
            Assert.assertTrue(activity.dialog.setClickButtonClosable(false))
        }
    }

    @Test
    fun testShowProgressDialog() {
        waitRunInUI {
            val activity = mActivityTestRule.activity
            val fragment = activity.fragmentManager.findFragmentById(android.R.id.content)
            val supportFragment = mFragmentActivityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content)

            Assert.assertNotNull(activity.showProgressDialog("by activity"))
            Assert.assertNotNull(activity.showProgressDialog(android.R.string.ok))

            Assert.assertNotNull(supportFragment?.showProgressDialog("by supportFragment"))
            Assert.assertNotNull(supportFragment?.showProgressDialog(android.R.string.yes))

            Assert.assertNotNull(fragment.showProgressDialog("by fragment"))
            Assert.assertNotNull(fragment.showProgressDialog(android.R.string.yes))
        }
    }


    class TestActivity : Activity() {

        val dialog: Dialog
            get() = Dialog(this)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            fragmentManager.beginTransaction()
                    .replace(android.R.id.content, android.app.Fragment())
                    .commit()
        }
    }

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, androidx.fragment.app.Fragment())
                    .commit()
        }
    }
}