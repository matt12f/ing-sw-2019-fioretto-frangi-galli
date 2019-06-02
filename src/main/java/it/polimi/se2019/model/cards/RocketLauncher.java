package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

public class RocketLauncher extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public RocketLauncher() {
        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target you can see that is not on your\n" +
                "square. Then you may move the target 1 square\n"+
                "with rocket jump: Move 1 or 2 squares. This effect can be used either\n" +
                "before or after the basic effect.\n"+
                "with fragmenting warhead: During the basic effect, deal 1 damage to\n" +
                "every player on your target's original square – including the target,\n" +
                "even if you move it.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
        this.tertiaryEffectCost = new char[1];
        tertiaryEffectCost[0] = 'y';
    }

    @Override
    void applyTertiaryEffect(ChosenAction playersChoice) {

    }

    @Override
    void targetsOfTertiaryEffect() {

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