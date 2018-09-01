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

package me.panpf.androidxkt.crypto

import me.panpf.javaxkt.crypto.*

import org.junit.Assert
import org.junit.Test

import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import java.security.InvalidKeyException
import java.security.SignatureException

class RsaTest {

    companion object {

        private const val SOURCE = "{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}{\"key1\":\"value1\",\"key2\":\"value2\"}"

        private const val SOURCE_OAEP = "小红那年七岁，她跟着爸妈去赶集，站在一个卖童装的摊位旁边"
    }

    /**
     * 测试公钥加密私钥解密
     */
    @Test
    @Throws(BadPaddingException::class, InvalidKeyException::class, IllegalBlockSizeException::class)
    fun testPubPriBytes() {
        val keyPair = createRsaKey(1024)
        val decryptResult = SOURCE.toByteArray().encrypt(RSA, keyPair.public).decryptToString(RSA, keyPair.private)
        Assert.assertEquals("testPubPriBytes", SOURCE, decryptResult)
    }

    /**
     * 测试私钥加密公钥解密
     */
    @Test
    @Throws(BadPaddingException::class, InvalidKeyException::class, IllegalBlockSizeException::class)
    fun testPriPubBytes() {
        val keyPair = createRsaKey(1024)
        val decryptResult = SOURCE.toByteArray().encrypt(RSA, keyPair.private).decryptToString(RSA, keyPair.public)
        Assert.assertEquals("testPriPubBytes", SOURCE, decryptResult)
    }

    /**
     * 测试公钥加密私钥解密转 Base64
     */
    @Test
    @Throws(BadPaddingException::class, InvalidKeyException::class, IllegalBlockSizeException::class)
    fun testPubPriWithBase64() {
        val keyPair = createRsaKey(1024)
        val decryptResult = SOURCE.encryptToBase64(RSA, keyPair.public).decryptToStringFromBase64(RSA, keyPair.private)
        Assert.assertEquals("testPubPriWithBase64", SOURCE, decryptResult)
    }

    /**
     * 测试私钥加密公钥解密转 Base64
     */
    @Test
    @Throws(BadPaddingException::class, InvalidKeyException::class, IllegalBlockSizeException::class)
    fun testPriPubWithBase64() {
        val keyPair = createRsaKey(1024)
        val decryptResult = SOURCE.encryptToBase64(RSA, keyPair.private).decryptToStringFromBase64(RSA, keyPair.public)
        Assert.assertEquals("testPriPubWithBase64", SOURCE, decryptResult)
    }


    /**
     * 测试签名、验证
     */
    @Test
    @Throws(InvalidKeyException::class, SignatureException::class)
    fun testSignBytes() {
        val keyPair = createRsaKey(1024)
        val bytesSign = SOURCE.rsaSign(keyPair.private)
        Assert.assertTrue("testSignBytes", bytesSign.rsaVerify(SOURCE, keyPair.public))
    }

    /**
     * 测试签名、验证 Base64
     */
    @Test
    @Throws(InvalidKeyException::class, SignatureException::class)
    fun testSignWithBase64() {
        val keyPair = createRsaKey(1024)
        val base64Sign = SOURCE.rsaSignToBase64(keyPair.private)
        Assert.assertTrue("testSignWithBase64", base64Sign.rsaVerifyFromBase64(SOURCE, keyPair.public))
    }

    /**
     * 使用错误的 KEY 解密
     */
    @Test
    @Throws(InvalidKeyException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun testErrorKey() {
        val encryptBytes = SOURCE.toByteArray().encrypt(RSA, createRsaKey(1024).public)
        var bytesPriKeyDecryptResult: String? = null
        try {
            bytesPriKeyDecryptResult = encryptBytes.decryptToString(RSA, createRsaKey(1024).private)
        } catch (e: Exception) {
            //            e.printStackTrace();
        }

        Assert.assertNotEquals("testErrorKey", SOURCE, bytesPriKeyDecryptResult)
    }

    @Test
    @Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun testEcbPKCS1Padding() {
        val keyPair = createRsaKey(1024)
        val decryptResult = SOURCE.toByteArray().encrypt(RSA_ECB_PKCS1, keyPair.public).decryptToString(RSA_ECB_PKCS1, keyPair.private)
        Assert.assertEquals("testEcbPKCS1Padding", SOURCE, decryptResult)
    }

    @Test
    @Throws(InvalidKeyException::class, BadPaddingException::class, IllegalBlockSizeException::class)
    fun testEcbOAEPPadding() {
        val keyPair = createRsaKey(1024)
        val decryptResult = SOURCE_OAEP.toByteArray().encrypt(RSA_ECB_OAEP, keyPair.public).decryptToString(RSA_ECB_OAEP, keyPair.private)
        Assert.assertEquals("testEcbOAEPPadding", SOURCE_OAEP, decryptResult)
    }
}
