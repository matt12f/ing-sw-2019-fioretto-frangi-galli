package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.Player;
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

    /**
     * here the order of the effects is not important because there's only one effect to perform
     */
    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions();

        targetsOfBaseEffect(actions, player);

        actions.validate();
        return actions;
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice){
        //TODO scrivere codice
    }

    /** target: 1 (that you cannot see)
     *  damage: 3
     * @param actions
     * @param player
     */
    @Override
     void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //copies the whole player list
        ArrayList<Player> allPlayers=new ArrayList<>(AdrenalineServer.getMainController().getMainGameModel().getPlayerList());

        //adds list of targets you cannot see
        actions.addToTargetList1(allPlayers);
        actions.setMaxNumberOfTargetsList1(1);

        //removes the targets that you can see
        ActionManager.untouchableRemover(allPlayers, ActionManager.visibleTargets(player));

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