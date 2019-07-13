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

@file:Suppress("NOTHING_TO_INLINE", "DEPRECATION")

package me.panpf.androidxkt.app

import android.os.Bundle
import me.panpf.androidx.app.Fragmentx

/**
 * Return true if the fragment has been destroyed
 */
inline fun android.app.Fragment.isDestroyedCompat(): Boolean = Fragmentx.isDestroyedCompat(this)

/**
 * Return true if the fragment has been destroyed
 */
inline fun androidx.fragment.app.Fragment.isDestroyedCompat(): Boolean = Fragmentx.isDestroyedCompat(this)


/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> androidx.fragment.app.Fragment.getImplFromParent(clazz: Class<T>): T? = Fragmentx.getImplFromParent(this, clazz)

/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> android.app.Fragment.getImplFromParent(clazz: Class<T>): T? = Fragmentx.getImplFromParent(this, clazz)


/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : androidx.fragment.app.Fragment> Class<out T>.instantiate(arguments: Bundle): T = Fragmentx.instantiate(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : androidx.fragment.app.Fragment> Class<out T>.instantiate(): T = Fragmentx.instantiate(this)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : android.app.Fragment> Class<out T>.instantiateOrigin(arguments: Bundle): T = Fragmentx.instantiateOrigin(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun <T : android.app.Fragment> Class<out T>.instantiateOrigin(): T = Fragmentx.instantiateOrigin(this)


/**
 * Find the visible fragments visible to the current user from the fragment list
 */
inline fun List<androidx.fragment.app.Fragment>?.findUserVisibleChildFragment(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)

/**
 * Find the visible fragments visible to the current user from the FragmentActivity
 */
inline fun androidx.fragment.app.FragmentActivity?.findUserVisibleChildFragment(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)

/**
 * Find the visible fragments visible to the current user from the fragment
 */
inline fun androidx.fragment.app.Fragment?.findUserVisibleChildFragment(): androidx.fragment.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)


/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun List<androidx.fragment.app.Fragment>?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): androidx.fragment.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun androidx.fragment.app.FragmentActivity?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): androidx.fragment.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun androidx.fragment.app.Fragment?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): androidx.fragment.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)
