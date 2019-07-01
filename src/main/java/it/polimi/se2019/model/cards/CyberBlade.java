package it.polimi.se2019.model.cards;


import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class CyberBlade extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public CyberBlade(){
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
        fourthCombination.add("Base");
        fourthCombination.add("Optional1");
        fourthCombination.add("Optional2");
        this.effectsOrder.add(fourthCombination);

        ArrayList<String> fifthCombination=new ArrayList<>();
        fifthCombination.add("Optional1");
        fifthCombination.add("Base");
        this.effectsOrder.add(fifthCombination);

        ArrayList<String> sixthCombination=new ArrayList<>();
        sixthCombination.add("Optional1");
        sixthCombination.add("Base");
        sixthCombination.add("Optional2");
        this.effectsOrder.add(sixthCombination);

        ArrayList<String> seventhCombination=new ArrayList<>();
        seventhCombination.add("Base");
        seventhCombination.add("Optional2");
        seventhCombination.add("Optional1");
        this.effectsOrder.add(seventhCombination);


        this.numberOfOptional = 2;

        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target on your square\n"+
                "with shadowstep: Move 1 square before or after the basic effect.\n"+
                "with slice and dice: Deal 2 damage to a different target on your square.\n" +
                "The shadowstep may be used before or after this effect.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

        this.tertiaryEffectCost = new char[1];
        tertiaryEffectCost[0] = 'y';
    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(Controller currentController, FictitiousPlayer player, ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions(effectsCombination,false);

        switch (effectsCombination.toString()){
            case "[Base]":targetsOfBaseEffect(currentController, actions, player);break;
            case "[Base, Optional1]":{
                targetsOfBaseEffect(currentController,actions, player);
                targetsOfSecondaryEffect(currentController, actions, player);
            } break;
            case "[Base, Optional2]":{
                targetsOfBaseEffect(currentController, actions, player);
                if(actions.getPlayersTargetList().size()<2)
                    actions.setOfferableOpt2(false);
            }break;
            case "[Base, Optional2, Optional1]":{
                targetsOfBaseEffect(currentController, actions, player);
                if(actions.getPlayersTargetList().size()<2)
                    actions.setOfferableOpt2(false);
                targetsOfSecondaryEffect(currentController, actions, player);
            }break;
            case "[Base, Optional1, Optional2]":{
                targetsOfBaseEffect(currentController, actions, player);
                targetsOfSecondaryEffect(currentController, actions, player);
                actions.setOfferableOpt2(enabler(actions));
            }break;
            case "[Optional1, Base]":targetsOfTertiaryEffect(currentController, actions, player);break;
            case "[Optional1, Base, Optional2]":{
                targetsOfTertiaryEffect(currentController, actions, player);
                actions.setOfferableOpt2(enabler(actions));
            }break;

            default:break;
        }

        actions.validate();
        return actions;
    }

    private boolean enabler(SingleEffectsCombinationActions actions) {
        boolean possibleOpt2=false;
        for(CellWithTargets cell:actions.getCellsWithTargets())
            if(cell.getTargets().size()>=2){
                possibleOpt2=true;
                cell.setMaxTargetsInCell(cell.getMaxTargetsInCell()+1);
                if(cell.getMinTargetsInCell()==0)
                    cell.setMinTargetsInCell(1);
            }
        return possibleOpt2;
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
     * Deal 2 damage to 1 target on your square.
     *
     * returns: a square with the targets there
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets = new ArrayList<>(Player.duplicateList(player.getPosition().getPlayers()));
        targets.remove(player.getCorrespondingPlayer());
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);
    }

    /**
     * Move 1 square before or after the basic effect.
     *
     * returns: squares where you can move
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell: ActionManager.cellsOneMoveAway(currentController,player.getPosition())){
            actions.addCellsWithTargets(cell,Player.duplicateList(cell.getPlayers()),0,0,true,false);
        }
        actions.setCanMoveYourself(true);
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
    }

    /**
     * Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect.
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell: ActionManager.cellsOneMoveAway(currentController,player.getPosition())){
            actions.addCellsWithTargets(cell,Player.duplicateList(cell.getPlayers()),1,1,true,false);
        }
        actions.setCanMoveYourself(true);
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new CyberBlade();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}