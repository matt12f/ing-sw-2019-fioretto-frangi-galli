package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

public class Sledgehammer extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Sledgehammer() {
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        this.description ="basic mode: Deal 2 damage to 1 target on\n" +
                "your square.\n"+
                "in pulverize mode: Deal 3 damage to 1 target\n" +
                "on your square, then move that target 0, 1,\n" +
                "or 2 squares in one direction.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
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