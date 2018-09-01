package me.panpf.androidx.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

/**
 * Permission related tool methods
 */
public class Permissionx {
    /**
     * Return true if specified permissions have been granted, false otherwise
     */
    public static boolean isGrantPermission(@NotNull Context context, @NotNull String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Return true if all specified permissions have been granted, false otherwise
     */
    public static boolean isGrantPermissions(@NotNull Context context, @NotNull String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
}
