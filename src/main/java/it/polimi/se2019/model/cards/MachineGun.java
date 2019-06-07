package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class MachineGun extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public MachineGun() {
        super();
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
    void applyTertiaryEffect(ChosenActions playersChoice) {

    }

    @Override
    void targetsOfTertiaryEffect() {

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
    void targetsOfBaseEffect() {

    }

    @Override
    void targetsOfSecondaryEffect() {

    }
}