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

package me.panpf.androidxkt.test.app.activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import me.panpf.androidxkt.test.app.fragment.TestSupportFragment
import me.panpf.javaxkt.util.requireNotNull

class ActivityxTestFragmentActivity : FragmentActivity(){
    var finished: Boolean = false
    var finishedActivity: Boolean = false
    var finishedActivityFromChild: Boolean = false
    var destoryed: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, TestSupportFragment()).commit()
    }

    override fun finish() {
        super.finish()
        finished = true
    }

    override fun finishActivity(requestCode: Int) {
        super.finishActivity(requestCode)
        finishedActivity = true
    }

    override fun finishActivityFromChild(child: Activity, requestCode: Int) {
        super.finishActivityFromChild(child, requestCode)
        finishedActivityFromChild = true
    }

    override fun onDestroy() {
        super.onDestroy()
        destoryed = true
    }

    fun getFragment(): android.support.v4.app.Fragment {
        return supportFragmentManager.findFragmentById(android.R.id.content).requireNotNull()
    }

    fun getView(): View {
        return findViewById(android.R.id.content)
    }
}