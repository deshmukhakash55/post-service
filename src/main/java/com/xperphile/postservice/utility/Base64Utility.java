package com.xperphile.postservice.utility;

import java.util.Base64;

public class Base64Utility {

    public static String encode(byte[] content){
        return Base64.getEncoder().encode(content).toString();
    }

    public static byte[] decode(String content){
        return Base64.getDecoder().decode(content.getBytes());
    }

}
