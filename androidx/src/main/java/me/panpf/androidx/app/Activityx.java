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
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import me.panpf.javax.lang.Classx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Comparisonx;
import me.panpf.javax.util.LazyValue;
import me.panpf.javax.util.Premisex;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Activityx {

    private static final LazyValue<String> ACTION_NOT_VIEW = new LazyValue<String>() {
        @NotNull
        @Override
        public String get() {
            return "Intent action not VIEW";
        }
    };
    private static final LazyValue<String> DATA_IS_NULL = new LazyValue<String>() {
        @NotNull
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


    @NotNull
    public static Context appContext(@NonNull Activity activity) {
        return activity.getApplicationContext();
    }


    /* ************************************* Intent Args ***************************************** */


    public static byte readByteArgOr(@NotNull Activity activity, @NotNull String argName, byte defaultValue) {
        return activity.getIntent().getByteExtra(argName, defaultValue);
    }


    @NotNull
    public static byte[] readByteArrayArg(@NotNull Activity activity, @NotNull String argName) {
        byte[] bytes = activity.getIntent().getByteArrayExtra(argName);
        if (bytes != null) return bytes;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static byte[] readByteArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull byte[] defaultValue) {
        byte[] bytes = activity.getIntent().getByteArrayExtra(argName);
        return bytes != null ? bytes : defaultValue;
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getByteArrayExtra(argName);
    }


    public static short readShortArgOr(@NotNull Activity activity, @NotNull String argName, short defaultValue) {
        return activity.getIntent().getShortExtra(argName, defaultValue);
    }


    @NotNull
    public static short[] readShortArrayArg(@NotNull Activity activity, @NotNull String argName) {
        short[] shorts = activity.getIntent().getShortArrayExtra(argName);
        if (shorts != null) return shorts;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static short[] readShortArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull short[] defaultValue) {
        short[] shorts = activity.getIntent().getShortArrayExtra(argName);
        return shorts != null ? shorts : defaultValue;
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getShortArrayExtra(argName);
    }


    public static int readIntArgOr(@NotNull Activity activity, @NotNull String argName, int defaultValue) {
        return activity.getIntent().getIntExtra(argName, defaultValue);
    }


    @NotNull
    public static int[] readIntArrayArg(@NotNull Activity activity, @NotNull String argName) {
        int[] ints = activity.getIntent().getIntArrayExtra(argName);
        if (ints != null) return ints;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static int[] readIntArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull int[] defaultValue) {
        int[] ints = activity.getIntent().getIntArrayExtra(argName);
        return ints != null ? ints : defaultValue;
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getIntArrayExtra(argName);
    }


    @NotNull
    public static ArrayList<Integer> readIntArrayListArg(@NotNull Activity activity, @NotNull String argName) {
        ArrayList<Integer> integers = activity.getIntent().getIntegerArrayListExtra(argName);
        if (integers != null) return integers;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull ArrayList<Integer> defaultValue) {
        ArrayList<Integer> integers = activity.getIntent().getIntegerArrayListExtra(argName);
        return integers != null ? integers : defaultValue;
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getIntegerArrayListExtra(argName);
    }


    public static long readLongArgOr(@NotNull Activity activity, @NotNull String argName, long defaultValue) {
        return activity.getIntent().getLongExtra(argName, defaultValue);
    }


    @NotNull
    public static long[] readLongArrayArg(@NotNull Activity activity, @NotNull String argName) {
        long[] longs = activity.getIntent().getLongArrayExtra(argName);
        if (longs != null) return longs;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static long[] readLongArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull long[] defaultValue) {
        long[] longs = activity.getIntent().getLongArrayExtra(argName);
        return longs != null ? longs : defaultValue;
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getLongArrayExtra(argName);
    }


    public static float readFloatArgOr(@NotNull Activity activity, @NotNull String argName, float defaultValue) {
        return activity.getIntent().getFloatExtra(argName, defaultValue);
    }


    @NotNull
    public static float[] readFloatArrayArg(@NotNull Activity activity, @NotNull String argName) {
        float[] floats = activity.getIntent().getFloatArrayExtra(argName);
        if (floats != null) return floats;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static float[] readFloatArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull float[] defaultValue) {
        float[] floats = activity.getIntent().getFloatArrayExtra(argName);
        return floats != null ? floats : defaultValue;
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getFloatArrayExtra(argName);
    }


    public static double readDoubleArgOr(@NotNull Activity activity, @NotNull String argName, double defaultValue) {
        return activity.getIntent().getDoubleExtra(argName, defaultValue);
    }


    @NotNull
    public static double[] readDoubleArrayArg(@NotNull Activity activity, @NotNull String argName) {
        double[] doubles = activity.getIntent().getDoubleArrayExtra(argName);
        if (doubles != null) return doubles;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static double[] readDoubleArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull double[] defaultValue) {
        double[] doubles = activity.getIntent().getDoubleArrayExtra(argName);
        return doubles != null ? doubles : defaultValue;
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getDoubleArrayExtra(argName);
    }


    public static boolean readBooleanArgOr(@NotNull Activity activity, @NotNull String argName, boolean defaultValue) {
        return activity.getIntent().getBooleanExtra(argName, defaultValue);
    }


    @NotNull
    public static boolean[] readBooleanArrayArg(@NotNull Activity activity, @NotNull String argName) {
        boolean[] booleans = activity.getIntent().getBooleanArrayExtra(argName);
        if (booleans != null) return booleans;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static boolean[] readBooleanArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull boolean[] defaultValue) {
        boolean[] booleans = activity.getIntent().getBooleanArrayExtra(argName);
        return booleans != null ? booleans : defaultValue;
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getBooleanArrayExtra(argName);
    }


    public static char readCharArgOr(@NotNull Activity activity, @NotNull String argName, char defaultValue) {
        return activity.getIntent().getCharExtra(argName, defaultValue);
    }


    @NotNull
    public static char[] readCharArrayArg(@NotNull Activity activity, @NotNull String argName) {
        char[] chars = activity.getIntent().getCharArrayExtra(argName);
        if (chars != null) return chars;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static char[] readCharArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull char[] defaultValue) {
        char[] chars = activity.getIntent().getCharArrayExtra(argName);
        return chars != null ? chars : defaultValue;
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getCharArrayExtra(argName);
    }


    @NotNull
    public static CharSequence readCharSequenceArg(@NotNull Activity activity, @NotNull String argName) {
        CharSequence charSequence = activity.getIntent().getCharSequenceExtra(argName);
        if (charSequence != null) return charSequence;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static CharSequence readCharSequenceArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull CharSequence defaultValue) {
        CharSequence charSequence = activity.getIntent().getCharSequenceExtra(argName);
        return charSequence != null && !Comparisonx.areEqual(charSequence.toString().trim(), "") ? charSequence : defaultValue;
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getCharSequenceExtra(argName);
    }


    @NotNull
    public static CharSequence[] readCharSequenceArrayArg(@NotNull Activity activity, @NotNull String argName) {
        CharSequence[] charSequences = activity.getIntent().getCharSequenceArrayExtra(argName);
        if (charSequences != null) return charSequences;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull CharSequence[] defaultValue) {
        CharSequence[] charSequences = activity.getIntent().getCharSequenceArrayExtra(argName);
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getCharSequenceArrayExtra(argName);
    }


    @NotNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NotNull Activity activity, @NotNull String argName) {
        ArrayList<CharSequence> charSequences = activity.getIntent().getCharSequenceArrayListExtra(argName);
        if (charSequences != null) return charSequences;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull ArrayList<CharSequence> defaultValue) {
        ArrayList<CharSequence> charSequences = activity.getIntent().getCharSequenceArrayListExtra(argName);
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getCharSequenceArrayListExtra(argName);
    }


    @NotNull
    public static String readStringArg(@NotNull Activity activity, @NotNull String argName) {
        String string = activity.getIntent().getStringExtra(argName);
        if (string != null) return string;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static String readStringArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull String defaultValue) {
        String string = activity.getIntent().getStringExtra(argName);
        return string != null && !Comparisonx.areEqual(string.trim(), "") ? string : defaultValue;
    }

    @Nullable
    public static String readStringArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getStringExtra(argName);
    }


    @NotNull
    public static String[] readStringArrayArg(@NotNull Activity activity, @NotNull String argName) {
        String[] strings = activity.getIntent().getStringArrayExtra(argName);
        if (strings != null) return strings;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static String[] readStringArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull String[] defaultValue) {
        String[] strings = activity.getIntent().getStringArrayExtra(argName);
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getStringArrayExtra(argName);
    }


    @NotNull
    public static ArrayList<String> readStringArrayListArg(@NotNull Activity activity, @NotNull String argName) {
        ArrayList<String> strings = activity.getIntent().getStringArrayListExtra(argName);
        if (strings != null) return strings;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static ArrayList<String> readStringArrayListArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull ArrayList<String> defaultValue) {
        ArrayList<String> strings = activity.getIntent().getStringArrayListExtra(argName);
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getStringArrayListExtra(argName);
    }


    @NotNull
    public static <V extends Parcelable> V readParcelableArg(@NotNull Activity activity, @NotNull String argName) {
        V parcelable = activity.getIntent().getParcelableExtra(argName);
        if (parcelable != null) return parcelable;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static <V extends Parcelable> V readParcelableArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull V defaultValue) {
        V parcelable = activity.getIntent().getParcelableExtra(argName);
        return parcelable != null ? parcelable : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getParcelableExtra(argName);
    }


    @NotNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NotNull Activity activity, @NotNull String argName) {
        //noinspection unchecked
        V[] parcelables = (V[]) activity.getIntent().getParcelableArrayExtra(argName);
        if (parcelables != null) return parcelables;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull V[] defaultValue) {
        //noinspection unchecked
        V[] parcelables = (V[]) activity.getIntent().getParcelableArrayExtra(argName);
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        //noinspection unchecked
        return (V[]) activity.getIntent().getParcelableArrayExtra(argName);
    }


    @NotNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NotNull Activity activity, @NotNull String argName) {
        ArrayList<V> parcelables = activity.getIntent().getParcelableArrayListExtra(argName);
        if (parcelables != null) return parcelables;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull ArrayList<V> defaultValue) {
        ArrayList<V> parcelables = activity.getIntent().getParcelableArrayListExtra(argName);
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getParcelableArrayListExtra(argName);
    }


    @NotNull
    public static <V extends Serializable> V readSerializableArg(@NotNull Activity activity, @NotNull String argName) {
        //noinspection unchecked
        V serializable = (V) activity.getIntent().getSerializableExtra(argName);
        if (serializable != null) return serializable;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static <V extends Serializable> V readSerializableArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull V defaultValue) {
        //noinspection unchecked
        V serializable = (V) activity.getIntent().getSerializableExtra(argName);
        return serializable != null ? serializable : defaultValue;
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        //noinspection unchecked
        return (V) activity.getIntent().getSerializableExtra(argName);
    }


    @NotNull
    public static Bundle readBundleArg(@NotNull Activity activity, @NotNull String argName) {
        Bundle bundle = activity.getIntent().getBundleExtra(argName);
        if (bundle != null) return bundle;
        throw new IllegalArgumentException(String.format("Param '%s' not found", argName));
    }

    @NotNull
    public static Bundle readBundleArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull Bundle defaultValue) {
        Bundle bundle = activity.getIntent().getBundleExtra(argName);
        return bundle != null ? bundle : defaultValue;
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        return activity.getIntent().getBundleExtra(argName);
    }


    @NotNull
    public static Bundle readExtrasArg(@NotNull Activity activity) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) return extras;
        throw (new IllegalArgumentException("Not found 'extras'"));
    }

    @NotNull
    public static Bundle readExtrasArgOr(@NotNull Activity activity, @NotNull Bundle defaultValue) {
        Bundle extras = activity.getIntent().getExtras();
        return extras != null ? extras : defaultValue;
    }

    @Nullable
    public static Bundle readExtrasArgOrNull(@NotNull Activity activity) {
        return activity.getIntent().getExtras();
    }


    /* ************************************* Uri Args ***************************************** */


    public static byte readByteUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Byte.parseByte(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static byte readByteUriArgOr(@NotNull Activity activity, @NotNull String argName, byte defaultValue) {
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
    public static Byte readByteUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static short readShortUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Short.parseShort(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static short readShortUriArgOr(@NotNull Activity activity, @NotNull String argName, short defaultValue) {
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
    public static Short readShortUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static int readIntUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Integer.parseInt(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static int readIntUriArgOr(@NotNull Activity activity, @NotNull String argName, int defaultValue) {
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
    public static Integer readIntUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static long readLongUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Long.parseLong(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static long readLongUriArgOr(@NotNull Activity activity, @NotNull String argName, long defaultValue) {
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
    public static Long readLongUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static float readFloatUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Float.parseFloat(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static float readFloatUriArgOr(@NotNull Activity activity, @NotNull String argName, float defaultValue) {
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
    public static Float readFloatUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static double readDoubleUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Double.parseDouble(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static double readDoubleUriArgOr(@NotNull Activity activity, @NotNull String argName, double defaultValue) {
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
    public static Double readDoubleUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static boolean readBooleanUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return Boolean.parseBoolean(value);
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    public static boolean readBooleanUriArgOr(@NotNull Activity activity, @NotNull String argName, boolean defaultValue) {
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
    public static Boolean readBooleanUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    @NotNull
    public static String readStringUriArg(@NotNull Activity activity, @NotNull String argName) {
        Premisex.require(Intent.ACTION_VIEW.equals(activity.getIntent().getAction()), ACTION_NOT_VIEW);
        Uri uri = Premisex.requireNotNull(activity.getIntent().getData(), DATA_IS_NULL);
        String value = Stringx.orEmpty(uri.getQueryParameter(argName)).trim();
        if (!"".equals(value)) return value;
        throw new IllegalArgumentException(String.format("Param '%s' not found: %s", argName, uri));
    }

    @NotNull
    public static String readStringUriArgOr(@NotNull Activity activity, @NotNull String argName, @NotNull String defaultValue) {
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
    public static String readStringUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
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


    public static byte readByteUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, byte defaultValue) {
        Byte uriValue = readByteUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readByteArgOr(activity, argName, defaultValue);
    }

    public static short readShortUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, short defaultValue) {
        Short uriValue = readShortUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readShortArgOr(activity, argName, defaultValue);
    }

    public static int readIntUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, int defaultValue) {
        Integer uriValue = readIntUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readIntArgOr(activity, argName, defaultValue);
    }

    public static long readLongUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, long defaultValue) {
        Long uriValue = readLongUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readLongArgOr(activity, argName, defaultValue);
    }

    public static float readFloatUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, float defaultValue) {
        Float uriValue = readFloatUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readFloatArgOr(activity, argName, defaultValue);
    }

    public static double readDoubleUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, double defaultValue) {
        Double uriValue = readDoubleUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readDoubleArgOr(activity, argName, defaultValue);
    }

    public static boolean readBooleanUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, boolean defaultValue) {
        Boolean uriValue = readBooleanUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readBooleanArgOr(activity, argName, defaultValue);
    }

    @NonNull
    public static String readStringUriIntentArg(@NotNull Activity activity, @NotNull String argName) {
        String uriValue = readStringUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readStringArg(activity, argName);
    }

    @NonNull
    public static String readStringUriIntentArgOr(@NotNull Activity activity, @NotNull String argName, String defaultValue) {
        String uriValue = readStringUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readStringArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringUriIntentArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        String uriValue = readStringUriArgOrNull(activity, argName);
        return uriValue != null ? uriValue : readStringArgOrNull(activity, argName);
    }


    /* ************************************* Intent Uri Args ***************************************** */


    public static byte readByteIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, byte defaultValue) {
        byte uriValue = readByteArgOr(activity, argName, Byte.MIN_VALUE);
        return uriValue != Byte.MIN_VALUE ? uriValue : readByteUriArgOr(activity, argName, defaultValue);
    }

    public static short readShortIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, short defaultValue) {
        short uriValue = readShortArgOr(activity, argName, Short.MIN_VALUE);
        return uriValue != Short.MIN_VALUE ? uriValue : readShortUriArgOr(activity, argName, defaultValue);
    }

    public static int readIntIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, int defaultValue) {
        int uriValue = readIntArgOr(activity, argName, Integer.MIN_VALUE);
        return uriValue != Integer.MIN_VALUE ? uriValue : readIntUriArgOr(activity, argName, defaultValue);
    }

    public static long readLongIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, long defaultValue) {
        long uriValue = readLongArgOr(activity, argName, Long.MIN_VALUE);
        return uriValue != Long.MIN_VALUE ? uriValue : readLongUriArgOr(activity, argName, defaultValue);
    }

    public static float readFloatIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, float defaultValue) {
        float uriValue = readFloatArgOr(activity, argName, Float.MIN_VALUE);
        return uriValue != Float.MIN_VALUE ? uriValue : readFloatUriArgOr(activity, argName, defaultValue);
    }

    public static double readDoubleIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, double defaultValue) {
        double uriValue = readDoubleArgOr(activity, argName, Double.MIN_VALUE);
        return uriValue != Double.MIN_VALUE ? uriValue : readDoubleUriArgOr(activity, argName, defaultValue);
    }

    public static boolean readBooleanIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, boolean defaultValue) {
        boolean uriValue = readBooleanArgOr(activity, argName, false);
        return uriValue || readBooleanUriArgOr(activity, argName, defaultValue);
    }

    @NonNull
    public static String readStringIntentUriArg(@NotNull Activity activity, @NotNull String argName) {
        String uriValue = readStringArgOrNull(activity, argName);
        return uriValue != null && Stringx.isSafe(uriValue) ? uriValue : readStringUriArg(activity, argName);
    }

    @NonNull
    public static String readStringIntentUriArgOr(@NotNull Activity activity, @NotNull String argName, String defaultValue) {
        String uriValue = readStringArgOrNull(activity, argName);
        return uriValue != null && Stringx.isSafe(uriValue) ? uriValue : readStringUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringIntentUriArgOrNull(@NotNull Activity activity, @NotNull String argName) {
        String uriValue = readStringArgOrNull(activity, argName);
        return uriValue != null && Stringx.isSafe(uriValue) ? uriValue : readStringUriArgOrNull(activity, argName);
    }
}