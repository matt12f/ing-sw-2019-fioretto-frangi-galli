package it.polimi.se2019.model.cards;

public class Furnace extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Furnace() {
        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'b';
        this.description ="basic mode: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.\n"+
                "in cozy fire mode: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

    }
}