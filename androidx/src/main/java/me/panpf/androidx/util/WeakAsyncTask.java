package me.panpf.androidx.util;

import android.app.Activity;
import android.app.Fragment;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.lang.reflect.Modifier;

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
@SuppressWarnings("WeakerAccess")
public abstract class WeakAsyncTask<Page, Param, Progress, Result> extends AsyncTask<Param, Progress, Result> {

    @NotNull
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
            if (Build.VERSION.SDK_INT >= 17) {
                if (((Activity) page).isDestroyed()) {
                    return null;
                }
            } else {
                if (((Activity) page).isFinishing()) {
                    return null;
                }
            }
        } else if (page instanceof Fragment) {
            if (((Fragment) page).getActivity() == null) {
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

    protected void onPreExecute(@NotNull Page page) {

    }

    @Override
    protected final Result doInBackground(Param[] params) {
        Page page = getPage();
        return page != null ? doInBackground(page, params) : null;
    }

    @Nullable
    protected abstract Result doInBackground(@NotNull Page page, @NotNull Param[] params);

    @Override
    protected final void onProgressUpdate(Progress[] values) {
        super.onProgressUpdate(values);
        Page page = getPage();
        if (page != null) {
            onProgressUpdate(page, values);
        }
    }

    protected void onProgressUpdate(@NotNull Page page, @NotNull Progress[] values) {

    }

    @Override
    protected final void onPostExecute(Result result) {
        super.onPostExecute(result);
        Page page = getPage();
        if (page != null) {
            onPostExecute(page, result);
        }
    }

    protected void onPostExecute(@NotNull Page page, @Nullable Result result) {
    }
}
