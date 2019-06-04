package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns;

    public GameStats(ArrayList<Player> ranking, int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
        Collections.sort(ranking, new IntegerComparator());
        this.ranking = ranking;

    }

    public ArrayList<Player> getRanking() {
        return ranking;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }
}

class IntegerComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return Integer.compare(o1.getScore(),o2.getScore());
    }
}