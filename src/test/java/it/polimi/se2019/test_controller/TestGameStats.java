package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.GameStats;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

        Controller controller=new Controller(players,2,3);

        player1.setScore(23);
        player2.setScore(12);
        player3.setScore(42);
        player4.setScore(21);

        controller.getMainGameModel().getKillshotTrack().setKills("bb");
        controller.getMainGameModel().getKillshotTrack().setKills("y");
        controller.getMainGameModel().getKillshotTrack().setKills("gg");

        GameStats testRanking=new GameStats(controller,25);

        assertEquals("Game results\n" +
                "The game was 25 turns long\n" +
                "\n" +
                "There's a single winner: WHITE Player with id: 3; with nickname: miles; Scored 42 points!\n" +
                "\n" +
                "Ranking: \n" +
                "1° place: WHITE Player with id: 3; with nickname: miles; Scored 42 points!\n" +
                "2° place: BLUE Player with id: 1; with nickname: frank; Scored 31 points!\n" +
                "3° place: GREEN Player with id: 4; with nickname: carl; Scored 27 points!\n" +
                "4° place: YELLOW Player with id: 2; with nickname: george; Scored 16 points!\n",testRanking.toString());

        assertEquals(25, testRanking.getNumberOfTurns());
        assertEquals(player3,testRanking.getRanking().get(0));
        assertEquals(player1,testRanking.getRanking().get(1));
        assertEquals(player4,testRanking.getRanking().get(2));
        assertEquals(player2,testRanking.getRanking().get(3));
    }

    @Test
    public void testTieBreaking() {
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles",Color.WHITE);
        Player player4=new Player(4,"carl",Color.GREEN);

        ArrayList<Player> players=new ArrayList<>();

        Controller controller=new Controller(players,2,3);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        player1.setScore(23);
        player2.setScore(25);
        player3.setScore(12);
        player4.setScore(5);


        controller.getMainGameModel().getKillshotTrack().setKills("bb");
        controller.getMainGameModel().getKillshotTrack().setKills("yy");
        controller.getMainGameModel().getKillshotTrack().setKills("g");

        GameStats testRanking=new GameStats(controller,25);

        assertEquals("Game results\n" +
                "The game was 25 turns long\n" +
                "\n" +
                "There's a tie between Player: \n" +
                "BLUE Player with id: 1; with nickname: frank; Scored 31 points!\n" +
                "YELLOW Player with id: 2; with nickname: george; Scored 31 points!\n" +
                "\n" +
                "Ranking: \n" +
                "1° place: BLUE Player with id: 1; with nickname: frank; Scored 31 points!\n" +
                "2° place: YELLOW Player with id: 2; with nickname: george; Scored 31 points!\n" +
                "3° place: WHITE Player with id: 3; with nickname: miles; Scored 12 points!\n" +
                "4° place: GREEN Player with id: 4; with nickname: carl; Scored 9 points!\n",testRanking.toString());

        assertEquals(25, testRanking.getNumberOfTurns());
        assertEquals(player1,testRanking.getRanking().get(0));
        assertEquals(player2,testRanking.getRanking().get(1));
        assertEquals(player3,testRanking.getRanking().get(2));
        assertEquals(player4,testRanking.getRanking().get(3));


    }


}
