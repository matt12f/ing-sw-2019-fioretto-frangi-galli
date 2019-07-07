package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Player;

import java.io.Serializable;
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
 * Usage of effectsCombinationActions: contains the actions possible with a certain combination (and order) of effects
 *
 * Usage of mustSwap: it indicates that to use a card you must swap it with one in your hand
 */

public class SingleCardActions implements Serializable {
    private GunCard gunToUse;
    private ArrayList<String> availableCombinations; //For the GUI/CLI to list them efficiently
    private ArrayList<SingleEffectsCombinationActions> effectsCombinationActions;
    private boolean mustSwap;

    public SingleCardActions(Controller currentController, GunCard gunCard, FictitiousPlayer player, boolean mustSwap) {
        //this part builds the list of combination of the effects a player can afford to use
        this.gunToUse=gunCard;

        //indicates all of the possible effects order
        ArrayList<ArrayList<String>> effectsOrder=reduceToAffordableEffects(gunCard,gunCard.getEffectsOrder(),player);

        this.effectsCombinationActions =new ArrayList<>();
        this.availableCombinations=new ArrayList<>();
        this.mustSwap=mustSwap;
        for(ArrayList<String> effectsCombination: effectsOrder){
            try{
            this.effectsCombinationActions.add(gunCard.buildAvailableActions(currentController,player,effectsCombination));
            this.availableCombinations.add(this.effectsCombinationActions.size()-1 +". "+effectsCombination.toString());
            }
        catch (UnavailableEffectCombinationException e){
                //it means there are no targets for this combination and it won't be added
            }
        }
    }

    private ArrayList<ArrayList<String>> reduceToAffordableEffects(GunCard gunCard, ArrayList<ArrayList<String>> cardEffects,FictitiousPlayer player) {
        ArrayList<ArrayList<String>> availableEffectsCombinations=new ArrayList<>();
        boolean affordable;
        for(ArrayList<String> combination: cardEffects) {
            affordable=true;
            for (String effect : combination)
                switch (effect) {
                case "Optional1":{
                    //the false at the end considers the full cost of the effect cost
                        if(!ActionManager.canAffordCost(player.getCorrespondingPlayer(),player.getAvailableAmmo(),gunCard.getSecondaryEffectCost(),false))
                            affordable=false;
                    }break;
                    case "Optional2":{
                        //the false at the end considers the full cost of the effect cost
                        if(!ActionManager.canAffordCost(player.getCorrespondingPlayer(),player.getAvailableAmmo(),gunCard.getTertiaryEffectCost(),false))
                            affordable=false;
                    }break;
                        default: break; //case "Base"
                }
            if(affordable)
                availableEffectsCombinations.add(combination);
        }
        return availableEffectsCombinations;
    }

    public GunCard getGunCardToUse() {
        return gunToUse;
    }

    public ArrayList<String> getAvailableCombinations() {
        return availableCombinations;
    }

    public boolean isMustSwap() {
        return mustSwap;
    }

    public ArrayList<SingleEffectsCombinationActions> getEffectsCombinationActions(){
        return effectsCombinationActions;
    }
}
