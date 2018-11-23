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

package me.panpf.androidx.test.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentActivity;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.test.R;
import me.panpf.androidx.view.Displayx;
import me.panpf.androidx.view.inputmethod.InputMethodx;
import me.panpf.javax.util.Premisex;

@RunWith(AndroidJUnit4.class)
public class InputMethodxTest {

    @NonNull
    private final ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    @Rule
    @NonNull
    public final ActivityTestRule getActivityRule() {
        return this.activityRule;
    }

    @Test
    public void testShowSoftInput() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText originEditText = activity.getOriginFragmentEditTxt();

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.showSoftInput(originEditText);
            }
        });
        Thread.sleep(500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));

        // hide
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity);
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.showSoftInputAndMoveCursorToEnd(originEditText);
            }
        });
        Thread.sleep(500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(originEditText.length(), Selection.getSelectionEnd(originEditText.getText()));
    }

    @Test
    public void testDelayShowSoftInput() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText supportEditText = activity.getOriginFragmentEditTxt();

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.delayShowSoftInput(supportEditText);
            }
        });
        Thread.sleep(500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.getText()));

        // hide
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity);
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.delayShowSoftInputAndMoveCursorToEnd(supportEditText);
            }
        });
        Thread.sleep(500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(supportEditText.length(), Selection.getSelectionEnd(supportEditText.getText()));

        // hide
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity);
                InputMethodx.moveCursorToStart(supportEditText);
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.delayShowSoftInput(supportEditText, 500);
            }
        });
        Thread.sleep(500 + 500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.getText()));

        // hide
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity);
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(0, Selection.getSelectionEnd(supportEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.delayShowSoftInputAndMoveCursorToEnd(supportEditText, 500);
            }
        });
        Thread.sleep(500 + 500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Assert.assertEquals(supportEditText.length(), Selection.getSelectionEnd(supportEditText.getText()));
    }

    @Test
    public void testHideSoftInput() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText originEditText = activity.getOriginFragmentEditTxt();
        final EditText supportEditText = activity.getSupportFragmentEditTxt();

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.showSoftInput(originEditText);
            }
        });
        Thread.sleep(500);

        // hide
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity);
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.showSoftInput(originEditText);
            }
        });
        Thread.sleep(500);

        // hide
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity.getOriginFragment());
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.showSoftInput(supportEditText);
            }
        });
        Thread.sleep(500);

        // hide
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(activity.getSupportFragment());
            }
        });
        Thread.sleep(500);

        // show
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.showSoftInput(supportEditText);
            }
        });
        Thread.sleep(500);

        // hide
        if (Displayx.isOrientationPortrait(activity)) Assert.assertTrue(InputMethodx.isSoftInputShowing(activity));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.hideSoftInput(supportEditText);
            }
        });
        Thread.sleep(500);
        if (Displayx.isOrientationPortrait(activity)) Assert.assertFalse(InputMethodx.isSoftInputShowing(activity));
    }

    @Test
    public void testMoveCursor() throws InterruptedException {
        final TestActivity activity = activityRule.getActivity();
        final EditText originEditText = activity.getOriginFragmentEditTxt();

        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.moveCursorToEnd(originEditText);
            }
        });
        Thread.sleep(100);
        Assert.assertEquals(originEditText.length(), Selection.getSelectionEnd(originEditText.getText()));

        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.moveCursorToStart(originEditText);
            }
        });
        Thread.sleep(100);
        Assert.assertEquals(0, Selection.getSelectionEnd(originEditText.getText()));

        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                InputMethodx.moveCursorTo(originEditText, originEditText.length() / 2);
            }
        });
        Thread.sleep(100);
        Assert.assertEquals(originEditText.length() / 2, Selection.getSelectionEnd(originEditText.getText()));
    }

    public static class TestActivity extends FragmentActivity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.at_multi_frame);

            getFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame1, new EditOriginFragment())
                    .commit();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.multiFrameAt_frame2, new EditSupportFragment())
                    .commit();
        }

        @NonNull
        public android.app.Fragment getOriginFragment() {
            return getFragmentManager().findFragmentById(R.id.multiFrameAt_frame1);
        }

        @NonNull
        public EditText getOriginFragmentEditTxt() {
            //noinspection ConstantConditions
            return (EditText) getOriginFragment().getView();
        }

        @NonNull
        public android.support.v4.app.Fragment getSupportFragment() {
            return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(R.id.multiFrameAt_frame2));
        }

        @NonNull
        public EditText getSupportFragmentEditTxt() {
            //noinspection ConstantConditions
            return (EditText) getSupportFragment().getView();
        }

        public View getView() {
            return findViewById(android.R.id.content);
        }
    }

    public static class EditOriginFragment extends android.app.Fragment {
        @NonNull
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            EditText editText = new EditText(getContext());
            editText.setText("0123456789");
            return editText;
        }
    }

    public static class EditSupportFragment extends android.support.v4.app.Fragment {
        @NonNull
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            EditText editText = new EditText(getContext());
            editText.setText("0123456789");
            return editText;
        }
    }
}
