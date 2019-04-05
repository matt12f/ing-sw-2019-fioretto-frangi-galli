package it.polimi.se2019.database;

public class MachineGun extends GunCard {

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
    public MachineGun() {
        this.numberOfOptional = 2;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'r';
        this.basicEffectDescription ="basic effect: Choose 1 or 2 targets you can see and deal\n" +
                "1 damage to each.";
        this.secondaryEffectDescription ="with focus shot: Deal 1 additional damage to one of those\n" +
                "targets.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'y';
        this.thirdEffectDescription ="with turret tripod: Deal 1 additional damage to the other\n" +
                "of those targets and/or deal 1 damage to a different target\n" +
                "you can see.";
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