package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.*;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns;

    public GameStats(ArrayList<Player> ranking, int numberOfTurns) {
        this.ranking = ranking;
        this.numberOfTurns = numberOfTurns;
        Collections.sort(ranking, new CustomComparator());
    }
}

class CustomComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if(o1.getScore()>=o2.getScore()){
            return -1;
        }else{
            return 1;
        }
    }
}