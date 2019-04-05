package it.polimi.se2019.database;

public class Thor extends GunCard {

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
    public Thor() {
        this.numberOfOptional = 2;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'r';
        this.basicEffectDescription ="basic effect: Deal 2 damage to 1 target you can see.";
        this.secondaryEffectDescription ="with chain reaction: Deal 1 damage to a second target that\n" +
                "your first target can see.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
        this.thirdEffectDescription ="with high voltage: Deal 2 damage to a third target that\n" +
                "your second target can see. You cannot use this effect\n" +
                "unless you first use the chain reaction.";
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