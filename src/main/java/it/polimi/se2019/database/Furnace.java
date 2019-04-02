package it.polimi.se2019.database;

public class Furnace extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Furnace() {
        this.hasAlternativeEffect = true;
        this.id = 9;
        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'b';
        this.basicEffectDescription ="basic mode: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.";
        this.secondaryEffectDescription ="in cozy fire mode: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

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