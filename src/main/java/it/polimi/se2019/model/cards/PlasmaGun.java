package it.polimi.se2019.model.cards;

public class PlasmaGun extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public PlasmaGun() {
        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0] = 'b';
        ammoCost[1]= 'y';
        this.description ="basic effect: Deal 2 damage to 1 target you can see.\n"+
                "with phase glide: Move 1 or 2 squares. This effect can be\n" +
                "used either before or after the basic effect.\n"+
                "with charged shot: Deal 1 additional damage to your\n" +
                "target.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

        this.thirdEffectCost = new char[1];
        thirdEffectCost[0] = 'b';
    }
}