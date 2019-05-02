package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.DamageTracker;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDamageTracker {

    @Test
    public void testDamageTracker(){
        DamageTracker testDmgTracker=new DamageTracker();
        assertTrue(testDmgTracker.getMarks().isEmpty());
        assertEquals(12,testDmgTracker.getDamage().length);
        for(int i=0;i<12;i++)
            assertEquals(' ',testDmgTracker.getDamage()[i]);
    }

    @Test
    public void testAddDamage(){
        DamageTracker testDmgTracker=new DamageTracker();
        testDmgTracker.addDamage('b'); //this adds one blue damage droplet
        assertEquals('b',testDmgTracker.getDamage()[0]);

        //here I'll fill up the damage track
        testDmgTracker.addDamage('y');
        testDmgTracker.addDamage('b');
        testDmgTracker.addDamage('y');
        for(int i=0;i<3;i++)
            testDmgTracker.addDamage('b');
        for(int i=0;i<3;i++)
            testDmgTracker.addDamage('g');
        for(int i=0;i<2;i++)
            testDmgTracker.addDamage('r');

        //here I'll verify that it's added the right amount of damage droplets
        int contB=0,contY=0,contR=0,contG=0;
        for(int i=0;i<12;i++){
            if(testDmgTracker.getDamage()[i]=='b')
                contB++;
            else if(testDmgTracker.getDamage()[i]=='y')
                contY++;
            else if(testDmgTracker.getDamage()[i]=='r')
                contR++;
            else if(testDmgTracker.getDamage()[i]=='g')
                contG++;
            else fail();
        }
        assertEquals(5,contB);
        assertEquals(2,contY);
        assertEquals(3,contG);
        assertEquals(2,contR);

        //then I'll try to add a damage that I expect not to fit
        char previousLastDamage=testDmgTracker.getDamage()[11];
        testDmgTracker.addDamage('y');
        assertEquals(previousLastDamage,testDmgTracker.getDamage()[11]);
    }
    @Test
    public void testAddMarks(){
        DamageTracker testDmgTracker=new DamageTracker();
        testDmgTracker.addMarks("bby");
        assertTrue(testDmgTracker.getMarks().contains("bby"));
    }

    @Test
    public void testSetKill(){
        DamageTracker testDmgTracker=new DamageTracker();
        //here I'll fill up the damage track
        testDmgTracker.addDamage('y');
        testDmgTracker.addDamage('b');
        testDmgTracker.addDamage('y');
        for(int i=0;i<3;i++)
            testDmgTracker.addDamage('b');
        for(int i=0;i<3;i++)
            testDmgTracker.addDamage('g');
        for(int i=0;i<3;i++)
            testDmgTracker.addDamage('r');

        testDmgTracker.setKill();
        for(int i=0;i<12;i++)
            assertEquals(' ',testDmgTracker.getDamage()[i]);
    }
}
