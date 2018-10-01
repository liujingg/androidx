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

package me.panpf.androidx.test.content;

import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import me.panpf.androidx.Androidx;
import me.panpf.androidx.content.ClipContent;
import me.panpf.androidx.content.ClipHtmlText;
import me.panpf.androidx.content.ClipIntent;
import me.panpf.androidx.content.ClipPlainText;
import me.panpf.androidx.content.ClipUri;
import me.panpf.androidx.content.Clipboardx;
import me.panpf.androidx.test.util.WeakAsyncTaskTestActivity;
import me.panpf.androidx.test.widget.ToastxTestActivity;

@RunWith(AndroidJUnit4.class)
public class ClipboardxTest {

    private static final String TEST_TEXT = "我也在奢望，奢望有一天，你能来找我";
    private static final String TEST_TEXT2 = "你还会回来吗？应该不会了。";
    private static final String TEST_HTML_TEXT = "短文学伤感文章情感日志心情日记散文精选诗歌大全经典语句专题长篇小说投稿网址积分商城";
    private static final String TEST_HTML_HTML = "<ul>\n" +
            "\t\t<li class=\"thiscase\"><a href=\"/sanwen/suibi/\">散文随笔</a></li>\n" +
            "\t\t<li><a href=\"/qinggan/meiwen/\">美文欣赏</a></li>\n" +
            "\t\t<li><a href=\"/yuju/shanggan/\">伤感的句子</a></li>\n" +
            "\t\t<li><a href=\"/yuju/youmei/\">优美的句子</a></li>\n" +
            "\t\t<li><a href=\"/yuju/weimei/\">唯美的句子</a></li>\n" +
            "\t\t<li><a href=\"/yuju/shangxin/\">伤心的句子</a></li>\n" +
            "\t\t<li><a href=\"/duanwen/mingyan/\">名言名句</a></li>\n" +
            "\t\t<li><a href=\"/yuju/xiangnian/\">想念的句子</a></li>\n" +
            "\t\t<li><a href=\"/duanwen/lizhi/\">励志签名</a></li>\n" +
            "\t\t<li><a href=\"/duanwen/gerenqianming/\">个人签名</a></li>\n" +
            "\t\t<li><a href=\"/huayu/ganren/\">感人的话</a></li>\n" +
            "\t\t<li class=\"thiscase\"><a href=\"/yulu/aiqing/\">爱情语录</a></li>\n" +
            "\t\t<li><a href=\"/huayu/biaobai/\">表白的话</a></li>\n" +
            "\t\t<li><a href=\"/juzi/beishang/\">悲伤的句子</a></li>\n" +
            "\t\t<li><a href=\"/yulu/gaoxiao/\">搞笑语录</a></li>\n" +
            "\t\t<li><a href=\"/yulu/aiqingxuanyan/\">爱情宣言</a></li>\n" +
            "\t\t<li><a href=\"/juzi/biaobai/\">表白的句子</a></li>\n" +
            "\t\t<li><a href=\"/duanwen/geyan/\">人生格言</a></li>\n" +
            "\t\t<li><a href=\"/yulu/yijuhua/\">一句话经典语录</a></li>\n" +
            "\t\t<li><a href=\"/huayu/lizhi/\">励志的话</a></li>\n" +
            "\t\t<li><a href=\"/yulu/shangxinqianming/\">伤心的个性签名</a></li>\n" +
            "\t\t<li><a href=\"/huayu/zheli/\">有哲理的话</a></li>\n" +
            "\t</ul>\n";
    private static final String TEST_HTML_TEXT2 = "散文随笔美文欣赏伤感的句子优美的句子唯美的句子伤心的句子名言名句想念的句子励志签名个人签名感人的话爱情语录表白的话悲伤的句子搞笑语录爱情宣言表白的句子人生格言一句话经典语录励志的话伤心的个性签名有哲理的话";
    private static final String TEST_HTML_HTML2 = "<ul>\n" +
            "    <li><a href=\"/\" >短文学</a></li>\n" +
            "    <li><a href=\"/shanggan/\" >伤感文章</a></li>\n" +
            "    <li><a href=\"/qinggan/\" >情感日志</a></li>\n" +
            "    <li><a href=\"/diary/\" >心情日记</a></li>\n" +
            "    <li><a href=\"/sanwen/\" >散文精选</a></li>\n" +
            "    <li><a href=\"/shige/\" >诗歌大全</a></li>\n" +
            "    <li><a href=\"/yuju/\" >经典语句</a></li>\n" +
            "    <li><a href=\"/haowenzhang/\" >专题</a></li>\n" +
            "    <li><a href=\"/book/\">长篇小说</a></li>\n" +
            "    <li><a href=\"/tougao.html\">投稿网址</a></li>\n" +
            "    <li><a href=\"/shop\">积分商城</a></li>\n" +
            "  </ul>\n";

