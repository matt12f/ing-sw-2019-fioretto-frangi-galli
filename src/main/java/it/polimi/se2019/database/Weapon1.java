package it.polimi.se2019.database;

public class Weapon1 extends GunCard {
    /**
     * Default constructor
     */
    public Weapon1() {
    }
    private int numberOfOptional;
    private boolean hasAlternativeEffect;
    private char thirdEffectCost;
    private char secondaryEffectCost;
    private String thirdEffectDescriprion;
    private String secondaryEffectDescription;
    private String basicEffectDescription;
    public void doThirdEffect() {
    }

    /**
     * @return
     */
    public String getThirdEffectDescription() {
        return "";
    }
    public void doSecondaryEffect() {
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

    /**
     * @return
     */
    public boolean getHasAlternativeEffect() {
        return false;
    }
    public void doBasicEfect() {
    }

    /**
     * @return
     */
    public String getBasicEffectDescription() {
        return "";
    }
    public void Weapon1() {
    }
}