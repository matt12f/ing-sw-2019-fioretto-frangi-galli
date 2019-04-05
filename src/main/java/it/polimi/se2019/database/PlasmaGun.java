package it.polimi.se2019.database;

public class PlasmaGun extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect ;
    private String basicEffectDescription;
    private String secondaryEffectDescription;
    private char[] secondaryEffectCost;
    private char[] thirdEffectCost;
    private String thirdEffectDescription;


    /**
     * constructor
     */
    public PlasmaGun() {
        this.numberOfOptional = 2;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0] = 'b';
        ammoCost[1]= 'y';
        this.basicEffectDescription ="basic effect: Deal 2 damage to 1 target you can see.";
        this.secondaryEffectDescription ="with phase glide: Move 1 or 2 squares. This effect can be\n" +
                "used either before or after the basic effect.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
        this.thirdEffectDescription ="with charged shot: Deal 1 additional damage to your\n" +
                "target.";
        this.thirdEffectCost = new char[1];
        thirdEffectCost[0] = 'b';
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
    public String getBasicEffectDescription() {

        return basicEffectDescription;
    }

    public void doBasicEffect() {
    }
    /**
     * @return
     */
    public String getSecondaryEffectDescription() {

        return secondaryEffectDescription;
    }
    /**
     * @return
     */
    public char[] getSecondaryEffectCost() {

        return secondaryEffectCost;
    }
    public void doSecondaryEffect() {
    }
    /**
     * @return
     */
    public String getThirdEffectDescription() {

        return thirdEffectDescription;
    }
    /**
     * @return
     */
    public char[] getThirdEffectCost() {
        return thirdEffectCost;
    }

    public void doThirdEffect() {

    }
}