package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.Player;

public class TurnManager {

    private Player activePlayer;

    public Player getActivePlayer() {
        return activePlayer;
    }

    public TurnManager(GameModel model){
        this.activePlayer = model.getPlayerList().get(0);
    }

    public void nextTurn(Controller currentController){
        int turn = currentController.getMainGameModel().getTurn();
        turn++;
        if(turn == currentController.getMainGameModel().getPlayerList().size()){
            turn = 0;
        }
        currentController.getMainGameModel().setTurn(turn);
        this.activePlayer = currentController.getMainGameModel().getPlayerList().get(turn);
    }

    /**
     * this method sets the active player
     */
    private void setPlayerTurn(int turn, Controller controller){
        this.activePlayer = controller.getMainGameModel().getPlayerList().get(turn);
    }

    /**
     * This method activates the final frenzy mode for the whole game (it's centered around a the active player)
     */
    public static void frenzyActivator(Controller currentController){
        currentController.getMainGameModel().activateFinalFrenzy(currentController.getActiveTurn().getActivePlayer().getId());
    }

}
