package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.ActionTileNormal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestActionTileNormal {

    @Test
    public void testActionTileNormal(){
        ActionTileNormal testTile=new ActionTileNormal();
        assertEquals(2,testTile.getActionCounter());
        assertTrue(!testTile.getAdrenalineMode1());
        assertTrue(!testTile.getAdrenalineMode2());
    }
    @Test
    public void testSetAdrenalineModes(){
        ActionTileNormal testTile=new ActionTileNormal();
        assertTrue(!testTile.getAdrenalineMode1());
        testTile.setAdrenalineMode1(true);
        assertTrue(testTile.getAdrenalineMode1());
        testTile.setAdrenalineMode1(false);
        assertTrue(!testTile.getAdrenalineMode1());

        assertTrue(!testTile.getAdrenalineMode2());
        testTile.setAdrenalineMode2(true);
        assertTrue(testTile.getAdrenalineMode2());
        testTile.setAdrenalineMode2(false);
        assertTrue(!testTile.getAdrenalineMode2());
    }
}
