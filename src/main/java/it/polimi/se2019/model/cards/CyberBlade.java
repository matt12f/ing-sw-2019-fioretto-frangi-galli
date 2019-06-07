package it.polimi.se2019.model.cards;


import it.polimi.se2019.controller.FictitiousPlayer;
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