    @NotNull
    private Context prepareContext() {
        final Context context = InstrumentationRegistry.getContext();

        /*
         * 在测试环境首次获取 ClipboardManager 时 Android 内部会创建 ClipboardManager，并创建 Handler，
         * 由于测试代码是在异步线程执行的，因此这里就涉及到了在异步线程创建 Handler 的问题
         */
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Androidx.runInUI(new Runnable() {
            @Override
            public void run() {
                Clipboardx.get(context);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return context;
    }

    @Test
    public void testLabel() {
        Context context = prepareContext();

        Clipboardx.copyText(context, "clipLabel", TEST_TEXT);
        Assert.assertEquals(Clipboardx.getLabel(context), "clipLabel");
    }

    @Test
    public void testText() {
        Context context = prepareContext();

        Clipboardx.copyText(context, TEST_TEXT);
        Assert.assertEquals(Clipboardx.getText(context), TEST_TEXT);

        Clipboardx.copyText(context, new CharSequence[]{TEST_TEXT, TEST_TEXT2});
        CharSequence[] texts = Clipboardx.getTexts(context);
        Assert.assertEquals(texts != null ? texts[0] : "null", TEST_TEXT);
        Assert.assertEquals(texts != null ? texts[1] : "null", TEST_TEXT2);
    }

    @Test
    public void testHtmlText() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Context context = prepareContext();

            Clipboardx.copyHtmlText(context, TEST_HTML_TEXT, TEST_HTML_HTML);
            ClipHtmlText clipHtmlText = Clipboardx.getHtmlText(context);
            Assert.assertEquals(clipHtmlText != null ? clipHtmlText.text : "null", TEST_HTML_TEXT);
            Assert.assertEquals(clipHtmlText != null ? clipHtmlText.htmlText : "null", TEST_HTML_HTML);

            Clipboardx.copyHtmlText(context, new ClipHtmlText[]{new ClipHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML), new ClipHtmlText(TEST_HTML_TEXT2, TEST_HTML_HTML2)});
            ClipHtmlText[] htmlTexts = Clipboardx.getHtmlTexts(context);

            ClipHtmlText clipHtmlText1 = htmlTexts != null ? htmlTexts[0] : null;
            Assert.assertEquals(clipHtmlText1 != null ? clipHtmlText1.text : "null", TEST_HTML_TEXT);
            Assert.assertEquals(clipHtmlText1 != null ? clipHtmlText1.htmlText : "null", TEST_HTML_HTML);

            ClipHtmlText clipHtmlText2 = htmlTexts != null ? htmlTexts[1] : null;
            Assert.assertEquals(clipHtmlText2 != null ? clipHtmlText2.text : "null", TEST_HTML_TEXT2);
            Assert.assertEquals(clipHtmlText2 != null ? clipHtmlText2.htmlText : "null", TEST_HTML_HTML2);
        }
    }

    @Test
    public void testIntent() {
        Context context = prepareContext();

        Intent intent = new Intent(context, ToastxTestActivity.class);
        Clipboardx.copyIntent(context, intent);

        Intent result = Clipboardx.getIntent(context);
        Assert.assertEquals(result.getComponent().toString(), intent.getComponent().toString());

        Intent intent2 = new Intent(context, WeakAsyncTaskTestActivity.class);
        Clipboardx.copyIntent(context, new Intent[]{intent, intent2});

        Intent[] results = Clipboardx.getIntents(context);
        Assert.assertEquals(results[0].getComponent().toString(), intent.getComponent().toString());
        Assert.assertEquals(results[1].getComponent().toString(), intent2.getComponent().toString());
    }

