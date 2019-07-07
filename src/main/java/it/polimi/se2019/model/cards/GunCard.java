package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class GunCard implements Serializable {
    protected char [] ammoCost;
    protected String description;
    private boolean loaded;
    protected char[] secondaryEffectCost;
    protected ArrayList<ArrayList<String>> effectsOrder;

    /**
     * every guncard has a base effect
     */
    public GunCard(){
        this.loaded=true;
        this.effectsOrder=new ArrayList<>();
        ArrayList<String> firstCombination=new ArrayList<>();
        firstCombination.add("Base");
        this.effectsOrder.add(firstCombination);
    }

    public char[] getAmmoCost() {
        return ammoCost;
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

    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }

    public abstract char[] getTertiaryEffectCost();
    protected abstract void setTertiaryEffectCost(char[] tertiaryEffectCost);

    /**
     * This method calls the single effects method and applies the player's choices, using the methods below
     */
    /**
     *
     * @param currentController
     * @param playersChoices
     */
    public abstract void applyEffects(Controller currentController, ChosenActions playersChoices);

    /**
     *This method builds the available actions, returning an exception in case there are no targets
     */
    /**
     *
     * @param currentController
     * @param player
     * @param effectsCombination
     * @return
     * @throws UnavailableEffectCombinationException
     */
    public abstract SingleEffectsCombinationActions buildAvailableActions(Controller currentController, FictitiousPlayer player, ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException;

    /**
     * methods that apply the individual effects, with the selections of the player as inputs
     */
    /**
     *
     * @param currentController
     * @param playersChoice
     */
    abstract void applyBaseEffect(Controller currentController, ChosenActions playersChoice);

    /**
     *
     * @param currentController
     * @param playersChoice
     */
    abstract void applySecondaryEffect(Controller currentController, ChosenActions playersChoice);

    /**
     * Methods to calculate the possible targets that return a SingleEffectAction
     */
    /**
     *
     * @param currentController
     * @param actions
     * @param player
     */
    abstract void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player);

    /**
     *
     * @param currentController
     * @param actions
     * @param player
     */
    abstract void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player);


    @Override
    public abstract GunCard clone();

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        GunCard card= (GunCard) obj;
        return this.getClass().isAssignableFrom(card.getClass()) && this.isLoaded()==card.isLoaded();
    }
}