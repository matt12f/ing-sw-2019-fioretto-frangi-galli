package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

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

    /**
     * This applies the base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.movePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice.getCellToMoveOpponent());
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromCell().get(0),playersChoice,1,0);
    }
    /**
     * This applies the secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.movePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice.getFictitiousPlayer().getPosition());
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,3,0);
    }

    /**
     * Move a target 0, 1, or 2 squares to a square you can see, and give it 1 damage.
     *
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board=currentController.getMainGameModel().getCurrentMap().getBoardMatrix();

        //these are the squares you can see
        ArrayList<NewCell> visibleSquares=new ArrayList<>(ActionManager.visibleSquares(currentController,player));

        //These are the players that you can move to end up in squares that you can see
        for(NewCell visibleSquare:visibleSquares){
            ArrayList<Player> targetsYouCanMove=new ArrayList<>();
            for(Player target:currentController.getMainGameModel().getPlayerList())
               if(!target.equals(player.getCorrespondingPlayer()) && MapManager.distanceBetweenCells(board,target.getFigure().getCell(),visibleSquare) <= 2)
                   targetsYouCanMove.add(target);

               if(!targetsYouCanMove.isEmpty())
                   actions.addCellsWithTargets(visibleSquare,targetsYouCanMove,1,1,false,true);
        }
        actions.setMaxCellToSelect(1);
        actions.setMinCellToSelect(1);
        actions.setCanMoveOpponent(true);
    }

    /**
     * Choose a target 0, 1, or 2 moves away from you. Move the target to your square and deal 3 damage to it.
     *
     *
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board=currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<Player> targets = new ArrayList<>();
        for(Player target:currentController.getMainGameModel().getPlayerList())
            if(!target.equals(player.getCorrespondingPlayer())&& MapManager.distanceBetweenCells(board,target.getFigure().getCell(),player.getPosition())<=2)
                targets.add(target);

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new TractorBeam();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}