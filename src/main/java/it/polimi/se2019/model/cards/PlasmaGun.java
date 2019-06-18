package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
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
     * Deal 2 damage to 1 target you can see.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player){
        actions.addToPlayerTargetList(new ArrayList<>(ActionManager.visibleTargets(player)));
        actions.setMaxNumPlayerTargets(1);
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
     * Deal 1 additional damage to your target.
     */
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        actions.setMaxNumPlayerTargets(2);
    }
}