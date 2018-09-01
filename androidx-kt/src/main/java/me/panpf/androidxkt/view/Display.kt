package me.panpf.androidxkt.view

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import android.app.Fragment as OriginFragment
import android.support.v4.app.Fragment as SupportFragment

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