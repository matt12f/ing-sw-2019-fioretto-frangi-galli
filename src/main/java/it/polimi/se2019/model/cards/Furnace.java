package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

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