package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.cards.AmmoTileCard;
import it.polimi.se2019.model.game.AmmoTilesDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestAmmoTilesDeck {

    @Test
    public void testAmmoTilesDeck(){
        AmmoTilesDeck testDeck=new AmmoTilesDeck();
        assertTrue(testDeck.getDiscardedDeck().isEmpty());
        assertTrue(testDeck.getActiveDeck().size()==36);

        AmmoTilesDeck testDeck2=new AmmoTilesDeck();
        assertTrue(!testDeck2.getActiveDeck().equals(testDeck.getActiveDeck()));
    }

    @Test
    public void testSetActiveDeck(){
        AmmoTilesDeck testDeck=new AmmoTilesDeck();
        int size=emptyActiveDeck(testDeck);
        testDeck.setActiveDeck();
        assertTrue(testDeck.getActiveDeck().size()==size);
        assertTrue(testDeck.getDiscardedDeck().isEmpty());
    }

    private int emptyActiveDeck(AmmoTilesDeck testDeck){
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
        AmmoTilesDeck testDeck=new AmmoTilesDeck();
        AmmoTileCard testCard=testDeck.draw();
        assertTrue(!testDeck.draw().equals(testCard));
        int size=emptyActiveDeck(testDeck);
        assertTrue(size==testDeck.getDiscardedDeck().size()); //not useful but since the method returns an int... (that here doesn't serve a purpose)
        testCard=testDeck.draw();
        assertTrue(!testDeck.draw().equals(testCard));
    }
}
