package it.polimi.se2019.model.cards;

public class Shockwave extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Shockwave() {
        this.ammoCost = new char[1];
        ammoCost[0]= 'y';
        this.description ="basic mode: Choose up to 3 targets on\n" +
                "different squares, each exactly 1 move away.\n" +
                "Deal 1 damage to each target.\n"+
                "in tsunami mode: Deal 1 damage to all\n" +
                "targets that are exactly 1 move away";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'y';
    }
}