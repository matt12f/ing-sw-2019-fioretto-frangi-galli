package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class PlasmaGun extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public PlasmaGun() {
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

        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0] = 'b';
        ammoCost[1]= 'y';
        this.description ="basic effect: Deal 2 damage to 1 target you can see.\n"+
                "with phase glide: Move 1 or 2 squares. This effect can be\n" +
                "used either before or after the basic effect.\n"+
                "with charged shot: Deal 1 additional damage to your\n" +
                "target.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

        this.tertiaryEffectCost = new char[1];
        tertiaryEffectCost[0] = 'b';
    }

    /**
     * here the target methods are used in a different way than the actions of the optional actions
     */
    @Override
    public SingleEffectsCombinationActions buildAvailableActions(Controller currentController, FictitiousPlayer player, ArrayList<String> effectsCombination) throws UnavailableEffectCombinationException {
        SingleEffectsCombinationActions actions=new SingleEffectsCombinationActions(effectsCombination.toString());
        actions.setOfferableOpt2(false);

        switch (effectsCombination.toString()){
            case "[Base]":targetsOfBaseEffect(currentController, actions, player);break;
            case "[Optional1, Base]": targetsOfTertiaryEffect(currentController, actions, player);break;
            case "[Base, Optional1]":{
                targetsOfBaseEffect(currentController, actions, player);
                targetsOfSecondaryEffect(currentController, actions, player);
            } break;
            case "[Base, Optional1, Optional2]":{
                targetsOfBaseEffect(currentController, actions, player);
                targetsOfSecondaryEffect(currentController, actions, player);
                actions.setOfferableOpt2(true);
            }break;
            case "[Base, Optional2]":{
                targetsOfBaseEffect(currentController, actions, player);
                actions.setOfferableOpt2(true);
            }break;
            case "[Optional1, Base, Optional2]":{
                targetsOfTertiaryEffect(currentController, actions, player);
                actions.setOfferableOpt2(true);
            }break;
            default:break;
        }

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
     * Deal 2 damage to 1 target you can see.
     *
     * actually returns: 1 square with your position and the targets visible from there
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        actions.addToPlayerTargetList(ActionManager.visibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(1);
    }

    /**
     * Move 1 or 2 squares. This effect can be used either before or after the basic effect.
     *
     * actually returns: squares 1 e 2 moves away without targets (where you can move)
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell: MapManager.squaresInRadius2(currentController,player)){
            actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,true,false);
        }
        actions.setCanMoveYourself(true);
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
    }

    /**
     * Deal 1 additional damage to your target.
     *
     * actually returns: square 1 e 2 moves away (where you can move) containing the targets visible from there
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cellToAddTargets: MapManager.squaresInRadius2(currentController,player)){
            actions.addCellsWithTargets(cellToAddTargets,ActionManager.visibleTargets(currentController,cellToAddTargets),1,1,true,false);
        }
        actions.setCanMoveYourself(true);
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
        }



}