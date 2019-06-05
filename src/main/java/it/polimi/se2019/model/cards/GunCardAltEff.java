package it.polimi.se2019.model.cards;


import it.polimi.se2019.view.ChosenAction;

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

    public void applyEffects(ChosenAction playersChoice){
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
