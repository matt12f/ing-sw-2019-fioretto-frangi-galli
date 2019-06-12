package it.polimi.se2019.model.cards;


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

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions();
        if (effectsCombination.get(0).equals("Base")){
            targetsOfBaseEffect(actions, player);
        }
        else if (effectsCombination.get(0).equals("Optional1")){
            targetsOfSecondaryEffect(actions, player);
        }
        actions.validate();
        return actions;
    }

    public void applyEffects(ChosenActions playersChoice){
        for(int i=0;i<playersChoice.getOrderOfExecution().size();i++)
            switch (playersChoice.getOrderOfExecution().get(i)){
                case "Base":applyBaseEffect(playersChoice);break;
                case "Alternative":applySecondaryEffect(playersChoice);break;
                default:break;
            }
    }

    public boolean getHasAlternativeEffect() {
        return hasAlternativeEffect;
    }

    public char[] getTertiaryEffectCost() {
        return null;
    }

}
