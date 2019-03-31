package it.polimi.se2019.database;

public class Furnace extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Furnace() {
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
    public String getBasicEffectDescription() {

        return "";
    }

    public void doBasicEfect() {

    }
    /**
     * @return
     */
    public char getSecondaryEfectCost() {
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