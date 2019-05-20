package it.polimi.se2019.model.cards;

public class Railgun extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Railgun() {
        this.ammoCost = new char[3];
        ammoCost[0]= 'y';
        ammoCost[1]= 'y';
        ammoCost[2]= 'b';
        this.description ="basic mode: Choose a cardinal direction and 1 target in that direction.\n" +
                "Deal 3 damage to it.\n"+
                "in piercing mode: Choose a cardinal direction and 1 or 2 targets in that\n" +
                "direction. Deal 2 damage to each.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }
}