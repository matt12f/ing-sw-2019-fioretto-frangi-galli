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

    }
    /**
     * @return
     */
    public boolean getHasAlternativeEffect() {
        return false;
    }
    /**
     * @return
     */
    public int getNumberOfOptional() {
        return 0;
    }
    /**
     * @return
     */
    public String getBasicEffectDescription() {
        return "";
    }

    public void doBasicEffect() {
    }
    /**
     * @return
     */
    public char getSecondaryEffectCost() {
        return '0';
    }

    /**
     * @return
     */
    public String getSecondaryEffectDescription() {
        return "";
    }
    public void doSecondaryEffect() {

    }


}