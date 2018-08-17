package me.panpf.androidx.crypto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES 对称加密、解密工具
 * <p>
 * <p>
 * DES 不支持 NoPadding、PKCS1Padding
 */
@SuppressWarnings("unused")
public class DESHelper {

    private static final String ALGORITHM = "DES";
    private static final String MODE_ECB = "ECB";
    private static final String MODE_CBC = "CBC";
    private static final String PADDING_PKCS5 = "PKCS5Padding";
    private static final String PADDING_PKCS7 = "PKCS7Padding";

    private Key key;
    private String mode;
    private String padding;

    private DESHelper(@NonNull Key key, @Nullable String mode, @Nullable String padding) {
        this.key = key;
        this.mode = mode;
        this.padding = padding;
    }

    /**
     * 获取密码 key
     *
     * @param password 密码
     * @return 用于解码的 key 通过密码生成
     * @throws InvalidKeyException     密码无效
     * @throws InvalidKeySpecException 密码无效
     */
    @NonNull
    public static Key key(@NonNull String password)
            throws InvalidKeyException, InvalidKeySpecException {
        DESKeySpec keySpec = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyFactory.generateSecret(keySpec);
    }


    /**
     * 创建 DESHelper.Builder
     *
     * @param key 秘钥
     */
    @NonNull
    public static DESHelper.Builder makeBuilder(@NonNull Key key) {
        return new Builder(key);
    }

    /**
     * 创建使用默认 mode 和 padding 的 DESHelper
     *
     * @param key 秘钥
     */
    @NonNull
    public static DESHelper defaultConfig(@NonNull Key key) {
        return new Builder(key).build();
    }


    /**
     * 加密
     *
     * @param textBytes 待加密的明文
     * @return 加密后的密文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public byte[] encrypt(@NonNull byte[] textBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return createCipher(Cipher.ENCRYPT_MODE).doFinal(textBytes);
    }

    /**
     * 加密
     *
     * @param text 待加密的明文
     * @return 加密后的密文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public byte[] encrypt(@NonNull String text)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encrypt(text.getBytes());
    }

    /**
     * 加密并返回 BASE64 字符串
     *
     * @param textBytes 待加密的明文
     * @return 加密后的密文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String encryptToBase64(@NonNull byte[] textBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeToString(encrypt(textBytes), Base64.NO_WRAP);
    }

    /**
     * 加密并返回 BASE64 字符串
     *
     * @param text 待加密的明文
     * @return 加密后的密文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String encryptToBase64(@NonNull String text)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeToString(encrypt(text.getBytes()), Base64.NO_WRAP);
    }

    /**
     * 解密
     *
     * @param cipherTextBytes 待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decrypt(@NonNull byte[] cipherTextBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = createCipher(Cipher.DECRYPT_MODE);
        byte[] data = cipher.doFinal(cipherTextBytes);
        return new String(data);
    }

    /**
     * 解密
     *
     * @param cipherText 待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decrypt(@NonNull String cipherText)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(cipherText.getBytes());
    }

    /**
     * 解密使用了 BASE64 转码的密文
     *
     * @param cipherTextBytes 待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decryptFromBase64(@NonNull byte[] cipherTextBytes)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(Base64.decode(cipherTextBytes, Base64.DEFAULT));
    }

    /**
     * 解密使用了 BASE64 转码的密文
     *
     * @param cipherText 待解密的密文
     * @return 解密后的明文
     * @throws InvalidKeyException       密码无效
     * @throws BadPaddingException       密码错误
     * @throws IllegalBlockSizeException 密码长度和密文长度不匹配，请检查密码长度个密文长度
     */
    @NonNull
    public String decryptFromBase64(@NonNull String cipherText)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(Base64.decode(cipherText.getBytes(), Base64.DEFAULT));
    }

    @NonNull
    private Cipher createCipher(int opMode) throws InvalidKeyException {
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

        AlgorithmParameterSpec spec = null;
        if (MODE_CBC.equals(mode)) {
            spec = new IvParameterSpec(new byte[cipher.getBlockSize()]);
        }

        try {
            cipher.init(opMode, key, spec);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }

        return cipher;
    }

    @SuppressWarnings("WeakerAccess")
    public static class Builder {
        private static final String PADDING_NO = "NoPadding";
//        private static final String PADDING_PKCS1 = "PKCS1Padding";  // Android 不支持
        private static final String PADDING_PKCS5 = "PKCS5Padding";
        private static final String PADDING_PKCS7 = "PKCS7Padding";
        private static final String PADDING_ISO10126 = "ISO10126Padding";
//        private static final String PADDING_OAEP = "OAEPPadding";  // Android 不支持
//        private static final String PADDING_SSL3 = "SSL3Padding";  // Android 不支持

        private Key key;
        private String mode;
        private String padding;

        private Builder(@NonNull Key key) {
            this.key = key;
        }

        @NonNull
        public Builder ecbMode() {
            mode = MODE_ECB;
            return this;
        }

        @NonNull
        public Builder cbcMode() {
            mode = MODE_CBC;
            return this;
        }

        @NonNull
        public Builder noPadding() {
            padding = PADDING_NO;
            return this;
        }

//        @NonNull
//        public Builder pkcs1Padding() {
//            padding = PADDING_PKCS1;
//            return this;
//        }

        @NonNull
        public Builder pkcs5Padding() {
            padding = PADDING_PKCS5;
            return this;
        }

        @NonNull
        public Builder pkcs7Padding() {
            padding = PADDING_PKCS7;
            return this;
        }

        @NonNull
        public Builder iso10126Padding() {
            padding = PADDING_ISO10126;
            return this;
        }

//        @NonNull
//        public Builder oaepPadding() {
//            padding = PADDING_OAEP;
//            return this;
//        }

//        @NonNull
//        public Builder ssl3Padding() {
//            padding = PADDING_SSL3;
//            return this;
//        }

        @NonNull
        public DESHelper build() {
            return new DESHelper(key, mode, padding);
        }
    }
}
