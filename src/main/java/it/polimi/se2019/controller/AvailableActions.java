package it.polimi.se2019.controller;

import it.polimi.se2019.enums.ActionType;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.ActionRequestView;
import it.polimi.se2019.view.PowerupUse;

import java.util.ArrayList;

/**
 * This class builds the message object that the ActionManager in controller builds and sends over to the Client
 * to choose from
 *
 * In the Normal 1 combination (move, move, move) you will only use the cell object
 *
 * In the Normal 2 combination (move,(move) grab) you will only use the cell object
 *
 * In the Normal 3 combination ((move), shoot) you will use the cell object and an object for each card
 * in your hand that indicates if you can use it and in which way
 *
 * The client will then build an "answer object" containing his selection
 */

public class AvailableActions {
    private ArrayList<FictitiousPlayer> fictitiousPlayers; //the player will choose one of these

    /**
     * Method that calculates the actions available to a player during a certain turn (after the selection of a macro action)
     * @param macroAction player's selection (in ActionRequestView through GUI or CLI)
     * @param playerId player's number (ID)
     * @return available actions depending on the game status
     */
    public AvailableActions(ActionRequestView macroAction, int playerId,Controller currentController) {
        Player player=currentController.getMainGameModel().getPlayerList().get(playerId);

        switch (macroAction.getActionToRequest()){
            case NORMAL1:  buildActions(currentController,player,macroAction,3,false,false,false);break;
            case NORMAL2:  buildActions(currentController,player,macroAction,1,true,false,false);break;
            case NORMAL3:  buildActions(currentController,player,macroAction,0,false,true,false);break;
            case FRENZY1:  buildActions(currentController,player,macroAction,1,false,true,true);break;
            case FRENZY2:  buildActions(currentController,player,macroAction,4,false,false,false);break;
            case FRENZY3:  buildActions(currentController,player,macroAction,2,true,false,false);break;
            case FRENZY4:  buildActions(currentController,player,macroAction,2,false,true,true);break;
            case FRENZY5:  buildActions(currentController,player,macroAction,3,true,false,false);break;
        }
    }

    /**
     * method that builds the available actions from the macro actions
     * @param player: is the subject of the action
     * @param maxMoveDistance: number of move actions in the macro actions
     * @param grab: if there is a grab option
     * @param shoot: if there is a shoot option (sometimes it's the only action possible)
     * @param frenzyReload: if there's a frenzy action with the possibility to reload a gun before shooting it must
     *                      be considered when evaluating which cards the player can use
     * @return available actions object to return to the player (CLIENT)
     */
    private void buildActions(Controller currentController, Player player, ActionRequestView macroAction, int maxMoveDistance, boolean grab, boolean shoot, boolean frenzyReload){
        NewCell[][] board=currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        for (PowerupUse powerupUse : macroAction.getPowerupUse()){
            if(powerupUse.getDirectionOfMove().equals("None"))
                PowerupManager.teleporterManager(currentController,powerupUse.getIndexInHand(),MapManager.cellViewToNewCell(currentController, powerupUse.getCellForSelfMovement()));
            else
                PowerupManager.newtonManager(currentController,powerupUse.getIndexInHand(),currentController.getMainGameModel().getPlayerByColor(powerupUse.getColorPlayerToMove()),powerupUse.getMovementDistance(),MapManager.getIndexOfMove(powerupUse.getDirectionOfMove()));
        }

        //checks for adrenaline modes
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode1() && grab)
            maxMoveDistance++;
        if(player.getPlayerBoard().getActionTileNormal().getAdrenalineMode2() && shoot)
            maxMoveDistance++;

        //determines the minimum move distance
        int minMoveDistance;
        if(macroAction.getActionToRequest().equals(ActionType.NORMAL1))
            minMoveDistance=1;
        else
            minMoveDistance=0;

        //for move and grab actions
        ArrayList<CellInfo> singleArrivalCells=createArrivalCells(board,player,minMoveDistance,maxMoveDistance,grab);

        this.fictitiousPlayers=new ArrayList<>();
        for(CellInfo cell:singleArrivalCells)
            this.fictitiousPlayers.add(new FictitiousPlayer(currentController, player, cell, shoot, frenzyReload));
    }

    /**
     * arrivalCell setup
     */
    private static  ArrayList<CellInfo> createArrivalCells(NewCell[][] board, Player player,int minMoveDistance, int maxMoveDistance, boolean grab){
        ArrayList<CellInfo> singleArrivalCells = new ArrayList<>();

        //in case the player is allowed NOT to move from his current cell (all of the macroActions except for Normal1)
        if(minMoveDistance==0)
            singleArrivalCells.add(new CellInfo(player.getFigure().getCell(),grab,player.getFigure().getCell().getDrop()!=null && grab));

        NewCell referenceCell=player.getFigure().getCell();

        if(maxMoveDistance!=0){ //For move and move+grab actions
            //here I'll add the cells that the player can move into
            for(NewCell [] cellRow: board)
                for(NewCell singleCell: cellRow)
                    if(!singleCell.getCellType().equals(CellType.OUTSIDEBOARD) && singleCell!=player.getFigure().getCell() && MapManager.distanceBetweenCells(board,referenceCell,singleCell) <= maxMoveDistance)
                        singleArrivalCells.add(new CellInfo(referenceCell, grab,singleCell.getDrop()!=null && grab));

        }else if(grab) //for actions without movement but with grab
            singleArrivalCells.add(new CellInfo(player.getFigure().getCell(),true, player.getFigure().getCell().getCellType().equals(CellType.DROP) && player.getFigure().getCell().getDrop()!=null));
        else  //for actions without movement and grab
            singleArrivalCells.add(new CellInfo(player.getFigure().getCell(),false,false));
        return singleArrivalCells;
    }

    public ArrayList<FictitiousPlayer> getFictitiousPlayers() {
        return fictitiousPlayers;
    }
}