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

package me.panpf.androidxkt.test.util

import android.app.Activity
import android.os.Bundle

import me.panpf.androidxkt.util.WeakAsyncTask

class WeakAsyncTaskTestActivity : Activity() {

    var result = "None"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LoadDataTask(this).execute(0)
    }

    private class LoadDataTask internal constructor(weakAsyncTaskTestActivity: WeakAsyncTaskTestActivity) : WeakAsyncTask<WeakAsyncTaskTestActivity, Int, Int, Int>(weakAsyncTaskTestActivity) {

        override fun doInBackground(page: WeakAsyncTaskTestActivity, params: Array<Int>): Int? {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(page: WeakAsyncTaskTestActivity, result: Int?) {
            super.onPostExecute(page, result)
            page.result = "onPostExecute"
        }
    }
}