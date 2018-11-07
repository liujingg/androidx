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

package me.panpf.androidx.test.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import me.panpf.androidx.test.app.fragment.TestSupportFragment;
import me.panpf.javax.util.Premisex;

public class ActivityxTestFragmentActivity extends FragmentActivity {
    public boolean finished;
    public boolean finishedActivity;
    public boolean finishedActivityFromChild;
    public boolean destoryed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new TestSupportFragment()).commit();
    }

    @Override
    public void finish() {
        super.finish();
        finished = true;
    }

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
        finishedActivity = true;
    }

    @Override
    public void finishActivityFromChild(@NonNull Activity child, int requestCode) {
        super.finishActivityFromChild(child, requestCode);
        finishedActivityFromChild = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryed = true;
    }

    @NonNull
    public android.support.v4.app.Fragment getFragment(){
        return Premisex.requireNotNull(getSupportFragmentManager().findFragmentById(android.R.id.content));
    }

    public View getView(){
        return findViewById(android.R.id.content);
    }
}