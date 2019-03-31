package it.polimi.se2019.database;

public class Sledgehammer extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Sledgehammer() {
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