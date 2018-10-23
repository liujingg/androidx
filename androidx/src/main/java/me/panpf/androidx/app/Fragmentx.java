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
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Fragmentx {


    /**
     * Return true if the fragment has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull android.app.Fragment fragment) {
        return fragment.getActivity() == null;
    }

    /**
     * Return true if the fragment has been destroyed
     */
    public static boolean isDestroyedCompat(@NonNull android.support.v4.app.Fragment fragment) {
        return fragment.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED;
    }


    /**
     * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplWithParent(@NonNull android.support.v4.app.Fragment fragment, @NonNull Class<T> clazz) {
        android.support.v4.app.Fragment parent = fragment;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parent = parent.getParentFragment();
            }
        }
        Activity parentActivity = fragment.getActivity();
        while (parentActivity != null) {
            if (clazz.isAssignableFrom(parentActivity.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parentActivity = parentActivity.getParent();
            }
        }
        return null;
    }

    /**
     * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplWithParent(@NonNull android.app.Fragment fragment, @NonNull Class<T> clazz) {
        android.app.Fragment parent = fragment;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    parent = parent.getParentFragment();
                } else {
                    parent = null;
                }
            }
        }
        Activity parentActivity = fragment.getActivity();
        while (parentActivity != null) {
            if (clazz.isAssignableFrom(parentActivity.getClass())) {
                //noinspection unchecked
                return (T) clazz;
            } else {
                parentActivity = parentActivity.getParent();
            }
        }
        return null;
    }


    /**
     * Instantiate a Fragment and set arguments
     */
    public static android.support.v4.app.Fragment instance(@NonNull Class<? extends android.support.v4.app.Fragment> clas, @Nullable Bundle arguments) {
        android.support.v4.app.Fragment fragment;
        //noinspection TryWithIdenticalCatches
        try {
            fragment = clas.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    /**
     * Instantiate a Fragment and set arguments
     */
    public static android.support.v4.app.Fragment instance(@NonNull Class<? extends android.support.v4.app.Fragment> clas) {
        return instance(clas, null);
    }

    /**
     * Instantiate a Fragment and set arguments
     */
    public static android.app.Fragment instanceOrigin(@NonNull Class<? extends android.app.Fragment> clas, @Nullable Bundle arguments) {
        android.app.Fragment fragment;
        //noinspection TryWithIdenticalCatches
        try {
            fragment = clas.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
        if (arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    /**
     * Instantiate a Fragment and set arguments
     */
    public static android.app.Fragment instanceOrigin(@NonNull Class<? extends android.app.Fragment> clas) {
        return instanceOrigin(clas, null);
    }


    /**
     * Find the visible fragments visible to the current user from the fragment list
     */
    @Nullable
    public static android.support.v4.app.Fragment findUserVisibleChildFragment(@Nullable List<android.support.v4.app.Fragment> fragmentList) {
        android.support.v4.app.Fragment userVisibleFragment = null;
        if (fragmentList != null) {
            for (android.support.v4.app.Fragment childFragment : fragmentList) {
                if (childFragment != null) {
                    if (childFragment.isResumed() && childFragment.getUserVisibleHint()) {
                        userVisibleFragment = childFragment;
                    } else {
                        List<android.support.v4.app.Fragment> childFragmentList = childFragment.getChildFragmentManager().getFragments();
                        userVisibleFragment = findUserVisibleChildFragment(childFragmentList);
                    }
                    if (userVisibleFragment != null) {
                        break;
                    }
                }
            }
        }
        return userVisibleFragment;
    }

    /**
     * Find the visible fragments visible to the current user from the FragmentActivity
     */
    @Nullable
    public static android.support.v4.app.Fragment findUserVisibleChildFragment(@Nullable FragmentActivity fragmentActivity) {
        return findUserVisibleChildFragment(fragmentActivity != null ? fragmentActivity.getSupportFragmentManager().getFragments() : null);
    }

    /**
     * Find the visible fragments visible to the current user from the fragment
     */
    @Nullable
    public static android.support.v4.app.Fragment findUserVisibleChildFragment(@Nullable android.support.v4.app.Fragment fragment) {
        return findUserVisibleChildFragment(fragment != null ? fragment.getChildFragmentManager().getFragments() : null);
    }


    /**
     * Find the target fragment from the specified fragment list based on the current Item of ViewPager
     */
    @Nullable
    public static android.support.v4.app.Fragment findFragmentByViewPagerCurrentItem(
            @Nullable List<android.support.v4.app.Fragment> fragmentList, int viewPagerCurrentItem) {
        if (fragmentList != null) {
            String viewPagerCurrentItemString = String.valueOf(viewPagerCurrentItem);
            for (android.support.v4.app.Fragment fragment : fragmentList) {
                String fragmentTag = fragment.getTag();
                if (fragmentTag != null) {
                    String[] tagItems = fragmentTag.split(":");
                    if (tagItems.length > 0 && tagItems[tagItems.length - 1].equals(viewPagerCurrentItemString)) {
                        return fragment;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Find the target fragment from the specified fragment list based on the current Item of ViewPager
     */
    @Nullable
    public static android.support.v4.app.Fragment findFragmentByViewPagerCurrentItem(@Nullable FragmentActivity fragmentActivity, int viewPagerCurrentItem) {
        return findFragmentByViewPagerCurrentItem(fragmentActivity != null ? fragmentActivity.getSupportFragmentManager().getFragments() : null, viewPagerCurrentItem);
    }

    /**
     * Find the target fragment from the specified fragment list based on the current Item of ViewPager
     */
    @Nullable
    public static android.support.v4.app.Fragment findFragmentByViewPagerCurrentItem(@Nullable android.support.v4.app.Fragment fragment, int viewPagerCurrentItem) {
        return findFragmentByViewPagerCurrentItem(fragment != null ? fragment.getChildFragmentManager().getFragments() : null, viewPagerCurrentItem);
    }


    @NonNull
    public static Context requireContext(@NonNull android.support.v4.app.Fragment fragment) {
        return fragment.requireContext();
    }

    @NonNull
    public static Context requireContext(@NonNull android.app.Fragment fragment) {
        Context context = fragment.getActivity();
        if (context == null) {
            throw new IllegalStateException("Fragment " + fragment + " not attached to a context.");
        } else {
            return context;
        }
    }


    @NonNull
    public static Context requireAppContext(@NonNull android.support.v4.app.Fragment fragment) {
        return requireContext(fragment).getApplicationContext();
    }

    @NonNull
    public static Context requireAppContext(@NonNull android.app.Fragment fragment) {
        return requireContext(fragment).getApplicationContext();
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
}
