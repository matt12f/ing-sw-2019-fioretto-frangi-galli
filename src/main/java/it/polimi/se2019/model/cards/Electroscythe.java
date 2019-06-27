package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

public class Electroscythe extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Electroscythe() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'b';
        this.description =  "basic mode: Deal 1 damage to every other player on your square.\n"+
                            "in reaper mode: Deal 2 damage to every other player on your square.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'b';
        secondaryEffectCost[1] = 'r';
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {

    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {

    }

    /**
     * Deal 1 damage to every other player on your square.
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if(player.getPosition().getPlayers().size()==1)
            actions.setOfferableBase(false);
    }

    /**
     * Deal 2 damage to every other player on your square.
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if(player.getPosition().getPlayers().size()==1)
            actions.setOfferableOpt1(false);
    }
}