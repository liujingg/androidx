package me.panpf.androidx.content;

import android.content.ClipData;

import org.jetbrains.annotations.NotNull;

public abstract class ClipContent {
    @NotNull
    public String mimeType;

    public ClipContent(@NotNull String mimeType) {
        this.mimeType = mimeType;
    }

    public abstract ClipData.Item toItem();
}
