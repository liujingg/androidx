package me.panpf.androidxkt.test.util

import me.panpf.androidxkt.util.WeakAsyncTask

class TestWeakAsyncTask2 internal constructor(weakAsyncTaskTest: WeakAsyncTaskTest) : WeakAsyncTask<WeakAsyncTaskTest, Int, Int, Int>(weakAsyncTaskTest) {

    override fun doInBackground(page: WeakAsyncTaskTest, params: Array<Int>): Int? = null
}