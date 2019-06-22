package it.polimi.se2019.model.cards;


import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
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
     * Deal 2 damage to 1 target on your square.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>(player.getPosition().getPlayers());
        targets.remove(player.getCorrespondingPlayer());

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
    }

    /**
     * Move 1 square before or after the basic effect.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO se usato dopo effetto base: no problem

        //TODO se usato prima dell'effetto base: devo ricalcolare le possibilità
    }

    /**
     * Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect.
     */
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        actions.setSameListDifferentTarget(true);
        //TODO basta scegliere di usare l'effetto (se ci sono altri target sul tuo square)

    }
}