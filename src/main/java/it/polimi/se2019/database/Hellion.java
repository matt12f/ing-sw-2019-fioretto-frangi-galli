package it.polimi.se2019.database;

public class Hellion extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public Hellion() {
        this.hasAlternativeEffect = true;
        this.id = 11;
        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'y';
        this.basicEffectDescription ="basic mode: Deal 1 damage to 1 target you can see at least\n" +
                "1 move away. Then give 1 mark to that target and everyone\n" +
                "else on that square.";
        this.secondaryEffectDescription ="in nano-tracer mode: Deal 1 damage to 1 target you can\n" +
                "see at least 1 move away. Then give 2 marks to that target\n" +
                "and everyone else on that square.";
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