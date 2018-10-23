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

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.androidxkt.view

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import me.panpf.androidx.view.Viewx

/*
 * View related extension method
 */


fun View.appContext(): Context = Viewx.appContext(this)


inline fun View.setLongClickToastHint(hintContent: String) = Viewx.setLongClickToastHint(this, hintContent)

inline fun View.setLongClickToastHint(hintContentId: Int) = Viewx.setLongClickToastHint(this, hintContentId)


inline fun View.setLayoutWidth(newWidth: Int, initHeight: Int) = Viewx.setLayoutWidth(this, newWidth, initHeight)

inline fun View.setLayoutWidth(newWidth: Int) = Viewx.setLayoutWidth(this, newWidth)

inline fun View.setLayoutHeight(newHeight: Int, initWidth: Int) = Viewx.setLayoutHeight(this, newHeight, initWidth)

inline fun View.setLayoutHeight(newHeight: Int) = Viewx.setLayoutHeight(this, newHeight)

inline fun View.setLayoutSize(width: Int, height: Int) = Viewx.setLayoutSize(this, width, height)

inline fun View.setLayoutMarginTop(newMarinTop: Int) = Viewx.setLayoutMarginTop(this, newMarinTop)


inline fun View.addLayoutWidth(addWidth: Int) = Viewx.addLayoutWidth(this, addWidth)

inline fun View.addLayoutHeight(addHeight: Int) = Viewx.addLayoutHeight(this, addHeight)

inline fun View.addLayoutSize(addWidth: Int, addHeight: Int) = Viewx.addLayoutSize(this, addWidth, addHeight)

inline fun View.addLayoutMarginTop(addMarinTop: Int) = Viewx.addLayoutMarginTop(this, addMarinTop)


inline fun View.toBitmap(config: Bitmap.Config, scale: Float): Bitmap = Viewx.toBitmap(this, config, scale)

inline fun View.toBitmap(config: Bitmap.Config): Bitmap = Viewx.toBitmap(this, config)

inline fun View.toBitmapByMaxWidth(config: Bitmap.Config, maxWidth: Int): Bitmap = Viewx.toBitmapByMaxWidth(this, config, maxWidth)

inline fun View.toBitmapByMaxHeight(config: Bitmap.Config, maxHeight: Int): Bitmap = Viewx.toBitmapByMaxHeight(this, config, maxHeight)


inline fun Context.inflateLayout(@LayoutRes id: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View =
        Viewx.inflateLayout(this, id, parent, attachToRoot)

inline fun Context.inflateLayout(@LayoutRes id: Int, parent: ViewGroup? = null): View = Viewx.inflateLayout(this, id, parent)
