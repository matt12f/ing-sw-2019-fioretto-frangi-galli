package it.polimi.se2019.model.cards;

public class Hellion extends GunCard {


    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;



    /**
     * constructor
     */
    public Hellion() {
        this.hasAlternativeEffect = true;

        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'y';
        this.description ="basic mode: Deal 1 damage to 1 target you can see at least\n" +
                "1 move away. Then give 1 mark to that target and everyone\n" +
                "else on that square.\n"+
                "in nano-tracer mode: Deal 1 damage to 1 target you can\n" +
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
    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }

}