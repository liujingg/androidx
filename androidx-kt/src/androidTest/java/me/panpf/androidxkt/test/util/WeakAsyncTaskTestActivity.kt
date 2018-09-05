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