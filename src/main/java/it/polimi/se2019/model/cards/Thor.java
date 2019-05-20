package it.polimi.se2019.model.cards;

public class Thor extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Thor() {
        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target you can see.\n"+
                "with chain reaction: Deal 1 damage to a second target that\n" +
                "your first target can see.\n"+
                "with high voltage: Deal 2 damage to a third target that\n" +
                "your second target can see. You cannot use this effect\n" +
                "unless you first use the chain reaction.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
        this.thirdEffectCost = new char[1];
        thirdEffectCost[0] = 'b';
    }
}