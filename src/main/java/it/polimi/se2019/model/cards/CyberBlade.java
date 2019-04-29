package it.polimi.se2019.model.cards;

public class CyberBlade extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect ;

    private char[] secondaryEffectCost;
    private char[] thirdEffectCost;

    public CyberBlade() {
        this.numberOfOptional = 2;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target on your square\n"+
                "with shadowstep: Move 1 square before or after the basic effect.\n"+
                "with slice and dice: Deal 2 damage to a different target on your square.\n" +
                "The shadowstep may be used before or after this effect.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

        this.thirdEffectCost = new char[1];
        thirdEffectCost[0] = 'y';
    }
    /**
     * @return
     */
    public boolean getHasAlternativeEffect() {

        return hasAlternativeEffect;
    }
    /**
     * @return
     */
    public int getNumberOfOptional() {
        return numberOfOptional;
    }


    /**
     * @return
     */
    public char[] getSecondaryEffectCost() {

        return secondaryEffectCost;
    }

    /**
     * @return
     */
    public char[] getThirdEffectCost() {
        return thirdEffectCost;
    }


}