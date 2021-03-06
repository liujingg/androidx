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

package me.panpf.androidx.util;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import me.panpf.androidx.app.Activityx;
import me.panpf.androidx.app.Fragmentx;

/**
 * Dedicated to performing asynchronous tasks in an Activity or Fragment, the WeakAsyncTask has the following differences compared to AsyncTask:
 * <p>
 * 1. If it is an inner class, it must be static.
 * <br>
 * 2. Weak reference holding Page
 * <br>
 * 3. Overloads the onPreExecute, doInBackground, onProgressUpdate, and onPostExecute methods,
 * extending a Page parameter, checking whether the Page has been destroyed before calling back related methods
 *
 * @param <Page>
 * @param <Param>
 * @param <Progress>
 * @param <Result>
 */
public abstract class WeakAsyncTask<Page, Param, Progress, Result> extends AsyncTask<Param, Progress, Result> {

    @NonNull
    private WeakReference<Page> reference;

    public WeakAsyncTask(@NonNull Page page) {
        // The class name contains '$' is the inner class, the inner class must be static
        if (getClass().getName().contains("$") && !Modifier.isStatic(getClass().getModifiers())) {
            throw new IllegalArgumentException("If it is an inner class, it must be static: " + getClass().getName());
        }
        this.reference = new WeakReference<>(page);
    }

    public boolean isUnbind() {
        return getPage() == null;
    }

    public boolean isBinded() {
        return getPage() != null;
    }

    private Page getPage() {
        Page page = reference.get();
        if (page == null) {
            return null;
        } else if (page instanceof LifecycleOwner) {
            if (((LifecycleOwner) page).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                return null;
            }
        } else if (page instanceof Activity) {
            if (Activityx.isDestroyedCompat((Activity) page)) {
                return null;
            }
        } else if (page instanceof android.app.Fragment) {
            if (Fragmentx.isDestroyedCompat((android.app.Fragment) page)) {
                return null;
            }
        }
        return page;
    }

    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        Page page = getPage();
        if (page != null) {
            onPreExecute(page);
        }
    }

    protected void onPreExecute(@NonNull Page page) {

    }

    @Override
    protected final Result doInBackground(Param[] params) {
        Page page = getPage();
        return page != null ? doInBackground(page, params) : null;
    }

    @Nullable
    protected abstract Result doInBackground(@NonNull Page page, @NonNull Param[] params);

    @Override
    protected final void onProgressUpdate(Progress[] values) {
        super.onProgressUpdate(values);
        Page page = getPage();
        if (page != null) {
            onProgressUpdate(page, values);
        }
    }

    protected void onProgressUpdate(@NonNull Page page, @NonNull Progress[] values) {

    }

    @Override
    protected final void onPostExecute(Result result) {
        super.onPostExecute(result);
        Page page = getPage();
        if (page != null) {
            onPostExecute(page, result);
        }
    }

    protected void onPostExecute(@NonNull Page page, @Nullable Result result) {
    }
}
