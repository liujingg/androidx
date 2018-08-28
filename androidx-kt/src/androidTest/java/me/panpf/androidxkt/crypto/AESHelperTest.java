package me.panpf.androidxkt.crypto;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AESHelperTest {
    private static final String SOURCE = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边，盯着那条裙子，无论如何都不肯走。\n" +
            "\n" +
            "她太想要这样一条裙子了，或者说白了，她想要一件新衣服，一件不是妈妈手工做的，而是商店里买来的衣服。\n" +
            "\n" +
            "她听见已经走出了几步的妈妈跟站得更远的爸爸说，“这孩子到底随谁呢？才这么大就这么臭美？我们家里人哪有这种样子的？能不能要点脸？”\n" +
            "\n" +
            "爸爸已经非常烦躁，走上前来，不由分说劈头给了她一个耳光，“就知道穿穿穿，打扮那么排场出去干什么？”\n" +
            "\n" +
            "她哇地一声哭了，妈妈倒是很恼怒地跑过来拉住爸爸，“你有病吗？谁让你打她了？”\n" +
            "\n" +
            "可是裙子最终还是没买。她反而成了全家人的笑柄，一直到很多年后，妈妈提起她小时候，还是笑得肚子疼，“一丁点儿大，臭美得很！”\n" +
            "\n" +
            "然后有兄弟姐妹在旁边起哄揶揄她";

    private static final String SOURCE_NOPADDING = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边，盯着那";

    @Test
    public void testDefaultConfig() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.defaultConfig(AESHelper.createKey(128));
        String decryptResult = aesHelper.decrypt(aesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testDefaultConfig", SOURCE, decryptResult);
    }

    @Test
    public void testDefaultConfigWithBase64() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.defaultConfig(AESHelper.createKey(128));
        String decryptResult = aesHelper.decryptFromBase64(aesHelper.encryptToBase64(SOURCE.getBytes()));
        Assert.assertEquals("testDefaultConfigWithBase64", SOURCE, decryptResult);
    }


    @Test
    public void testEcbNoPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper ebcPacks5PaddingAesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).ecbMode().noPadding().build();
        String decryptResult = ebcPacks5PaddingAesHelper.decrypt(ebcPacks5PaddingAesHelper.encrypt(SOURCE_NOPADDING.getBytes()));
        Assert.assertEquals("testEcbNoPadding", SOURCE_NOPADDING, decryptResult);
    }

    @Test
    public void testEcbPKCS5Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper ebcPacks5PaddingAesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).ecbMode().pkcs5Padding().build();
        String decryptResult = ebcPacks5PaddingAesHelper.decrypt(ebcPacks5PaddingAesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testEcbPKCS5Padding", SOURCE, decryptResult);
    }

    @Test
    public void testEcbPKCS7Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).ecbMode().pkcs7Padding().build();
        String decryptResult = aesHelper.decrypt(aesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testEcbPKCS7Padding", SOURCE, decryptResult);
    }

    @Test
    public void testEcbISO10126Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).ecbMode().iso10126Padding().build();
        String decryptResult = aesHelper.decrypt(aesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testEcbISO10126Padding", SOURCE, decryptResult);
    }


    @Test
    public void testCbcNoPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper ebcPacks5PaddingAesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).cbcMode().noPadding().build();
        String decryptResult = ebcPacks5PaddingAesHelper.decrypt(ebcPacks5PaddingAesHelper.encrypt(SOURCE_NOPADDING.getBytes()));
        Assert.assertEquals("testCbcNoPadding", SOURCE_NOPADDING, decryptResult);
    }

    @Test
    public void testCbcPKCS5Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper ebcPacks5PaddingAesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).cbcMode().pkcs5Padding().build();
        String decryptResult = ebcPacks5PaddingAesHelper.decrypt(ebcPacks5PaddingAesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testCbcPKCS5Padding", SOURCE, decryptResult);
    }

    @Test
    public void testCbcPKCS7Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).cbcMode().pkcs7Padding().build();
        String decryptResult = aesHelper.decrypt(aesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testCbcPKCS7Padding", SOURCE, decryptResult);
    }

    @Test
    public void testCbcISO10126Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).cbcMode().iso10126Padding().build();
        String decryptResult = aesHelper.decrypt(aesHelper.encrypt(SOURCE.getBytes()));
        Assert.assertEquals("testCbcISO10126Padding", SOURCE, decryptResult);
    }


    @Test
    public void testErrorPassword() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AESHelper aesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).cbcMode().pkcs5Padding().build();
        AESHelper errorAesHelper = AESHelper.makeBuilder(AESHelper.createKey(128)).cbcMode().pkcs5Padding().build();

        String encryptResult = aesHelper.encryptToBase64(SOURCE);
        String errorDecryptResult = null;
        try {
            errorDecryptResult = errorAesHelper.decryptFromBase64(encryptResult);
        } catch (BadPaddingException e) {
//            e.printStackTrace();
        }

        Assert.assertNotEquals("testErrorPassword", SOURCE, errorDecryptResult);
    }

    @Test
    public void testCreateKeyBySeed() {
        String seed = "" + System.currentTimeMillis();

        String key1 = AESHelper.keyToBase64(AESHelper.createKeyBySeed(seed, 16));
        String key2 = AESHelper.keyToBase64(AESHelper.createKeyBySeed(seed, 16));

        Assert.assertEquals("testCreateKeyBySeed", key1, key2);
    }
}
