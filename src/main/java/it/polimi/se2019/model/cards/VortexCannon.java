package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

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
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere metodo

       // TODO nota://the move of the opponent must be managed through the name of the class. There will not be "available" squares where to
        //        // move the player, meaning this is a VortexCannon card and the player that was hit must be moved automatically
        //        // to the selected vortex

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
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetFiller(currentController,actions,player,1);
    }

    /**
     * Choose up to 2 other targets on the vortex or 1 move away from it.
     * Move them onto the vortex and give them each 1 damage.
     */
     @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
         actions.getCellsWithTargets().clear();
         targetFiller(currentController,actions,player,3);
    }

    /**
     * this is an accessory method that returns a list of cells that can become a "Vortex" with the available targets
     * @param actions object to build on
     * @param player POV
     * @param maxTargetCell max number of targets on a cell
     */
    private void targetFiller(Controller currentController,SingleEffectsCombinationActions actions, FictitiousPlayer player, int maxTargetCell){
        for(NewCell cell:ActionManager.visibleSquares(currentController,player)){
            if (!cell.equals(player.getPosition())){
                ArrayList<Player> targets=new ArrayList<>(Player.duplicateList(cell.getPlayers()));
                targets.remove(player.getCorrespondingPlayer());

                targets.addAll(ActionManager.targetsOneMoveAway(currentController,player));
                actions.addCellsWithTargets(cell, targets, maxTargetCell, 1,false,false);
            }
        }
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);
    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new VortexCannon();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}