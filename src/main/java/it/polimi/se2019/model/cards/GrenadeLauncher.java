package it.polimi.se2019.model.cards;

public class GrenadeLauncher extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public GrenadeLauncher() {
        this.numberOfOptional = 1;
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic effect: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.\n"+
                "the target 1 square.\n" +
                "with extra grenade: Deal 1 damage to every player on a square you can\n" +
                "see. You can use this before or after the basic effect's move.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;
    }
}