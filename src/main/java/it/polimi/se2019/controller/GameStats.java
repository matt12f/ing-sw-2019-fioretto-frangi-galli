package it.polimi.se2019.controller;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.PlayerBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns; //it's the number of turns it took to end the game

    public GameStats(Controller currentController, int numberOfTurns){
        ArrayList<Player> players=currentController.getMainGameModel().getPlayerList();

        ArrayList<Character> killshotTrackScoring= finalScoring(currentController,players);
        this.numberOfTurns = numberOfTurns;
        Collections.sort(players, new IntegerComparator());
        this.ranking = players;


        //section for TIE BREAKING
        //if there's a tie, it will break in favor of the player that has the highest score on the killshot track
        //if it's still a tie, they both win
        //TODO gestione delle parità
    }

    /**
     * this method scores the killshot track at the end of the fame
     * it will:
     *      - score all boards that still have damage tokens, as usual but without killshot
     *      - score the killshot track: players (ordered by number of tokens) will get 8, 6, 4, 2, 1 points
     *      - ties in the score on the killshot track break in favor of the player that has the earliest token(s)
     *
     * @param players are all of the players in the game
     * @return the player that has the highest score on the killshot track
     */
    private ArrayList<Character> finalScoring(Controller currentController, ArrayList<Player> players){
        //scores the boards
        players.forEach(player -> PlayerManager.dealPoints(currentController,
                player.getPlayerBoard().getCurrentBoardValue(),
                PlayerManager.listOffenders(player.getPlayerBoard().getDamageTrack().getDamage())));

        //here we "translate" the tokens to a single string, and then we process it as
        ArrayList<Character> tokens=new ArrayList<>();
        for(String token: currentController.getMainGameModel().getKillshotTrack().getKills())
            for(int i=0;i<token.length();i++)
                tokens.add(token.charAt(i));

        char [] tokensArray=new char[tokens.size()];
        for (int i = 0; i < tokensArray.length; i++)
            tokensArray[i]=tokens.get(i);

        ArrayList<Character> killShotTrackOffenders = PlayerManager.listOffenders(tokensArray);

        //deals the points
        PlayerManager.dealPoints(currentController,8, killShotTrackOffenders);

        //returns the ranking of players in terms of killshot track points
        return killShotTrackOffenders;
    }

    public ArrayList<Player> getRanking() {
        return ranking;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    @Override
    public String toString() {
        //TODO scrivere metodo to String custom
        return null;
    }
}

class IntegerComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return Integer.compare(o1.getScore(), o2.getScore());
    }
}
