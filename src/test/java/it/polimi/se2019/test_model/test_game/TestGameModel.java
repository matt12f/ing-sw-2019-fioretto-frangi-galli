package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
//TODO correggere test (niente più gameMode)
public class TestGameModel {
    @Test
    public void testGameModel(){
        ArrayList<Player> players = new ArrayList<>();
        Player player1=new Player(1,"george101", Color.YELLOW);
        players.add(player1);
        GameModel testModel=new GameModel(players,1, 5);
        assertEquals(Decks.class,testModel.getCurrentDecks().getClass());
        assertTrue(testModel.getPlayerList().contains(player1));
        assertFalse(testModel.getFinalFrenzy());
        assertEquals(Map.class,testModel.getCurrentMap().getClass());
        assertEquals(KillShotTrack.class,testModel.getKillshotTrack().getClass());
        assertEquals(testModel.getKillshotTrack().getSkulls(), 5);

        /*testModel=new GameModel(1,players,"turret",1);
        assertEquals("turret",testModel.getGameMode());
        assertEquals(KillShotTrackTurret.class,testModel.getKillshotTrack().getClass());
        testModel=new GameModel(1,players,"domination",1);
        assertEquals("domination",testModel.getGameMode());
        assertEquals(KillShotTrackDomination.class,testModel.getKillshotTrack().getClass());
        testModel=new GameModel(1,players,"x",1);
        assertEquals("x",testModel.getGameMode());
        assertNull(testModel.getKillshotTrack());*/

    }

    //@Test
    /*public void testGetIncrementTurn(){
        ArrayList<Player> players = new ArrayList<>();
        Player player1=new Player(1,"george101",Color.YELLOW);
        players.add(player1);
        GameModel testModel=new GameModel(1,players,"normal",3);

        int turn=testModel.getTurn();
        assertEquals(0,turn);

        testModel.incrementTurn();
        assertEquals(1,turn+1);
    }*/

    /*@Test
    public void testActivateFinalFrenzy(){
        ArrayList<Player> players = new ArrayList<>();
        Player player1=new Player(1,"george101",Color.YELLOW);
        players.add(player1);
        GameModel testModel=new GameModel(1,players,"normal",1);

        assertFalse(testModel.getFinalFrenzy());
        testModel.activateFinalFrenzy(1);
        assertTrue(testModel.getFinalFrenzy());

        //TODO verificare numero di mosse assegnate ai giocatori

        testModel=new GameModel(1,players,"normal",1);
        assertFalse(testModel.getFinalFrenzy());
        testModel.activateFinalFrenzy(3);
        assertTrue(testModel.getFinalFrenzy());

        //TODO verificare numero di mosse assegnate ai giocatori

    }*/

}
