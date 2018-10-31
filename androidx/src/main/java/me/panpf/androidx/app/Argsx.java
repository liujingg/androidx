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
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Comparisonx;
import me.panpf.javax.util.LazyValue;
import me.panpf.javax.util.Premisex;

@SuppressWarnings({"WeakerAccess"})
public class Argsx {

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

    private Argsx() {
    }

    /* ************************************* Activity Intent Args ***************************************** */


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


    /* ************************************* Activity Uri Args ***************************************** */


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


    /* ************************************* Activity Uri Intent Args ***************************************** */


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


    /* ************************************* Activity Intent Uri Args ***************************************** */


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
        return Stringx.isSafe(uriValue) ? uriValue : readStringUriArg(activity, argName);
    }

    @NonNull
    public static String readStringIntentUriArgOr(@NonNull Activity activity, @NonNull String argName, String defaultValue) {
        String uriValue = readStringArgOrNull(activity, argName);
        return Stringx.isSafe(uriValue) ? uriValue : readStringUriArgOr(activity, argName, defaultValue);
    }

    @Nullable
    public static String readStringIntentUriArgOrNull(@NonNull Activity activity, @NonNull String argName) {
        String uriValue = readStringArgOrNull(activity, argName);
        return Stringx.isSafe(uriValue) ? uriValue : readStringUriArgOrNull(activity, argName);
    }


    /* ************************************* Activity Intent Args ***************************************** */


    public static byte readByteArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static byte[] readByteArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull byte[] defaultValue) {
        return Argsx.readByteArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static short readShortArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static short[] readShortArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull short[] defaultValue) {
        return Argsx.readShortArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static int readIntArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static int[] readIntArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull int[] defaultValue) {
        return Argsx.readIntArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<Integer> defaultValue) {
        return Argsx.readIntArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    public static long readLongArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static long[] readLongArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull long[] defaultValue) {
        return Argsx.readLongArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static float readFloatArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static float[] readFloatArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull float[] defaultValue) {
        return Argsx.readFloatArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static double readDoubleArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static double[] readDoubleArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull double[] defaultValue) {
        return Argsx.readDoubleArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static boolean readBooleanArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static boolean[] readBooleanArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull boolean[] defaultValue) {
        return Argsx.readBooleanArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    public static char readCharArgOr(@NonNull Activity activity, @StringRes int argNameResId, char defaultValue) {
        return Argsx.readCharArgOr(activity, activity.getString(argNameResId), defaultValue);
    }


    @NonNull
    public static char[] readCharArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull char[] defaultValue) {
        return Argsx.readCharArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static CharSequence readCharSequenceArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull CharSequence defaultValue) {
        return Argsx.readCharSequenceArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull CharSequence[] defaultValue) {
        return Argsx.readCharSequenceArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<CharSequence> defaultValue) {
        return Argsx.readCharSequenceArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String readStringArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String[] readStringArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull String[] defaultValue) {
        return Argsx.readStringArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<String> defaultValue) {
        return Argsx.readStringArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readParcelableArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull V[] defaultValue) {
        return Argsx.readParcelableArrayArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull ArrayList<V> defaultValue) {
        return Argsx.readParcelableArrayListArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readSerializableArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readSerializableArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static Bundle readBundleArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBundleArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull Bundle defaultValue) {
        return Argsx.readBundleArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* Activity Uri Args ***************************************** */


    public static byte readByteUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteUriArg(activity, activity.getString(argNameResId));
    }

    public static byte readByteUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Byte readByteUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readByteUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static short readShortUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortUriArg(activity, activity.getString(argNameResId));
    }

    public static short readShortUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Short readShortUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readShortUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static int readIntUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntUriArg(activity, activity.getString(argNameResId));
    }

    public static int readIntUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Integer readIntUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readIntUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static long readLongUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongUriArg(activity, activity.getString(argNameResId));
    }

    public static long readLongUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Long readLongUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readLongUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static float readFloatUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatUriArg(activity, activity.getString(argNameResId));
    }

    public static float readFloatUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Float readFloatUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readFloatUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static double readDoubleUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleUriArg(activity, activity.getString(argNameResId));
    }

    public static double readDoubleUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Double readDoubleUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readDoubleUriArgOrNull(activity, activity.getString(argNameResId));
    }


    public static boolean readBooleanUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanUriArg(activity, activity.getString(argNameResId));
    }

    public static boolean readBooleanUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Boolean readBooleanUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readBooleanUriArgOrNull(activity, activity.getString(argNameResId));
    }


    @NonNull
    public static String readStringUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* Activity Uri Intent Args ***************************************** */


    public static byte readByteUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static short readShortUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static int readIntUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static long readLongUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static float readFloatUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static double readDoubleUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static boolean readBooleanUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @NonNull
    public static String readStringUriIntentArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriIntentArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringUriIntentArgOr(@NonNull Activity activity, @StringRes int argNameResId, String defaultValue) {
        return Argsx.readStringUriIntentArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringUriIntentArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringUriIntentArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* Activity Intent Uri Args ***************************************** */


    public static byte readByteIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static short readShortIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static int readIntIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static long readLongIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static float readFloatIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static double readDoubleIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    public static boolean readBooleanIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @NonNull
    public static String readStringIntentUriArg(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringIntentUriArg(activity, activity.getString(argNameResId));
    }

    @NonNull
    public static String readStringIntentUriArgOr(@NonNull Activity activity, @StringRes int argNameResId, String defaultValue) {
        return Argsx.readStringIntentUriArgOr(activity, activity.getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringIntentUriArgOrNull(@NonNull Activity activity, @StringRes int argNameResId) {
        return Argsx.readStringIntentUriArgOrNull(activity, activity.getString(argNameResId));
    }


    /* ************************************* SupportFragment Args ***************************************** */


    public static byte readByteArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, byte defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getByte(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static byte[] readByteArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        byte[] bytes = arguments != null ? arguments.getByteArray(argName) : null;
        if (bytes != null) return bytes;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull byte[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        byte[] bytes = arguments != null ? arguments.getByteArray(argName) : null;
        return bytes != null ? bytes : defaultValue;
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getByteArray(argName) : null;
    }


    public static short readShortArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, short defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getShort(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static short[] readShortArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        short[] shorts = arguments != null ? arguments.getShortArray(argName) : null;
        if (shorts != null) return shorts;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull short[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        short[] shorts = arguments != null ? arguments.getShortArray(argName) : null;
        return shorts != null ? shorts : defaultValue;
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getShortArray(argName) : null;
    }


    public static int readIntArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, int defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getInt(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static int[] readIntArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        int[] ints = arguments != null ? arguments.getIntArray(argName) : null;
        if (ints != null) return ints;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull int[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        int[] ints = arguments != null ? arguments.getIntArray(argName) : null;
        return ints != null ? ints : defaultValue;
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getIntArray(argName) : null;
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<Integer> ints = arguments != null ? arguments.getIntegerArrayList(argName) : null;
        if (ints != null) return ints;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<Integer> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<Integer> integers = arguments != null ? arguments.getIntegerArrayList(argName) : null;
        return integers != null ? integers : defaultValue;
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getIntegerArrayList(argName) : null;
    }


    public static long readLongArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, long defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getLong(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static long[] readLongArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        long[] longs = arguments != null ? arguments.getLongArray(argName) : null;
        if (longs != null) return longs;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull long[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        long[] longs = arguments != null ? arguments.getLongArray(argName) : null;
        return longs != null ? longs : defaultValue;
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getLongArray(argName) : null;
    }


    public static float readFloatArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, float defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getFloat(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static float[] readFloatArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        float[] floats = arguments != null ? arguments.getFloatArray(argName) : null;
        if (floats != null) return floats;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull float[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        float[] floats = arguments != null ? arguments.getFloatArray(argName) : null;
        return floats != null ? floats : defaultValue;
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getFloatArray(argName) : null;
    }


    public static double readDoubleArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, double defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getDouble(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static double[] readDoubleArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        double[] doubles = arguments != null ? arguments.getDoubleArray(argName) : null;
        if (doubles != null) return doubles;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull double[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        double[] doubles = arguments != null ? arguments.getDoubleArray(argName) : null;
        return doubles != null ? doubles : defaultValue;
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getDoubleArray(argName) : null;
    }


    public static boolean readBooleanArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, boolean defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBoolean(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static boolean[] readBooleanArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        boolean[] booleans = arguments != null ? arguments.getBooleanArray(argName) : null;
        if (booleans != null) return booleans;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull boolean[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        boolean[] booleans = arguments != null ? arguments.getBooleanArray(argName) : null;
        return booleans != null ? booleans : defaultValue;
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBooleanArray(argName) : null;
    }


    public static char readCharArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, char defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getChar(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static char[] readCharArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        char[] chars = arguments != null ? arguments.getCharArray(argName) : null;
        if (chars != null) return chars;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull char[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        char[] chars = arguments != null ? arguments.getCharArray(argName) : null;
        return chars != null ? chars : defaultValue;
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharArray(argName) : null;
    }


    @NonNull
    public static CharSequence readCharSequenceArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        CharSequence charSequence = arguments != null ? arguments.getCharSequence(argName) : null;
        if (charSequence != null) return charSequence;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull CharSequence defaultValue) {
        Bundle arguments = fragment.getArguments();
        CharSequence charSequence = arguments != null ? arguments.getCharSequence(argName, defaultValue) : null;
        return charSequence != null ? charSequence : defaultValue;
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequence(argName) : null;
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        CharSequence[] charSequences = arguments != null ? arguments.getCharSequenceArray(argName) : null;
        if (charSequences != null) return charSequences;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull CharSequence[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        CharSequence[] charSequences = arguments != null ? arguments.getCharSequenceArray(argName) : null;
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequenceArray(argName) : null;
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<CharSequence> charSequences = arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
        if (charSequences != null) return charSequences;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<CharSequence> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<CharSequence> charSequences = arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
    }


    @NonNull
    public static String readStringArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        String string = arguments != null ? arguments.getString(argName) : null;
        if (string != null) return string;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static String readStringArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull String defaultValue) {
        Bundle arguments = fragment.getArguments();
        String string = arguments != null ? arguments.getString(argName, defaultValue) : null;
        return string != null ? string : defaultValue;
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getString(argName) : null;
    }


    @NonNull
    public static String[] readStringArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        String[] strings = arguments != null ? arguments.getStringArray(argName) : null;
        if (strings != null) return strings;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull String[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        String[] strings = arguments != null ? arguments.getStringArray(argName) : null;
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getStringArray(argName) : null;
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<String> strings = arguments != null ? arguments.getStringArrayList(argName) : null;
        if (strings != null) return strings;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<String> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<String> strings = arguments != null ? arguments.getStringArrayList(argName) : null;
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getStringArrayList(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        V parcelable = arguments != null ? (V) arguments.getParcelable(argName) : null;
        if (parcelable != null) return parcelable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull V defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        Parcelable parcelable = arguments != null ? arguments.getParcelable(argName) : null;
        //noinspection unchecked
        return parcelable != null ? (V) parcelable : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (V) arguments.getParcelable(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        V[] parcelables = arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
        if (parcelables != null) return parcelables;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull V[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        V[] parcelables = arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        ArrayList<V> parcelable = arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
        if (parcelable != null) return parcelable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<V> defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        ArrayList<V> parcelables = arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        SparseArray<V> sparseArray = arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
        if (sparseArray != null) return sparseArray;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull SparseArray<V> defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        SparseArray<V> sparseArray = arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
        return sparseArray != null ? sparseArray : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        V serializable = arguments != null ? (V) arguments.getSerializable(argName) : null;
        if (serializable != null) return serializable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull V defaultValue) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        V serializable = arguments != null ? (V) arguments.getSerializable(argName) : null;
        return serializable != null ? serializable : defaultValue;
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        return arguments != null ? (V) arguments.getSerializable(argName) : null;
    }


    @NonNull
    public static Bundle readBundleArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        Bundle bundle = arguments != null ? arguments.getBundle(argName) : null;
        if (bundle != null) return bundle;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull Bundle defaultValue) {
        Bundle arguments = fragment.getArguments();
        Bundle bundle = arguments != null ? arguments.getBundle(argName) : null;
        return bundle != null ? bundle : defaultValue;
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBundle(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        IBinder iBinder = arguments != null ? arguments.getBinder(argName) : null;
        if (iBinder != null) return iBinder;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull IBinder defaultValue) {
        Bundle arguments = fragment.getArguments();
        IBinder iBinder = arguments != null ? arguments.getBinder(argName) : null;
        return iBinder != null ? iBinder : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBinder(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        Size size = arguments != null ? arguments.getSize(argName) : null;
        if (size != null) return size;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull Size defaultValue) {
        Bundle arguments = fragment.getArguments();
        Size size = arguments != null ? arguments.getSize(argName) : null;
        return size != null ? size : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getSize(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArg(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        SizeF sizeF = arguments != null ? arguments.getSizeF(argName) : null;
        if (sizeF != null) return sizeF;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOr(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName, @NonNull SizeF defaultValue) {
        Bundle arguments = fragment.getArguments();
        SizeF sizeF = arguments != null ? arguments.getSizeF(argName) : null;
        return sizeF != null ? sizeF : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getSizeF(argName) : null;
    }


    /* ************************************* SupportFragment Args ***************************************** */


    public static byte readByteArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static byte[] readByteArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull byte[] defaultValue) {
        return Argsx.readByteArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static short readShortArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static short[] readShortArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull short[] defaultValue) {
        return Argsx.readShortArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static int readIntArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static int[] readIntArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull int[] defaultValue) {
        return Argsx.readIntArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<Integer> defaultValue) {
        return Argsx.readIntArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static long readLongArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static long[] readLongArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull long[] defaultValue) {
        return Argsx.readLongArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static float readFloatArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static float[] readFloatArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull float[] defaultValue) {
        return Argsx.readFloatArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static double readDoubleArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static double[] readDoubleArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull double[] defaultValue) {
        return Argsx.readDoubleArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static boolean readBooleanArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static boolean[] readBooleanArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull boolean[] defaultValue) {
        return Argsx.readBooleanArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static char readCharArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, char defaultValue) {
        return Argsx.readCharArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static char[] readCharArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull char[] defaultValue) {
        return Argsx.readCharArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static CharSequence readCharSequenceArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull CharSequence defaultValue) {
        return Argsx.readCharSequenceArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull CharSequence[] defaultValue) {
        return Argsx.readCharSequenceArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<CharSequence> defaultValue) {
        return Argsx.readCharSequenceArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static String readStringArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static String readStringArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static String[] readStringArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull String[] defaultValue) {
        return Argsx.readStringArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<String> defaultValue) {
        return Argsx.readStringArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readParcelableArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull V[] defaultValue) {
        return Argsx.readParcelableArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<V> defaultValue) {
        return Argsx.readParcelableArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSparseParcelableArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull SparseArray<V> defaultValue) {
        return Argsx.readSparseParcelableArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSparseParcelableArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSerializableArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readSerializableArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static Bundle readBundleArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBundleArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull Bundle defaultValue) {
        return Argsx.readBundleArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBinderArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull IBinder defaultValue) {
        return Argsx.readBinderArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBinderArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull Size defaultValue) {
        return Argsx.readSizeArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArg(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeFArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOr(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId, @NonNull SizeF defaultValue) {
        return Argsx.readSizeFArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrNull(@NonNull android.support.v4.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeFArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    /* ************************************* OriginFragment Args ***************************************** */


    public static byte readByteArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, byte defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getByte(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static byte[] readByteArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        byte[] bytes = arguments != null ? arguments.getByteArray(argName) : null;
        if (bytes != null) return bytes;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull byte[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        byte[] bytes = arguments != null ? arguments.getByteArray(argName) : null;
        return bytes != null ? bytes : defaultValue;
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getByteArray(argName) : null;
    }


    public static short readShortArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, short defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getShort(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static short[] readShortArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        short[] shorts = arguments != null ? arguments.getShortArray(argName) : null;
        if (shorts != null) return shorts;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull short[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        short[] shorts = arguments != null ? arguments.getShortArray(argName) : null;
        return shorts != null ? shorts : defaultValue;
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getShortArray(argName) : null;
    }


    public static int readIntArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, int defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getInt(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static int[] readIntArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        int[] ints = arguments != null ? arguments.getIntArray(argName) : null;
        if (ints != null) return ints;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull int[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        int[] ints = arguments != null ? arguments.getIntArray(argName) : null;
        return ints != null ? ints : defaultValue;
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getIntArray(argName) : null;
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<Integer> ints = arguments != null ? arguments.getIntegerArrayList(argName) : null;
        if (ints != null) return ints;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<Integer> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<Integer> integers = arguments != null ? arguments.getIntegerArrayList(argName) : null;
        return integers != null ? integers : defaultValue;
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getIntegerArrayList(argName) : null;
    }


    public static long readLongArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, long defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getLong(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static long[] readLongArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        long[] longs = arguments != null ? arguments.getLongArray(argName) : null;
        if (longs != null) return longs;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull long[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        long[] longs = arguments != null ? arguments.getLongArray(argName) : null;
        return longs != null ? longs : defaultValue;
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getLongArray(argName) : null;
    }


    public static float readFloatArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, float defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getFloat(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static float[] readFloatArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        float[] floats = arguments != null ? arguments.getFloatArray(argName) : null;
        if (floats != null) return floats;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull float[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        float[] floats = arguments != null ? arguments.getFloatArray(argName) : null;
        return floats != null ? floats : defaultValue;
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getFloatArray(argName) : null;
    }


    public static double readDoubleArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, double defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getDouble(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static double[] readDoubleArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        double[] doubles = arguments != null ? arguments.getDoubleArray(argName) : null;
        if (doubles != null) return doubles;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull double[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        double[] doubles = arguments != null ? arguments.getDoubleArray(argName) : null;
        return doubles != null ? doubles : defaultValue;
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getDoubleArray(argName) : null;
    }


    public static boolean readBooleanArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, boolean defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBoolean(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static boolean[] readBooleanArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        boolean[] booleans = arguments != null ? arguments.getBooleanArray(argName) : null;
        if (booleans != null) return booleans;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull boolean[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        boolean[] booleans = arguments != null ? arguments.getBooleanArray(argName) : null;
        return booleans != null ? booleans : defaultValue;
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBooleanArray(argName) : null;
    }


    public static char readCharArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, char defaultValue) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getChar(argName, defaultValue) : defaultValue;
    }


    @NonNull
    public static char[] readCharArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        char[] chars = arguments != null ? arguments.getCharArray(argName) : null;
        if (chars != null) return chars;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull char[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        char[] chars = arguments != null ? arguments.getCharArray(argName) : null;
        return chars != null ? chars : defaultValue;
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharArray(argName) : null;
    }


    @NonNull
    public static CharSequence readCharSequenceArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        CharSequence charSequence = arguments != null ? arguments.getCharSequence(argName) : null;
        if (charSequence != null) return charSequence;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull CharSequence defaultValue) {
        Bundle arguments = fragment.getArguments();
        CharSequence charSequence = arguments != null ? arguments.getCharSequence(argName, defaultValue) : null;
        return charSequence != null ? charSequence : defaultValue;
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequence(argName) : null;
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        CharSequence[] charSequences = arguments != null ? arguments.getCharSequenceArray(argName) : null;
        if (charSequences != null) return charSequences;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull CharSequence[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        CharSequence[] charSequences = arguments != null ? arguments.getCharSequenceArray(argName) : null;
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequenceArray(argName) : null;
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<CharSequence> charSequences = arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
        if (charSequences != null) return charSequences;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<CharSequence> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<CharSequence> charSequences = arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
        return charSequences != null ? charSequences : defaultValue;
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getCharSequenceArrayList(argName) : null;
    }


    @NonNull
    public static String readStringArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        String string = arguments != null ? arguments.getString(argName) : null;
        if (string != null) return string;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static String readStringArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull String defaultValue) {
        Bundle arguments = fragment.getArguments();
        String string = arguments != null ? arguments.getString(argName, defaultValue) : null;
        return string != null ? string : defaultValue;
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getString(argName) : null;
    }


    @NonNull
    public static String[] readStringArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        String[] strings = arguments != null ? arguments.getStringArray(argName) : null;
        if (strings != null) return strings;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull String[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        String[] strings = arguments != null ? arguments.getStringArray(argName) : null;
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getStringArray(argName) : null;
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        ArrayList<String> strings = arguments != null ? arguments.getStringArrayList(argName) : null;
        if (strings != null) return strings;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<String> defaultValue) {
        Bundle arguments = fragment.getArguments();
        ArrayList<String> strings = arguments != null ? arguments.getStringArrayList(argName) : null;
        return strings != null ? strings : defaultValue;
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getStringArrayList(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        V parcelable = arguments != null ? (V) arguments.getParcelable(argName) : null;
        if (parcelable != null) return parcelable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull V defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        Parcelable parcelable = arguments != null ? arguments.getParcelable(argName) : null;
        //noinspection unchecked
        return parcelable != null ? (V) parcelable : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (V) arguments.getParcelable(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        V[] parcelables = arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
        if (parcelables != null) return parcelables;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull V[] defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        V[] parcelables = arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (V[]) arguments.getParcelableArray(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        ArrayList<V> parcelable = arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
        if (parcelable != null) return parcelable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull ArrayList<V> defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        ArrayList<V> parcelables = arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
        return parcelables != null ? parcelables : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (ArrayList<V>) arguments.getParcelableArrayList(argName) : null;
    }


    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        SparseArray<V> sparseArray = arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
        if (sparseArray != null) return sparseArray;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull SparseArray<V> defaultValue) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        SparseArray<V> sparseArray = arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
        return sparseArray != null ? sparseArray : defaultValue;
    }

    @Nullable
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        // Because the ClassLoader may be lost when the Bundle is passed in the Bundle, you need to restore it to deserialize the custom Parcelable.
        if (arguments != null) arguments.setClassLoader(Fragmentx.class.getClassLoader());
        //noinspection unchecked
        return arguments != null ? (SparseArray<V>) arguments.getSparseParcelableArray(argName) : null;
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        V serializable = arguments != null ? (V) arguments.getSerializable(argName) : null;
        if (serializable != null) return serializable;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull V defaultValue) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        V serializable = arguments != null ? (V) arguments.getSerializable(argName) : null;
        return serializable != null ? serializable : defaultValue;
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        //noinspection unchecked
        return arguments != null ? (V) arguments.getSerializable(argName) : null;
    }


    @NonNull
    public static Bundle readBundleArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        Bundle bundle = arguments != null ? arguments.getBundle(argName) : null;
        if (bundle != null) return bundle;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull Bundle defaultValue) {
        Bundle arguments = fragment.getArguments();
        Bundle bundle = arguments != null ? arguments.getBundle(argName) : null;
        return bundle != null ? bundle : defaultValue;
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBundle(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        IBinder iBinder = arguments != null ? arguments.getBinder(argName) : null;
        if (iBinder != null) return iBinder;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull IBinder defaultValue) {
        Bundle arguments = fragment.getArguments();
        IBinder iBinder = arguments != null ? arguments.getBinder(argName) : null;
        return iBinder != null ? iBinder : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getBinder(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        Size size = arguments != null ? arguments.getSize(argName) : null;
        if (size != null) return size;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull Size defaultValue) {
        Bundle arguments = fragment.getArguments();
        Size size = arguments != null ? arguments.getSize(argName) : null;
        return size != null ? size : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getSize(argName) : null;
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArg(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        SizeF sizeF = arguments != null ? arguments.getSizeF(argName) : null;
        if (sizeF != null) return sizeF;
        throw (new IllegalArgumentException(String.format("Param '%s' not found", argName)));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOr(@NonNull android.app.Fragment fragment, @NonNull String argName, @NonNull SizeF defaultValue) {
        Bundle arguments = fragment.getArguments();
        SizeF sizeF = arguments != null ? arguments.getSizeF(argName) : null;
        return sizeF != null ? sizeF : defaultValue;
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrNull(@NonNull android.app.Fragment fragment, @NonNull String argName) {
        Bundle arguments = fragment.getArguments();
        return arguments != null ? arguments.getSizeF(argName) : null;
    }


    /* ************************************* SupportFragment Args ***************************************** */


    public static byte readByteArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, byte defaultValue) {
        return Argsx.readByteArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static byte[] readByteArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static byte[] readByteArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull byte[] defaultValue) {
        return Argsx.readByteArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static byte[] readByteArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readByteArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static short readShortArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, short defaultValue) {
        return Argsx.readShortArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static short[] readShortArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static short[] readShortArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull short[] defaultValue) {
        return Argsx.readShortArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static short[] readShortArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readShortArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static int readIntArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, int defaultValue) {
        return Argsx.readIntArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static int[] readIntArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static int[] readIntArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull int[] defaultValue) {
        return Argsx.readIntArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static int[] readIntArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static ArrayList<Integer> readIntArrayListArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static ArrayList<Integer> readIntArrayListArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<Integer> defaultValue) {
        return Argsx.readIntArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<Integer> readIntArrayListArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readIntArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static long readLongArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, long defaultValue) {
        return Argsx.readLongArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static long[] readLongArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static long[] readLongArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull long[] defaultValue) {
        return Argsx.readLongArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static long[] readLongArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readLongArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static float readFloatArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, float defaultValue) {
        return Argsx.readFloatArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static float[] readFloatArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static float[] readFloatArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull float[] defaultValue) {
        return Argsx.readFloatArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static float[] readFloatArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readFloatArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static double readDoubleArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, double defaultValue) {
        return Argsx.readDoubleArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static double[] readDoubleArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static double[] readDoubleArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull double[] defaultValue) {
        return Argsx.readDoubleArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static double[] readDoubleArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readDoubleArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static boolean readBooleanArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, boolean defaultValue) {
        return Argsx.readBooleanArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static boolean[] readBooleanArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static boolean[] readBooleanArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull boolean[] defaultValue) {
        return Argsx.readBooleanArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static boolean[] readBooleanArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBooleanArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    public static char readCharArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, char defaultValue) {
        return Argsx.readCharArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }


    @NonNull
    public static char[] readCharArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static char[] readCharArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull char[] defaultValue) {
        return Argsx.readCharArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static char[] readCharArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static CharSequence readCharSequenceArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static CharSequence readCharSequenceArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull CharSequence defaultValue) {
        return Argsx.readCharSequenceArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence readCharSequenceArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static CharSequence[] readCharSequenceArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static CharSequence[] readCharSequenceArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull CharSequence[] defaultValue) {
        return Argsx.readCharSequenceArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static CharSequence[] readCharSequenceArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<CharSequence> defaultValue) {
        return Argsx.readCharSequenceArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<CharSequence> readCharSequenceArrayListArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readCharSequenceArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static String readStringArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static String readStringArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull String defaultValue) {
        return Argsx.readStringArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String readStringArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static String[] readStringArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static String[] readStringArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull String[] defaultValue) {
        return Argsx.readStringArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static String[] readStringArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static ArrayList<String> readStringArrayListArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static ArrayList<String> readStringArrayListArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<String> defaultValue) {
        return Argsx.readStringArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static ArrayList<String> readStringArrayListArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readStringArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V readParcelableArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V readParcelableArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readParcelableArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V readParcelableArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> V[] readParcelableArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull V[] defaultValue) {
        return Argsx.readParcelableArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> V[] readParcelableArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull ArrayList<V> defaultValue) {
        return Argsx.readParcelableArrayListArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> ArrayList<V> readParcelableArrayListArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readParcelableArrayListArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSparseParcelableArrayArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull SparseArray<V> defaultValue) {
        return Argsx.readSparseParcelableArrayArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Parcelable> SparseArray<V> readSparseParcelableArrayArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSparseParcelableArrayArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static <V extends Serializable> V readSerializableArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSerializableArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static <V extends Serializable> V readSerializableArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull V defaultValue) {
        return Argsx.readSerializableArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static <V extends Serializable> V readSerializableArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSerializableArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    public static Bundle readBundleArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBundleArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    public static Bundle readBundleArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull Bundle defaultValue) {
        return Argsx.readBundleArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    public static Bundle readBundleArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBundleArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBinderArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull IBinder defaultValue) {
        return Argsx.readBinderArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static IBinder readBinderArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readBinderArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull Size defaultValue) {
        return Argsx.readSizeArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static Size readSizeArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }


    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArg(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeFArg(fragment, fragment.getResources().getString(argNameResId));
    }

    @NonNull
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOr(@NonNull android.app.Fragment fragment, @StringRes int argNameResId, @NonNull SizeF defaultValue) {
        return Argsx.readSizeFArgOr(fragment, fragment.getResources().getString(argNameResId), defaultValue);
    }

    @Nullable
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public static SizeF readSizeFArgOrNull(@NonNull android.app.Fragment fragment, @StringRes int argNameResId) {
        return Argsx.readSizeFArgOrNull(fragment, fragment.getResources().getString(argNameResId));
    }
}