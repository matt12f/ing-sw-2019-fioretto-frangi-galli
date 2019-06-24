package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class RocketLauncher extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public RocketLauncher() {
        super();

        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Base");
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);

        ArrayList<String> thirdCombination=new ArrayList<>();
        thirdCombination.add("Base");
        thirdCombination.add("Optional2");
        this.effectsOrder.add(thirdCombination);

        ArrayList<String> fourthCombination=new ArrayList<>();
        fourthCombination.add("Optional1");
        fourthCombination.add("Base");
        this.effectsOrder.add(fourthCombination);

        ArrayList<String> fifthCombination=new ArrayList<>();
        fifthCombination.add("Base");
        fifthCombination.add("Optional2");
        fifthCombination.add("Optional1");
        this.effectsOrder.add(fifthCombination);

        ArrayList<String> sixthCombination=new ArrayList<>();
        sixthCombination.add("Optional1");
        sixthCombination.add("Base");
        sixthCombination.add("Optional2");
        this.effectsOrder.add(sixthCombination);


        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target you can see that is not on your\n" +
                "square. Then you may move the target 1 square\n"+
                "with rocket jump: Move 1 or 2 squares. This effect can be used either\n" +
                "before or after the basic effect.\n"+
                "with fragmenting warhead: During the basic effect, deal 1 damage to\n" +
                "every player on your target's original square – including the target,\n" +
                "even if you move it.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
        this.tertiaryEffectCost = new char[1];
        tertiaryEffectCost[0] = 'y';
    }


    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions();

        if(effectsCombination.toString().equals("[Base]")||effectsCombination.toString().equals("[Base, Optional2]"))
            targetsOfBaseEffect(actions,player);
        else if(effectsCombination.toString().equals("[Base, Optional1]")||effectsCombination.toString().equals("[Base, Optional1, Optional2]")) {
            targetsOfBaseEffect(actions,player);
            targetsOfSecondaryEffect(actions,player);
        }else if(effectsCombination.toString().equals("[Optional1, Base]")||effectsCombination.toString().equals("[Optional1, Base, Optional2]"))
                targetsOfTertiaryEffect(actions,player);

        actions.validate();
        return actions;
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    /**
     * Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(ActionManager.visibleTargets(player));
        targets.removeAll(player.getPosition().getPlayers());

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);

        actions.setCanMoveOpponent(true);
        for(NewCell cell: ActionManager.cellsOneMoveAway(player.getPosition()))
            actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,false,true);
    }

    /**
     * Move 1 or 2 squares. This effect can be used either before or after the basic effect.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell:MapManager.squaresInRadius2(player))
            actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,true,false);
        actions.setCanMoveYourself(true);
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);
    }

    /**
     * During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.
     *
     * actually returns: cells where you can move & hit a target + cells where you can move the target after you've hit it
     */
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell:MapManager.squaresInRadius2(player)){
            ArrayList<Player> targets = new ArrayList<>(ActionManager.visibleTargets(cell));
            targets.removeAll(cell.getPlayers());
            actions.addCellsWithTargets(cell, targets, 1, 1, true, false);
        }

        for(CellWithTargets cellWithTarget: actions.getCellsWithTargets()){
            for(NewCell cell: ActionManager.cellsOneMoveAway(cellWithTarget.getTargetCell()))
                actions.addCellsWithTargets(cell, new ArrayList<>(),0,0,false,true);
        }
        actions.setCanMoveYourself(true);
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);

    }
}