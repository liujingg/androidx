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
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;

import java.util.List;

import me.panpf.javax.util.Premisex;

public class Fragmentx {

    private Fragmentx() {
    }

    /**
     * Get Application from Fragment
     */
    @Nullable
    public static Application getApplication(@Nullable Fragment fragment) {
        Context context = fragment != null ? fragment.getContext() : null;
        return context != null ? (Application) context.getApplicationContext() : null;
    }

    /**
     * Get Application from Fragment
     */
    @Nullable
    public static Application getApplication(@Nullable android.app.Fragment fragment) {
        Activity activity = fragment != null ? fragment.getActivity() : null;
        return activity != null ? activity.getApplication() : null;
    }

    /**
     * Get Application from Fragment, throws an exception if null
     */
    @NonNull
    public static Application requireApplication(@NonNull Fragment fragment) {
        return (Application) fragment.requireContext().getApplicationContext();
    }

    /**
     * Get Application from Fragment, throws an exception if null
     */
    @NonNull
    public static Application requireApplication(@NonNull android.app.Fragment fragment) {
        return Premisex.requireNotNull(fragment.getActivity()).getApplication();
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
    public static boolean isDestroyedCompat(@NonNull Fragment fragment) {
        return fragment.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED || fragment.getLifecycle().getCurrentState() == Lifecycle.State.INITIALIZED;
    }


    /**
     * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
     */
    @Nullable
    public static <T> T getImplFromParent(@NonNull Fragment fragment, @NonNull Class<T> clazz) {
        Fragment parent = fragment;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) parent;
            } else {
                parent = parent.getParentFragment();
            }
        }
        Activity parentActivity = fragment.getActivity();
        while (parentActivity != null) {
            if (clazz.isAssignableFrom(parentActivity.getClass())) {
                //noinspection unchecked
                return (T) parentActivity;
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
    public static <T> T getImplFromParent(@NonNull android.app.Fragment fragment, @NonNull Class<T> clazz) {
        android.app.Fragment parent = fragment;
        while (parent != null) {
            if (clazz.isAssignableFrom(parent.getClass())) {
                //noinspection unchecked
                return (T) parent;
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
                return (T) parentActivity;
            } else {
                parentActivity = parentActivity.getParent();
            }
        }
        return null;
    }


    /**
     * Instantiate a Fragment and set arguments
     */
    public static <T extends Fragment> T instantiate(@NonNull Class<? extends T> clazz, @Nullable Bundle arguments) {
        T fragment;
        try {
            fragment = clazz.newInstance();
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
    public static <T extends Fragment> T instantiate(@NonNull Class<? extends T> clazz) {
        return instantiate(clazz, null);
    }

    /**
     * Instantiate a Fragment and set arguments
     */
    public static <T extends android.app.Fragment> T instantiateOrigin(@NonNull Class<? extends T> clazz, @Nullable Bundle arguments) {
        T fragment;
        try {
            fragment = clazz.newInstance();
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
    public static <T extends android.app.Fragment> T instantiateOrigin(@NonNull Class<? extends T> clazz) {
        return instantiateOrigin(clazz, null);
    }


    /**
     * Find the visible fragments visible to the current user from the fragment list
     */
    @Nullable
    public static Fragment findUserVisibleChildFragment(@Nullable List<Fragment> fragmentList) {
        Fragment userVisibleFragment = null;
        if (fragmentList != null) {
            for (Fragment childFragment : fragmentList) {
                if (childFragment != null) {
                    if (childFragment.isResumed() && childFragment.getUserVisibleHint()) {
                        userVisibleFragment = childFragment;
                    } else {
                        List<Fragment> childFragmentList = childFragment.getChildFragmentManager().getFragments();
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
    public static Fragment findUserVisibleChildFragment(@Nullable FragmentActivity fragmentActivity) {
        return findUserVisibleChildFragment(fragmentActivity != null ? fragmentActivity.getSupportFragmentManager().getFragments() : null);
    }

    /**
     * Find the visible fragments visible to the current user from the fragment
     */
    @Nullable
    public static Fragment findUserVisibleChildFragment(@Nullable Fragment fragment) {
        return findUserVisibleChildFragment(fragment != null ? fragment.getChildFragmentManager().getFragments() : null);
    }


    /**
     * Find the target fragment from the specified fragment list based on the current Item of ViewPager
     */
    @Nullable
    public static Fragment findFragmentByViewPagerCurrentItem(
            @Nullable List<Fragment> fragmentList, int viewPagerCurrentItem) {
        if (fragmentList != null) {
            String viewPagerCurrentItemString = String.valueOf(viewPagerCurrentItem);
            for (Fragment fragment : fragmentList) {
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
    public static Fragment findFragmentByViewPagerCurrentItem(@Nullable FragmentActivity fragmentActivity, int viewPagerCurrentItem) {
        return findFragmentByViewPagerCurrentItem(fragmentActivity != null ? fragmentActivity.getSupportFragmentManager().getFragments() : null, viewPagerCurrentItem);
    }

    /**
     * Find the target fragment from the specified fragment list based on the current Item of ViewPager
     */
    @Nullable
    public static Fragment findFragmentByViewPagerCurrentItem(@Nullable Fragment fragment, int viewPagerCurrentItem) {
        return findFragmentByViewPagerCurrentItem(fragment != null ? fragment.getChildFragmentManager().getFragments() : null, viewPagerCurrentItem);
    }
}
