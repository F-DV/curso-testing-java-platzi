package com.platzi.javatests.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {
    /*
        - Normalmente realizamos una funcion para cada prueba,
        - Especificamos que test estamos realizando en el nombre de la funcion
        - Para un caso de error como por ejemplo negativo en este caso, debemos lanzar la exception en el caso de uso
            y capturarla aqui en los tests
     */
    @Test
    public void repeat_string_once(){
        Assert.assertEquals("hola",StringUtil.repeat("hola",1));
    }
    @Test
    public void repeat_string_multiple_times(){
        Assert.assertEquals("holaholahola",StringUtil.repeat("hola",3));
    }
    @Test
    public void repeat_string_cero_times(){
        Assert.assertEquals("",StringUtil.repeat("hola",0));
    }
    @Test(expected = IllegalArgumentException.class)
    public void repeat_string_negative_times(){
        Assert.assertEquals("",StringUtil.repeat("hola",-1));
    }

    @Test
    public void if_String_isEmpty(){
        Assert.assertEquals(true,StringUtil.isEmpty(""));
    }
    @Test
    public void if_String_has_tab(){
        Assert.assertEquals(true,StringUtil.isEmpty(" "));
    }
    @Test
    public void if_String_not_isEmpty(){
        Assert.assertEquals(false,StringUtil.isEmpty("Hello"));
    }
}
