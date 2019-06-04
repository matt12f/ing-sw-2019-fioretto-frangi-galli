package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.GameStats;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameStats {
    @Test
    public void testGameStats(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles",Color.WHITE);
        Player player4=new Player(4,"carl",Color.GREEN);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        player1.setScore(23);
        player2.setScore(12);
        player3.setScore(42);
        player4.setScore(21);

        GameStats testRanking=new GameStats(players,25);

        assertEquals(25,testRanking.getNumberOfTurns());
        assertEquals(player2,testRanking.getRanking().get(0));
        assertEquals(player4,testRanking.getRanking().get(1));
        assertEquals(player1,testRanking.getRanking().get(2));
        assertEquals(player3,testRanking.getRanking().get(3));

    }
}
