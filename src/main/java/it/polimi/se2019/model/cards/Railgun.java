package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class Railgun extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Railgun() {
        super();
        this.ammoCost = new char[3];
        ammoCost[0]= 'y';
        ammoCost[1]= 'y';
        ammoCost[2]= 'b';
        this.description ="basic mode: Choose a cardinal direction and 1 target in that direction.\n" +
                "Deal 3 damage to it.\n"+
                "in piercing mode: Choose a cardinal direction and 1 or 2 targets in that\n" +
                "direction. Deal 2 damage to each.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {

    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {

    }
    /**
     * Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO 4 liste di target? una per direzione?
    }

    /**
     * Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //TODO 4 liste di target? una per direzione?
    }

}