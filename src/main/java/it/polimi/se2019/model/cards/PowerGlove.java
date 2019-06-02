package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

public class PowerGlove extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public PowerGlove() {
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'b';
        this.description ="basic mode: Choose 1 target on any square\n" +
                "exactly 1 move away. Move onto that square\n" +
                "and give the target 1 damage and 2 marks.\n"+
                "in rocket fist mode: Choose a square\n" +
                "exactly 1 move away. Move onto that square.\n" +
                "You may deal 2 damage to 1 target there.\n" +
                "If you want, you may move 1 more square in\n" +
                "that same direction (but only if it is a legal\n" +
                "move). You may deal 2 damage to 1 target\n" +
                "there, as well.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
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