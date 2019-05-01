package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.Hand;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TestHand {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testHand(){
        Hand testHand =new Hand();
        assertTrue(testHand.getGuns().length==3);
        assertTrue(testHand.getPowerups().length==3);
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
        testHand.setGun(testGun1);
        testHand.setGun(testGun2);
        testHand.setGun(testGun3);

        //TODO Invertire ordine carte
        assertEquals(testHand.getGuns()[2],testGun1);
        assertEquals(testHand.getGuns()[1],testGun2);
        assertEquals(testHand.getGuns()[0],testGun3);

        GunCard testGun4;
        testGun4= new Shockwave();
        exception.expect(ArrayIndexOutOfBoundsException.class);
        testHand.setGun(testGun4);
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
        testHand.setPowerup(testPwup1);
        testHand.setPowerup(testPwup2);
        testHand.setPowerup(testPwup3);

        //TODO Invertire ordine carte
        assertEquals(testHand.getPowerups()[2],testPwup1);
        assertEquals(testHand.getPowerups()[1],testPwup2);
        assertEquals(testHand.getPowerups()[0],testPwup3);

        PowerupCard testPwup4;
        testPwup4=new PowerupCard("TargettingScope",'r');
        exception.expect(ArrayIndexOutOfBoundsException.class);
        testHand.setPowerup(testPwup4);
    }

    @Rule
    public ExpectedException notFoundException = ExpectedException.none();

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
        testHand.setGun(testGun1);
        testHand.setGun(testGun2);
        testHand.setGun(testGun3);

        GunCard testGun4;
        testGun4= new Shockwave();
        testHand.substitutionGunCard(testGun2,testGun4); //case in which the card to substitute is present
        boolean present=false;
        for(GunCard gunCard:testHand.getGuns()){ //It verifies that testGun2 has been removed and testGun4 has been added
            assertNotEquals(gunCard,testGun2);
            if (gunCard.equals(testGun4))
                present=true;
        }
        assertTrue(present);

        notFoundException.expect(NoSuchElementException.class);
        GunCard previousGuns[]=testHand.getGuns();
        testHand.substitutionGunCard(testGun2,testGun4); //case in which the card to substitute is not present
        assertTrue(previousGuns.equals(testHand.getGuns())); //it verifies that no changes occured
    }

    @Rule
    public ExpectedException notFoundException2 = ExpectedException.none();

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
        testHand.setPowerup(testPwup1);
        testHand.setPowerup(testPwup2);
        testHand.setPowerup(testPwup3);

        PowerupCard testPwup4;
        testPwup4=new PowerupCard("TargettingScope",'r');

        testHand.substitutionPowerup(testPwup3,testPwup4); //case in which the card to substitute is present
        boolean present=false;
        for(PowerupCard powerupCard:testHand.getPowerups()){ //It verifies that testGun2 has been removed and testGun4 has been added
            assertNotEquals(powerupCard,testPwup3);
            if (powerupCard.equals(testPwup4))
                present=true;
        }
        assertTrue(present);

        notFoundException2.expect(NoSuchElementException.class);
        PowerupCard previousPwUps[]=testHand.getPowerups();
        testHand.substitutionPowerup(testPwup3,testPwup4); //case in which the card to substitute is not present
        assertTrue(previousPwUps.equals(testHand.getGuns())); //it verifies that no changes occured
    }

}
