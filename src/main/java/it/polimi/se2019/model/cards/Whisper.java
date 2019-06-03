package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenAction;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class Whisper extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Whisper() {
        this.effectsOrder= new ArrayList<>();
        this.effectsOrder.add(new ArrayList<>());
        this.effectsOrder.get(0).add("Base");
        this.numberOfOptional = 0;
        this.ammoCost = new char[3];
        ammoCost[0] = 'b';
        ammoCost[1]= 'b';
        ammoCost[2]= 'y';
        this.description ="effect: Deal 3 damage and 1 mark to 1 target you can see.\n" +
                "Your target must be at least 2 moves away from you.";
        this.secondaryEffectCost=null;
        this.tertiaryEffectCost =null;
    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        //TODO scrivere codice
        return null;
    }

    @Override
    void applyBaseEffect(ChosenAction playersChoice) {
        //TODO scrivere codice
    }

    /** target: 1 (that you can see, but at LEAST 2 moves away from you)
     *  damage: 3
     *  marker: 1
     */
    @Override
    void targetsOfBaseEffect() {
        //TODO scrivere codice

    }

    @Override
    void applyTertiaryEffect(ChosenAction playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect() {
        throw new UnsupportedOperationException();
    }
    @Override
    void applySecondaryEffect(ChosenAction playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfSecondaryEffect() {
        throw new UnsupportedOperationException();
    }
}