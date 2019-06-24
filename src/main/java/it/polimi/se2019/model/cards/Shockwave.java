package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class Shockwave extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Shockwave() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'y';
        this.description ="basic mode: Choose up to 3 targets on\n" +
                "different squares, each exactly 1 move away.\n" +
                "Deal 1 damage to each target.\n"+
                "in tsunami mode: Deal 1 damage to all\n" +
                "targets that are exactly 1 move away";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'y';
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
     * Choose up to 3 targets on different squares, each exactly 1 move away. Deal 1 damage to each target.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        //These are the players that you can move to end up in squares that you can see
        for(NewCell visibleSquare:ActionManager.cellsOneMoveAway(player.getPosition())){
           if(!visibleSquare.getPlayers().isEmpty())
               actions.addCellsWithTargets(visibleSquare,visibleSquare.getPlayers(),1,1,false,false);
        }
        actions.setMaxCellToSelect(3);
        actions.setMinCellToSelect(1);
    }

    /**
     * Deal 1 damage to all targets that are exactly 1 move away.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        if(ActionManager.targetsOneMoveAway(player).isEmpty())
            actions.setOfferableOpt1(false);
    }
}