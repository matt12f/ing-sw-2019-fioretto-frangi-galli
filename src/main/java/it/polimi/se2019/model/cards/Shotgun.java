package it.polimi.se2019.model.cards;

public class Shotgun extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Shotgun() {
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'y';
        this.description ="basic mode: Deal 3 damage to 1 target on\n" +
                "your square. If you want, you may then move\n" +
                "the target 1 square.\n"+
                "in long barrel mode: Deal 2 damage to\n" +
                "1 target on any square exactly one move\n" +
                "away.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }
}