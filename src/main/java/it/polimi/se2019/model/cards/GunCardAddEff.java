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

    /**
     * This method works for every card of this type (except CyberBlade, PlasmaGun and RocketLauncher)
     *
     * @param currentController it the current controller of the game
     * @param effectsCombination is a specific combination of the possible effects
     * @param player contains the attributes of the player to calculate the actions upon
     * @return available usages of the card for a certain combination
     * @throws UnavailableEffectCombinationException if the combination has no targets
     */
    @Override
    public SingleEffectsCombinationActions buildAvailableActions(Controller currentController, FictitiousPlayer player, ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions(effectsCombination);
        for(String effect: effectsCombination){
            if (effect.equals("Base"))
                targetsOfBaseEffect(currentController, actions, player);
            else if (effect.equals("Optional1"))
                targetsOfSecondaryEffect(currentController, actions, player);
            else if(effect.equals("Optional2"))
                targetsOfTertiaryEffect(currentController, actions, player);
        }
        actions.validate(effectsCombination);
        return actions;
    }

    /**
     * This method applies the effects one by one from a given combination
     *
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    public void applyEffects(Controller currentController, ChosenActions playersChoice){
        for(int i=0;i<playersChoice.getOrderOfExecution().size();i++)
            switch (playersChoice.getOrderOfExecution().get(i)){
                case "Base":applyBaseEffect(currentController, playersChoice);break;
                case "Optional1":applySecondaryEffect(currentController, playersChoice);break;
                case "Optional2":applyTertiaryEffect(currentController, playersChoice);break;
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
    abstract void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice);

    /**
     * Methods called by available actions builder to calculate the possible targets
     */
    abstract void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player);


}
