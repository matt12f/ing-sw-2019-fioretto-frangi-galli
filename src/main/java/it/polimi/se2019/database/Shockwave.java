package it.polimi.se2019.database;

public class Shockwave extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Shockwave() {
        this.hasAlternativeEffect = true;
        this.id = 20;
        this.ammoCost = new char[1];
        ammoCost[0]= 'y';
        this.basicEffectDescription ="basic mode: Choose up to 3 targets on\n" +
                "different squares, each exactly 1 move away.\n" +
                "Deal 1 damage to each target.";
        this.secondaryEffectDescription ="in tsunami mode: Deal 1 damage to all\n" +
                "targets that are exactly 1 move away";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'y';

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