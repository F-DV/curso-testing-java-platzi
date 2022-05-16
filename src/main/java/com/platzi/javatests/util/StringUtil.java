package com.platzi.javatests.util;

public class StringUtil {

    public static String repeat(String str, int times){
        if(times <0){
            throw new IllegalArgumentException("Negative times no allowed");
        }
        String result = "";
        for (int i = 0; i < times; i++) {
            result += str;
        }
        return result;
    }

    public static boolean isEmpty(String str){
        System.out.println(str.trim());
        return str == null || str.trim().length() <=0;
    }
}
