package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.*;

import java.util.ArrayList;

public class ActionManager {

    //TODO scrivere codice; va qui? deve partire quando riceve l'oggetto ChosenActions
    public static void applyPlayersChoices(){}

    //TODO rivedere metodo
    public static boolean inflictDamage(ArrayList<Player> targets, char[] damage){
        Player target;
        for(int i= 0; i<targets.size(); i++){
            target = targets.get(i);
            PlayerManager.damageDealer(target, damage);
        }
        return false;
    }

    //TODO rivedere metodo
    public static void appointMarker(ArrayList<Player> targets, char [] markers){
        Player target;

        for(int i= 0; i < targets.size(); i++){
            target = targets.get(i);
            PlayerManager.markerDealer(target, markers);
        }
    }

    /**
     * it moves a player from its current position to an arrival cell sent as a parameter
     * @param player
     * @param arrivalCell
     */
    public static void movePlayer(Controller currentController,Player player, NewCell arrivalCell) {
        if(!player.getFigure().getCell().getColor().equals(arrivalCell.getColor())){
            MapManager.getRoom(currentController,player.getFigure().getCell()).removePlayers(player);
            MapManager.getRoom(currentController,arrivalCell).addPlayers(player);
        }

        player.getFigure().getCell().removePlayers(player);
        arrivalCell.addPlayers(player);
    }

    /**
     * this method puts new ammos in the player's ammo vectors and, if there is one, a powerup card,
     * controlling if it's possible
     * @param drop
     */
    //TODO rivedere metodo
    public void dropManager(AmmoTileCard drop){
        //TODO serve questo metodo?
    }

    /**
     * this method allows to put a weapon from the spawn slot into the player's hand,
     * including the controlling cycle about the player's weapon hand
     */
    //TODO rivedere metodo
    public void spawnDropManager(GunCard weapon){
        //TODO serve questo metodo?
    }

    /**
     * this method evaluates if a player can pay the cost to grab a GunCard from a SpawnCell
     * @param fullOrReload: if true it evaluates the full cost of reloading, if false it evaluates only the buying cost
     */
    public static boolean canAffordCost(Player activePlayer, Ammo availableAmmo, char[] ammoCost, boolean fullOrReload) {
        int blue=0;
        int red=0;
        int yellow=0;
        int start;
        if (fullOrReload)
            start=0;
        else
            start=1;
        for(int i=start;i<ammoCost.length;i++){
            switch (ammoCost[i]) {
                case 'b':blue++;break;
                case 'y':yellow++;break;
                case 'r':red++;break;
            }
        }
        int availableBlue=availableAmmo.getBlue();
        int availableRed=availableAmmo.getRed();
        int availableYellow=availableAmmo.getYellow();

        for(PowerupCard powerupCard: activePlayer.getPlayerBoard().getHand().getPowerups())
            switch (powerupCard.getCubeColor()){
                case 'b':availableBlue++;break;
                case 'y':availableYellow++;break;
                case 'r':availableRed++;break;
            }
        return availableBlue>=blue&&availableRed>=red&&availableYellow>=yellow;
    }

    /**
     *  Returns the targets a certain player can see (doesn't include yourself)
     *  */
    public static ArrayList<Player> visibleTargets(Controller currentController,FictitiousPlayer playersPOV){
        ArrayList<Player> targets = visibleTargets(currentController,playersPOV.getPosition());
        //It will finally remove the current player from the target list
        targets.remove(playersPOV.getCorrespondingPlayer());

        return targets;
    }

    public static ArrayList<Player> visibleTargets(Controller currentController,NewCell playersPosition){
        //it will add all of the targets in the room first
        ArrayList<Player> targets=new ArrayList<>(MapManager.getRoom(currentController, playersPosition).getPlayers());

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

    public static ArrayList<Player> notVisibleTargets(Controller currentController,FictitiousPlayer playersPOV){
        ArrayList<Player> allTargets = new ArrayList<>(currentController.getMainGameModel().getPlayerList());

        allTargets.removeAll(visibleTargets(currentController,playersPOV));

        allTargets.remove(playersPOV.getCorrespondingPlayer());

        return allTargets;
    }

    /**
     * returns the squares you can see (including yours)
     * */
    public static ArrayList<NewCell> visibleSquares(Controller currentController, FictitiousPlayer playersPOV){
        //it will add first the squares in your room
        ArrayList<NewCell> squares=new ArrayList<>(MapManager.getRoom(currentController, playersPOV.getPosition()).getCells());

        //it will then add the squares in adjacent rooms (through a door)
        for(int i=0;i<4;i++)
            if(playersPOV.getPosition().getEdge(i).equals(CellEdge.DOOR))
                try {
                    Room roomYouCanSee=MapManager.getRoom(currentController, MapManager.getCellInDirection(currentController.getMainGameModel().getCurrentMap().getBoardMatrix(),playersPOV.getPosition(),1,i));
                    squares.addAll(roomYouCanSee.getCells());
                } catch (OuterWallException e){
                    //Won't ever happen
                }

        return squares;
    }

    /**
     * This method gives you a list of players one move away
     */
    public static ArrayList<Player> targetsOneMoveAway(Controller currentController, FictitiousPlayer player){
        ArrayList<Player> targets=new ArrayList<>();
        for(NewCell cell: cellsOneMoveAway(currentController,player.getPosition()))
            targets.addAll(cell.getPlayers());
        return targets;
    }

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
