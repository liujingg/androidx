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

package me.panpf.androidx.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.arch.lifecycle.Lifecycle;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import me.panpf.javax.lang.Classx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Comparisonx;
import me.panpf.javax.util.LazyValue;
import me.panpf.javax.util.Premisex;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Activityx {

    private static final LazyValue<String> ACTION_NOT_VIEW = new LazyValue<String>() {
        @NonNull
        @Override
        public String get() {
            return "Intent action not VIEW";
        }
    };
    private static final LazyValue<String> DATA_IS_NULL = new LazyValue<String>() {
        @NonNull
        @Override
        public String get() {
            return "Intent data is null";
        }
    };

    /**
     * Return true if the activity has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull Activity activity) {
        // First determine that FragmentActivity can use the compatible isDestroyed method in versions below 17.
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return activity.isDestroyed() || activity.isFinishing();
        } else {
            return activity.isFinishing();
        }
    }

    /**
     * Return true if the activity has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull FragmentActivity activity) {
        return activity.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
    }


    /**
     * Convert a translucent themed Activity
     * [android.R.attr.windowIsTranslucent] to a fullscreen opaque
     * Activity.
     * <p>
     * Call this whenever the background of a translucent Activity has changed
     * to become opaque. Doing so will allow the [android.view.Surface] of
     * the Activity behind to be released.
     * <p>
     * This call has no effect on non-translucent activities or on activities
     * with the [android.R.attr.windowIsFloating] attribute.
     */
    public static boolean convertFromTranslucent(@NonNull Activity activity) {
        try {
            Classx.callMethod(activity, "convertFromTranslucent");
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Convert a translucent themed Activity
     * [android.R.attr.windowIsTranslucent] back from opaque to
     * translucent following a call to
     * [.convertActivityFromTranslucent] .
     * <p>
     * <p>
     * Calling this allows the Activity behind this one to be seen again. Once
     * all such Activities have been redrawn
     * <p>
     * <p>
     * This call has no effect on non-translucent activities or on activities
     * with the [android.R.attr.windowIsFloating] attribute.
     */
    public static boolean convertToTranslucent(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                ActivityOptions options = (ActivityOptions) Classx.callMethod(activity, "getActivityOptions");
                Field mTranslucentCallbackField = Classx.getFieldWithParent(activity, "mTranslucentCallback");
                Method method = Classx.getMethodWithParent(activity, "convertToTranslucent", mTranslucentCallbackField.getType(), ActivityOptions.class);
                Classx.callMethod(activity, method, Classx.getFieldValue(activity, mTranslucentCallbackField), options);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                Field mTranslucentCallbackField = Classx.getFieldWithParent(activity, "mTranslucentCallback");
                Method method = Classx.getMethodWithParent(activity, "convertToTranslucent", mTranslucentCallbackField.getType());
                Classx.callMethod(activity, method, Classx.getFieldValue(activity, mTranslucentCallbackField));
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    /**
     * If the own or parent activity implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplWithParent(@NonNull Activity activity, @NonNull Class<T> clazz) {
        Activity parent = activity;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parent = parent.getParent();
            }
        }
        return null;
    }


    @NonNull
    public static Context appContext(@NonNull Activity activity) {
        return activity.getApplicationContext();
    }


    /**
     * Test if you can start Activity
     */
    public static boolean canStart(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfoList != null && resolveInfoList.size() > 0;
    }


    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Context context, @NonNull Intent intent, @NonNull Bundle options) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(intent, options);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Safely launch an Activity, catch ActivityNotFoundException and return false
     */
    public static boolean safeStart(@NonNull Context context, @NonNull Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void start(@NonNull Context context, @NonNull Class<? extends Activity> clazz, @NonNull Bundle options) {
        safeStart(context, new Intent(context, clazz), options);
    }

    public static void start(@NonNull Context context, @NonNull Class<? extends Activity> clazz) {
        safeStart(context, new Intent(context, clazz));
    }

    public static void start(@NonNull android.support.v4.app.Fragment fragment, @NonNull Class<? extends Activity> clazz, @NonNull Bundle options) {
        fragment.startActivity(new Intent(Fragmentx.requireContext(fragment), clazz), options);
    }

    public static void start(@NonNull android.support.v4.app.Fragment fragment, @NonNull Class<? extends Activity> clazz) {
        fragment.startActivity(new Intent(Fragmentx.requireContext(fragment), clazz));
    }

    public static void start(@NonNull android.app.Fragment fragment, @NonNull Class<? extends Activity> clazz, @NonNull Bundle options) {
        fragment.startActivity(new Intent(Fragmentx.requireContext(fragment), clazz), options);
    }

    public static void start(@NonNull android.app.Fragment fragment, @NonNull Class<? extends Activity> clazz) {
        fragment.startActivity(new Intent(Fragmentx.requireContext(fragment), clazz));
    }

    public static void start(@NonNull View view, @NonNull Class<? extends Activity> clazz, @NonNull Bundle options) {
        safeStart(view.getContext(), new Intent(view.getContext(), clazz), options);
    }

    public static void start(@NonNull View view, @NonNull Class<? extends Activity> clazz) {
        safeStart(view.getContext(), new Intent(view.getContext(), clazz));
    }

    public static void start(@NonNull View view, @NonNull Intent intent, @NonNull Bundle options) {
        safeStart(view.getContext(), intent, options);
    }

    public static void start(@NonNull View view, @NonNull Intent intent) {
        safeStart(view.getContext(), intent);
    }


    /* ************************************* Intent Args ***************************************** */


    public static byte readByteArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        return activity.getIntent().getByteExtra(argName, defaultValue);
    }


    @NonNull
    public static byte[] readByteArrayArg(@NonNull Activity activity, @NonNull String argName) {
        byte[] bytes = activity.getIntent().getByteArrayExtra(argName);
        if (bytes != null) return bytes;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull byte[] defaultValue) {
        byte[] bytes = activity.getIntent().getByteArrayExtra(argName);
        return bytes != null ? bytes : defaultValue;
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getByteArrayExtra(argName);
    }


    public static short readShortArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        return activity.getIntent().getShortExtra(argName, defaultValue);
    }


    @NonNull
    public static short[] readShortArrayArg(@NonNull Activity activity, @NonNull String argName) {
        short[] shorts = activity.getIntent().getShortArrayExtra(argName);
        if (shorts != null) return shorts;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull short[] defaultValue) {
        short[] shorts = activity.getIntent().getShortArrayExtra(argName);
        return shorts != null ? shorts : defaultValue;
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getShortArrayExtra(argName);
    }


    public static int readIntArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        return activity.getIntent().getIntExtra(argName, defaultValue);
    }


    @NonNull
    public static int[] readIntArrayArg(@NonNull Activity activity, @NonNull String argName) {
        int[] ints = activity.getIntent().getIntArrayExtra(argName);
        if (ints != null) return ints;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull int[] defaultValue) {
        int[] ints = activity.getIntent().getIntArrayExtra(argName);
        return ints != null ? ints : defaultValue;
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getIntArrayExtra(argName);
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArg(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<Integer> integers = activity.getIntent().getIntegerArrayListExtra(argName);
        if (integers != null) return integers;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<Integer> defaultValue) {
        ArrayList<Integer> integers = activity.getIntent().getIntegerArrayListExtra(argName);
        return integers != null ? integers : defaultValue;
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getIntegerArrayListExtra(argName);
    }


    public static long readLongArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        return activity.getIntent().getLongExtra(argName, defaultValue);
    }


    @NonNull
    public static long[] readLongArrayArg(@NonNull Activity activity, @NonNull String argName) {
        long[] longs = activity.getIntent().getLongArrayExtra(argName);
        if (longs != null) return longs;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull long[] defaultValue) {
        long[] longs = activity.getIntent().getLongArrayExtra(argName);
        return longs != null ? longs : defaultValue;
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getLongArrayExtra(argName);
    }


    public static float readFloatArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        return activity.getIntent().getFloatExtra(argName, defaultValue);
    }


    @NonNull
    public static float[] readFloatArrayArg(@NonNull Activity activity, @NonNull String argName) {
        float[] floats = activity.getIntent().getFloatArrayExtra(argName);
        if (floats != null) return floats;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull float[] defaultValue) {
        float[] floats = activity.getIntent().getFloatArrayExtra(argName);
        return floats != null ? floats : defaultValue;
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getFloatArrayExtra(argName);
    }


    public static double readDoubleArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        return activity.getIntent().getDoubleExtra(argName, defaultValue);
    }


    @NonNull
    public static double[] readDoubleArrayArg(@NonNull Activity activity, @NonNull String argName) {
        double[] doubles = activity.getIntent().getDoubleArrayExtra(argName);
        if (doubles != null) return doubles;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull double[] defaultValue) {
        double[] doubles = activity.getIntent().getDoubleArrayExtra(argName);
        return doubles != null ? doubles : defaultValue;
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getDoubleArrayExtra(argName);
    }


    public static boolean readBooleanArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        return activity.getIntent().getBooleanExtra(argName, defaultValue);
    }


    @NonNull
    public static boolean[] readBooleanArrayArg(@NonNull Activity activity, @NonNull String argName) {
        boolean[] booleans = activity.getIntent().getBooleanArrayExtra(argName);
        if (booleans != null) return booleans;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull boolean[] defaultValue) {
        boolean[] booleans = activity.getIntent().getBooleanArrayExtra(argName);
        return booleans != null ? booleans : defaultValue;
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getBooleanArrayExtra(argName);
    }


    public static char readCharArgOr(@NonNull Activity activity, @NonNull String argName, char defaultValue) {
        return activity.getIntent().getCharExtra(argName, defaultValue);
    }


    @NonNull
    public static char[] readCharArrayArg(@NonNull Activity activity, @NonNull String argName) {
        char[] chars = activity.getIntent().getCharArrayExtra(argName);
        if (chars != null) return chars;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull char[] defaultValue) {
        char[] chars = activity.getIntent().getCharArrayExtra(argName);
        return chars != null ? chars : defaultValue;
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharArrayExtra(argName);
    }


    @NonNull
    public static CharSequence readCharSequenceArg(@NonNull Activity activity, @NonNull String argName) {
        CharSequence charSequence = activity.getIntent().getCharSequenceExtra(argName);
        if (charSequence != null) return charSequence;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull CharSequence defaultValue) {
        CharSequence charSequence = activity.getIntent().getCharSequenceExtra(argName);
        return charSequence != null && !Comparisonx.areEqual(charSequence.toString().trim(), "") ? charSequence : defaultValue;
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharSequenceExtra(argName);
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArg(@NonNull Activity activity, @NonNull String argName) {
        CharSequence[] charSequences = activity.getIntent().getCharSequenceArrayExtra(argName);
        if (charSequences != null) return charSequences;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull CharSequence[] defaultValue) {
        CharSequence[] charSequences = activity.getIntent().getCharSequenceArrayExtra(argName);
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharSequenceArrayExtra(argName);
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<CharSequence> charSequences = activity.getIntent().getCharSequenceArrayListExtra(argName);
        if (charSequences != null) return charSequences;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<CharSequence> defaultValue) {
        ArrayList<CharSequence> charSequences = activity.getIntent().getCharSequenceArrayListExtra(argName);
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getCharSequenceArrayListExtra(argName);
    }


    @NonNull
    public static String readStringArg(@NonNull Activity activity, @NonNull String argName) {
        String string = activity.getIntent().getStringExtra(argName);
        if (string != null) return string;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static String readStringArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull String defaultValue) {
        String string = activity.getIntent().getStringExtra(argName);
        return string != null && !Comparisonx.areEqual(string.trim(), "") ? string : defaultValue;
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getStringExtra(argName);
    }


    @NonNull
    public static String[] readStringArrayArg(@NonNull Activity activity, @NonNull String argName) {
        String[] strings = activity.getIntent().getStringArrayExtra(argName);
        if (strings != null) return strings;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull String[] defaultValue) {
        String[] strings = activity.getIntent().getStringArrayExtra(argName);
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getStringArrayExtra(argName);
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArg(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<String> strings = activity.getIntent().getStringArrayListExtra(argName);
        if (strings != null) return strings;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<String> defaultValue) {
        ArrayList<String> strings = activity.getIntent().getStringArrayListExtra(argName);
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getStringArrayListExtra(argName);
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArg(@NonNull Activity activity, @NonNull String argName) {
        V parcelable = activity.getIntent().getParcelableExtra(argName);
        if (parcelable != null) return parcelable;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull V defaultValue) {
        V parcelable = activity.getIntent().getParcelableExtra(argName);
        return parcelable != null ? parcelable : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getParcelableExtra(argName);
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        V[] parcelables = (V[]) activity.getIntent().getParcelableArrayExtra(argName);
        if (parcelables != null) return parcelables;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull V[] defaultValue) {
        //noinspection unchecked
        V[] parcelables = (V[]) activity.getIntent().getParcelableArrayExtra(argName);
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        return (V[]) activity.getIntent().getParcelableArrayExtra(argName);
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NonNull Activity activity, @NonNull String argName) {
        ArrayList<V> parcelables = activity.getIntent().getParcelableArrayListExtra(argName);
        if (parcelables != null) return parcelables;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull ArrayList<V> defaultValue) {
        ArrayList<V> parcelables = activity.getIntent().getParcelableArrayListExtra(argName);
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getParcelableArrayListExtra(argName);
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArg(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        V serializable = (V) activity.getIntent().getSerializableExtra(argName);
        if (serializable != null) return serializable;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull V defaultValue) {
        //noinspection unchecked
        V serializable = (V) activity.getIntent().getSerializableExtra(argName);
        return serializable != null ? serializable : defaultValue;
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        //noinspection unchecked
        return (V) activity.getIntent().getSerializableExtra(argName);
    }


    @NonNull
    public static Bundle readBundleArg(@NonNull Activity activity, @NonNull String argName) {
        Bundle bundle = activity.getIntent().getBundleExtra(argName);
        if (bundle != null) return bundle;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull Bundle defaultValue) {
        Bundle bundle = activity.getIntent().getBundleExtra(argName);
        return bundle != null ? bundle : defaultValue;
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        return activity.getIntent().getBundleExtra(argName);
    }


    @NonNull
    public static Bundle readExtrasArg(@NonNull Activity activity) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) return extras;
        throw (new IllegalArgumentException("Not found 'extras'"));
    }

    @NonNull
    public static Bundle readExtrasArgOr(@NonNull Activity activity, @NonNull Bundle defaultValue) {
        Bundle extras = activity.getIntent().getExtras();
        return extras != null ? extras : defaultValue;
    }

    @Nullable
    public static Bundle readExtrasArgOrNull(@NonNull Activity activity) {
        return activity.getIntent().getExtras();
    }


    /* ************************************* Uri Args ***************************************** */


    public static byte readByteUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Byte.parseByte(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static byte readByteUriArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Byte.parseByte(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Byte readByteUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Byte.parseByte(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static short readShortUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Short.parseShort(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static short readShortUriArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Short.parseShort(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Short readShortUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Short.parseShort(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static int readIntUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Integer.parseInt(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static int readIntUriArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Integer readIntUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static long readLongUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Long.parseLong(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static long readLongUriArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Long readLongUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static float readFloatUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Float.parseFloat(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static float readFloatUriArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Float readFloatUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Float.parseFloat(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static double readDoubleUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Double.parseDouble(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static double readDoubleUriArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Double readDoubleUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean readBooleanUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Boolean.parseBoolean(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static boolean readBooleanUriArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return Boolean.parseBoolean(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static Boolean readBooleanUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return Boolean.parseBoolean(value.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    @NonNull
    public static String readStringUriArg(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return value;
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    @NonNull
    public static String readStringUriArgOr(@NonNull Activity activity, @NonNull String argName, @NonNull String defaultValue) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return defaultValue;
        try {
            return value;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    @Nullable
    public static String readStringUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if ("".equals(value.trim())) return null;
        try {
            return value;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    /* ************************************* Uri Intent Args ***************************************** */


    public static byte readByteUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        Byte uriValue = readByteUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readByteArgOr(activity, argName, defaultValue);
    }

    public static short readShortUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        Short uriValue = readShortUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readShortArgOr(activity, argName, defaultValue);
    }

    public static int readIntUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        Integer uriValue = readIntUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readIntArgOr(activity, argName, defaultValue);
    }

    public static long readLongUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        Long uriValue = readLongUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readLongArgOr(activity, argName, defaultValue);
    }

    public static float readFloatUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        Float uriValue = readFloatUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readFloatArgOr(activity, argName, defaultValue);
    }

    public static double readDoubleUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        Double uriValue = readDoubleUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readDoubleArgOr(activity, argName, defaultValue);
    }

    public static boolean readBooleanUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        Boolean uriValue = readBooleanUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readBooleanArgOr(activity, argName, defaultValue);
    }

    @NonNull
    public static String readStringUriIntentArg(@NonNull Activity activity, @NonNull String argName) {
        String uriValue = readStringUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readStringArg(activity, argName);
    }

    @NonNull
    public static String readStringUriIntentArgOr(@NonNull Activity activity, @NonNull String argName, String defaultValue) {
        String uriValue = readStringUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readStringArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringUriIntentArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        String uriValue = readStringUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readStringArgOrNull(activity, argName);
    }


    /* ************************************* Intent Uri Args ***************************************** */


    public static byte readByteIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, byte defaultValue) {
        byte uriValue = readByteArgOr(activity, argName, Byte.MIN_VALUE);
        return uriValue != Byte.MIN_VALUE ? uriValue : readByteUriArgOr(activity, argName, defaultValue);
    }

    public static short readShortIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, short defaultValue) {
        short uriValue = readShortArgOr(activity, argName, Short.MIN_VALUE);
        return uriValue != Short.MIN_VALUE ? uriValue : readShortUriArgOr(activity, argName, defaultValue);
    }

    public static int readIntIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, int defaultValue) {
        int uriValue = readIntArgOr(activity, argName, Integer.MIN_VALUE);
        return uriValue != Integer.MIN_VALUE ? uriValue : readIntUriArgOr(activity, argName, defaultValue);
    }

    public static long readLongIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, long defaultValue) {
        long uriValue = readLongArgOr(activity, argName, Long.MIN_VALUE);
        return uriValue != Long.MIN_VALUE ? uriValue : readLongUriArgOr(activity, argName, defaultValue);
    }

    public static float readFloatIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, float defaultValue) {
        float uriValue = readFloatArgOr(activity, argName, Float.MIN_VALUE);
        return uriValue != Float.MIN_VALUE ? uriValue : readFloatUriArgOr(activity, argName, defaultValue);
    }

    public static double readDoubleIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, double defaultValue) {
        double uriValue = readDoubleArgOr(activity, argName, Double.MIN_VALUE);
        return uriValue != Double.MIN_VALUE ? uriValue : readDoubleUriArgOr(activity, argName, defaultValue);
    }

    public static boolean readBooleanIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, boolean defaultValue) {
        boolean uriValue = readBooleanArgOr(activity, argName, false);
        return uriValue || readBooleanUriArgOr(activity, argName, defaultValue);
    }

    @NonNull
    public static String readStringIntentUriArg(@NonNull Activity activity, @NonNull String argName) {
        String uriValue = readStringArgOrNull(activity, argName);
        return uriValue != null && Stringx.isSafe(uriValue) ? uriValue : readStringUriArg(activity, argName);
    }

    @NonNull
    public static String readStringIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, String defaultValue) {
        String uriValue = readStringArgOrNull(activity, argName);
        return uriValue != null && Stringx.isSafe(uriValue) ? uriValue : readStringUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        String uriValue = readStringArgOrNull(activity, argName);
        return uriValue != null && Stringx.isSafe(uriValue) ? uriValue : readStringUriArgOrNull(activity, argName);
    }
}