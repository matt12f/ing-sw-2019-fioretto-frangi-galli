package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class TurnManager {

    private static Player activePlayer;
    private KillShotTrackerManager killshotTrackerManager;
    private MapManager mapManager;
    private ActionManager actionManager;

    public TurnManager() {
        this.killshotTrackerManager = new KillShotTrackerManager();
        this.mapManager = new MapManager();
        this.actionManager = new ActionManager();
    }

    public static Player getActivePlayer() {
        return activePlayer;
    }

    public void playTurn(){
        //TODO qui va considerato che alla fine del turno del singolo giocatore si devono riempire
        // eventuali dropCells dove è stata pescata la carta

        //TODO va inoltre considerato che quando viene pescata una gunCard da una spawnCell ne va
        // rimessa una nuova (non lo fa in automatico perché il model non si occupa di queste cose)
    }
    private void gameStatsUpdate(){
        //TODO scrivere metodo
    }
}
