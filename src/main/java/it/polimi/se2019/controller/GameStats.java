package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns;

    public GameStats(ArrayList<Player> ranking, int numberOfTurns){
        finalScoring(ranking);
        this.numberOfTurns = numberOfTurns;
        Collections.sort(ranking, new IntegerComparator());
        this.ranking = ranking;
    }

    /**
     * this method scores the killshot track at the end of the fame
     * @param players are all of the players in the game
     */
    private void finalScoring(ArrayList<Player> players){
        //TODO scrivere metodo
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
