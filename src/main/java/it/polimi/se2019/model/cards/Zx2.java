package it.polimi.se2019.model.cards;

public class Zx2 extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Zx2() {
        this.hasAlternativeEffect = true;

        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'r';
        this.basicEffectDescription ="basic mode: Deal 1 damage and 2 marks to\n" +
                "1 target you can see.";
        this.secondaryEffectDescription ="in scanner mode: Choose up to 3 targets you\n" +
                "can see and deal 1 mark to each.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

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
    public String getBasicEffectDescription() {

        return basicEffectDescription;
    }

    public void doBasicEffect() {

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
    public String getSecondaryEffectDescription() {

        return secondaryEffectDescription;
    }
    public void doSecondaryEffect() {

    }
}