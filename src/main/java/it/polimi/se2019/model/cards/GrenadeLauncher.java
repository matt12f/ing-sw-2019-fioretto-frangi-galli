package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        this.description ="basic effect: Deal 1 damage to 1 target you can see. Then you may move the target 1 square."+
                "\nwith extra grenade: Deal 1 damage to every player on a square you can see. \nYou can use this before or after the basic effect's move.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice){
        Player target=playersChoice.getTargetsFromList1().get(0);
        ActionManager.giveDmgandMksToOnePlayer(currentController,target,playersChoice,1,0);
        //it chose to move the player
        if(playersChoice.getCellToMoveOpponent()!=null && !currentController.getMainGameModel().getDeadPlayers().contains(target))
            ActionManager.movePlayer(currentController,target,playersChoice.getCellToMoveOpponent());
    }
    /**
     * This applies the optional1 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetCell().getPlayers(),playersChoice,1,0);
    }

    /**
     * Deal 1 damage to 1 target you can see. Then you may move the target 1 square.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(ActionManager.visibleTargets(currentController,player));
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);


        if(!targets.isEmpty()){
            ArrayList<NewCell> cellsToMoveTargetOn=new ArrayList<>();

            for(Player target: actions.getPlayersTargetList())
                    cellsToMoveTargetOn.addAll(ActionManager.cellsOneMoveAway(currentController,target.getFigure().getCell()));

            List<NewCell> cellsOk= cellsToMoveTargetOn.stream().distinct().collect(Collectors.toList());

            for(NewCell cell: cellsOk)
                actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,false,true);

        actions.setCanMoveOpponent(true);
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);
        }else
            actions.setOfferableBase(false);
    }

    /**
     * Deal 1 damage to every player on a square you can see. You can use this before or after the basic effect.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
       actions.addToTargetCells(new ArrayList<>(ActionManager.visibleSquares(currentController,player)));
       if(actions.getTargetCells().isEmpty())
           actions.setOfferableOpt1(false);
    }

    /**
     * This applies the optional2 effect
     * @param currentController
     * @param playersChoice
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }

    /**
     * find the target for theoptional 2
     * @param currentController
     * @param actions
     * @param player
     */
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