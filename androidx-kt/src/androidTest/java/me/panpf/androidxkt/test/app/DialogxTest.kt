@file:Suppress("DEPRECATION")

package me.panpf.androidxkt.test.app

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
    val mFragmentActivityTestRule = ActivityTestRule(TestFragmentActivity::class.java)

    @Test
    fun testSetClickButtonClosable() {
        waitRunInUI {
            val activity = mFragmentActivityTestRule.activity
            Assert.assertTrue(activity.dialog.setClickButtonClosable(true))
            Assert.assertTrue(activity.dialog.setClickButtonClosable(false))
        }
    }

    @Test
    fun testShowProgressDialog() {
        waitRunInUI {
            val activity = mFragmentActivityTestRule.activity
            val supportFragment = mFragmentActivityTestRule.activity.supportFragmentManager.findFragmentById(android.R.id.content)

            Assert.assertNotNull(activity.showProgressDialog("by activity"))
            Assert.assertNotNull(activity.showProgressDialog(android.R.string.ok))

            Assert.assertNotNull(supportFragment?.showProgressDialog("by supportFragment"))
            Assert.assertNotNull(supportFragment?.showProgressDialog(android.R.string.yes))
        }
    }

    class TestFragmentActivity : androidx.fragment.app.FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, androidx.fragment.app.Fragment())
                    .commit()
        }

        val dialog: Dialog
            get() = Dialog(this)
    }
}