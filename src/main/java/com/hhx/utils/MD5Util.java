package com.hhx.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: hhx
 * @Date: 2024/5/15 10:20
 * @Description: TODO 加密工具类
 * @Version: 1.0
 */
@Component
public final class MD5Util {
    public static String encrypt(String strStc){
        try {
            char hexChars[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            byte[] bytes = strStc.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            bytes= md5.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k=0;
            for(int i=0;i<bytes.length;i++){
                byte b=bytes[i];
                chars[k++]=hexChars[b>>>4&0xf];
                chars[k++]=hexChars[b&0xf];
            }
            return new String(chars);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！+"+e);
        }

    }
}
