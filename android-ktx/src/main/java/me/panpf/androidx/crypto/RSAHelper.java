package me.panpf.androidx.crypto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * RSA 非对称加解密、签名/验证、生成/加载 KEY
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class RSAHelper {

    private static final String ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String MODE_ECB = "ECB";
//    private static final String MODE_CBC = "CBC";   // Android 不支持

    private String mode;
    private String padding;

    private RSAHelper(@Nullable String mode, @Nullable String padding) {
        this.mode = mode;
        this.padding = padding;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyText 公钥数据字符串
     * @throws InvalidKeySpecException 公钥无效
     */
    @NonNull
    public static PublicKey pubKey(@NonNull String publicKeyText) throws InvalidKeySpecException {
        byte[] buffer = Base64.decode(publicKeyText.getBytes(), Base64.DEFAULT);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 从字符串中加载私钥
     *
     * @param privateKeyText 私钥数据字符串
     * @throws InvalidKeySpecException 私钥无效
     */
    @NonNull
    public static PrivateKey priKey(@NonNull String privateKeyText) throws InvalidKeySpecException {
        byte[] buffer = Base64.decode(privateKeyText.getBytes(), Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 创建一对 RSA 密钥
     *
     * @param keySize key长度，一般为 1024 的倍数
     */
    @NonNull
    public static KeyPair createKey(int keySize) {
        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGen.initialize(keySize);
        return keyPairGen.generateKeyPair();
    }

    /**
     * 将公钥转成 BASE64 字符串便于输出保存
     *
     * @param key 公钥
     * @return 公钥的 BASE64 字符串，可使用{@link #pubKey(String)} 方法恢复成公钥
     */
    @NonNull
    public static String keyToBase64(@NonNull PublicKey key) {
        return Base64.encodeToString(key.getEncoded(), Base64.NO_WRAP);
    }

    /**
     * 将私钥转成 BASE64 字符串便于输出保存
     *
     * @param key 私钥
     * @return 私钥的 BASE64 字符串，可使用{@link #priKey(String)} 方法恢复成私钥
     */
    @NonNull
    public static String keyToBase64(@NonNull PrivateKey key) {
        return Base64.encodeToString(key.getEncoded(), Base64.NO_WRAP);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param priKey    私钥
     * @param textBytes 待签名数据
     * @return 对信息生成的签名
     * @throws InvalidKeyException 私钥无效
     * @throws SignatureException  签名异常
     */
    @NonNull
    public static byte[] sign(@NonNull PrivateKey priKey, @NonNull byte[] textBytes)
            throws InvalidKeyException, SignatureException {
        Signature signature;
        try {
            signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        signature.initSign(priKey);
        signature.update(textBytes);

        return signature.sign();
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param priKey 私钥
     * @param text   待签名数据
     * @return 对信息生成的签名
     * @throws InvalidKeyException 私钥无效
     * @throws SignatureException  签名异常
     */
    @NonNull
    public static byte[] sign(@NonNull PrivateKey priKey, @NonNull String text)
            throws InvalidKeyException, SignatureException {
        return sign(priKey, text.getBytes());
    }

    /**
     * 用私钥对信息生成数字签名并返回 BASE64 字符串
     *
     * @param priKey    私钥
     * @param textBytes 待签名数据
     * @return 对信息生成的签名并使用 BASE64 转码
     * @throws InvalidKeyException 私钥无效
     * @throws SignatureException  签名异常
     */
    @NonNull
    public static String signToBase64(@NonNull PrivateKey priKey, @NonNull byte[] textBytes)
            throws InvalidKeyException, SignatureException {
        return Base64.encodeToString(sign(priKey, textBytes), Base64.NO_WRAP);
    }

    /**
     * 用私钥对信息生成数字签名并返回 BASE64 字符串
     *
     * @param priKey 私钥
     * @param text   待签名文本
     * @return 对信息生成的签名并使用 BASE64 转码
     * @throws InvalidKeyException 私钥无效
     * @throws SignatureException  签名异常
     */
    @NonNull
    public static String signToBase64(@NonNull PrivateKey priKey, @NonNull String text)
            throws InvalidKeyException, SignatureException {
        return Base64.encodeToString(sign(priKey, text.getBytes()), Base64.NO_WRAP);
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey    公钥
     * @param data      原始数据
     * @param signBytes 签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verify(@NonNull PublicKey pubKey, @NonNull byte[] data, @NonNull byte[] signBytes)
            throws InvalidKeyException, SignatureException {
        Signature signature;
        try {
            signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(signBytes);
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey 公钥
     * @param data   原始数据
     * @param sign   签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verify(@NonNull PublicKey pubKey, @NonNull byte[] data, @NonNull String sign)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, data, sign.getBytes());
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey    公钥
     * @param text      原始文本
     * @param signBytes 签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verify(@NonNull PublicKey pubKey, @NonNull String text, @NonNull byte[] signBytes)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, text.getBytes(), signBytes);
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey 公钥
     * @param text   原始文本
     * @param sign   签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verify(@NonNull PublicKey pubKey, @NonNull String text, @NonNull String sign)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, text.getBytes(), sign.getBytes());
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey    公钥
     * @param data      原始数据
     * @param signBytes 签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verifyFromBase64(@NonNull PublicKey pubKey, @NonNull byte[] data, @NonNull byte[] signBytes)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, data, Base64.decode(signBytes, Base64.DEFAULT));
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey 公钥
     * @param data   原始数据
     * @param sign   签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verifyFromBase64(@NonNull PublicKey pubKey, @NonNull byte[] data, @NonNull String sign)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, data, Base64.decode(sign.getBytes(), Base64.DEFAULT));
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey    公钥
     * @param text      原始文本
     * @param signBytes 签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verifyFromBase64(@NonNull PublicKey pubKey, @NonNull String text, @NonNull byte[] signBytes)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, text.getBytes(), Base64.decode(signBytes, Base64.DEFAULT));
    }

    /**
     * 用公钥校验签名
     *
     * @param pubKey 公钥
     * @param text   原始文本
     * @param sign   签名
     * @throws InvalidKeyException 公钥无效
     * @throws SignatureException  签名异常
     */
    public static boolean verifyFromBase64(@NonNull PublicKey pubKey, @NonNull String text, @NonNull String sign)
            throws InvalidKeyException, SignatureException {
        return verify(pubKey, text.getBytes(), Base64.decode(sign.getBytes(), Base64.DEFAULT));
    }


    /**
     * 创建 RSAHelper.Builder
     */
    @NonNull
    public static RSAHelper.Builder makeBuilder() {
        return new Builder();
    }

    /**
     * 创建使用默认 mode 和 padding 的 RSAHelper
     */
    @NonNull
    public static RSAHelper defaultConfig() {
        return new Builder().build();
    }


    /**
     * 加密
     *
     * @param key       公钥或私钥
     * @param textBytes 待加密的明文
     * @return 加密后的密文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public byte[] encrypt(@NonNull Key key, @NonNull byte[] textBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = createCipher(key, Cipher.ENCRYPT_MODE);
        return blockDoCipher(cipher, Cipher.ENCRYPT_MODE, textBytes);
    }

    /**
     * 加密
     *
     * @param key  公钥或私钥
     * @param text 待加密的明文
     * @return 加密后的密文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public byte[] encrypt(@NonNull Key key, @NonNull String text)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(key, text.getBytes());
    }

    /**
     * 加密并返回 BASE64 字符串
     *
     * @param key       公钥或私钥
     * @param textBytes 待加密的明文
     * @return 加密并使用 BASE64 转码的密文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String encryptToBase64(@NonNull Key key, @NonNull byte[] textBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeToString(encrypt(key, textBytes), Base64.NO_WRAP);
    }

    /**
     * 加密并返回 BASE64 字符串
     *
     * @param key  公钥或私钥
     * @param text 待加密的明文
     * @return 加密并使用 BASE64 转码的密文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String encryptToBase64(@NonNull Key key, @NonNull String text)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeToString(encrypt(key, text.getBytes()), Base64.NO_WRAP);
    }

    /**
     * 解密
     *
     * @param key             公钥或私钥
     * @param cipherTextBytes 待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decrypt(@NonNull Key key, @NonNull byte[] cipherTextBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = createCipher(key, Cipher.DECRYPT_MODE);
        byte[] data = blockDoCipher(cipher, Cipher.DECRYPT_MODE, cipherTextBytes);
        return new String(data);
    }

    /**
     * 解密
     *
     * @param key        公钥或私钥
     * @param cipherText 待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decrypt(@NonNull Key key, @NonNull String cipherText)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(key, cipherText.getBytes());
    }

    /**
     * 解密使用 BASE64 转码的密文
     *
     * @param key             公钥或私钥
     * @param cipherTextBytes 使用了 BASE64 转码的待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decryptFromBase64(@NonNull Key key, @NonNull byte[] cipherTextBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(key, Base64.decode(cipherTextBytes, Base64.DEFAULT));
    }

    /**
     * 解密使用 BASE64 转码的密文
     *
     * @param key        公钥或私钥
     * @param cipherText 使用了 BASE64 转码的待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       秘钥无效
     * @throws BadPaddingException       数据填充异常，请检查指定的填充算法
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decryptFromBase64(@NonNull Key key, @NonNull String cipherText)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(key, Base64.decode(cipherText.getBytes(), Base64.DEFAULT));
    }

    private Cipher createCipher(@NonNull Key key, int opMode) throws InvalidKeyException {
        String cipherAlgorithm;
        if (mode == null || padding == null) {
            cipherAlgorithm = ALGORITHM;
        } else {
            cipherAlgorithm = String.format("%s/%s/%s", ALGORITHM, mode, padding);
        }

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(cipherAlgorithm);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalArgumentException(e);
        }

        cipher.init(opMode, key);

        return cipher;
    }

    private byte[] blockDoCipher(@NonNull Cipher cipher, int opMode, @NonNull byte[] data) throws BadPaddingException, IllegalBlockSizeException {
        int dataLength = data.length;
        int blockSize = cipher.getBlockSize();
        if (blockSize <= 0) blockSize = opMode == Cipher.ENCRYPT_MODE ? 117 : 128;
        if (dataLength > blockSize) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int offset = 0;
            while (offset < dataLength) {
                int length = offset + blockSize <= dataLength ? blockSize : dataLength - offset;
                byte[] cache = cipher.doFinal(data, offset, length);
                try {
                    outputStream.write(cache);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                offset += length;
            }
            return outputStream.toByteArray();
        } else {
            return cipher.doFinal(data);
        }
    }

    public static class Builder {
//        private static final String PADDING_NO = "NoPadding";  // Android 不支持
        private static final String PADDING_PKCS1 = "PKCS1Padding";
//        private static final String PADDING_PKCS5 = "PKCS5Padding";   // Android 不支持
//        private static final String PADDING_PKCS7 = "PKCS7Padding";  // Android 不支持
//        private static final String PADDING_ISO10126 = "ISO10126Padding";
        private static final String PADDING_OAEP = "OAEPPadding";
//        private static final String PADDING_SSL3 = "SSL3Padding";  // Android 不支持

        private String mode;
        private String padding;

        @NonNull
        public Builder ecbMode() {
            mode = MODE_ECB;
            return this;
        }

//        @NonNull
//        public Builder cbcMode() {
//            mode = MODE_CBC;
//            return this;
//        }

//        @NonNull
//        public Builder noPadding() {
//            padding = PADDING_NO;
//            return this;
//        }

        @NonNull
        public Builder pkcs1Padding() {
            padding = PADDING_PKCS1;
            return this;
        }

//        @NonNull
//        public Builder pkcs5Padding() {
//            padding = PADDING_PKCS5;
//            return this;
//        }

//        @NonNull
//        public Builder pkcs7Padding() {
//            padding = PADDING_PKCS7;
//            return this;
//        }

//        @NonNull
//        public Builder iso10126Padding() {
//            padding = PADDING_ISO10126;
//            return this;
//        }

        @NonNull
        public Builder oaepPadding() {
            padding = PADDING_OAEP;
            return this;
        }

//        @NonNull
//        public Builder ssl3Padding() {
//            padding = PADDING_SSL3;
//            return this;
//        }

        @NonNull
        public RSAHelper build() {
            return new RSAHelper(mode, padding);
        }
    }
}
