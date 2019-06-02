package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

public class LockRifle extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public LockRifle() {
        this.numberOfOptional = 1;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'b';
        this.description ="basic effect: Deal 2 damage and 1 mark to 1 target\n" +
                "you can see.\n" + "with second lock: Deal 1 mark to a different target\n" +
                "you can see.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;

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