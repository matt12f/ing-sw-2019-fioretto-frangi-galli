package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.SpawnCell;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestSpawnCell {

    @Test
    public void testSpawncell(){
        SpawnCell testSpawnCell=new SpawnCell('b','w','d','w','r');
        for (int i=0;i<3;i++)
            assertTrue(testSpawnCell.getWeaponCards().isEmpty());
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSetWeapon(){
        SpawnCell testSpawnCell=new SpawnCell('b','w','d','w','r');
        GunCard testGun1=new Heatseeker();
        GunCard testGun2=new Hellion();
        GunCard testGun3=new LockRifle();
        testSpawnCell.setWeaponCard(testGun1);
        testSpawnCell.setWeaponCard(testGun2);
        testSpawnCell.setWeaponCard(testGun3);

        //here it fills up the cards slots
        assertEquals(testGun1,testSpawnCell.getWeaponCards().get(0));
        assertEquals(testGun2,testSpawnCell.getWeaponCards().get(1));
        assertEquals(testGun3,testSpawnCell.getWeaponCards().get(2));

        GunCard testGun4=new FlameThrower();
        exception.expect(IndexOutOfBoundsException.class);
        testSpawnCell.setWeaponCard(testGun4);
    }

    @Test
    public void testPickWeapon(){
        //I'll set up the cell to then test the method that picks the cards
        SpawnCell testSpawnCell=new SpawnCell('b','w','d','w','r');
        GunCard testGun1=new Heatseeker();
        GunCard testGun2=new Hellion();
        GunCard testGun3=new LockRifle();
        testSpawnCell.setWeaponCard(testGun1);
        testSpawnCell.setWeaponCard(testGun2);
        testSpawnCell.setWeaponCard(testGun3);

        GunCard firstPick=testSpawnCell.pickWeapon(1);
        GunCard secondPick=testSpawnCell.pickWeapon(1);
        assertNotEquals(firstPick,secondPick);

        assertEquals(1,testSpawnCell.getWeaponCards().size());
    }
}
