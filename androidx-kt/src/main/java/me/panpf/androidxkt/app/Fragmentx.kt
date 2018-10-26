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

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import me.panpf.androidx.app.Fragmentx

/**
 * Return true if the fragment has been destroyed
 */
inline fun android.app.Fragment.isDestroyedCompat(): Boolean = Fragmentx.isDestroyedCompat(this)

/**
 * Return true if the fragment has been destroyed
 */
inline fun android.support.v4.app.Fragment.isDestroyedCompat(): Boolean = Fragmentx.isDestroyedCompat(this)


/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> android.support.v4.app.Fragment.getImplWithParent(clazz: Class<T>): T? = Fragmentx.getImplWithParent(this, clazz)

/**
 * If the own or parent Fragment implements the specified [clazz], it returns its implementation.
 */
inline fun <T> android.app.Fragment.getImplWithParent(clazz: Class<T>): T? = Fragmentx.getImplWithParent(this, clazz)


/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.support.v4.app.Fragment>.instance(arguments: Bundle): android.support.v4.app.Fragment = Fragmentx.instance(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.support.v4.app.Fragment>.instance(): android.support.v4.app.Fragment = Fragmentx.instance(this)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.app.Fragment>.instanceOrigin(arguments: Bundle): android.app.Fragment = Fragmentx.instanceOrigin(this, arguments)

/**
 * Instantiate a Fragment and set arguments
 */
inline fun Class<out android.app.Fragment>.instanceOrigin(): android.app.Fragment = Fragmentx.instanceOrigin(this)


/**
 * Find the visible fragments visible to the current user from the fragment list
 */
inline fun List<android.support.v4.app.Fragment>?.findUserVisibleChildFragment(): android.support.v4.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)

/**
 * Find the visible fragments visible to the current user from the FragmentActivity
 */
inline fun FragmentActivity?.findUserVisibleChildFragment(): android.support.v4.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)

/**
 * Find the visible fragments visible to the current user from the fragment
 */
inline fun android.support.v4.app.Fragment?.findUserVisibleChildFragment(): android.support.v4.app.Fragment? = Fragmentx.findUserVisibleChildFragment(this)


/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun List<android.support.v4.app.Fragment>?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun FragmentActivity?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)

/**
 * Find the target fragment from the specified fragment list based on the current Item of ViewPager
 */
inline fun android.support.v4.app.Fragment?.findFragmentByViewPagerCurrentItem(viewPagerCurrentItem: Int): android.support.v4.app.Fragment? =
        Fragmentx.findFragmentByViewPagerCurrentItem(this, viewPagerCurrentItem)


inline fun android.app.Fragment.requireContext(): Context = Fragmentx.requireContext(this)


inline fun android.support.v4.app.Fragment.requireAppContext(): Context = Fragmentx.requireAppContext(this)

inline fun android.app.Fragment.requireAppContext(): Context = Fragmentx.requireAppContext(this)
