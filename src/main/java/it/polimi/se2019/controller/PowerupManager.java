package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

/**
 * The methods in this class will be called to perform the actions of the corresponding powerups
 * calling methods from other managers
 */
public class PowerupManager {

    /**
     * You may play this card on your turn before or after any action. Choose any other player's figure and move it
     * 1 or 2 squares in one direction. (You can't use this to move a figure after it respawns at the end of your turn.
     * That would be too late.)
     */
    public static void newtonManager(int cardIndexInHand, Player player, int distance, String direction) {
        //TODO scrivere metodo x spostamento
        removeFromHand(cardIndexInHand);
    }


    /**
     * You may play this card on your turn before or after any action. Pick up your figure and set it down on any square
     * of the board. (You can't use this after you see where someone respawns at the end of your turn. By then it is too
     * late.)
     */
    public static void teleporterManager(int cardIndexInHand, Player player, NewCell destinationCell){
        //TODO scrivere metodo x spostamento
        removeFromHand(cardIndexInHand);
    }

    /**
     * You may play this card when you are dealing damage to one or more targets. Pay 1 ammo cube of any color.
     * Choose 1 of those targets and give it an extra point of damage. Note: You cannot use this to do 1 damage to a
     * target that is receiving only marks.
     */
    public static void targetingScopeManager(int cardIndexInHand) {
        //TODO scrivere metodo
        removeFromHand(cardIndexInHand);
    }


    /**
     *  You may play this card when you receive damage from a player you can see. Give that player 1 mark.
     */
    public static void grenadeManager(int cardIndexInHand){
        //TODO scrivere metodo
        removeFromHand(cardIndexInHand);
    }

    private static void removeFromHand(int cardIndexInHand) {
        AdrenalineServer.getMainController().getActiveturn().getActivePlayer().getPlayerBoard().getHand().removePowerUp(cardIndexInHand);
    }
}
