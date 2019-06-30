package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;


public abstract class GunCardAddEff extends GunCard{

    protected int numberOfOptional;
    protected boolean hasAlternativeEffect=false;
    protected char[] tertiaryEffectCost;

    public GunCardAddEff() {
        super();
    }

    public boolean getHasAlternativeEffect() {
        return hasAlternativeEffect;
    }

    public int getNumberOfOptional() {
        return numberOfOptional;
    }

    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(Controller currentController, FictitiousPlayer player, ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions(effectsCombination,false);
        for(String effect: effectsCombination){
            if (effect.equals("Base"))
                targetsOfBaseEffect(currentController, actions, player);
            else if (effect.equals("Optional1"))
                targetsOfSecondaryEffect(currentController, actions, player);
            else if(effect.equals("Optional2"))
                targetsOfTertiaryEffect(currentController, actions, player);
        }
        actions.validate();
        return actions;
    }

    public void applyEffects(ChosenActions playersChoice){
        for(int i=0;i<playersChoice.getOrderOfExecution().size();i++)
            switch (playersChoice.getOrderOfExecution().get(i)){
                case "Base":applyBaseEffect(playersChoice);break;
                case "Optional1":applySecondaryEffect(playersChoice);break;
                case "Optional2":applyTertiaryEffect(playersChoice);break;
                default:break;
            }
    }

    @Override
    public char[] getTertiaryEffectCost() {
        return tertiaryEffectCost;
    }

    protected void setTertiaryEffectCost(char[] tertiaryEffectCost) {
        this.tertiaryEffectCost = tertiaryEffectCost;
    }

    /**
     * methods that apply the individual effects, with the selections of the player as inputs
     */
    abstract void applyTertiaryEffect(ChosenActions playersChoice);

    /**
     * Methods called by available actions builder to calculate the possible targets
     */
    abstract void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player);


}
