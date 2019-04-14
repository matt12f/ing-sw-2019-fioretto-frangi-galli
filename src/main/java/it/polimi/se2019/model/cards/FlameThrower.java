package it.polimi.se2019.model.cards;

public class FlameThrower extends GunCard {


    private boolean hasAlternativeEffect;
    private char[] secondaryEffectCost;



    /**
     * constructor
     */
    public FlameThrower() {
        this.hasAlternativeEffect = true;

        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic mode: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage\n"+
                "in barbecue mode: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'y';
        secondaryEffectCost[1] = 'y';


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