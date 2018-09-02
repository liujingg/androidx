package me.panpf.androidx.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.jetbrains.annotations.NotNull;

public class Displayx {

    @NotNull
    public static Point getScreenSize(@NotNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point;
    }

    public static int getScreenWidth(@NotNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getScreenHeight(@NotNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point.y;
    }

    @NotNull
    public static DisplayMetrics getDisplayMetrics(@NotNull Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getScreenDensity(@NotNull Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenDensityDpi(@NotNull Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static boolean isPortraitOrientation(@NotNull Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean isPortraitOrientation(@NotNull android.support.v4.app.Fragment fragment) {
        return fragment.getResources().getConfiguration().orientation == 1;
    }

    public static boolean isPortraitOrientation(@NotNull Activity activity) {
        return activity.getResources().getConfiguration().orientation == 1;
    }
}
