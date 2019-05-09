package it.polimi.se2019.controller;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.model.game.Cell;
import it.polimi.se2019.model.game.DropCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.SpawnCell;

public class TurnManager {

    private Player activePlayer;
    private KillShotTrackerManager killshotTrackerManager;
    private MapManager mapManager;
    private ActionManager actionManager;
    private PlayerManager playerManager;

    public TurnManager() {
        this.killshotTrackerManager = new KillShotTrackerManager();
        this.mapManager = new MapManager();
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
        //TODO richiesta interazione con la GUI
        mapManager.refillEmptiedCells();
    }
    private void gameStatsUpdate(){
        //TODO scrivere metodo
    }


}
