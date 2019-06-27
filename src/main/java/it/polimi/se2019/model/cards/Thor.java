package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

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
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        //here we'll fill the list of visible players
        actions.addToPlayerTargetList(ActionManager.visibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(1);
    }

    /**
     * Deal 1 damage to a second target that your first target can see.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player){
        //here I'm filling a list with every player and the targets they can see
        for(Player player1: currentController.getMainGameModel().getPlayerList())
            actions.addPlayersWithTargets(currentController,player1);
    }

    /**
     * Deal 2 damage to a third target that your second target can see. You cannot use this effect unless you first use the chain reaction.
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //custom management in view
    }
}