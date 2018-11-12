package me.panpf.androidxkt.test.app

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.FragmentActivity
import me.panpf.androidxkt.app.setClickButtonClosable
import me.panpf.androidxkt.app.showProgressDialog
import me.panpf.androidxkt.test.app.fragment.TestOriginFragment
import me.panpf.androidxkt.test.app.fragment.TestSupportFragment
import me.panpf.androidxkt.waitRunInUI
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * <P>Created by Vincent on 2018/11/12.</P>
 */
@RunWith(AndroidJUnit4::class)
class DialogxTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(DialogxTestActivity::class.java)

    @get:Rule
    val mFragmentActivityTestRule = ActivityTestRule(DialogxTestFragmentActivity::class.java)

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


    class DialogxTestActivity : Activity() {

        val dialog: Dialog
            get() = Dialog(this)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            fragmentManager.beginTransaction()
                    .replace(android.R.id.content, TestOriginFragment())
                    .commit()
        }
    }

    class DialogxTestFragmentActivity : FragmentActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, TestSupportFragment())
                    .commit()
        }

    }
}