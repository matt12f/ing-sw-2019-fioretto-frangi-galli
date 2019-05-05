package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.PlayerBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPlayerBoard {

    @Test
    public void testPlayerBoard(){
        PlayerBoard testPlayerBoard=new PlayerBoard('b');
        assertNotNull(testPlayerBoard.getAmmo());
        assertNotNull(testPlayerBoard.getDamageTrack());
        assertNotNull(testPlayerBoard.getHand());
        assertNotNull(testPlayerBoard.getActionTileNormal());
        assertEquals(0,testPlayerBoard.getSkulls());
        assertEquals('b',testPlayerBoard.getColor());
    }

    @Test
    public void testAddSkull(){
        PlayerBoard testPlayerBoard=new PlayerBoard('b');
        int prevSkulls=testPlayerBoard.getSkulls();
        testPlayerBoard.addSkull();
        assertEquals(prevSkulls+1,testPlayerBoard.getSkulls());
    }

    @Test
    public void testActivateFrenzy(){
        PlayerBoard testPlayerBoard=new PlayerBoard('b');
        testPlayerBoard.activateFrenzy(2);
        assertEquals(0,testPlayerBoard.getSkulls());
        assertNotNull(testPlayerBoard.getActionTileFrenzy());
        assertEquals(2,testPlayerBoard.getActionTileFrenzy().getActionCounter());
    }

}
