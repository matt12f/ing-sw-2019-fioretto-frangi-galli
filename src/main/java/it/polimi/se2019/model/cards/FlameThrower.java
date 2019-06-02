package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

public class FlameThrower extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public FlameThrower() {
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic mode: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage\n"+
                "in barbecue mode: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'y';
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