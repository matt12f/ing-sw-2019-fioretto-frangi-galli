package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.view.ChosenActions;


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

    public void applyEffects(ChosenActions playersChoice){
        for(int i=0;i<playersChoice.getOrderOfExecution().size();i++)
            switch (playersChoice.getOrderOfExecution().get(i)){
                case "Base":applyBaseEffect(playersChoice);break;
                case "Second":applySecondaryEffect(playersChoice);break;
                case "Third":applyTertiaryEffect(playersChoice);break;
                default:break;
            }
    }

    public char[] getTertiaryEffectCost() {
        return tertiaryEffectCost;
    }


    /**
     * methods that apply the individual effects, with the selections of the player as inputs
     */
    abstract void applyTertiaryEffect(ChosenActions playersChoice);

    /**
     * Methods called by available actions builder to calculate the possible targets
     * @param actions
     * @param player
     */
    abstract void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player);

}
