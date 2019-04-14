package it.polimi.se2019.model.cards;

public class Sledgehammer extends GunCard {


    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;



    /**
     * constructor
     */
    public Sledgehammer() {
        this.hasAlternativeEffect = true;

        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        this.description ="basic mode: Deal 2 damage to 1 target on\n" +
                "your square.\n"+
                "in pulverize mode: Deal 3 damage to 1 target\n" +
                "on your square, then move that target 0, 1,\n" +
                "or 2 squares in one direction.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';

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
    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }

}