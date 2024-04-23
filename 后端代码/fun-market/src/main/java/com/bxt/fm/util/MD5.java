package com.bxt.fm.util;

//MD5加密工具类

//引入MD5库
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String encrypt(String strSrc){
        try {
            //定义一个用于存储十六进制字符的字符数组
            char[] hexChars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            //将要加密的数据转换为字节数组
            byte[] bytes = strSrc.getBytes();
            //创建MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            //将字节数组提供给MD5算法进行加密
            md.update(bytes);
            //完成加密操作，并获取加密结果
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            //通过位运算进行加密
            for (byte b : bytes) {
                //将高四位转换为对应的十六进制字符
                chars[k++] = hexChars[b >>> 4 & 0xf];
                //将低四位转换为对应的十六进制字符
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }
}
