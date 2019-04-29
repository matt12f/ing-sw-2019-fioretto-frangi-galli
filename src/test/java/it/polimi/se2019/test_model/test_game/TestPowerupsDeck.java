package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.PowerupsDeck;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPowerupsDeck {

    @Test
    public void testPowerupsDeck(){
        PowerupsDeck testDeck=new PowerupsDeck();
        assertTrue(testDeck.getDiscardedDeck().isEmpty());
        assertTrue(testDeck.getActiveDeck().size()==24);

        PowerupsDeck testDeck2=new PowerupsDeck();
        assertTrue(!testDeck2.getActiveDeck().equals(testDeck.getActiveDeck()));
    }

    @Test
    public void testSetActiveDeck(){
        PowerupsDeck testDeck=new PowerupsDeck();
        int size=emptyActiveDeck(testDeck);
        testDeck.setActiveDeck();
        assertTrue(testDeck.getActiveDeck().size()==size);
        assertTrue(testDeck.getDiscardedDeck().isEmpty());
    }

    private int emptyActiveDeck(PowerupsDeck testDeck){
        int size=testDeck.getActiveDeck().size();
        for(int i=1;i<=size;i++){
            testDeck.setDiscardedDeck(testDeck.getActiveDeck().get(size-i));
            testDeck.getActiveDeck().remove(testDeck.getActiveDeck().get(size-i));
        }
        assertTrue(testDeck.getActiveDeck().isEmpty());
        assertTrue(testDeck.getDiscardedDeck().size()==size);
        return size;
    }

    @Test
    public void testDraw(){
        PowerupsDeck testDeck=new PowerupsDeck();
        PowerupCard testCard=testDeck.draw();
        assertTrue(!testDeck.draw().equals(testCard));
        int size=emptyActiveDeck(testDeck);
        assertTrue(size==testDeck.getDiscardedDeck().size()); //not useful but since the method returns an int... (that here doesn't serve a purpose)
        testCard=testDeck.draw();
        assertTrue(!testDeck.draw().equals(testCard));
    }
}
