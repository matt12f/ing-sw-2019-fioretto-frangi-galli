package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class Heatseeker extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Heatseeker() {
        super();
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
    void applyBaseEffect(ChosenActions playersChoice){
        //TODO scrivere codice
    }

    /**
     * Choose 1 target you cannot see and deal 3 damage to it.
     */
    @Override
     void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //adds list of targets you cannot see
        actions.addToPlayerTargetList(ActionManager.notVisibleTargets(player));
        actions.setMaxNumPlayerTargets(1);
    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }
    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }
}