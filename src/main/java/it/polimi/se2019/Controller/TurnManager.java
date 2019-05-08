package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class TurnManager {

    private static Player activePlayer;
    private KillShotTrackerManager killshotTrackerManager;
    private MapManager mapManager;
    private static ActionManager actionManager;
    private static PlayerManager playerManager;

    public TurnManager() {
        this.killshotTrackerManager = new KillShotTrackerManager();
        this.mapManager = new MapManager();
        this.actionManager = new ActionManager();
        this.playerManager = new PlayerManager();
    }

    public static Player getActivePlayer() {
        return activePlayer;
    }

    public static PlayerManager getPlayerManager (){return playerManager;}
    public static ActionManager getActionManager(){
        return actionManager;
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
