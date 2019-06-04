package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.PlayerBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPlayerBoard {

    @Test
    public void testPlayerBoard(){
        PlayerBoard testPlayerBoard=new PlayerBoard(Color.BLUE);
        assertNotNull(testPlayerBoard.getAmmo());
        assertNotNull(testPlayerBoard.getDamageTrack());
        assertNotNull(testPlayerBoard.getHand());
        assertNotNull(testPlayerBoard.getActionTileNormal());
        assertEquals(8,testPlayerBoard.getCurrentBoardValue());
        assertEquals(Color.BLUE,testPlayerBoard.getColor());
    }

    @Test
    public void testDecreaseBoardValue(){
        PlayerBoard testPlayerBoard=new PlayerBoard(Color.BLUE);
        int prevBoardValue=testPlayerBoard.getCurrentBoardValue();
        testPlayerBoard.decreaseBoardValue();
        assertEquals(prevBoardValue-2,testPlayerBoard.getCurrentBoardValue());
    }

    @Test
    public void testActivateFrenzy(){
        PlayerBoard testPlayerBoard=new PlayerBoard(Color.BLUE);
        testPlayerBoard.activateFrenzy(2);
        assertEquals(8,testPlayerBoard.getCurrentBoardValue());
        assertNotNull(testPlayerBoard.getActionTileFrenzy());
        assertEquals(2,testPlayerBoard.getActionTileFrenzy().getActionCounter());
    }

}
