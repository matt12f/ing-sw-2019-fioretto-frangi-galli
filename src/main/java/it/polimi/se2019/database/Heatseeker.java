package it.polimi.se2019.database;

public class Heatseeker extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;
    private String basicEffectDescription;

   /**
     * constructor
     */
    public Heatseeker() {

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



}