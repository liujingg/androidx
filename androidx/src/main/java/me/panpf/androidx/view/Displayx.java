package me.panpf.androidx.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Displayx {

    @NonNull
    public static Point getScreenSize(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point;
    }

    public static int getScreenWidth(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point.x;
    }

    public static int getScreenHeight(@NonNull Context context) {
        Object service = context.getSystemService(Context.WINDOW_SERVICE);
        if (service == null) throw new IllegalStateException("WindowManager not found");

        Point point = new Point();
        ((WindowManager) service).getDefaultDisplay().getSize(point);
        return point.y;
    }

    @NonNull
    public static DisplayMetrics getDisplayMetrics(@NonNull Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getScreenDensity(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenDensityDpi(@NonNull Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static boolean isPortraitOrientation(@NonNull Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean isPortraitOrientation(@NonNull android.support.v4.app.Fragment fragment) {
        return fragment.getResources().getConfiguration().orientation == 1;
    }

    public static boolean isPortraitOrientation(@NonNull Activity activity) {
        return activity.getResources().getConfiguration().orientation == 1;
    }
}
