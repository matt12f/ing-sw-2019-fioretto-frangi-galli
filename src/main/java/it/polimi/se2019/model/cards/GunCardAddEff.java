package it.polimi.se2019.model.cards;

public abstract class GunCardAddEff extends GunCard{

    protected int numberOfOptional;
    protected boolean hasAlternativeEffect=false;
    protected char[] secondaryEffectCost;
    protected char[] thirdEffectCost;

    public boolean getHasAlternativeEffect() {
        return hasAlternativeEffect;
    }

    public int getNumberOfOptional() {
        return numberOfOptional;
    }

    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }

    public char[] getThirdEffectCost() {
        return thirdEffectCost;
    }


}
