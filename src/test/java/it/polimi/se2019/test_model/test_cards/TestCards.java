package it.polimi.se2019.test_model.test_cards;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.cards.VortexCannon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCards {

    @Test
    public void testCardNames(){
        GunCard test = new VortexCannon();

        assertEquals(test.getClass().getSimpleName(),"VortexCannon");
    }
}
