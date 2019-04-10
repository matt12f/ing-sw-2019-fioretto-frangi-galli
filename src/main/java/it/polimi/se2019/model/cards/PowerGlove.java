package it.polimi.se2019.model.cards;

public class PowerGlove extends GunCard {

    private String basicEffectDescription;
    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;
    private String secondaryEffectDescription;


    /**
     * constructor
     */
    public PowerGlove() {
        this.hasAlternativeEffect = true;

        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'b';
        this.basicEffectDescription ="basic mode: Choose 1 target on any square\n" +
                "exactly 1 move away. Move onto that square\n" +
                "and give the target 1 damage and 2 marks.";
        this.secondaryEffectDescription ="in rocket fist mode: Choose a square\n" +
                "exactly 1 move away. Move onto that square.\n" +
                "You may deal 2 damage to 1 target there.\n" +
                "If you want, you may move 1 more square in\n" +
                "that same direction (but only if it is a legal\n" +
                "move). You may deal 2 damage to 1 target\n" +
                "there, as well.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';

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