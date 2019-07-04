package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns; //it's the number of turns it took to end the game

    public GameStats(Controller currentController, int numberOfTurns){
        ArrayList<Player> players=currentController.getMainGameModel().getPlayerList();

        Player killshotTrackWinner= finalScoring(players);
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
    private Player finalScoring(ArrayList<Player> players){

        //TODO scrivere metodo
        return null;
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
