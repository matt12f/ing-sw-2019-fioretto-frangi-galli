package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.Hand;

import it.polimi.se2019.model.game.NewCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHand {
    @Test
    public void testHand(){
        Hand testHand =new Hand();
        assertEquals(3,testHand.getGuns().length);
        assertEquals(3,testHand.getPowerups().length);
        for(int i=0;i<3;i++){
            assertNull(testHand.getGuns()[i]);
            assertNull(testHand.getPowerups()[i]);
        }
    }

    @Test
    public void testSetGun(){
        Hand testHand=new Hand();
        GunCard testGun1, testGun2,testGun3;
        testGun1=new CyberBlade();
        testGun2=new MachineGun();
        testGun3=new LockRifle();

        for(int i=0;i<3;i++){
            assertNull(testHand.getGuns()[i]);
        }
        assertDoesNotThrow(()->testHand.setGun(testGun1));
        assertDoesNotThrow(()->testHand.setGun(testGun2));
        assertDoesNotThrow(()->testHand.setGun(testGun3));

        assertEquals(testHand.getGuns()[0],testGun1);
        assertEquals(testHand.getGuns()[1],testGun2);
        assertEquals(testHand.getGuns()[2],testGun3);

        GunCard testGun4;
        testGun4= new Shockwave();
        assertThrows(FullException.class, () -> testHand.setGun(testGun4),"guns");
    }
    @Test
    public void testSetPowerUp(){
        Hand testHand=new Hand();
        PowerupCard testPwup1, testPwup2,testPwup3;
        testPwup1=new PowerupCard("Newton",'r');
        testPwup2=new PowerupCard("TagbackGrenade",'y');
        testPwup3=new PowerupCard("Teleporter",'b');

        for(int i=0;i<3;i++){
            assertNull(testHand.getPowerups()[i]);
        }
        assertDoesNotThrow(()->testHand.setPowerup(testPwup1));
        assertDoesNotThrow(()->testHand.setPowerup(testPwup2));
        assertDoesNotThrow(()->testHand.setPowerup(testPwup3));

        assertEquals(testHand.getPowerups()[0],testPwup1);
        assertEquals(testHand.getPowerups()[1],testPwup2);
        assertEquals(testHand.getPowerups()[2],testPwup3);

        PowerupCard testPwup4;
        testPwup4=new PowerupCard("TargettingScope",'r');
        assertThrows(FullException.class, () -> testHand.setPowerup(testPwup4),"powerups");

    }

    @Test
    public void testSubstitutionGunCard(){
        Hand testHand=new Hand();
        GunCard testGun1, testGun2,testGun3;
        testGun1=new CyberBlade();
        testGun2=new MachineGun();
        testGun3=new LockRifle();

        for(int i=0;i<3;i++){
            assertNull(testHand.getGuns()[i]);
        }
        assertDoesNotThrow(()->testHand.setGun(testGun1));
        assertDoesNotThrow(()->testHand.setGun(testGun2));
        assertDoesNotThrow(()->testHand.setGun(testGun3));

        GunCard testGun4 = new Shockwave();
        NewCell spawnPoint =new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.WALL,CellEdge.WALL,CellEdge.WALL,CellType.SPAWN);
        assertDoesNotThrow(()->spawnPoint.setItem(testGun4));
        assertDoesNotThrow(()->spawnPoint.setItem(testGun1));
        assertDoesNotThrow(()->spawnPoint.setItem(testGun3));


        assertDoesNotThrow(()->testHand.substitutionGunCard(spawnPoint,testGun2,testGun4)); //case in which the card to substitute is present
        boolean present=false;
        for(GunCard gunCard:testHand.getGuns()){ //It verifies that testGun2 has been removed and testGun4 has been added
            assertNotEquals(gunCard,testGun2);
            if (gunCard.equals(testGun4))
                present=true;
        }
        assertTrue(present);

        GunCard [] previousGuns=new GunCard[3];
        for(int i=0;i<3;i++)
            previousGuns[i]=testHand.getGuns()[i];
        assertThrows(CardNotFoundException.class,()->testHand.substitutionGunCard(spawnPoint,testGun2,testGun4),"guns"); //case in which the card to substitute is not present

        for(int i=0;i<3;i++) //it verifies that no changes occurred
            assertEquals(previousGuns[i],testHand.getGuns()[i]);
    }

    @Test
    public void testSubstitutionPowerupCard(){
        Hand testHand=new Hand();
        PowerupCard testPwup1, testPwup2,testPwup3;
        testPwup1=new PowerupCard("Newton",'r');
        testPwup2=new PowerupCard("TagbackGrenade",'y');
        testPwup3=new PowerupCard("Teleporter",'b');

        for(int i=0;i<3;i++){
            assertNull(testHand.getPowerups()[i]);
        }
        assertDoesNotThrow(()->testHand.setPowerup(testPwup1));
        assertDoesNotThrow(()->testHand.setPowerup(testPwup2));
        assertDoesNotThrow(()->testHand.setPowerup(testPwup3));

        PowerupCard testPwup4;
        testPwup4=new PowerupCard("TargettingScope",'r');

        assertDoesNotThrow(()->testHand.substitutionPowerup(testPwup3,testPwup4)); //case in which the card to substitute is present
        boolean present=false;
        for(PowerupCard powerupCard:testHand.getPowerups()){ //It verifies that testGun2 has been removed and testGun4 has been added
            assertNotEquals(powerupCard,testPwup3);
            if (powerupCard.equals(testPwup4))
                present=true;
        }
        assertTrue(present);

        PowerupCard [] previousPwUps=new PowerupCard[3];
        for(int i=0;i<3;i++)
            previousPwUps[i]=testHand.getPowerups()[i];
        assertThrows(CardNotFoundException.class,()-> testHand.substitutionPowerup(testPwup3,testPwup4),"powerups"); //case in which the card to substitute is not present

        for(int i=0;i<3;i++)  //it verifies that no changes occured
            assertEquals(previousPwUps[i],testHand.getPowerups()[i]);
    }

}
