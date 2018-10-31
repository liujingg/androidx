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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Fragmentx {

    private Fragmentx() {
    }

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
}
