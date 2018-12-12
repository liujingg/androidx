package me.panpf.androidx.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Listening to Lifecycle implements automatic registration, anti-registration of the broadcast receiver, as follows:
 * <pre class="prettyprint">
 * new LifecycleBroadcastReceiver(context, getLifecycle()){
 *     public void onReceive(Context context, Intent intent){
 *         ...
 *     }
 * }.registerCreateDestroy(new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
 * </pre>
 */
public abstract class LifecycleBroadcastReceiver extends BroadcastReceiver {

    @NonNull
    private Context appContext;
    @NonNull
    private Lifecycle lifecycle;
    @Nullable
    private LifecycleObserver lifecycleObserver;

    public LifecycleBroadcastReceiver(@NonNull Context context, @NonNull Lifecycle lifecycle) {
        this.appContext = context.getApplicationContext();
        this.lifecycle = lifecycle;
    }

    public LifecycleBroadcastReceiver(@NonNull Fragment fragment) {
        this(Contextx.requireContext(fragment), fragment.getLifecycle());
    }

    public LifecycleBroadcastReceiver(@NonNull FragmentActivity activity) {
        this(activity, activity.getLifecycle());
    }

    public synchronized boolean registerCreateDestroy(@NonNull IntentFilter filter) {
        if (lifecycleObserver == null) {
            try {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    appContext.registerReceiver(this, filter);
                }
                lifecycle.addObserver(lifecycleObserver = new CreateDestroyObserver(appContext, this, filter));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public synchronized boolean registerStartStop(@NonNull IntentFilter filter) {
        if (lifecycleObserver == null) {
            try {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    appContext.registerReceiver(this, filter);
                }
                lifecycle.addObserver(lifecycleObserver = new StartStopObserver(appContext, this, filter));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public synchronized boolean registerResumePause(@NonNull IntentFilter filter) {
        if (lifecycleObserver == null) {
            try {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    appContext.registerReceiver(this, filter);
                }
                lifecycle.addObserver(lifecycleObserver = new ResumePauseObserver(appContext, this, filter));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static class CreateDestroyObserver implements LifecycleObserver {
        @NonNull
        private Context appContext;
        @NonNull
        private LifecycleBroadcastReceiver receiver;
        @NonNull
        private IntentFilter filter;

        CreateDestroyObserver(@NonNull Context context, @NonNull LifecycleBroadcastReceiver receiver, @NonNull IntentFilter filter) {
            this.appContext = context.getApplicationContext();
            this.receiver = receiver;
            this.filter = filter;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void onCreate() {
            try {
                appContext.registerReceiver(receiver, filter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            try {
                appContext.unregisterReceiver(receiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class StartStopObserver implements LifecycleObserver {
        @NonNull
        private Context appContext;
        @NonNull
        private LifecycleBroadcastReceiver receiver;
        @NonNull
        private IntentFilter filter;

        StartStopObserver(@NonNull Context context, @NonNull LifecycleBroadcastReceiver receiver, @NonNull IntentFilter filter) {
            this.appContext = context.getApplicationContext();
            this.receiver = receiver;
            this.filter = filter;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            try {
                appContext.registerReceiver(receiver, filter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop() {
            try {
                appContext.unregisterReceiver(receiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ResumePauseObserver implements LifecycleObserver {
        @NonNull
        private Context appContext;
        @NonNull
        private LifecycleBroadcastReceiver receiver;
        @NonNull
        private IntentFilter filter;

        ResumePauseObserver(@NonNull Context context, @NonNull LifecycleBroadcastReceiver receiver, @NonNull IntentFilter filter) {
            this.appContext = context.getApplicationContext();
            this.receiver = receiver;
            this.filter = filter;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            try {
                appContext.registerReceiver(receiver, filter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            try {
                appContext.unregisterReceiver(receiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
