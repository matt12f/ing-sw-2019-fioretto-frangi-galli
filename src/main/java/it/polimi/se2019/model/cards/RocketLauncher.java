package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
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

    /**
     * This method was overridden to accomodate the weirdness of the card
     *
     * @param currentController it the current controller of the game
     * @param effectsCombination is a specific combination of the possible effects
     * @param player contains the attributes of the player to calculate the actions upon
     * @return available usages of the card for a certain combination
     * @throws UnavailableEffectCombinationException if the combination has no targets
     */
    @Override
    public SingleEffectsCombinationActions buildAvailableActions(Controller currentController, FictitiousPlayer player, ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions(effectsCombination);

        if(effectsCombination.toString().equals("[Base]")||effectsCombination.toString().equals("[Base, Optional2]"))
            targetsOfBaseEffect(currentController, actions, player);
        else if(effectsCombination.toString().equals("[Base, Optional1]")||effectsCombination.toString().equals("[Base, Optional1, Optional2]")) {
            targetsOfBaseEffect(currentController, actions, player);
            targetsOfSecondaryEffect(currentController, actions, player);
        }else if(effectsCombination.toString().equals("[Optional1, Base]")||effectsCombination.toString().equals("[Optional1, Base, Optional2]"))
                targetsOfTertiaryEffect(currentController, actions, player);

        actions.validate(effectsCombination);
        return actions;
    }

    /**
     * This was overridden because it needs a special management, given that the card has "weird" combinations
     * of the effects
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    public void applyEffects(Controller currentController, ChosenActions playersChoice){
        String combination=playersChoice.getOrderOfExecution().toString();
        if(combination.equals("[Base]")||combination.equals("[Base, Optional2]"))
            applyBaseEffect(currentController, playersChoice);
        else if(combination.equals("[Base, Optional1]")||combination.equals("[Base, Optional1, Optional2]"))
            applySecondaryEffect(currentController, playersChoice);
        else if(combination.equals("[Optional1, Base]")||combination.equals("[Optional1, Base, Optional2]"))
            applyTertiaryEffect(currentController, playersChoice);
    }


    /**
     * This applies the base/base+opt2 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);

        //this manages the Base + Opt2 combination (you must damage everyone before moving the single target)
        if(playersChoice.getOrderOfExecution().contains("Optional2"))
            applyThird(currentController,playersChoice,playersChoice.getTargetsFromList1().get(0).getFigure().getCell());

        if(playersChoice.getCellToMoveOpponent()!=null)
            ActionManager.movePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice.getCellToMoveOpponent());
    }

    /**
     * this applies the base/base+Opt2 and moves you after that

     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        applyBaseEffect(currentController, playersChoice);

        ActionManager.movePlayer(currentController, currentController.getActiveTurn().getActivePlayer(), playersChoice.getCellToMoveYourself());
    }

    /**
     * you will have selected cells where you want move and hit a target + cells where you can move the target after you've hit it
     *
     * This applies the tertiary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        //you first move, then hit a target and then move the target
        ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getCellToMoveYourself());
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,2,0);

        //this manages the Opt1 + Base + Opt2 combination (you must damage everyone before moving the single target)
        if(playersChoice.getOrderOfExecution().contains("Optional2"))
            applyThird(currentController,playersChoice,playersChoice.getTargetsFromCell().get(0).getFigure().getCell());

        if(playersChoice.getCellToMoveOpponent()!=null)
            ActionManager.movePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice.getCellToMoveOpponent());

    }

    /**
     * this method applies only the third effect
     *
     *
     * @param currentController
     * @param playersChoice
     * @param cell
     */
    private void applyThird(Controller currentController, ChosenActions playersChoice, NewCell cell) {
        ActionManager.giveDmgandMksToPlayers(currentController,cell.getPlayers(),playersChoice,1,0);
    }

    /**
     * Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square.
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets = new ArrayList<>(ActionManager.visibleTargets(currentController, player));
        targets.removeAll(player.getPosition().getPlayers());

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

        if (actions.getPlayersTargetList().isEmpty())
            actions.setOfferableBase(false);
        else{
            actions.setCanMoveOpponent(true);
            for (NewCell cell : ActionManager.cellsOneMoveAway(currentController, player.getPosition()))
                actions.addCellsWithTargets(cell, new ArrayList<>(), 0, 0, false, true);

        }
    }

    /**
     * Move 1 or 2 squares. This effect can be used either before or after the basic effect.
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell:MapManager.squaresInRadius2(currentController,player))
            actions.addCellsWithTargets(cell, new ArrayList<>(),0,0,true,false);
        actions.setCanMoveYourself(true);
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);
    }

    /**
     * During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.
     *
     * adds to actions: cells where you can move and hit a target + cells where you can move the target after you've hit it
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell:MapManager.squaresInRadius2(currentController,player)){
            ArrayList<Player> targets = new ArrayList<>(ActionManager.visibleTargets(currentController,cell));
            targets.removeAll(cell.getPlayers());
            targets.remove(player.getCorrespondingPlayer());
            if (!targets.isEmpty())
                actions.addCellsWithTargets(cell, targets, 1, 1, true, false);
        }

        if(actions.getCellsWithTargets().isEmpty()){
            actions.setOfferableOpt1(false);
            actions.setOfferableBase(false);
            actions.setOfferableOpt2(false);
        }

        ArrayList<CellWithTargets> temp=new ArrayList<>();
        for(CellWithTargets cellWithTarget: actions.getCellsWithTargets()){
            for(NewCell cell: ActionManager.cellsOneMoveAway(currentController,cellWithTarget.getTargetCell()))
                temp.add(new CellWithTargets(cell, new ArrayList<>(),0,0,false,true));
        }

        actions.addCellsWithTargets(temp);

        actions.setCanMoveYourself(true);
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);

    }

    /**
     * method for deep cloning
     * @return a deep clone of the card
     */
    @Override
    public GunCard clone() {
        GunCard gunCard = new RocketLauncher();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}