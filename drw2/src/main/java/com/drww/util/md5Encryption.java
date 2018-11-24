package com.drww.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * className:md5Encryption
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 17:37
 */
public class md5Encryption {


    public static  String getMd5Encryption(String passWord){
        String hashAlgorithmName = "MD5";
        Object salt = null;
        int hashIterations= 2;
        Object simpleHash = new SimpleHash(hashAlgorithmName, passWord, salt, hashIterations);
        return simpleHash.toString();
    }
}
