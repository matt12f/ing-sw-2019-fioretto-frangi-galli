package it.polimi.se2019.database;

public class Electroscythe extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Electroscythe() {
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