package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.Hand;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.omg.CosNaming.NamingContextPackage.NotFound;

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


    /*LO STO RIVEDENDO. L'HO OSCURATO PER LA PUSH

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
        notFoundException.expect(NotFound.class);
        testHand.substitutionGunCard(testGun2,testGun4); //case in which the card to substitute is present
    }*/
    //TODO finire
}
