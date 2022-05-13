package com.platzi.javatests.util;

class StringUtilTest {

    public static void main(String[] args) {
        String result = StringUtil.repeat("hola",3);
        assertEqual(result, "holaholahola");

        String result1 = StringUtil.repeat("hola",2);
        assertEqual(result1, "holahola");


    }

    //Funcion creada para evaluar
    private static void assertEqual(String actual, String expected) {
        if(!actual.equals(expected)){
            throw new RuntimeException(actual + " is not equal to expeted: " + expected);
        }
    }
}