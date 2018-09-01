/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
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

package me.panpf.androidx.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import me.panpf.androidx.widget.Toastx;

@SuppressWarnings("WeakerAccess")
public class Viewx {

    public static void setLongClickToastHint(@NotNull final View view, final String hintContent) {
        view.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toastx.showShort(view, hintContent);
                return true;
            }
        });
    }

    public static void setLongClickToastHint(@NotNull View view, final int hintContentId) {
        setLongClickToastHint(view, view.getResources().getString(hintContentId));
    }


    public static void setLayoutWidth(@NotNull View view, int newWidth, int initHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(newWidth, initHeight);
        } else {
            layoutParams.width = newWidth;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutWidth(@NotNull View view, int newWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = newWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setLayoutHeight(@NotNull View view, int newHeight, int initWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(initWidth, newHeight);
        } else {
            layoutParams.height = newHeight;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutHeight(@NotNull View view, int newHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = newHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setLayoutSize(@NotNull View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(width, height);
        } else {
            layoutParams.width = width;
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutMarginTop(@NotNull View view, int newMarinTop) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = newMarinTop;
            view.setLayoutParams(layoutParams);
        }
    }


    public static void addLayoutHeight(@NotNull View view, int addHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height += addHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addLayoutWidth(@NotNull View view, int addWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width += addWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addLayoutSize(@NotNull View view, int addWidth, int addHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width += addWidth;
            layoutParams.height += addHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addLayoutMarginTop(@NotNull View view, int addMarinTop) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin += addMarinTop;
            view.setLayoutParams(layoutParams);
        }
    }

    @NotNull
    public static Bitmap toBitmap(@NotNull View view, @NotNull Bitmap.Config config, float scale) {
        int bitmapWidth = view.getWidth();
        int bitmapHeight = view.getHeight();
        Matrix matrix = new Matrix();
        if (scale > (float) 0) {
            bitmapWidth = (int) ((float) bitmapWidth * scale);
            bitmapHeight = (int) ((float) bitmapHeight * scale);
            matrix.setScale(scale, scale);
        }

        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, config);
        Canvas canvas = new Canvas(bitmap);
        canvas.setMatrix(matrix);
        view.draw(canvas);
        return bitmap;
    }

    @NotNull
    public static Bitmap toBitmap(@NotNull View view, @NotNull Bitmap.Config config) {
        return toBitmap(view, config, 1.0f);
    }

    @NotNull
    public static Bitmap toBitmapByMaxWidth(@NotNull View view, @NotNull Bitmap.Config config, int maxWidth) {
        return toBitmap(view, config, Math.min((float) maxWidth / (float) view.getWidth(), 1.0F));
    }

    @NotNull
    public static Bitmap toBitmapByMaxHeight(@NotNull View view, @NotNull Bitmap.Config config, int maxHeight) {
        return toBitmap(view, config, Math.min((float) maxHeight / (float) view.getHeight(), 1.0F));
    }
}