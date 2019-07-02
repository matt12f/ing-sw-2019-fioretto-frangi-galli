package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.NewCell;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSpawnCell {

    @Test
    public void testSpawncell(){
        NewCell testSpawnCell=new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.WALL,CellEdge.ROOM, CellType.SPAWN);
        for (int i=0;i<3;i++)
            assertTrue(testSpawnCell.getWeaponCards().isEmpty());
    }

    @Test
    public void testSetWeapon(){
        NewCell testSpawnCell=new NewCell(Color.BLUE,CellEdge.WALL,CellEdge.DOOR,CellEdge.WALL,CellEdge.ROOM,CellType.SPAWN);
        GunCard testGun1=new Heatseeker();
        GunCard testGun2=new Hellion();
        GunCard testGun3=new LockRifle();

        assertDoesNotThrow(()->testSpawnCell.setItem(testGun1));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun2));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun3));

        //here it fills up the cards slots
        assertEquals(testGun1,testSpawnCell.getWeaponCards().get(0));
        assertEquals(testGun2,testSpawnCell.getWeaponCards().get(1));
        assertEquals(testGun3,testSpawnCell.getWeaponCards().get(2));

        GunCard testGun4=new FlameThrower();
        assertThrows(FullException.class,()-> testSpawnCell.setItem(testGun4),"guns");
    }

    @Test
    public void testPickWeapon(){
        //I'll set up the cell to then test the method that picks the cards
        NewCell testSpawnCell=new NewCell(Color.BLUE,CellEdge.WALL,CellEdge.DOOR,CellEdge.WALL,CellEdge.ROOM,CellType.SPAWN);
        GunCard testGun1=new Heatseeker();
        GunCard testGun2=new Hellion();
        GunCard testGun3=new LockRifle();
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun1));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun2));
        assertDoesNotThrow(()->testSpawnCell.setItem(testGun3));

        GunCard firstPick=testSpawnCell.pickItem(testGun1);
        GunCard secondPick=testSpawnCell.pickItem(testGun2);
        assertEquals(firstPick,testGun1);
        assertEquals(secondPick,testGun2);

        assertNotEquals(firstPick,secondPick);

        assertEquals(1,testSpawnCell.getWeaponCards().size());
    }
}
