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

    public void playTurn(Controller currentController) {
        PlayerManager.adrenalineManager(activePlayer);
        //TODO scrivere metodo
        MapManager.refillEmptiedCells(currentController.getMainGameModel().getCurrentMap().getBoardMatrix(),currentController.getMainGameModel().getCurrentDecks());
    }
    private void gameStatsUpdate(){
        //TODO scrivere metodo
    }

    public void nextTurn(Controller currentController){
        int turn = currentController.getMainGameModel().getTurn();
        turn++;
        if(turn == currentController.getMainGameModel().getPlayerList().size()){
            turn = 0;
        }
        currentController.getMainGameModel().setTurn(turn);
        this.setPlayerTurn(turn, currentController);
    }

    private void setPlayerTurn(int turn, Controller controller){
        Player playersTurn = controller.getMainGameModel().getPlayerList().get(turn);
        this.activePlayer = playersTurn;
    }
}
