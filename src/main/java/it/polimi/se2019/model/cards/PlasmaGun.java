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
     * manages Base and Base + Opt2 combinations
     *
     * @param currentController the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        if(playersChoice.getOrderOfExecution().contains("Optional2"))
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,3,0);
        else
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);

    }

    /**
     * this applies the base/base+opt2 + opt 1
     *
     *
     * @param currentController the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        applyBaseEffect(currentController,playersChoice);
        ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getCellToMoveYourself());
    }

    /**
     * this applies the opt1 + base/base+opt2
     *
     *
     *
     * @param currentController the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getCellToMoveYourself());
        if(playersChoice.getOrderOfExecution().contains("Optional2"))
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,3,0);
        else
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,2,0);
    }

    /**
     * Base effect description: Deal 2 damage to 1 target you can see.
     *
     * adds to actions: normal list of players you can see
     *
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        actions.addToPlayerTargetList(ActionManager.visibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableBase(false);

    }

    /**
     * Optional1 effect description: Move 1 or 2 squares. This effect can be used either before or after the basic effect.
     *
     * adds to actions: squares 1 e 2 moves away without targets (where you can move)
     *
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
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
     * Optional2 effect description: Deal 1 additional damage to your target.
     *
     * adds to actions: square 1 e 2 moves away (where you can move) containing the targets visible from there
     *
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
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

    /**
     * method for deep cloning
     * @return a deep clone of the card
     */
    @Override
    public GunCard clone() {
        GunCard gunCard = new PlasmaGun();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }

}