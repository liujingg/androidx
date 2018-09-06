package me.panpf.androidx.content;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;

import org.jetbrains.annotations.NotNull;

public class ClipIntent extends ClipContent {

    @NotNull
    public Intent intent;

    ClipIntent(@NotNull String mimeType, @NotNull Intent intent) {
        super(mimeType);
        this.intent = intent;
    }

    public ClipIntent(@NotNull Intent intent) {
        this(ClipDescription.MIMETYPE_TEXT_INTENT, intent);
    }

    @Override
    public ClipData.Item toItem() {
        return new ClipData.Item(intent);
    }
}
