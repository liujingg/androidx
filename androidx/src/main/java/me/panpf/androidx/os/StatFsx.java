package me.panpf.androidx.os;

import android.os.Build;
import android.os.StatFs;
import android.support.annotation.NonNull;

/*
 * StatFs related extension methods or properties
 */
public class StatFsx {

    public static long getCompatAvailableBytes(@NonNull StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBytes() : (long) statFs.getAvailableBlocks() * (long) statFs.getBlockSize();
    }

    public static long getCompatFreeBytes(@NonNull StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getFreeBytes() : (long) statFs.getFreeBlocks() * (long) statFs.getBlockSize();
    }

    public static long getCompatTotalBytes(@NonNull StatFs statFs) {
        return Build.VERSION.SDK_INT >= 18 ? statFs.getTotalBytes() : (long) statFs.getBlockCount() * (long) statFs.getBlockSize();
    }
}