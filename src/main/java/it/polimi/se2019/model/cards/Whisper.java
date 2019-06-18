package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;
import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;

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

    @Override
    void applyBaseEffect(ChosenActions playersChoice) {
        //TODO scrivere codice
    }

    /**
     * Deal 3 damage and 1 mark to 1 target you can see. Your target must be at least 2 moves away from you.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets=new ArrayList<>();
        for(Player target: ActionManager.visibleTargets(player))
            if(MapManager.distanceBetweenCells(AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix(),player.getPosition(),target.getFigure().getCell())>2)
                targets.add(target);

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
    }

    @Override
    void applyTertiaryEffect(ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfTertiaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }
    @Override
    void applySecondaryEffect(ChosenActions playersChoice) {
        throw new UnsupportedOperationException();
    }
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        throw new UnsupportedOperationException();
    }
}