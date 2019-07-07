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
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice){
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,3,0);
    }

    /**
     * Choose 1 target you cannot see and deal 3 damage to it.

     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
     void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //adds list of targets you cannot see
        actions.addToPlayerTargetList(ActionManager.notVisibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);
    }

    /**
     * This applies the third effect
     * @param currentController
     * @param playersChoice
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }

    /**
     * find the target for the effect
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }

    /**
     * This applies the second effect
     * @param currentController
     * @param playersChoice
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }

    /**
     * find the target for the effect
     * @param currentController
     * @param actions
     * @param player
     */
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