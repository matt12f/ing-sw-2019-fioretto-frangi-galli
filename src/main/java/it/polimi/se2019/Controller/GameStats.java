package it.polimi.se2019.Controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class GameStats {
    private ArrayList<Player> ranking;
    private int numberOfTurns;

    public GameStats(ArrayList<Player> ranking, int numberOfTurns) {
        //TODO elaborare ranking ordinandolo per punteggio
        this.ranking = ranking;
        this.numberOfTurns = numberOfTurns;
    }
}
