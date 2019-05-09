package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.SpawnCell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSpawnCell {

    @Test
    public void testSpawncell(){
        SpawnCell testSpawnCell=new SpawnCell('b','w','d','w','r');
        for (int i=0;i<3;i++)
            assertTrue(testSpawnCell.getItem().isEmpty());
    }

    @Test
    public void testSetWeapon(){
        SpawnCell testSpawnCell=new SpawnCell('b','w','d','w','r');
        GunCard testGun1=new Heatseeker();
        GunCard testGun2=new Hellion();
        GunCard testGun3=new LockRifle();

        assertDoesNotThrow(()->testSpawnCell.setItem(testGun1));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun2));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun3));

        //here it fills up the cards slots
        assertEquals(testGun1,testSpawnCell.getItem().get(0));
        assertEquals(testGun2,testSpawnCell.getItem().get(1));
        assertEquals(testGun3,testSpawnCell.getItem().get(2));

        GunCard testGun4=new FlameThrower();
        assertThrows(FullException.class,()-> testSpawnCell.setItem(testGun4),"guns");
    }

    @Test
    public void testPickWeapon(){
        //I'll set up the cell to then test the method that picks the cards
        SpawnCell testSpawnCell=new SpawnCell('b','w','d','w','r');
        GunCard testGun1=new Heatseeker();
        GunCard testGun2=new Hellion();
        GunCard testGun3=new LockRifle();
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun1));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun2));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun3));

        GunCard firstPick=testSpawnCell.pickItem(1);
        GunCard secondPick=testSpawnCell.pickItem(1);
        assertNotEquals(firstPick,secondPick);

        assertEquals(1,testSpawnCell.getItem().size());
    }
}
