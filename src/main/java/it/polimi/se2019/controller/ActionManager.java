package it.polimi.se2019.controller;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class ActionManager {

    /**
     * deals a certain amount of damage and marks to a single player
     * @param currentController is the current controller
     * @param target is the single target to hit
     * @param playersChoice is the choices the player made
     * @param numDmg is the number of damage to deal
     * @param numMks  is the number of marks to deal
     */
    public static void giveDmgandMksToOnePlayer(Controller currentController, Player target, ChosenActions playersChoice, int numDmg, int numMks){
        if(numDmg!=0)
            PlayerManager.damageDealer(currentController,target,playersChoice.damageSequence(numDmg));
        if(numMks!=0)
            PlayerManager.markerDealer(currentController, target,playersChoice.damageSequence(numMks));
    }

    /**
     * deals the same amount of damage and marks to more than one player
     *
     * @param currentController is the current controller
     * @param targetList is the list of targets to hit
     * @param playersChoice is the choices the player made
     * @param numDmg is the number of damage to deal
     * @param numMks  is the number of marks to deal
     */
    public static void giveDmgandMksToPlayers(Controller currentController, ArrayList<Player> targetList, ChosenActions playersChoice, int numDmg, int numMks){
        if(numDmg!=0){
            for(Player target:targetList)
                PlayerManager.damageDealer(currentController,target,playersChoice.damageSequence(numDmg));
        }
        if(numMks!=0){
            for(Player target: targetList)
                PlayerManager.markerDealer(currentController,target,playersChoice.damageSequence(numMks));
        }
    }

    /**
     * it moves a player from its current position to an arrival cell sent as a parameter
     * @param currentController is the controller of the game
     * @param player is the player to move
     * @param arrivalCell is the cell to move the player into
     */
    public static void movePlayer(Controller currentController, Player player, NewCell arrivalCell) {
        //if the room has changed
        if(!player.getFigure().getCell().getColor().equals(arrivalCell.getColor())){
            MapManager.getRoom(currentController,player.getFigure().getCell()).removePlayers(player);
            MapManager.getRoom(currentController,arrivalCell).addPlayers(player);
        }

        NewCell[][] board=currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        NewCell realArrivalCell=board[MapManager.getLineOrColumnIndex(board,arrivalCell,true)]
                [MapManager.getLineOrColumnIndex(board,arrivalCell,false)];

        player.getFigure().getCell().removePlayers(player); //removes the player from the cell
        player.getFigure().setCell(realArrivalCell); //changes the cell saved in the player
        realArrivalCell.addPlayers(player); //adds the player on the new cell
    }

    /**
     * this method evaluates if a player can pay the cost to grab a GunCard from a SpawnCell
     * @param activePlayer is the active player (important to get the powerup cubes)
     * @param availableAmmo this is the available ammo it has (it could be different from the one in his hand)
     * @param ammoCost is the ammocost of the item
     * @param pickOrFullReload: if true it evaluates the cost of picking the item (removes the first cube),
     *                       otherwise if false it evaluates the full cost (full reload cost)
     * @return if a player can pay the cost
     */
    public static boolean canAffordCost(Player activePlayer, Ammo availableAmmo, char[] ammoCost, boolean pickOrFullReload) {
        int blue=0;
        int red=0;
        int yellow=0;
        int start;

        if (pickOrFullReload)
            start=1;
        else
            start=0;

        for(int i=start;i<ammoCost.length;i++){
            switch (ammoCost[i]) {
                case 'b':blue++;break;
                case 'y':yellow++;break;
                case 'r':red++;break;
                default:break;
            }
        }
        int availableBlue=availableAmmo.getBlue();
        int availableRed=availableAmmo.getRed();
        int availableYellow=availableAmmo.getYellow();

        for(int i=0;i<activePlayer.getPlayerBoard().getHand().getPowerups().length;i++)
            if(activePlayer.getPlayerBoard().getHand().getPowerups()[i]!=null)
                switch (activePlayer.getPlayerBoard().getHand().getPowerups()[i].getCubeColor()){
                    case 'b':availableBlue++;break;
                    case 'y':availableYellow++;break;
                    case 'r':availableRed++;break;
                    default:break;
            }
        return availableBlue>=blue&&availableRed>=red&&availableYellow>=yellow;
    }

    /**
     * @param currentController is the controller of the game
     * @param playersPOV is the player POV from which to calculate the targets
     * @return players you can see (doesn't include yourself)
     * */
    public static ArrayList<Player> visibleTargets(Controller currentController,FictitiousPlayer playersPOV){
        ArrayList<Player> targets = visibleTargets(currentController,playersPOV.getPosition());
        //It will finally remove the current player from the target list
        targets.remove(playersPOV.getCorrespondingPlayer());

        return targets;
    }

    /**
     * @param currentController is the controller of the game
     * @param playersPosition is the  position from which to calculate the targets
     * @return players you can see (includes yourself)
     * */
    public static ArrayList<Player> visibleTargets(Controller currentController, NewCell playersPosition){
        //it will add all of the targets in the room first
        ArrayList<Player> targets=new ArrayList<>(Player.duplicateList(MapManager.getRoom(currentController, playersPosition).getPlayers()));

        //it will then add all of the targets in adjacent rooms (through a door)
        for(int i=0;i<4;i++)
            if(playersPosition.getEdge(i).equals(CellEdge.DOOR))
                try {
                    Room roomYouCanSee=MapManager.getRoom(currentController, MapManager.getCellInDirection(currentController.getMainGameModel().getCurrentMap().getBoardMatrix(),playersPosition,1,i));
                    if(!roomYouCanSee.isEmpty())
                        targets.addAll(roomYouCanSee.getPlayers());
                } catch (OuterWallException e){
                    //Won't ever happen
                }
        return targets;
    }

    /**
     * @param currentController is the controller of the game
     * @param playersPOV is the player POV from which to calculate the targets
     * @return players you cannot see
     * */
    public static ArrayList<Player> notVisibleTargets(Controller currentController,FictitiousPlayer playersPOV){
        ArrayList<Player> allTargets = new ArrayList<>(Player.duplicateList(currentController.getMainGameModel().getPlayerList()));

        allTargets.removeAll(visibleTargets(currentController,playersPOV));

        allTargets.remove(playersPOV.getCorrespondingPlayer());

        return allTargets;
    }

    /**
     * @param currentController is the controller of the game
     * @param playersPOV is the player POV from which to calculate the targets
     * @return squares you can see (including yours)
     * */
    public static ArrayList<NewCell> visibleSquares(Controller currentController, FictitiousPlayer playersPOV){
        //it will add first the squares in your room
        ArrayList<NewCell> squares=new ArrayList<>(NewCell.duplicateList(MapManager.getRoom(currentController, playersPOV.getPosition()).getCells()));

        //it will then add the squares in adjacent rooms (through a door)
        for(int i=0;i<4;i++)
            if(playersPOV.getPosition().getEdge(i).equals(CellEdge.DOOR))
                try {
                    Room roomYouCanSee=MapManager.getRoom(currentController, MapManager.getCellInDirection(currentController.getMainGameModel().getCurrentMap().getBoardMatrix(),playersPOV.getPosition(),1,i));
                    squares.addAll(NewCell.duplicateList(roomYouCanSee.getCells()));
                } catch (OuterWallException e){
                    //Won't ever happen
                }

        return squares;
    }

    /**
     * @param currentController is the controller of the game
     * @param player is the player from which to calculate the targets
     * @return  a list of players one move away
     */
    public static ArrayList<Player> targetsOneMoveAway(Controller currentController, FictitiousPlayer player){
        ArrayList<Player> targets=new ArrayList<>();
        for(NewCell cell: cellsOneMoveAway(currentController,player.getPosition()))
            targets.addAll(Player.duplicateList(cell.getPlayers()));
        return targets;
    }

    /**
     *
     * @param currentController is the controller of the game
     * @param position is the position from which to calculate the near cells
     * @return a list of cells one move away
     */
    public static ArrayList<NewCell> cellsOneMoveAway(Controller currentController,NewCell position){
        NewCell[][] board= currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<NewCell> cellsOneMoveAway=new ArrayList<>();
        try {
            for (int i = 0; i < 4 ; i++) {
                if (!position.getEdge(i).equals(CellEdge.WALL))
                    cellsOneMoveAway.add(MapManager.getCellInDirection(board,position,1,i));
            }
        }catch (OuterWallException e){
            //Won't happen
        }
        return cellsOneMoveAway;
    }

}
