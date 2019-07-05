package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class LockRifle extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public LockRifle() {
        super();
        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Base");
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);

        this.numberOfOptional = 1;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'b';
        this.description ="<html>basic effect: Deal 2 damage and 1 mark to 1 target<br>you can see.\n" + "with second lock: Deal 1 mark to a different target<br>you can see.</html>";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;

    }

    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,1);
    }

    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(1),playersChoice,0,1);
    }


    /**
     * Deal 2 damage and 1 mark to 1 target you can see.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(ActionManager.visibleTargets(currentController,player));
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

    }

    /**
     * Deal 1 mark to a different target you can see.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if (actions.getPlayersTargetList().size() < 2)
            actions.setOfferableOpt1(false);
        else
            actions.setMaxNumPlayerTargets(2);
    }

    //useless methods
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new LockRifle();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}