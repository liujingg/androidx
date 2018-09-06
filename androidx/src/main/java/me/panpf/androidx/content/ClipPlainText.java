package me.panpf.androidx.content;

import android.content.ClipData;
import android.content.ClipDescription;

import org.jetbrains.annotations.NotNull;

public class ClipPlainText extends ClipContent {

    @NotNull
    public CharSequence text;

    ClipPlainText(@NotNull String mimeType, @NotNull CharSequence text) {
        super(mimeType);
        this.text = text;
    }

    public ClipPlainText(@NotNull CharSequence text) {
        this(ClipDescription.MIMETYPE_TEXT_PLAIN, text);
    }

    @Override
    public ClipData.Item toItem() {
        return new ClipData.Item(text);
    }
}
