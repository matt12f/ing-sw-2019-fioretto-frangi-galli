package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

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
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice){
        //TODO scrivere codice
    }

    /**
     * Choose 1 target you cannot see and deal 3 damage to it.
     */
    @Override
     void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //adds list of targets you cannot see
        actions.addToPlayerTargetList(ActionManager.notVisibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);
    }

    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Heatseeker();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}