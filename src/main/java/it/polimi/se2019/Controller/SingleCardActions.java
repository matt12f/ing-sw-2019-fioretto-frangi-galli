package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

/**
 * with a certain guncard you can:
 * - use one, two or three effects in a certain order
 * - select one or more targets from a list of players
 * - move (yourself or others) by a given number of cells
 * - inflict damage to players in a certain cell
 * - inflict damage to one or more players in a certain cell
 *
 * Usage of effectsOrder: examples: {{"Base","Optional1","Optional2"}, {"Base","Optional2", "Optional1"}} or
 * {{"Base"}, {"Alternative"}} meaning that you can either use one or the other. Each card has its own, but it must
 * be customized considering which effects the individual player can pay for with ammo, and whether there are
 * available targets using a certain effect.
 *
 */
public class SingleCardActions{
    private String [][] effectsOrder; //indicates all of the possible effects order
    private ArrayList<SingleEffectsCombinationActions> effectActions;

    public SingleCardActions(GunCard gunCard, Player player) {
        this.effectsOrder = gunCard.getEffectsOrder();
        this.effectActions=new ArrayList<>();
        for(int i=0;i<effectsOrder.length;i++){
            try{
            this.effectActions.add(gunCard.buildAvailableActions(effectsOrder[i],player));
            }
        catch (UnavailableEffectCombinationException e){
                //nothing to see here
            }
        }
    }

    public String[][] getEffectsOrder() {
        return effectsOrder;
    }

    public ArrayList<SingleEffectsCombinationActions> getEffectActions() {
        return effectActions;
    }
}
