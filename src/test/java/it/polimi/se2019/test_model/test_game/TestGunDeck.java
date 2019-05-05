package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.GunDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestGunDeck {

    @Test
    public void testGunDeck(){
        GunDeck testDeck=new GunDeck();
        assertEquals(21,testDeck.getActiveDeck().size());
        GunDeck testDeck2=new GunDeck();
        assertNotEquals(testDeck,testDeck2);
    }
    @Test
    public void testDraw(){
        GunDeck testDeck=new GunDeck();
        GunCard pickedCard=testDeck.draw();
        GunCard pickedCard2=testDeck.draw();
        assertNotEquals(pickedCard,pickedCard2);
    }
}
