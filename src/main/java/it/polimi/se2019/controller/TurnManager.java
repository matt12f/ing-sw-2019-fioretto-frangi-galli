package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

public class TurnManager {

    private Player activePlayer;

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public void playTurn() {
        PlayerManager.adrenalineManager(activePlayer);
        //TODO scrivere metodo
        MapManager.refillEmptiedCells();
    }
    private void gameStatsUpdate(){
        //TODO scrivere metodo
    }


}
