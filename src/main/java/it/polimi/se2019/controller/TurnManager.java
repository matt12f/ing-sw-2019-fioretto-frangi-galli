package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class TurnManager {

    private Player activePlayer;
    private KillShotTrackerManager killshotTrackerManager;
    private ActionManager actionManager;
    private PlayerManager playerManager;

    public TurnManager() {
        this.killshotTrackerManager = new KillShotTrackerManager();
        this.actionManager = new ActionManager();
        this.playerManager = new PlayerManager();
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public PlayerManager getPlayerManager (){return playerManager;}
    public ActionManager getActionManager(){
        return actionManager;
    }

    public void playTurn() {
        //TODO scrivere metodo
        MapManager.refillEmptiedCells();
    }
    private void gameStatsUpdate(){
        //TODO scrivere metodo
    }


}
