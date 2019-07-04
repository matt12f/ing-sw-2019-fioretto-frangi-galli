package it.polimi.se2019.controller;

import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Ammo;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.io.Serializable;
import java.util.ArrayList;
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
public class FictitiousPlayer implements Serializable {
    private Player correspondingPlayer;
    private static final Logger LOGGER = Logger.getLogger(FictitiousPlayer.class.getName());
    private int playerId;
    private Color playerColor;
    private NewCell position;
    private boolean grabbedAmmo;
    private Ammo availableAmmo;
    private ArrayList<GunCard> pickableCards;
    private ArrayList<SingleCardActions> availableCardActions; //for the actions you can do with one card
    private boolean noTargets;

    /**
     * This method creates a fictitious player that has supposedly taken the choices contained in the parameter cell
     * @param cell encapsulates the choices about movement and
     * @param player used to add the ammo picked up to the existing ammo a player has
     * returns fictitious player
     */
    public FictitiousPlayer (Controller currentController, Player player, CellInfo cell, boolean shoot, boolean frenzyReload){
        this.correspondingPlayer=player;
        this.playerId=player.getId();
        ArrayList<GunCard> usableCards;
        this.playerColor=player.getFigure().getColor();
        this.position =cell.getCell();

        if (cell.isCanGrabAmmo()){
            this.availableAmmo=player.getPlayerBoard().getAmmo();
            if(cell.getCell().getDrop()!=null){
                this.availableAmmo.addAmmo(cell.getCell().getDrop().getContent());
                this.grabbedAmmo=true;
            }

        }
        this.pickableCards=new ArrayList<>();
        //this is done for grab/move+grab actions where you may want to just pick a card
        if(cell.isCanGrabCard())
            for(GunCard gunCard:cell.getCell().getWeaponCards())
                if(gunCard!=null && ActionManager.canAffordCost(player,this.availableAmmo,gunCard.getAmmoCost(),true))
                    this.pickableCards.add(gunCard);

        //the "pickable" cards can ALL be added to the cards a player can choose from, if it's noted that
        // if the player chooses one of those, he must discard one of his current cards. This could work
        // because the use of a card is exclusionary (you can only use one at a time).
        this.availableCardActions = new ArrayList<>();
        if(shoot){
            usableCards=evaluateUsableCards(player,frenzyReload);
            usableCards.addAll(pickableCards);

            for (GunCard gunCard : usableCards)
                this.availableCardActions.add(new SingleCardActions(currentController, gunCard,this,usableCards.size()>3));

            //removes cards with no actionsAvailable
            this.availableCardActions.removeIf(p->p.getEffectsCombinationActions().isEmpty());

            if(this.availableCardActions.isEmpty() && !player.getPlayerBoard().getActionTileNormal().getAdrenalineMode2())
                this.noTargets=true;
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
            if(gunCard!=null && (gunCard.isLoaded()||frenzyReload && ActionManager.canAffordCost(player,player.getPlayerBoard().getAmmo(),gunCard.getAmmoCost(),false)))
                usableCards.add(gunCard);
        }
        return usableCards;
    }

    public Player getCorrespondingPlayer(){
        return this.correspondingPlayer;
    }

    public boolean isGrabbedAmmo() {
        return grabbedAmmo;
    }

    public int getPlayerId() {
        return playerId;
    }

    public ArrayList<GunCard> getPickableCards() {
        return pickableCards;
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

    public boolean isNoTargets() {
        return noTargets;
    }

    public ArrayList<SingleCardActions> getAvailableCardActions() {
        return availableCardActions;
    }
}
