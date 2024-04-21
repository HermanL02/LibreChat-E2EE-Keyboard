/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * SPDX-FileCopyrightText: Copyright 2024 Fcitx5 for Android Contributors
 */

package org.fcitx.fcitx5.android.utils;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class KeyConversionUtils {

    // 将公钥转换为字符串
    public static String publicKeyToString(PublicKey publicKey) {
        // 获取编码后的公钥字节数据
        byte[] publicKeyBytes = publicKey.getEncoded();
        // 使用Base64进行编码
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    // 将私钥转换为字符串
    public static String privateKeyToString(PrivateKey privateKey) {
        // 获取编码后的私钥字节数据
        byte[] privateKeyBytes = privateKey.getEncoded();
        // 使用Base64进行编码
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    // 将字符串转换为公钥
    public static PublicKey stringToPublicKey(String publicKeyString) throws Exception {
        // Base64解码字符串
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        // 使用X509编码的KeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        // 获取RSA公钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 生成公钥
        return keyFactory.generatePublic(keySpec);
    }

    // 将字符串转换为私钥
    public static PrivateKey stringToPrivateKey(String privateKeyString) throws Exception {
        // Base64解码字符串
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        // 使用PKCS8编码的KeySpec对象
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        // 获取RSA私钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 生成私钥
        return keyFactory.generatePrivate(keySpec);
    }

    public static String encryptWithPublicKey(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    // 使用RSA私钥解密
    public static String decryptWithPrivateKey(String cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

}

