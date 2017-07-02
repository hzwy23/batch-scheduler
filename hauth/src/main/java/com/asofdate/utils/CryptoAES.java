package com.asofdate.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hzwy23 on 2017/5/16.
 */
public class CryptoAES {
    private static final String PASSWORD_KEY = "hzwy23@hustwb09y";

    /**
     * 解密AES加密过的字符串
     *
     * @param content AES加密过过的内容
     * @return 明文
     */
    public static String aesEncrypt(String content) {

        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = content.getBytes();

            int plaintextLength = dataBytes.length;

            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            Byte padding = (byte) Integer.parseInt(Integer.toHexString(blockSize - (dataBytes.length) % blockSize), 16);

            byte[] plaintext = new byte[plaintextLength];

            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            for (int i = dataBytes.length; i < plaintextLength; i++) {
                plaintext[i] = padding;
            }

            SecretKeySpec keyspec = new SecretKeySpec(PASSWORD_KEY.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(PASSWORD_KEY.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new BASE64Encoder().encode(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * AES加密字符串
     *
     * @param content 需要被加密的字符串
     * @return 密文
     */
    public static String aesDecrypt(String content) {
        try {

            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(PASSWORD_KEY.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(PASSWORD_KEY.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            String originalString = new String(cipher.doFinal(encrypted1));
            int length = originalString.length();
            int ch = originalString.charAt(length - 1);
            return originalString.substring(0, length - ch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.valueOf(System.currentTimeMillis());
    }

//    public static void main(String []args){
//        String content = "123456";
//        System.out.println("加密之前：" + content);
//
//        // 加密
//        String encrypt = aesEncrypt(content);
//        System.out.println("加密后的内容：" + encrypt);
//        // u9KHSP2NtcTkCklrKJctlg==
//        // MOmj8rUH7WtHjJiOuJI0wQ==
//        // 解密
//        String decrypt = aesDecrypt(encrypt);
//        System.out.println("解密后的内容：" + decrypt);
//        System.out.println("解密后的内容长度是：" + decrypt.length());
//    }
}