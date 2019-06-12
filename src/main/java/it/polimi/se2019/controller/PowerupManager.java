package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.exceptions.OuterWallException;
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
    public static void newtonManager(int cardIndexInHand, Player player, int distance, int directionIndex){
        NewCell cell= null;
        try {
            cell = MapManager.getCellInDirection(AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix(),player.getFigure().getCell(),distance,directionIndex);
        } catch (OuterWallException e) {
            //TODO loggare eccezione, sarebbe errato riceverla qui
        }
        if(cell!=null){
            ActionManager.movePlayer(player,cell);
            removeFromHand(cardIndexInHand);
        }
    }

    /**
     * You may play this card on your turn before or after any action. Pick up your figure and set it down on any square
     * of the board. (You can't use this after you see where someone respawns at the end of your turn. By then it is too
     * late.)
     */
    public static void teleporterManager(int cardIndexInHand, NewCell destinationCell){
        ActionManager.movePlayer(AdrenalineServer.getMainController().getActiveTurn().getActivePlayer(),destinationCell);
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
    public static void grenadeManager(Player playerDamaged,Player playerGivingDamage, int cardIndexInHand){
        char [] marks=new char[1];
        marks[0]=playerGivingDamage.getFigure().getColorChar();
        PlayerManager.markerDealer(playerDamaged,marks);
        removeFromHand(cardIndexInHand);
    }

    private static void removeFromHand(int cardIndexInHand) {
        AdrenalineServer.getMainController().getActiveTurn().getActivePlayer().getPlayerBoard().getHand().removePowerUp(cardIndexInHand);
    }
}
