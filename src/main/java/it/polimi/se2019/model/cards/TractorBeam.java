package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

import java.util.ArrayList;

public class TractorBeam extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public TractorBeam() {
        super();
        this.ammoCost = new char[1];
        ammoCost[0]= 'b';
        this.description ="basic mode: Move a target 0, 1, or 2 squares to a square\n" +
                "you can see, and give it 1 damage.\n"+
                "in punisher mode: Choose a target 0, 1, or 2 moves away\n" +
                "from you. Move the target to your square\n" +
                "and deal 3 damage to it.";

        this.secondaryEffectCost = new char[2];
        secondaryEffectCost[0] = 'r';
        secondaryEffectCost[1] = 'y';
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
     * Move a target 0, 1, or 2 squares to a square you can see, and give it 1 damage.
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board=AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();

        //these are the squares you can see
        ArrayList<NewCell> visibleSquares=new ArrayList<>(ActionManager.visibleSquares(player));

        //These are the players that you can move to end up in squares that you can see
        for(NewCell visibleSquare:visibleSquares){
            ArrayList<Player> targetsYouCanMove=new ArrayList<>();
            for(Player target:AdrenalineServer.getMainController().getMainGameModel().getPlayerList())
               if(!target.equals(player.getCorrespondingPlayer()) && MapManager.distanceBetweenCells(board,target.getFigure().getCell(),visibleSquare) <= 2)
                   targetsYouCanMove.add(target);
            actions.addCellsWithTargets(visibleSquare,targetsYouCanMove,1,1);
            actions.setMaxCellToSelect(1);
            actions.setMinCellToSelect(1);
        }
    }

    /**
     * Choose a target 0, 1, or 2 moves away from you. Move the target to your square and deal 3 damage to it.
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board=AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<Player> targets = new ArrayList<>();
        for(Player target:AdrenalineServer.getMainController().getMainGameModel().getPlayerList())
            if(MapManager.distanceBetweenCells(board,target.getFigure().getCell(),player.getPosition())<=2)
                targets.add(target);

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
    }
}