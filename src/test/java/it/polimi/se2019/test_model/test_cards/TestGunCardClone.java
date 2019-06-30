package it.polimi.se2019.test_model.test_cards;

import it.polimi.se2019.model.cards.CyberBlade;
import it.polimi.se2019.model.cards.GunCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestGunCardClone {
    GunCard gunCard =new CyberBlade();

    //TODO ripertelo per ogni carta
    @Test
    public void testCloneCyberblade(){
        GunCard gunCard =new CyberBlade();
        gunCard.setLoaded(false);

        GunCard deepClone = gunCard.clone();

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
}
