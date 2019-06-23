package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
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
        return null;
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {

    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {

    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {

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
        actions.setMaxDistanceOfMovement(1);
    }

    /**
     * Move 1 or 2 squares. This effect can be used either before or after the basic effect.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO se usato dopo effetto base: no problem

        //TODO se usato prima dell'effetto base: devo ricalcolare le possibilità
    }

    /**
     * During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.
     */
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableOpt2(false);
    }
}