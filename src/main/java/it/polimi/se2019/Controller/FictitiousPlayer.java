package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CardName;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Ammo;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to create an Arraylist that is going to contain: player's color, position, available ammo, list of usable cards and their
 * SingleCardActions.
 * It will also be used to evaluate the options to be offered to a player:
 * - The first case will always be considering the player hasn't moved (simply translating its status to an object of this class)
 * - If a player canGrabAmmo it must do so, if grab is part of the macroAction
 * - If a player canGrabCard it must do so (adding these cards – if it can afford them – to the ones he can use) if grab is part of the macroAction
 * - In conclusion the evaluation for the individual guncards must be done on the single FictitiousPlayer
 *
 */
public class FictitiousPlayer {
    private static final Logger LOGGER = Logger.getLogger(FictitiousPlayer.class.getName());
    private Color playerColor;
    private NewCell position;
    private ArrayList<CardName> usedPwUps;
    private Ammo availableAmmo;
    private ArrayList<SingleCardActions> availableCardActions; //for the actions you can do with one card


    /**
     * This method creates a fictitious player that has supposedly taken the choices contained in the parameter cell
     * @param cell incapsulates the choices about movement and
     * @param player used to add the ammo picked up to the existing ammo a player has
     * @return fictitious player
     */
    public FictitiousPlayer (Player player, CellInfo cell, boolean shoot, boolean frenzyReload, ArrayList<CardName> usedPwUps){
        ArrayList<GunCard> usableCards=new ArrayList<>();
        this.playerColor=player.getFigure().getColor();
        this.position =cell.getCell();
        this.usedPwUps=usedPwUps;
        if (cell.isCanGrabAmmo()){
            this.availableAmmo=player.getPlayerBoard().getAmmo();
            this.availableAmmo.addAmmo(cell.getCell().getDrop().getContent());
        }
            //the "pickable" cards can ALL be added to the cards a player can choose from, if it's noted that
            // if the player chooses one of those, he must discard one of his current cards. This could work
            // because the use of a card is exclusionary (you can only use one at a time).
        if(shoot){
            usableCards=evaluateUsableCards(player,frenzyReload);
            if(cell.isCanGrabCard())
                for(GunCard gunCard:cell.getCell().getWeaponCards())
                    if(ActionManager.canAffordCost(this.availableAmmo,gunCard.getAmmoCost(),true,this.usedPwUps))
                        usableCards.add(gunCard);

            this.availableCardActions = new ArrayList<>();
            for (GunCard gunCard : usableCards)
                this.availableCardActions.add(new SingleCardActions(gunCard,this,usableCards.size()>3));

            //removal of not usable cards (with no targets)
            try {
                this.availableCardActions.removeAll(Collections.singleton(null));
            }catch(NullPointerException e){
                LOGGER.log(Level.WARNING,"Fictitious Player unavailable card actions clearing ",e);
            }

        }else{
            this.availableCardActions = null;
        }
    }

    /**
     * this method simply evaluates whether the player can use a card by checking:
     * - if it's loaded
     * - if it can be reloaded in frenzy modes that allow it
     */
    private ArrayList<GunCard> evaluateUsableCards(Player player, boolean frenzyReload){
        ArrayList<GunCard> usableCards=new ArrayList<>();
        //evaluates if the guns are loaded
        for(GunCard gunCard: player.getPlayerBoard().getHand().getGuns()){
            if(gunCard!=null && (gunCard.isLoaded()||frenzyReload && ActionManager.canAffordCost(player.getPlayerBoard().getAmmo(),gunCard.getAmmoCost(),false,usedPwUps)))
                usableCards.add(gunCard);
        }
        return usableCards;
    }

    public ArrayList<CardName> getUsedPwUps() {
        return usedPwUps;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public NewCell getPosition() {
        return position;
    }

    public Ammo getAvailableAmmo() {
        return availableAmmo;
    }

    public ArrayList<SingleCardActions> getAvailableCardActions() {
        return availableCardActions;
    }
}
