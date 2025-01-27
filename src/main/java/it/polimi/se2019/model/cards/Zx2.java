package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class Zx2 extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Zx2() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'r';
        this.description ="basic mode: Deal 1 damage and 2 marks to\n" +
                "1 target you can see.\n"+
                "in scanner mode: Choose up to 3 targets you\n" +
                "can see and deal 1 mark to each.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,1,2);
    }
    /**
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetsFromList1(),playersChoice,0,1);

    }

    /**
     * Deal 1 damage and 2 marks to 1 target you can see.
     *
     *
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets = new ArrayList<>(ActionManager.visibleTargets(currentController,player));
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

    }

    /**
     * Choose up to 3 targets you can see and deal 1 mark to each.
     *
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(currentController, actions, player);
        actions.setMaxNumPlayerTargets(3);
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Zx2();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}