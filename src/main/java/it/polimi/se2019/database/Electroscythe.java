package it.polimi.se2019.database;

public class Electroscythe extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Electroscythe() {

        this.hasAlternativeEffect = true;
        this.id = 6;
        this.ammoCost = new char[1];
        ammoCost[0]= 'b';
        this.basicEffectDescription ="basic mode: Deal 1 damage to every other player\n" +
                "on your square.";
        this.secondaryEffectDescription ="in reaper mode: Deal 2 damage to every other player\n" +
                "on your square.";
        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'b';
        secondaryEffectCost[1] = 'r';
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
    public String getBasicEffectDescription() {

        return basicEffectDescription;
    }

    public void doBasicEffect() {

    }
    /**
     * @return
     */
    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
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