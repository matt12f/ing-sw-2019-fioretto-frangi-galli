package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class Whisper extends GunCardAddEff {
    /**
     * hard-coded constructor
     */
    public Whisper() {
        super();
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
    /**
     * This applies the base  effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,3,1);
    }

    /**
     * Deal 3 damage and 1 mark to 1 target you can see. Your target must be at least 2 moves away from you.
     */
    /**
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>();
        for(Player target: ActionManager.visibleTargets(currentController,player))
            if(MapManager.distanceBetweenCells(currentController.getMainGameModel().getCurrentMap().getBoardMatrix(),player.getPosition(),target.getFigure().getCell())>2)
                targets.add(target);

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

    }
    /**
     * This applies the third effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyTertiaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }

    /**
     * find targets
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfTertiaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }
    /**
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }

    /**
     * find targets
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Whisper();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}