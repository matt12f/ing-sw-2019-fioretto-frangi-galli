package it.polimi.se2019.database;

public class Sledgehammer extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Sledgehammer() {
        this.hasAlternativeEffect = true;
        this.id = 21;
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        this.basicEffectDescription ="basic mode: Deal 2 damage to 1 target on\n" +
                "your square.";
        this.secondaryEffectDescription ="in pulverize mode: Deal 3 damage to 1 target\n" +
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