package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.ActionTileFrenzy;
import it.polimi.se2019.model.game.ActionTileNormal;
import it.polimi.se2019.model.game.PlayerBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayerBoard {

    @Test
    public void testPlayerBoard(){
        PlayerBoard testPlayerBoard=new PlayerBoard('b');
        assertNotNull(testPlayerBoard.ammo);
        assertNotNull(testPlayerBoard.damage);
        assertNotNull(testPlayerBoard.hand);
        assertNotNull(testPlayerBoard.getActionTile());
        assertEquals(testPlayerBoard.getActionTile().getClass(),ActionTileNormal.class);
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
        assertEquals(testPlayerBoard.getActionTile().getClass(),ActionTileFrenzy.class);
        assertEquals(2,testPlayerBoard.getActionTile().getActionCounter());
    }

}
