package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class Sledgehammer extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Sledgehammer() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0]= 'y';
        this.description ="basic mode: Deal 2 damage to 1 target on\n" +
                "your square.\n"+
                "in pulverize mode: Deal 3 damage to 1 target\n" +
                "on your square, then move that target 0, 1,\n" +
                "or 2 squares in one direction.";

        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'r';
    }
    /**
     * This applies the base/base+opt2 effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,2,0);
    }
    /**
     * This applies the  secondary effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToOnePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice,3,0);
        ActionManager.movePlayer(currentController,playersChoice.getTargetsFromList1().get(0),playersChoice.getCellToMoveOpponent());
    }

    /**
     * Deal 2 damage to 1 target on your square.
     *
     * find targets
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        ArrayList<Player> targets =Player.duplicateList(player.getPosition().getPlayers());
        targets.remove(player.getCorrespondingPlayer());

        actions.addToPlayerTargetList(targets);
        actions.setMaxNumPlayerTargets(1);
        actions.setMinNumPlayerTargets(1);

        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableBase(false);

    }

    /**
     * Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.

     * find targets
     * @param currentController
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        targetsOfBaseEffect(currentController, actions, player);

        if(actions.getPlayersTargetList().isEmpty())
            actions.setOfferableOpt1(false);
        else {
        NewCell [][] board= currentController.getMainGameModel().getCurrentMap().getBoardMatrix();

        ArrayList<NewCell> cellsToMoveTargetOn=new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            try {
                if (!player.getPosition().getEdge(i).equals(CellEdge.WALL))
                    cellsToMoveTargetOn.add(MapManager.getCellInDirection(board,player.getPosition(),1,i));
                if(!cellsToMoveTargetOn.get(cellsToMoveTargetOn.size()-1).getEdge(i).equals(CellEdge.WALL))
                    cellsToMoveTargetOn.add(MapManager.getCellInDirection(board,cellsToMoveTargetOn.get(cellsToMoveTargetOn.size()-1),1,i));
            }catch (OuterWallException e){
                //Won't happen
            }
        }

        cellsToMoveTargetOn.add(player.getPosition());

        for(NewCell cell:cellsToMoveTargetOn)
            actions.addCellsWithTargets(cell,new ArrayList<>(),0,0,false,true);

        actions.setCanMoveOpponent(true);
        actions.setMinCellToSelect(1);
        actions.setMaxCellToSelect(1);
        }
    }

    @Override
    public GunCard clone() {
        GunCard gunCard = new Sledgehammer();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}