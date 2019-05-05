package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.Ammo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAmmo {
    @Test
    public void testAmmo(){
        Ammo test=new Ammo();
        assertEquals(1,test.getBlue());
        assertEquals(1,test.getRed());
        assertEquals(1,test.getYellow());
    }
    @Test
    public void testGetSetBlue(){
        Ammo test=new Ammo();
        int nTest=2;
        int previousValue=test.getBlue();
        test.setBlue(nTest);
        assertEquals(nTest+previousValue,test.getBlue());
        nTest=5;
        previousValue=test.getBlue();
        test.setBlue(nTest);
        assertEquals(previousValue,test.getBlue());
    }
    @Test
    public void testGetSetRed(){
        Ammo test=new Ammo();
        int nTest=2;
        int previousValue=test.getRed();
        test.setRed(nTest);
        assertEquals(nTest+previousValue,test.getRed());
        nTest=5;
        previousValue=test.getRed();
        test.setRed(nTest);
        assertEquals(previousValue,test.getRed());
    }
    @Test
    public void testGetSetYellow(){
        Ammo test=new Ammo();
        int nTest=2;
        int previousValue=test.getYellow();
        test.setYellow(nTest);
        assertEquals(nTest+previousValue,test.getYellow());
        nTest=5;
        previousValue=test.getYellow();
        test.setYellow(nTest);
        assertEquals(previousValue,test.getYellow());
    }
}
