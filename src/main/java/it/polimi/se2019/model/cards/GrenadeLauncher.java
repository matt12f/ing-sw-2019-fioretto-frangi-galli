package it.polimi.se2019.model.cards;

public class GrenadeLauncher extends GunCard {

    private int numberOfOptional;
    private boolean hasAlternativeEffect;
    private char [] secondaryEffectCost ;

    /**
     * constructor
     */
    public GrenadeLauncher() {
        this.numberOfOptional = 1;
        this.hasAlternativeEffect = false;

        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic effect: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.\n"+
                "the target 1 square.\n" +
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
    public char[] getSecondaryEffectCost() {
        return  secondaryEffectCost;
    }



}