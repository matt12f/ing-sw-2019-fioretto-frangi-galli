package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public abstract class GunCard{
    protected char [] ammoCost;
    protected String description;
    private boolean loaded;
    protected char[] secondaryEffectCost;
    protected ArrayList<ArrayList<String>> effectsOrder;

    public char[] getAmmoCost() {
        return ammoCost;
    }

    public int getRedAmmoCost() {
        int redCont=0;
        for(char singleCost:ammoCost)
            if (singleCost=='r')
                redCont++;
        return redCont;
    }
    public int getYellowAmmoCost() {
        int yellowCont=0;
        for(char singleCost:ammoCost)
            if (singleCost=='r')
                yellowCont++;
        return yellowCont;
    }

    public int getBlueAmmoCost() {
        int blueCont=0;
        for(char singleCost:ammoCost)
            if (singleCost=='r')
                blueCont++;
        return blueCont;
    }

    public String getDescription() {
        return description;
    }
    public boolean isLoaded() {
        return loaded;
    }
    public void setLoaded(boolean load){
        this.loaded = load;
    }
    public ArrayList<ArrayList<String>> getEffectsOrder(){return effectsOrder;}

    /**
     * This method calls the single effects method and applies the player's choices, using the methods below
     */
    public abstract void applyEffects(ChosenAction playersChoices);

    /**
     *This method builds the available actions, returning an exception in case there are no targets
     */
    public abstract SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException;

    /**
     * methods that apply the individual effects, with the selections of the player as inputs
     */
    abstract void applyBaseEffect(ChosenAction playersChoice);  //TODO valutare classe apposita per input al singolo effetto
    abstract void applySecondaryEffect(ChosenAction playersChoice);  //TODO valutare classe apposita per input al singolo effetto

    /**
     * Methods to calculate the possible targets that return a SingleEffectAction
     */
    abstract void targetsOfBaseEffect();
    abstract void targetsOfSecondaryEffect();
}