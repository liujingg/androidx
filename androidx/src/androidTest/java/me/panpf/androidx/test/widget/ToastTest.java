/*
 * Copyright (C) 2018 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.panpf.androidx.test.widget;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.test.R;
import me.panpf.androidx.view.Viewx;
import me.panpf.androidx.widget.Toastx;

@RunWith(AndroidJUnit4.class)
public class ToastTest {

    @NonNull
    private final ActivityTestRule<ToastTestActivity> supportFragmentRule = new ActivityTestRule<ToastTestActivity>(ToastTestActivity.class);

    @Rule
    @NonNull
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
        Toastx.showLongWithView(Viewx.inflateLayout(InstrumentationRegistry.getContext(), R.layout.view_toast));
        Toastx.showShortWithView(Viewx.inflateLayout(InstrumentationRegistry.getContext(), R.layout.view_toast));
    }
}
