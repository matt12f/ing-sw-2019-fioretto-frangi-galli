package it.polimi.se2019.model.cards;

public class Electroscythe extends GunCard {


    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;



    /**
     * constructor
     */
    public Electroscythe() {

        this.hasAlternativeEffect = true;

        this.ammoCost = new char[1];
        ammoCost[0]= 'b';
        this.description ="basic mode: Deal 1 damage to every other player\n" +
                "on your square.\n"+
                "in reaper mode: Deal 2 damage to every other player\n" +
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

    /**
     * @return
     */
    public char[] getSecondaryEffectCost() {
        return secondaryEffectCost;
    }
    /**
     * @return
     */

}