package me.panpf.androidxkt.util

import android.app.Activity
import android.app.Fragment
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.os.AsyncTask
import android.os.Build

import java.lang.ref.WeakReference
import java.lang.reflect.Modifier

/**
 * Dedicated to performing asynchronous tasks in an Activity or Fragment, the WeakAsyncTask has the following differences compared to AsyncTask:
 *
 * 1. If it is an inner class, it must be static.
 * <br></br>
 * 2. Weak reference holding Page
 * <br></br>
 * 3. Overloads the onPreExecute, doInBackground, onProgressUpdate, and onPostExecute methods,
 * extending a Page parameter, checking whether the Page has been destroyed before calling back related methods
 *
 * @param <Page>
 * @param <Params>
 * @param <Progress>
 * @param <Result>
 */
abstract class WeakAsyncTask<Page, Param, Progress, Result>(page: Page) : AsyncTask<Param, Progress, Result>() {

    private val reference: WeakReference<Page>

    val isUnbind: Boolean
        get() = page == null

    val isBinded: Boolean
        get() = page != null

    private val page: Page?
        get() {
            val page = reference.get()
            if (page == null) {
                return null
            } else if (page is LifecycleOwner) {
                if ((page as LifecycleOwner).lifecycle.currentState == Lifecycle.State.DESTROYED) {
                    return null
                }
            } else if (page is Activity) {
                if (Build.VERSION.SDK_INT >= 17) {
                    if ((page as Activity).isDestroyed) {
                        return null
                    }
                } else {
                    if ((page as Activity).isFinishing) {
                        return null
                    }
                }
            } else if (page is Fragment) {
                if ((page as Fragment).activity == null) {
                    return null
                }
            }
            return page
        }

    init {
        // The class name contains '$' is the inner class, the inner class must be static
        if (javaClass.name.contains("$") && !Modifier.isStatic(javaClass.modifiers)) {
            throw IllegalArgumentException("If it is an inner class, it must be static: " + javaClass.name)
        }
        this.reference = WeakReference(page)
    }

    final override fun onPreExecute() {
        super.onPreExecute()
        page?.let { onPreExecute(it) }
    }

    open fun onPreExecute(page: Page) {

    }

    final override fun doInBackground(params: Array<Param>): Result? {
        return page?.let { doInBackground(it, params) }
    }

    abstract fun doInBackground(page: Page, params: Array<Param>): Result?

    final override fun onProgressUpdate(values: Array<Progress>) {
        super.onProgressUpdate(*values)
        page?.let { onProgressUpdate(it, values) }
    }

    protected open fun onProgressUpdate(page: Page, values: Array<Progress>) {

    }

    final override fun onPostExecute(result: Result) {
        super.onPostExecute(result)
        page?.let { onPostExecute(it, result) }
    }

    open fun onPostExecute(page: Page, result: Result?) {}
}
