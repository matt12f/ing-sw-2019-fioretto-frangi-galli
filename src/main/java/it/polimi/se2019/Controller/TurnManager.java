package it.polimi.se2019.Controller;

import it.polimi.se2019.model.game.Player;

public class TurnManager {

    private static Player activePlayer;
    private PlayerManager playerManager; //TODO questo è inutile... PlayerManager ha solo metodi
    private KillShotTrackerManager killshotTrackerManager;
    private MapManager mapManager;
    private ActionManager actionManager;

    public TurnManager(){

    }
    public static Player getActivePlayer() {
        return activePlayer;
    }
    public void playTurn(){

    }
    private void gameStatsUpdate(){

    }
}
