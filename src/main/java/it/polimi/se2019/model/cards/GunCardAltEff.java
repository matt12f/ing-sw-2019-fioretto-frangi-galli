package it.polimi.se2019.model.cards;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

/**
 * Secondary in these cards refers to the alternative effect
 */
public abstract class GunCardAltEff extends GunCard{

    protected boolean hasAlternativeEffect=true;

    public GunCardAltEff(){
        super();
        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);
    }

    /**
     * This method works for every card of this type
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
        if (effectsCombination.get(0).equals("Base")){
            targetsOfBaseEffect(currentController, actions, player);
        }
        else if (effectsCombination.get(0).equals("Optional1")){
            targetsOfSecondaryEffect(currentController, actions, player);
        }
        actions.validate(effectsCombination);
        return actions;
    }

    /**
     * This method applies one or the other effect
     *
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    public void applyEffects(Controller currentController, ChosenActions playersChoice){
        for(int i=0;i<playersChoice.getOrderOfExecution().size();i++)
            switch (playersChoice.getOrderOfExecution().get(i)){
                case "Base":applyBaseEffect(currentController, playersChoice);break;
                case "Optional1":applySecondaryEffect(currentController, playersChoice);break;
                default:break;
            }
    }

    public boolean getHasAlternativeEffect() {
        return hasAlternativeEffect;
    }

    @Override
    public char[] getTertiaryEffectCost() {
        return null;
    }

    @Override
    protected void setTertiaryEffectCost(char[] tertiaryEffectCost) {

    }
}
