package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class MachineGun extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public MachineGun() {
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

        this.numberOfOptional = 2;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'r';
        this.description ="basic effect: Choose 1 or 2 targets you can see and deal\n" +
                "1 damage to each.\n"+"with focus shot: Deal 1 additional damage to one of those\n" +
                "targets.\n"+"with turret tripod: Deal 1 additional damage to the other\n" +
                "of those targets and/or deal 1 damage to a different target\n" +
                "you can see.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'y';
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
     * Choose 1 or 2 targets you can see and deal 1 damage to each.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        actions.addToPlayerTargetList(ActionManager.visibleTargets(currentController,player));
        actions.setMaxNumPlayerTargets(2);
    }

    /**
     * Deal 1 additional damage to one of those targets.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableOpt1(false);
    }

    /**
     * Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see.
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        actions.setOfferableOpt2(true);
        actions.setSameListDifferentTarget(true); //In this case it's from the original list

        actions.setOfferableExtra(true);
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new MachineGun();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}