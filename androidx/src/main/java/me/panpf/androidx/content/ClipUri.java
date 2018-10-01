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

package me.panpf.androidx.content;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentResolver;
import android.net.Uri;

import org.jetbrains.annotations.NotNull;

import me.panpf.javax.util.Arrayx;

public class ClipUri extends ClipContent {

    @NotNull
    public Uri uri;

    public ClipUri(@NotNull String mimeType, @NotNull Uri uri) {
        super(mimeType);
        this.uri = uri;
    }

    public ClipUri(@NotNull Uri uri) {
        super(ClipDescription.MIMETYPE_TEXT_URILIST);
        this.uri = uri;
    }

    public ClipUri(@NotNull ContentResolver resolver, @NotNull Uri uri) {
        super(getMimeTypes(resolver, uri)[0]);
        this.uri = uri;
    }

    /**
     * Finds all applicable MIME types for a given URI.
     *
     * @param resolver ContentResolver used to get information about the URI.
     * @param uri      The URI.
     * @return Returns an array of MIME types.
     */
    public static String[] getMimeTypes(ContentResolver resolver, Uri uri) {
        String[] mimeTypes = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            String realType = resolver.getType(uri);
            mimeTypes = resolver.getStreamTypes(uri, "*/*");
            if (realType != null) {
                if (mimeTypes == null) {
                    mimeTypes = new String[]{realType};
                } else if (!Arrayx.toList(mimeTypes).contains(realType)) {
                    String[] tmp = new String[mimeTypes.length + 1];
                    tmp[0] = realType;
                    System.arraycopy(mimeTypes, 0, tmp, 1, mimeTypes.length);
                    mimeTypes = tmp;
                }
            }
        }
        if (mimeTypes == null) {
            mimeTypes = new String[]{ClipDescription.MIMETYPE_TEXT_URILIST};
        }
        return mimeTypes;
    }

    @Override
    public ClipData.Item toItem() {
        return new ClipData.Item(uri);
    }
}
