package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class Thor extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Thor() {
        super();

        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Base");
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);

        ArrayList<String> thirdCombination=new ArrayList<>();
        thirdCombination.add("Base");
        thirdCombination.add("Optional1");
        thirdCombination.add("Optional2");
        this.effectsOrder.add(thirdCombination);

        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'r';
        this.description ="basic effect: Deal 2 damage to 1 target you can see.\n"+
                "with chain reaction: Deal 1 damage to a second target that\n" +
                "your first target can see.\n"+
                "with high voltage: Deal 2 damage to a third target that\n" +
                "your second target can see. You cannot use this effect\n" +
                "unless you first use the chain reaction.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'b';
        this.tertiaryEffectCost = new char[1];
        tertiaryEffectCost[0] = 'b';
    }
    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);
    }
    /**
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(1),playersChoice,1,0);
    }
    /**
     * This applies the third effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(2),playersChoice,2,0);
    }

    /**
     * Deal 2 damage to 1 target you can see.
     */
    /**
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        //here we'll fill the list of visible players
        actions.addToPlayerTargetList(ActionManager.visibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);
        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableBase(false);

    }

    /**
     * Deal 1 damage to a second target that your first target can see.
     */
    /**
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        //here I'm filling a list with every player and the targets they can see
        for(Player player1: currentController.getMainGameModel().getPlayerList())
            actions.addPlayersWithTargets(currentController,player1);

        //these are the player with targets available
        ArrayList<Player> playerWTAvailable=new ArrayList<>();
        for(PlayerWithTargets target:actions.getPlayersWithTargets())
            playerWTAvailable.add(target.getTarget());

        //removes the players that can't see other players
        actions.getPlayersTargetList().removeIf(target ->!playerWTAvailable.contains(target));

        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableOpt1(false);
    }

    /**
     * Deal 2 damage to a third target that your second target can see. You cannot use this effect unless you first use the chain reaction.
     */
    /**
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if(actions.isOfferableOpt1()){ //I already have a two-link chain of targets available
            ArrayList<PlayerWithTargets> playersToRemove=new ArrayList<>();
            for(PlayerWithTargets playerWT: actions.getPlayersWithTargets()){
                for(Player target: playerWT.getTargetsItCanSee())
                    if(!itIsInTheList(target, actions.getPlayersWithTargets()))
                        playersToRemove.add(playerWT);
            }
            actions.getPlayersWithTargets().removeAll(playersToRemove);

            if(actions.getPlayersWithTargets().isEmpty())
                actions.setOfferableOpt2(false);

        }else
            actions.setOfferableOpt2(false);
    }

    /**
     * checks if a Player is in the list of targets with targets
     *
     */
    /**
     *
     * @param target designed player to inflict the effect
     * @param list  list of players
     * @return
     */
    private boolean itIsInTheList(Player target,ArrayList<PlayerWithTargets> list){
        for(PlayerWithTargets playerWithTargets:list)
            if(playerWithTargets.getTarget().equals(target))
                return true;
        return false;
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Thor();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}