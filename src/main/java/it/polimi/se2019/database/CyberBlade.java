package it.polimi.se2019.database;

public class CyberBlade extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect ;
    private String basicEffectDescription;
    private String secondaryEffectDescription;
    private char secondaryEffectCost;
    private char thirdEffectCost;
    private String thirdEffectDescriprion;


    /**
     * constructor
     */
    public CyberBlade() {
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
    public String getSecondaryEffectDescription() {

        return "";
    }
    /**
     * @return
     */
    public char getSecondaryEffectCost() {

        return '0';
    }
    public void doSecondaryEffect() {
    }
    /**
     * @return
     */
    public String getThirdEffectDescription() {

        return "";
    }
    /**
     * @return
     */
    public char getThirdEffectCost() {
        return '0';
    }

    public void doThirdEffect() {

    }
}