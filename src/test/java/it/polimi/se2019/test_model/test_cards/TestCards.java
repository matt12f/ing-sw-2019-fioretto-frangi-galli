package it.polimi.se2019.test_model.test_cards;

import it.polimi.se2019.model.cards.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCards {

    @Test
    public void testCardNames(){
        GunCard test = new VortexCannon();

        assertEquals(test.getClass().getSimpleName(),"VortexCannon");

        final GunCard test2=null;
        assertThrows(NullPointerException.class,()->test2.getClass().getSimpleName().toLowerCase());

    }


    /**
     * these methods below test the deep cloning of cards (it's tested only on two because it's the same for
     * the others)
     */

    //3 effects cards
    @Test
    public void testCloneCyberblade(){
        GunCard gunCard =new CyberBlade();
        gunCard.setLoaded(false);

        GunCard deepClone = gunCard.clone();

        //checks the equals method
        assertEquals(gunCard,deepClone);
        assertNotEquals(gunCard,new Zx2());

        assertEquals(gunCard.isLoaded(),deepClone.isLoaded());

        deepClone.setLoaded(true);
        assertNotEquals(gunCard.isLoaded(),deepClone.isLoaded());

        assertNotEquals(gunCard.getAmmoCost(),deepClone.getAmmoCost());

        assertEquals(gunCard.getAmmoCost().length,deepClone.getAmmoCost().length);
        for (int i = 0; i < gunCard.getAmmoCost().length; i++)
            assertEquals(gunCard.getAmmoCost()[i],deepClone.getAmmoCost()[i]);

        assertEquals(gunCard.getDescription(),deepClone.getDescription());

        assertNotEquals(gunCard.getSecondaryEffectCost(),deepClone.getSecondaryEffectCost());

        assertEquals(gunCard.getSecondaryEffectCost().length,deepClone.getSecondaryEffectCost().length);
        for (int i = 0; i < gunCard.getSecondaryEffectCost().length; i++)
            assertEquals(gunCard.getSecondaryEffectCost()[i],deepClone.getSecondaryEffectCost()[i]);

        assertNotEquals(gunCard.getTertiaryEffectCost(),deepClone.getTertiaryEffectCost());

        assertEquals(gunCard.getTertiaryEffectCost().length,deepClone.getTertiaryEffectCost().length);
        for (int i = 0; i < gunCard.getTertiaryEffectCost().length; i++)
            assertEquals(gunCard.getTertiaryEffectCost()[i],deepClone.getTertiaryEffectCost()[i]);

        assertEquals(gunCard.getEffectsOrder(),deepClone.getEffectsOrder());

    }

    //2 effects cards
    @Test
    public void testCloneElectroscythe(){
        GunCard gunCard =new Electroscythe();
        gunCard.setLoaded(false);

        GunCard deepClone = gunCard.clone();

        //checks the equals method
        assertEquals(gunCard,deepClone);
        assertNotEquals(gunCard,new Zx2());

        assertEquals(gunCard.isLoaded(),deepClone.isLoaded());

        deepClone.setLoaded(true);
        assertNotEquals(gunCard.isLoaded(),deepClone.isLoaded());

        assertNotEquals(gunCard.getAmmoCost(),deepClone.getAmmoCost());

        assertEquals(gunCard.getAmmoCost().length,deepClone.getAmmoCost().length);
        for (int i = 0; i < gunCard.getAmmoCost().length; i++)
            assertEquals(gunCard.getAmmoCost()[i],deepClone.getAmmoCost()[i]);

        assertEquals(gunCard.getDescription(),deepClone.getDescription());

        assertNotEquals(gunCard.getSecondaryEffectCost(),deepClone.getSecondaryEffectCost());

        assertEquals(gunCard.getSecondaryEffectCost().length,deepClone.getSecondaryEffectCost().length);
        for (int i = 0; i < gunCard.getSecondaryEffectCost().length; i++)
            assertEquals(gunCard.getSecondaryEffectCost()[i],deepClone.getSecondaryEffectCost()[i]);

        assertEquals(gunCard.getEffectsOrder(),deepClone.getEffectsOrder());

    }
}
