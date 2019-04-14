package it.polimi.se2019.model.cards;

public class RocketLauncher extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect ;

    private char[] secondaryEffectCost;
    private char[] thirdEffectCost;



    /**
     * constructor
     */
    public RocketLauncher() {
        this.numberOfOptional = 2;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target you can see that is not on your\n" +
                "square. Then you may move the target 1 square\n"+
                "with rocket jump: Move 1 or 2 squares. This effect can be used either\n" +
                "before or after the basic effect.\n"+
                "with fragmenting warhead: During the basic effect, deal 1 damage to\n" +
                "every player on your target's original square – including the target,\n" +
                "even if you move it.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
        this.thirdEffectCost = new char[1];
        thirdEffectCost[0] = 'y';
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
    public char[] getSecondaryEffectCost() {

        return secondaryEffectCost;
    }

    /**
     * @return
     */
    public char[] getThirdEffectCost() {
        return thirdEffectCost;
    }


}