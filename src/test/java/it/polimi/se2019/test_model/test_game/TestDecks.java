package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.AmmoTilesDeck;
import it.polimi.se2019.model.game.Decks;
import it.polimi.se2019.model.game.GunDeck;
import it.polimi.se2019.model.game.PowerupsDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDecks {
    @Test
    public void testDecks(){
        Decks testDecks =new Decks();
        assertEquals(GunDeck.class,testDecks.getGunDeck().getClass());
        assertEquals(AmmoTilesDeck.class,testDecks.getAmmotilesDeck().getClass());
        assertEquals(PowerupsDeck.class,testDecks.getPowerupsDeck().getClass());
    }
}
