package it.polimi.se2019.database;

public class GrenadeLauncher extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;
    private String basicEffectDescription;
    private char [] secondaryEffectCost ;
    private String secondaryEffectDescription;
    /**
     * constructor
     */
    public GrenadeLauncher() {
        this.numberOfOptional = 1;
        this.hasAlternativeEffect = false;
        this.id = 13;
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.basicEffectDescription ="basic effect: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.";
        this.secondaryEffectDescription ="the target 1 square.\n" +
                "with extra grenade: Deal 1 damage to every player on a square you can\n" +
                "see. You can use this before or after the basic effect's move.";
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