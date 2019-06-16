package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

import java.util.ArrayList;

public class VortexCannon extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public VortexCannon() {
        super();
        ArrayList<String> secondCombination=new ArrayList<>();
        secondCombination.add("Base");
        secondCombination.add("Optional1");
        this.effectsOrder.add(secondCombination);

        this.numberOfOptional = 1;
        this.ammoCost = new char[2];
        ammoCost[0]= 'b';
        ammoCost[1]= 'b';
        this.description ="basic effect: Choose a square you can see, but not your\n" +
                "square. Call it \"the vortex\". Choose a target on the vortex\n" +
                "or 1 move away from it. Move it onto the vortex and give it\n" +
                "2 damage.\n"+
                "with black hole: Choose up to 2 other targets on the\n" +
                "vortex or 1 move away from it. Move them onto the vortex\n" +
                "and give them each 1 damage.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
        this.tertiaryEffectCost =null;
    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {

    }

    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {

    }

    @Override
    public SingleEffectsCombinationActions buildAvailableActions(ArrayList<String> effectsCombination, FictitiousPlayer player) throws UnavailableEffectCombinationException {
        return null;
    }

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo
    }

    /**
     * Choose a square you can see, but not your square. Call it "the vortex".
     * Choose a target on the vortex or 1 move away from it. Move it onto the vortex and give it 2 damage.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<NewCell> possibleVortex=new ArrayList<>(ActionManager.visibleSquares(player));
        actions.addToTargetCells(possibleVortex);
        //TODO nella view: scegliere target lì, o nei quadrati adiacenti
        //TODO maxNumberTargetsOnCell=1
    }

    /**
     * Choose up to 2 other targets on the vortex or 1 move away from it.
     * Move them onto the vortex and give them each 1 damage.
     */

     @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
         //TODO maxNumberTargetsOnCell=3
    }
}