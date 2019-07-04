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

        Controller controller=new Controller(players,2,5);

        player1.setScore(23);
        player2.setScore(12);
        player3.setScore(42);
        player4.setScore(21);


        GameStats testRanking=new GameStats(controller,25);

        assertEquals(25, testRanking.getNumberOfTurns());
        assertEquals(player2,testRanking.getRanking().get(0));
        assertEquals(player4,testRanking.getRanking().get(1));
        assertEquals(player1,testRanking.getRanking().get(2));
        assertEquals(player3,testRanking.getRanking().get(3));

    }

    @Test
    public void testTieBreaking() {
        ArrayList<Character> killshotTrackScoring = new ArrayList<>(Arrays.asList('y','b'));

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
        player2.setScore(23);
        player3.setScore(42);
        player4.setScore(21);


        ArrayList<Player> output=new ArrayList<>();

        //section for TIE BREAKING
        //if there's a tie, it will break in favor of the player that has the highest score on the killshot track
        //if it's still a tie, they both win
        players.stream()
                .sorted((Player entry1, Player entry2) -> -compare(entry1,entry2,killshotTrackScoring))
                .forEach(player -> output.add(player));

        //correct order: White, Yellow, Blue, Green
        assertEquals("[White Player with id: 3; with nickname: miles; Scored 42 points!, Yellow Player with id: 2; with nickname: george; Scored 23 points!, Blue Player with id: 1; with nickname: frank; Scored 23 points!, Green Player with id: 4; with nickname: carl; Scored 21 points!]",output.toString());
    }

    public int compare(Player o1, Player o2, ArrayList<Character> secondaryRanking){
        int result=Integer.compare(o1.getScore(), o2.getScore());
        if(result==0)
            return Integer.compare(secondaryRanking.indexOf(o2.getPlayerBoard().getColorChar()), secondaryRanking.indexOf(o1.getPlayerBoard().getColorChar()));
        return result;
    }


}
