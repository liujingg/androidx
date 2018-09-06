package me.panpf.androidx.content;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

public class ClipHtmlText extends ClipContent {

    @NotNull
    public CharSequence text;

    @NotNull
    public String htmlText;

    ClipHtmlText(@NotNull String mimeType, @NotNull CharSequence text, @NotNull String htmlText) {
        super(mimeType);
        this.text = text;
        this.htmlText = htmlText;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ClipHtmlText(@NotNull CharSequence text, @NotNull String htmlText) {
        this(ClipDescription.MIMETYPE_TEXT_HTML, text, htmlText);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public ClipData.Item toItem() {
        return new ClipData.Item(text, htmlText);
    }
}
