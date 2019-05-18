package it.polimi.se2019.model.cards;

public class TractorBeam extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public TractorBeam() {
        this.ammoCost = new char[1];
        ammoCost[0]= 'b';
        this.description ="basic mode: Move a target 0, 1, or 2 squares to a square\n" +
                "you can see, and give it 1 damage.\n"+
                "in punisher mode: Choose a target 0, 1, or 2 moves away\n" +
                "from you. Move the target to your square\n" +
                "and deal 3 damage to it.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'r';
        secondaryEffectCost[1] = 'y';
    }
}