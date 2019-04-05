package it.polimi.se2019.database;

public class LockRifle extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;
    private String basicEffectDescription;
    private char [] secondaryEffectCost ;
    private String secondaryEffectDescription;
   /**
     * constructor
     */
    public LockRifle() {

        this.numberOfOptional = 1;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'b';
        this.basicEffectDescription ="basic effect: Deal 2 damage and 1 mark to 1 target\n" +
                "you can see.";
        this.secondaryEffectDescription ="with second lock: Deal 1 mark to a different target\n" +
                "you can see.";
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
    public char[] getSecondaryEffectCost() {
        return  secondaryEffectCost;
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