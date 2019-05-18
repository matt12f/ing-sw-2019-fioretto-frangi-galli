package it.polimi.se2019.model.cards;

public class Heatseeker extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Heatseeker() {
        this.numberOfOptional = 0;
        this.ammoCost = new char[3];
        ammoCost[0] = 'r';
        ammoCost[1]= 'r';
        ammoCost[2]= 'y';
        this.description ="effect: Choose 1 target you cannot see\n"+
                "and deal 3 damage to it.";
        this.secondaryEffectCost=null;
        this.thirdEffectCost=null;
    }
}