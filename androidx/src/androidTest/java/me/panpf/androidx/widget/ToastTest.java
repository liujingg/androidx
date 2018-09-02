package me.panpf.androidx.widget;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.test.R;
import me.panpf.androidx.test.TestSupportActivity;
import me.panpf.androidx.view.Viewx;

@RunWith(AndroidJUnit4.class)
public class ToastTest {
    @NotNull
    private final ActivityTestRule<TestSupportActivity> supportFragmentRule = new ActivityTestRule<TestSupportActivity>(TestSupportActivity.class);

    @Rule
    @NotNull
    public final ActivityTestRule getSupportFragmentRule() {
        return this.supportFragmentRule;
    }

    @Test
    public final void testContextToast() {
        Activity activity = this.supportFragmentRule.getActivity();

        Toastx.showLong(activity, "今天是2018年10月18号");
        Toastx.showLong(activity, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showLong(activity, R.string.toast_test);
        Toastx.showLong(activity, R.string.toast_test_tp, 2018, 10, 18);
        Toastx.showShort(activity, "今天是2018年10月18号");
        Toastx.showShort(activity, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showShort(activity, R.string.toast_test);
        Toastx.showShort(activity, R.string.toast_test_tp, 2018, 10, 18);
    }

    @Test
    public final void testFragmentToast() {
        Fragment fragment = this.supportFragmentRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.testAt_frame);

        Toastx.showLong(fragment, "今天是2018年10月18号");
        Toastx.showLong(fragment, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showLong(fragment, R.string.toast_test);
        Toastx.showLong(fragment, R.string.toast_test_tp, 2018, 10, 18);
        Toastx.showShort(fragment, "今天是2018年10月18号");
        Toastx.showShort(fragment, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showShort(fragment, R.string.toast_test);
        Toastx.showShort(fragment, R.string.toast_test_tp, 2018, 10, 18);
    }

    @Test
    public final void testViewToast() {
        View view = this.supportFragmentRule.getActivity().findViewById(R.id.testAt_frame);

        Toastx.showLong(view, "今天是2018年10月18号");
        Toastx.showLong(view, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showLong(view, R.string.toast_test);
        Toastx.showLong(view, R.string.toast_test_tp, 2018, 10, 18);
        Toastx.showShort(view, "今天是2018年10月18号");
        Toastx.showShort(view, "今天是%d年%d月%d号", 2018, 10, 18);
        Toastx.showShort(view, R.string.toast_test);
        Toastx.showShort(view, R.string.toast_test_tp, 2018, 10, 18);
    }

    @Test
    public final void testWithViewToast() {
        View view = Viewx.inflateLayout(InstrumentationRegistry.getContext(), R.layout.view_toast);

        Toastx.showLongWithView(view);
        Toastx.showShortWithView(view);
    }
}