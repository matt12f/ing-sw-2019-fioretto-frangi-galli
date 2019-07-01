package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;


public class PlayerManager {

    public static boolean isAlive(Player player){
        return (player.getPlayerBoard().getDamageTrack().getDamage().length<12);
    }

    /**
     * this method applies the player's choices for the Macro Action it requested
     * @param currentController is the controller of the game
     * @param actions contains the choices
     */
    public static void choiceExecutor(Controller currentController, ChosenActions actions){
        Player player =actions.getFictitiousPlayer().getCorrespondingPlayer();
        FictitiousPlayer fictitiousPlayer=actions.getFictitiousPlayer();
        //moves the player
        ActionManager.movePlayer(currentController,player,fictitiousPlayer.getPosition());

        //grab management, ammo first, then guncards
        if(fictitiousPlayer.isGrabbedAmmo())
            player.getPlayerBoard().getAmmo().addAmmo(player.getFigure().getCell().pickItem().getContent());
        else if(actions.getCardToPick()!=null) {
            try {
                player.getPlayerBoard().getHand().substitutionGunCard(actions.getCardToDiscard(), actions.getCardToPick());
            } catch (CardNotFoundException e) {
                //nothing to see here
            }
        }

        actions.getChosenGun().applyEffects(actions);
    }

    //TODO rivedere
    public static void damageDealer(Player player, char[] damageToDeal){
        int markNumber = player.getPlayerBoard().getDamageTrack().checkMarks(damageToDeal[0]);
        for (char damage: damageToDeal){
            if (isAlive(player))
                player.getPlayerBoard().getDamageTrack().addDamage(damage);
            else{
                //TODO setkill
                //TODO throw eccezione morte
            }

        }
        for (int k=0;k<markNumber;k++)
            if (isAlive(player))
                player.getPlayerBoard().getDamageTrack().addDamage(damageToDeal[0]);
    }

   public static void markerDealer(Player player, char[] add) {
        for (char mark: add)
            player.getPlayerBoard().getDamageTrack().addMark(mark);
    }

    public static void adrenalineManager(Player player){
        if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 3)
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);

        else if(player.getPlayerBoard().getDamageTrack().getDamage().length >= 6)
            player.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(false);

    }

    public static void payGunCardCost(boolean fullOrReload){

    }

    /**
     * This method activates the final frenzy mode for the whole game (it's centered around a the active player)
     */
    public static void frenzyActivator(Controller currentController){
        currentController.getMainGameModel().activateFinalFrenzy(currentController.getActiveTurn().getActivePlayer().getId());
    }
}
