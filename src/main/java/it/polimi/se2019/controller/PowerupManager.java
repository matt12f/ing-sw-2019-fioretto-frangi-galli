package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The methods in this class will be called to perform the actions of the corresponding powerups
 * calling methods from other managers
 */
public class PowerupManager {
    private static final Logger LOGGER = Logger.getLogger(PowerupManager.class.getName());

    /**
     * You may play this card on your turn before or after any action. Choose any other player's figure and move it
     * 1 or 2 squares in one direction. (You can't use this to move a figure after it respawns at the end of your turn.
     * That would be too late.)

     *
     * @param currentController
     * @param cardIndexInHand
     * @param player
     * @param distance
     * @param directionIndex
     */
    public static void newtonManager(Controller currentController,int cardIndexInHand, Player player, int distance, int directionIndex){

        player = currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(player));

        NewCell cell= null;
        try {
            cell = MapManager.getCellInDirection(currentController.getMainGameModel().getCurrentMap().getBoardMatrix(),player.getFigure().getCell(),distance,directionIndex);
        } catch (OuterWallException e) {
            LOGGER.log(Level.WARNING,"newtonManager", e);
        }
        if(cell!=null){
            ActionManager.movePlayer(currentController,player,cell);
            removeFromHand(currentController,cardIndexInHand);
        }
    }

    /**
     * You may play this card on your turn before or after any action. Pick up your figure and set it down on any square
     * of the board. (You can't use this after you see where someone respawns at the end of your turn. By then it is too
     * late.)
     *
     *
     * @param currentController
     * @param cardIndexInHand
     * @param destinationCell
     */
    public static void teleporterManager(Controller currentController, int cardIndexInHand, NewCell destinationCell){
        Player player = currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList()
                        .indexOf(currentController.getActiveTurn().getActivePlayer()));

        NewCell[][] board=currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        destinationCell = board[MapManager.getLineOrColumnIndex(board,destinationCell,true)]
                [MapManager.getLineOrColumnIndex(board,destinationCell,false)];

        ActionManager.movePlayer(currentController,player,destinationCell);
        removeFromHand(currentController, cardIndexInHand);
    }

    /**
     * You may play this card when you are dealing damage to one or more targets. Pay 1 ammo cube of any color.
     * Choose 1 of those targets and give it an extra point of damage. Note: You cannot use this to do 1 damage to a
     * target that is receiving only marks.
     *
     *
     * @param currentController
     * @param playerDamaged
     * @param cardIndexInHand
     * @param ammoToPay
     */
    public static void targetingScopeManager(Controller currentController, Player playerDamaged, int cardIndexInHand,char ammoToPay) {

        playerDamaged = currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(playerDamaged));


        char [] cost=new char[1];
        cost [0]=ammoToPay;
        currentController.getActiveTurn().getActivePlayer().getPlayerBoard().getAmmo().subtractAmmo(cost);

        char [] dmg=new char[1];
        dmg[0]=currentController.getActiveTurn().getActivePlayer().getFigure().getColorChar();
        PlayerManager.damageDealer(currentController,playerDamaged,dmg);
        removeFromHand(currentController, cardIndexInHand);
    }


    /**
     *  You may play this card when you receive damage from a player you can see. Give that player 1 mark.

     *
     * @param currentController
     * @param playerDamaged
     * @param playerGivingDamage
     * @param cardIndexInHand
     */
    public static void grenadeManager(Controller currentController, Player playerDamaged, Player playerGivingDamage, int cardIndexInHand){
        playerDamaged = currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList().indexOf(playerDamaged));

        char [] marks=new char[1];
        marks[0]=playerGivingDamage.getFigure().getColorChar();
        PlayerManager.markerDealer(currentController, playerDamaged,marks);
        removeFromHand(currentController,cardIndexInHand);
    }

    /**
     * allow to remove a powerup from the hand when used
     * @param currentController
     * @param cardIndexInHand
     */
    private static void removeFromHand(Controller currentController,int cardIndexInHand) {
        Player player = currentController.getMainGameModel().getPlayerList().get(
                currentController.getMainGameModel().getPlayerList()
                        .indexOf(currentController.getActiveTurn().getActivePlayer()));

        player.getPlayerBoard().getHand().removePowerUp(cardIndexInHand);
    }
}
