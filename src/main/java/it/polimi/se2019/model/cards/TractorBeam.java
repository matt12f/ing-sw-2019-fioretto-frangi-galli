package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

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

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(String[] effectsCombination, Player player) throws UnavailableEffectCombinationException {
        return null;
    }

    @Override
    void applyBaseEffect(ChosenAction playersChoice) {

    }

    @Override
    void applySecondaryEffect(ChosenAction playersChoice) {

    }

    @Override
    void targetsOfBaseEffect() {

    }

    @Override
    void targetsOfSecondaryEffect() {

    }
}