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

    private void gameStatsUpdate(){
        //TODO creazione e visualizzazione di quello, usa il metodo showMessage e passagli l'oggetto
        // gameStats creato .toString() che lo creo custom

        //TODO scrivere
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

    /**
     * This method activates the final frenzy mode for the whole game (it's centered around a the active player)
     */
    public static void frenzyActivator(Controller currentController){
        currentController.getMainGameModel().activateFinalFrenzy(currentController.getActiveTurn().getActivePlayer().getId());
    }

}
