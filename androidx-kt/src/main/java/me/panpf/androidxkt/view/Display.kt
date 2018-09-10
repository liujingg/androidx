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

package me.panpf.androidxkt.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager

/*
 * Extension method related to screen display
 */

val Context.screenSize: Point get() = Point().apply { (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(this) }

val Context.screenWidth: Int get() = Point().apply { (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(this) }.x

val Context.screenHeight: Int get() = Point().apply { (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(this) }.y

val Context.displayMetrics: DisplayMetrics get() = this.resources.displayMetrics

val Context.screenDensity: Float get() = this.resources.displayMetrics.density

val Context.screenDensityDpi: Int get() = this.resources.displayMetrics.densityDpi


fun Context.isPortraitOrientation() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun android.app.Fragment.isPortraitOrientation() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun android.support.v4.app.Fragment.isPortraitOrientation() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Activity.isPortraitOrientation() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT