package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class GrenadeLauncher extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public GrenadeLauncher() {
        super();

        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Base");
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);

        ArrayList<String> thirdCombination=new ArrayList<>();
        thirdCombination.add("Optional1");
        thirdCombination.add("Base");
        this.effectsOrder.add(thirdCombination);

        this.numberOfOptional = 1;
        this.ammoCost = new char[1];
        ammoCost[0]= 'r';
        this.description ="basic effect: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.\n"+
                "with extra grenade: Deal 1 damage to every player on a square you can\n" +
                "see. You can use this before or after the basic effect's move.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    /**
     * Deal 1 damage to 1 target you can see. Then you may move the target 1 square.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(ActionManager.visibleTargets(currentController,player));
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);

        for(NewCell cell: ActionManager.cellsOneMoveAway(currentController, player.getPosition()))
            actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,false,true);

        actions.setCanMoveOpponent(true);
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);
    }

    /**
     * Deal 1 damage to every player on a square you can see. You can use this before or after the basic effect.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
       actions.addToTargetCells(new ArrayList<>(ActionManager.visibleSquares(currentController,player)));
    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new GrenadeLauncher();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}