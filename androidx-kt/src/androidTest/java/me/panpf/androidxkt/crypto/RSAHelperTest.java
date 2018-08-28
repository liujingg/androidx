package me.panpf.androidxkt.crypto;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RSAHelperTest {

    private static final String SOURCE = "{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}";

    private static final String SOURCE_OAEP = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边";

    /**
     * 测试公钥加密私钥解密
     */
    @Test
    public void testPubPriBytes() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = RSAHelper.createKey(1024);
        RSAHelper rsaHelper = RSAHelper.defaultConfig();
        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
        Assert.assertEquals("testPubPriBytes", SOURCE, decryptResult);
    }

    /**
     * 测试私钥加密公钥解密
     */
    @Test
    public void testPriPubBytes() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = RSAHelper.createKey(1024);
        RSAHelper rsaHelper = RSAHelper.defaultConfig();
        String decryptResult = rsaHelper.decrypt(keyPair.getPublic(), rsaHelper.encrypt(keyPair.getPrivate(), SOURCE.getBytes()));
        Assert.assertEquals("testPriPubBytes", SOURCE, decryptResult);
    }

    /**
     * 测试公钥加密私钥解密转 Base64
     */
    @Test
    public void testPubPriWithBase64() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = RSAHelper.createKey(1024);
        RSAHelper rsaHelper = RSAHelper.defaultConfig();
        String decryptResult = rsaHelper.decryptFromBase64(keyPair.getPrivate(), rsaHelper.encryptToBase64(keyPair.getPublic(), SOURCE));
        Assert.assertEquals("testPubPriWithBase64", SOURCE, decryptResult);
    }

    /**
     * 测试私钥加密公钥解密转 Base64
     */
    @Test
    public void testPriPubWithBase64() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        KeyPair keyPair = RSAHelper.createKey(1024);
        RSAHelper rsaHelper = RSAHelper.defaultConfig();
        String decryptResult = rsaHelper.decryptFromBase64(keyPair.getPublic(), rsaHelper.encryptToBase64(keyPair.getPrivate(), SOURCE));
        Assert.assertEquals("testPriPubWithBase64", SOURCE, decryptResult);
    }


    /**
     * 测试签名、验证
     */
    @Test
    public void testSignBytes() throws InvalidKeyException, SignatureException {
        KeyPair keyPair = RSAHelper.createKey(1024);
        byte[] bytesSign = RSAHelper.sign(keyPair.getPrivate(), SOURCE);
        Assert.assertTrue("testSignBytes", RSAHelper.verify(keyPair.getPublic(), SOURCE, bytesSign));
    }

    /**
     * 测试签名、验证 Base64
     */
    @Test
    public void testSignWithBase64() throws InvalidKeyException, SignatureException {
        KeyPair keyPair = RSAHelper.createKey(1024);
        String base64Sign = RSAHelper.signToBase64(keyPair.getPrivate(), SOURCE);
        Assert.assertTrue("testSignWithBase64", RSAHelper.verifyFromBase64(keyPair.getPublic(), SOURCE, base64Sign));
    }

    /**
     * 使用错误的 KEY 解密
     */
    @Test
    public void testErrorKey() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        RSAHelper rsaHelper = RSAHelper.defaultConfig();

        byte[] encryptBytes = rsaHelper.encrypt(RSAHelper.createKey(1024).getPublic(), SOURCE.getBytes());
        String bytesPriKeyDecryptResult = null;
        try {
            bytesPriKeyDecryptResult = rsaHelper.decrypt(RSAHelper.createKey(1024).getPrivate(), encryptBytes);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        Assert.assertNotEquals("testErrorKey", SOURCE, bytesPriKeyDecryptResult);
    }


//    @Test
//    public void testEcbNoPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().noPadding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testEcbNoPadding", SOURCE, decryptResult);
//    }

    @Test
    public void testEcbPKCS1Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().pkcs1Padding().build();
        KeyPair keyPair = RSAHelper.createKey(1024);
        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
        Assert.assertEquals("testEcbPKCS1Padding", SOURCE, decryptResult);
    }

//    @Test
//    public void testEcbPKCS5Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().pkcs5Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testEcbPKCS5Padding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testEcbPKCS7Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().pkcs7Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testEcbPKCS7Padding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testEcbISO10126Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().iso10126Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testEcbISO10126Padding", SOURCE, decryptResult);
//    }

    @Test
    public void testEcbOAEPPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().oaepPadding().build();
        KeyPair keyPair = RSAHelper.createKey(1024);
        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE_OAEP.getBytes()));
        Assert.assertEquals("testEcbOAEPPadding", SOURCE_OAEP, decryptResult);
    }

//    @Test
//    public void testEcbSSL3Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().ecbMode().ssl3Padding().build();
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testEcbSSL3Padding", SOURCE, decryptResult);
//    }


//    @Test
//    public void testCbcNoPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().noPadding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testCbcNoPadding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testCbcPKCS1Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().pkcs1Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testCbcPKCS1Padding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testCbcPKCS5Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().pkcs5Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testCbcPKCS5Padding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testCbcPKCS7Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().pkcs7Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testCbcPKCS7Padding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testCbcISO10126Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().iso10126Padding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testCbcISO10126Padding", SOURCE, decryptResult);
//    }

//    @Test
//    public void testCbcOAEPPadding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().oaepPadding().build();
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE_OAEP.getBytes()));
//        Assert.assertEquals("testCbcOAEPPadding", SOURCE_OAEP, decryptResult);
//    }

//    @Test
//    public void testCbcSSL3Padding() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        KeyPair keyPair = RSAHelper.createKey(1024);
//        RSAHelper rsaHelper = RSAHelper.makeBuilder().cbcMode().ssl3Padding().build();
//        String decryptResult = rsaHelper.decrypt(keyPair.getPrivate(), rsaHelper.encrypt(keyPair.getPublic(), SOURCE.getBytes()));
//        Assert.assertEquals("testCbcSSL3Padding", SOURCE, decryptResult);
//    }
}
