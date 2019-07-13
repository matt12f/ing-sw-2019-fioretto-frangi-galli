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
        this.description ="basic effect: Deal 2 damage to 1 target on your square\nwith shadowstep: Move 1 square before or after the basic effect.\n" +
                "with slice and dice: Deal 2 damage to a different target on your square.\n" +
                "The shadowstep may be used before or after this effect.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';

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

        switch (effectsCombination.toString()){
            case "[Base]":targetsOfBaseEffect(currentController, actions, player);break;
            case "[Base, Optional2]":{
                targetsOfBaseEffect(currentController, actions, player);
                if(actions.getPlayersTargetList().size()>=2){
                    actions.setMaxNumPlayerTargets(2);
                    actions.setOfferableOpt2(true);
                }
                else
                    actions.setOfferableOpt2(false);
            }break;
            case "[Base, Optional2, Optional1]":{
                targetsOfBaseEffect(currentController, actions, player);
                if(actions.getPlayersTargetList().size()>=2){
                    actions.setMaxNumPlayerTargets(2);
                    actions.setOfferableOpt2(true);
                }
                else
                    actions.setOfferableOpt2(false);
                targetsOfSecondaryEffect(currentController, actions, player);
            }break;
            case "[Base, Optional1]":{
                targetsOfBaseEffect(currentController,actions, player);
                targetsOfSecondaryEffect(currentController, actions, player);
            } break;
            case "[Optional1, Base]":targetsOfTertiaryEffect(currentController, actions, player);break;
            case "[Optional1, Base, Optional2]":{
                targetsOfTertiaryEffect(currentController, actions, player);
                actions.setOfferableOpt2(enabler(actions));
            }break;
            case "[Base, Optional1, Optional2]":{
                targetsOfBaseEffect(currentController, actions, player);
                targetsOfSecondaryEffect(currentController, actions, player);
                actions.setOfferableOpt2(enabler(actions));
            }break;
            default:break;
        }

        actions.validate(effectsCombination);
        return actions;
    }

    /**
     * this is a custom method that determines if the secondary optional effect is offerable
     * @param actions is the container of the actions
     * @return if the secondary optional effect is offerable
     */
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
        else if(combination.equals("[Base, Optional1]")||combination.equals("[Base, Optional2, Optional1]")){
            applyBaseEffect(currentController, playersChoice);
            applySecondaryEffect(currentController,playersChoice);
        }
        else if(combination.equals("[Optional1, Base]")||combination.equals("[Optional1, Base, Optional2]"))
            applyTertiaryEffect(currentController, playersChoice);
        else if(combination.equals("[Base, Optional1, Optional2]"))
            applySpecial(currentController,playersChoice);
    }

    /**
     * this method applies only [Base, Optional1, Optional2]
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    private void applySpecial(Controller currentController, ChosenActions playersChoice) {
        //damage one target
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);
        //move
        applySecondaryEffect(currentController,playersChoice);
        //damage a second target
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,2,0);

    }

    /**
     * This applies the base/base+opt2 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice){
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);

        if(playersChoice.getOrderOfExecution().contains("Optional2") && playersChoice.getTargetsFromList1().get(1)!=null)
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(1),playersChoice,2,0);
    }

    /**
     * This applies the Optional1 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.movePlayer(currentController,currentController.getActiveTurn().getActivePlayer(),playersChoice.getCellToMoveYourself());
    }

    /**
     * This applies the Optional1 + Base/Base+Opt2 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        applySecondaryEffect(currentController,playersChoice);
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,2,0);

        if(playersChoice.getOrderOfExecution().contains("Optional2"))
            ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(1),playersChoice,2,0);
    }

    /**
     * Base effect description: Deal 2 damage to 1 target on your square.
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets = new ArrayList<>(Player.duplicateList(player.getPosition().getPlayers()));
        targets.remove(player.getCorrespondingPlayer());
        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableBase(false);

    }

    /**
     * Optional1 effect description: Move 1 square before or after the basic effect.
     *
     * adds to action: squares where you can move
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        for(NewCell cell: ActionManager.cellsOneMoveAway(currentController,player.getPosition())){
            actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,true,false);
        }
        actions.setCanMoveYourself(true);
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
    }

    /**
     * Optional2 effect description: Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect.
     *
     * adds to action: squares where you can move with target you can hit
     * @param currentController it the current controller of the game
     * @param actions is the container of the actions
     * @param player contains the attributes of the player to calculate the actions upon
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

    /**
     * method for deep cloning
     * @return a deep clone of the card
     */
    @Override
    public GunCard clone() {
        GunCard gunCard = new CyberBlade();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}