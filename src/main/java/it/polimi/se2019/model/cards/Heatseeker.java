package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.Player;

public class Heatseeker extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Heatseeker() {
        this.effectsOrder= new String[][]{{"Base"}};
        this.numberOfOptional = 0;
        this.ammoCost = new char[3];
        ammoCost[0] = 'r';
        ammoCost[1]= 'r';
        ammoCost[2]= 'y';
        this.description ="effect: Choose 1 target you cannot see and deal 3 damage to it.";
        this.secondaryEffectCost=null;
        this.tertiaryEffectCost =null;
    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(String[] effectsCombination, Player player) throws UnavailableEffectCombinationException {
        //TODO scrivere codice
        return null;
    }

    @Override
    void applyBaseEffect(ChosenAction playersChoice) {
        //TODO scrivere codice
    }

    /** target: 1 (that you cannot see)
     *  damage: 3
     */
    @Override
    void targetsOfBaseEffect() {
        //TODO scrivere codice

    }

    @Override
    void applyTertiaryEffect(ChosenAction playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect() {
        throw new UnsupportedOperationException();
    }
    @Override
    void applySecondaryEffect(ChosenAction playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfSecondaryEffect() {
        throw new UnsupportedOperationException();
    }
}