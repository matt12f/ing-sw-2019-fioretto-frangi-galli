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
 * Usage of effectsCombinationActions: contains the actions possible with a certain combination (and order) of effects
 *
 * Usage of mustSwap: it indicates that to use a card you must swap it with one in your hand
 */

public class SingleCardActions{
    private ArrayList<ArrayList<String>> effectsOrder; //indicates all of the possible effects order
    private ArrayList<SingleEffectsCombinationActions> effectsCombinationActions;
    private boolean mustSwap;

    public SingleCardActions(GunCard gunCard, FictitiousPlayer player, boolean mustSwap) {
        //this part builds the list of combination of the effects a player can use

        //TODO metodo che prende tutti le combinazioni di un'arma e toglie quelli che non si può permettere
        this.effectsOrder=reduceToAvailableEffects(gunCard,gunCard.getEffectsOrder(),player);
        this.effectsCombinationActions =new ArrayList<>();
        this.mustSwap=mustSwap;
        for(int i=0;i<effectsOrder.size();i++){
            try{
            this.effectsCombinationActions.add(gunCard.buildAvailableActions(effectsOrder.get(i),player));
            }
        catch (UnavailableEffectCombinationException e){
                //it means there are no targets for this combination and it won't be added
            }
        }
    }

    /**
     */
    private ArrayList<ArrayList<String>> reduceToAvailableEffects(GunCard gunCard, ArrayList<ArrayList<String>> cardEffects,FictitiousPlayer player) {
        ArrayList<ArrayList<String>> availableEffectsCombinations=new ArrayList<>();
        boolean available;
        for(ArrayList<String> combination:cardEffects) {
            available=true;
            for (String effect : combination)
                switch (effect) {
                case "Optional1":{
                        if(!ActionManager.canAffordCost(player.getAvailableAmmo(),gunCard.getSecondaryEffectCost(),true))
                            available=false;
                    }break;
                    case "Optional2":{
                        if(!ActionManager.canAffordCost(player.getAvailableAmmo(),gunCard.getTertiaryEffectCost(),true))
                            available=false;
                    }break;
                        default: break; //case "Base"
                //TODO se si può permettere tutti gli effetti della combinazione allora posso aggiungerla così:
                }
            if(available)
                availableEffectsCombinations.add(combination);
        /*
        - parto da un vettore di stringhe con tutte le possibili combinazioni dell'arma
        - escludo gli effetti che il player non può pagare (considerare l'uso di powerup per pagare gli effetti)
        - Richiamo i metodi per "risolvere" le combinazioni, in ogni arma (alcune carte avranno più computazione di altre)
         */
        }
        return availableEffectsCombinations;
    }

    public ArrayList<ArrayList<String>> getEffectsOrder(){
        return effectsOrder;
    }

    public ArrayList<SingleEffectsCombinationActions> getEffectsCombinationActions(){
        return effectsCombinationActions;
    }
}
