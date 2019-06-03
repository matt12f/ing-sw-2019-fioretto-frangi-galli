package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class PlasmaGun extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public PlasmaGun() {
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
    void applyTertiaryEffect(ChosenAction playersChoice) {

    }

    @Override
    void targetsOfTertiaryEffect() {

    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        return null;
    }

    @Override
    void applyBaseEffect(ChosenAction playersChoice) {

    }

    @Override
    void applySecondaryEffect(ChosenAction playersChoice) {

    }

    @Override
    void targetsOfBaseEffect() {

    }

    @Override
    void targetsOfSecondaryEffect() {

    }
}