    @Test
    public void testRawUri() {
        Context context = prepareContext();

        Uri uri = Uri.parse("https://www.github.com");
        Clipboardx.copyRawUri(context, uri);

        ClipUri result = Clipboardx.getUri(context);
        Assert.assertEquals(result.mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
        Assert.assertEquals(result.uri.toString(), uri.toString());

        Uri uri2 = Uri.parse("https://www.youtube.com");
        Clipboardx.copyRawUri(context, new Uri[]{uri, uri2});

        ClipUri[] results = Clipboardx.getUris(context);
        Assert.assertEquals(results[0].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
        Assert.assertEquals(results[0].uri.toString(), uri.toString());
        Assert.assertEquals(results[1].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
        Assert.assertEquals(results[1].uri.toString(), uri2.toString());
    }

    @Test
    public void testMimeTypeUri() {
        Context context = prepareContext();

        Uri uri = Uri.parse("https://www.github.com");
        Clipboardx.copyMimeTypeUri(context, "app/android", uri);

        ClipUri result = Clipboardx.getUri(context);
        Assert.assertEquals(result.mimeType, "app/android");
        Assert.assertEquals(result.uri.toString(), uri.toString());

        Uri uri2 = Uri.parse("https://www.youtube.com");
        Clipboardx.copyMimeTypeUri(context, "app/android", new Uri[]{uri, uri2});

        ClipUri[] results = Clipboardx.getUris(context);
        Assert.assertEquals(results[0].mimeType, "app/android");
        Assert.assertEquals(results[0].uri.toString(), uri.toString());
        Assert.assertEquals(results[1].mimeType, "app/android");
        Assert.assertEquals(results[1].uri.toString(), uri2.toString());
    }

    @Test
    public void testUri() {
        Context context = prepareContext();

        Uri uri = Uri.parse("https://www.github.com");
        Clipboardx.copyUri(context, uri);

        ClipUri result = Clipboardx.getUri(context);
        Assert.assertEquals(result.mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
        Assert.assertEquals(result.uri.toString(), uri.toString());

        Uri uri2 = Uri.parse("https://www.youtube.com");
        Clipboardx.copyUri(context, new Uri[]{uri, uri2});

        ClipUri[] results = Clipboardx.getUris(context);
        Assert.assertEquals(results[0].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
        Assert.assertEquals(results[0].uri.toString(), uri.toString());
        Assert.assertEquals(results[1].mimeType, ClipDescription.MIMETYPE_TEXT_URILIST);
        Assert.assertEquals(results[1].uri.toString(), uri2.toString());
    }

    @Test
    public void testListener() {
        final Context context = prepareContext();

        final String[] result = new String[1];
        ClipboardManager.OnPrimaryClipChangedListener listener = new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                result[0] = "onPrimaryClipChanged";
            }
        };

        Clipboardx.addPrimaryClipChangedListener(context, listener);
        Clipboardx.copyText(context, "Hello Word");
        // Callback will be delayed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result[0], "onPrimaryClipChanged");

        result[0] = "None";
        Clipboardx.removePrimaryClipChangedListener(context, listener);
        Clipboardx.copyText(context, "Hello Word");
        // Callback will be delayed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(result[0], "None");
    }

    @Test
    public void testMultiType() {
        final Context context = prepareContext();

        ClipPlainText text = new ClipPlainText(TEST_TEXT);
        ClipHtmlText htmlText = new ClipHtmlText(TEST_HTML_TEXT, TEST_HTML_HTML);
        ClipIntent intent = new ClipIntent(new Intent(context, ToastxTestActivity.class));
        ClipUri uri = new ClipUri("app/android", Uri.parse("https://www.youtube.com"));

        Clipboardx.copyContents(context, new ClipContent[]{text, htmlText, intent, uri});

        ClipContent[] results = Clipboardx.getContents(context);

        ClipPlainText textResult = (ClipPlainText) results[0];
        Assert.assertEquals(textResult.text, text.text);

        ClipHtmlText htmlTextResult = (ClipHtmlText) results[1];
        Assert.assertEquals(htmlTextResult.text, htmlText.text);
        Assert.assertEquals(htmlTextResult.htmlText, htmlText.htmlText);

        ClipIntent intentResult = (ClipIntent) results[2];
        Assert.assertEquals(intentResult.intent.getComponent().toString(), intent.intent.getComponent().toString());

        ClipUri uriResult = (ClipUri) results[3];
        Assert.assertEquals(uriResult.mimeType, uri.mimeType);
        Assert.assertEquals(uriResult.uri.toString(), uri.uri.toString());
    }

    @Test
    public void testClean() {
        if (Build.VERSION.SDK_INT >= 28) {
            final Context context = prepareContext();

            Clipboardx.copyText(context, "Hello Word");
            Assert.assertEquals(Clipboardx.getText(context), "Hello Word");

            Clipboardx.clear(context);
            Assert.assertNull(Clipboardx.getText(context));
        }
    }
}
