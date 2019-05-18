package it.polimi.se2019.model.cards;

public abstract class GunCardAltEff extends GunCard{

    protected boolean hasAlternativeEffect=true;
    protected char[] secondaryEffectCost;

    public boolean getHasAlternativeEffect() {
        return hasAlternativeEffect;
    }

    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }

}
