package me.panpf.androidx.os;

import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Collectionx;
import me.panpf.javax.util.Predicate;

/*
 * Process related tool methods
 */
@SuppressWarnings("WeakerAccess")
public class Processx {

    /**
     * Get the name of the current process
     */
    @Nullable
    public static String getInProcessName(@NonNull Context context) {
        final int myPid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningAppProcessInfo info = activityManager != null ? Collectionx.find(activityManager.getRunningAppProcesses(), new Predicate<ActivityManager.RunningAppProcessInfo>() {
            @Override
            public boolean predicate(@NonNull ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
                return runningAppProcessInfo.pid == myPid;
            }
        }) : null;
        return info != null ? info.processName : null;
    }

    /**
     * Get the suffix of the current process name, for example, the process name is 'com.my.app:push', then the suffix is ​​':push'
     */
    @Nullable
    public static String getInProcessNameSuffix(@NonNull Context $receiver) {
        String processName = getInProcessName($receiver);
        if (processName == null) return null;
        String packageName = $receiver.getPackageName();
        int lastIndex = Stringx.lastIndexOf(processName, packageName, 0, false);
        return lastIndex != -1 ? processName.substring(lastIndex + packageName.length()) : null;
    }

    /**
     * Is in the main process?
     */
    public static boolean isMainProcess(@NonNull Context context) {
        return context.getPackageName().equals(getInProcessName(context));
    }
}
