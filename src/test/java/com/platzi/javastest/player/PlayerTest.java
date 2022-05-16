package com.platzi.javastest.player;

import com.platzi.javatests.player.Dice;
import com.platzi.javatests.player.Player;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {
/*
    @Test
    public void loose_when_dice_number_isToo_low(){  //Test sin Mockito no es muy efectiva porque el test funciona cuando el dado lanzado aleatoriamente es verdadero
        Dice dice = new Dice(6);
        Player player = new Player(dice,3);
        assertEquals(false,player.play());
    }
*/

    /*
    * - Agregamos la dependencia Mockito
    * - Instanciamos la clase que deseamos simular con Mockito
    * - realizamos validacion esperada.
    * */
    @Test
    public void lose_when_dice_number_is_too_low(){
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(2);

        Player player = new Player(dice,3);
        assertFalse(player.play());
    }

    @Test
    public void wins_when_dice_number_is_too_big(){
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(4);

        Player player = new Player(dice,3);
        assertTrue(player.play());
    }
}
