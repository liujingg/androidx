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

package me.panpf.androidx.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import me.panpf.androidx.widget.Toastx;

@SuppressWarnings("WeakerAccess")
public class Viewx {

    private Viewx() {
    }


    public static void setLongClickToastHint(@NonNull final View view, final String hintContent) {
        view.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toastx.showShort(view, hintContent);
                return true;
            }
        });
    }

    public static void setLongClickToastHint(@NonNull View view, final int hintContentId) {
        setLongClickToastHint(view, view.getResources().getString(hintContentId));
    }


    public static void setLayoutWidth(@NonNull View view, int newWidth, int initHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(newWidth, initHeight);
        } else {
            layoutParams.width = newWidth;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutWidth(@NonNull View view, int newWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = newWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setLayoutHeight(@NonNull View view, int newHeight, int initWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(initWidth, newHeight);
        } else {
            layoutParams.height = newHeight;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutHeight(@NonNull View view, int newHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = newHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setLayoutSize(@NonNull View view, int width, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(width, height);
        } else {
            layoutParams.width = width;
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutMarginTop(@NonNull View view, int newMarinTop) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = newMarinTop;
            view.setLayoutParams(layoutParams);
        }
    }


    public static void addLayoutHeight(@NonNull View view, int addHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height += addHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addLayoutWidth(@NonNull View view, int addWidth) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width += addWidth;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addLayoutSize(@NonNull View view, int addWidth, int addHeight) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width += addWidth;
            layoutParams.height += addHeight;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addLayoutMarginTop(@NonNull View view, int addMarinTop) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin += addMarinTop;
            view.setLayoutParams(layoutParams);
        }
    }


    @NonNull
    public static Bitmap toBitmap(@NonNull View view, @NonNull Bitmap.Config config, float scale) {
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

    @NonNull
    public static Bitmap toBitmap(@NonNull View view, @NonNull Bitmap.Config config) {
        return toBitmap(view, config, 1.0f);
    }

    @NonNull
    public static Bitmap toBitmapByMaxWidth(@NonNull View view, @NonNull Bitmap.Config config, int maxWidth) {
        return toBitmap(view, config, Math.min((float) maxWidth / (float) view.getWidth(), 1.0F));
    }

    @NonNull
    public static Bitmap toBitmapByMaxHeight(@NonNull View view, @NonNull Bitmap.Config config, int maxHeight) {
        return toBitmap(view, config, Math.min((float) maxHeight / (float) view.getHeight(), 1.0F));
    }


    @NonNull
    public static View inflateLayout(@NonNull Context context, @LayoutRes int id, @Nullable ViewGroup parent, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(id, parent, attachToRoot);
    }

    @NonNull
    public static View inflateLayout(@NonNull Context context, @LayoutRes int id, @Nullable ViewGroup parent) {
        return LayoutInflater.from(context).inflate(id, parent, false);
    }

    @NonNull
    public static View inflateLayout(@NonNull Context context, @LayoutRes int id) {
        return LayoutInflater.from(context).inflate(id, null, false);
    }
}