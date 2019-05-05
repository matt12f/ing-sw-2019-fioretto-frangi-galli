package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameModel {
    @Test
    public void testGameModel(){
        ArrayList<Player> players = new ArrayList<>();
        Player player1=new Player(1,"george101",'y');
        players.add(player1);
        GameModel testModel=new GameModel(1,players,"normal",1);

        assertEquals(1,testModel.getGameNumberId());
        assertEquals(Decks.class,testModel.getCurrentDecks().getClass());
        assertTrue(testModel.getPlayerList().contains(player1));
        assertFalse(testModel.getFinalFrenzy());
        assertEquals("normal",testModel.getGameMode());
        assertEquals(Map.class,testModel.getCurrentMap().getClass());
        assertEquals(KillShotTrack.class,testModel.getKillshotTrack().getClass());

        testModel=new GameModel(1,players,"turret",1);
        assertEquals("turret",testModel.getGameMode());
        testModel=new GameModel(1,players,"domination",1);
        assertEquals("domination",testModel.getGameMode());

    }

    @Test
    public void testGetIncrementTurn(){
        ArrayList<Player> players = new ArrayList<>();
        Player player1=new Player(1,"george101",'y');
        players.add(player1);
        GameModel testModel=new GameModel(1,players,"normal",1);

        int turn=testModel.getTurn();
        assertEquals(turn,0);

        testModel.incrementTurn();
        assertEquals(turn+1,1);
    }

    @Test
    public void testActivateFinalFrenzy(){
        ArrayList<Player> players = new ArrayList<>();
        Player player1=new Player(1,"george101",'y');
        players.add(player1);
        GameModel testModel=new GameModel(1,players,"normal",1);

        assertFalse(testModel.getFinalFrenzy());
        testModel.activateFinalFrenzy(1);
        assertTrue(testModel.getFinalFrenzy());

        //TODO estendere test che verifichi l'effettiva attivazione del final frenzy
    }

}
