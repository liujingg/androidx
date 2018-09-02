package me.panpf.androidxkt.test.widget

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import me.panpf.androidxkt.test.R
import me.panpf.androidxkt.test.TestSupportActivity
import me.panpf.androidxkt.view.inflateLayout
import me.panpf.androidxkt.widget.showLongToast
import me.panpf.androidxkt.widget.showLongToastWithSelf
import me.panpf.androidxkt.widget.showShortToast
import me.panpf.androidxkt.widget.showShortToastWithSelf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastTest {

    @get:Rule
    val supportFragmentRule: ActivityTestRule<TestSupportActivity> = ActivityTestRule<TestSupportActivity>(TestSupportActivity::class.java)

    @Test
    fun testContextToast(){
        supportFragmentRule.activity.showLongToast("今天是2018年10月18号")
        supportFragmentRule.activity.showLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        supportFragmentRule.activity.showLongToast(R.string.toast_test)
        supportFragmentRule.activity.showLongToast(R.string.toast_test_tp, 2018, 10, 18)

        supportFragmentRule.activity.showShortToast("今天是2018年10月18号")
        supportFragmentRule.activity.showShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        supportFragmentRule.activity.showShortToast(R.string.toast_test)
        supportFragmentRule.activity.showShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testFragmentToast(){
        val fragment = supportFragmentRule.activity.supportFragmentManager.findFragmentById(R.id.testAt_frame)

        fragment.showLongToast("今天是2018年10月18号")
        fragment.showLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showLongToast(R.string.toast_test)
        fragment.showLongToast(R.string.toast_test_tp, 2018, 10, 18)

        fragment.showShortToast("今天是2018年10月18号")
        fragment.showShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        fragment.showShortToast(R.string.toast_test)
        fragment.showShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testViewToast(){
        val view = supportFragmentRule.activity.findViewById<View>(R.id.testAt_frame)

        view.showLongToast("今天是2018年10月18号")
        view.showLongToast("今天是%d年%d月%d号", 2018, 10, 18)
        view.showLongToast(R.string.toast_test)
        view.showLongToast(R.string.toast_test_tp, 2018, 10, 18)

        view.showShortToast("今天是2018年10月18号")
        view.showShortToast("今天是%d年%d月%d号", 2018, 10, 18)
        view.showShortToast(R.string.toast_test)
        view.showShortToast(R.string.toast_test_tp, 2018, 10, 18)
    }

    @Test
    fun testWithViewToast(){
        val view = InstrumentationRegistry.getContext().inflateLayout(R.layout.view_toast)

        view.showLongToastWithSelf()
        view.showShortToastWithSelf()
    }
}