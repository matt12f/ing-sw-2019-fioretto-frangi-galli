package it.polimi.se2019.model.cards;

import it.polimi.se2019.controller.*;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Room;
import it.polimi.se2019.view.ChosenActions;

import java.util.ArrayList;

public class Furnace extends GunCardAltEff {
    /**
     * hard-coded constructor
     */
    public Furnace() {
        super();
        this.ammoCost = new char[2];
        ammoCost[0]= 'r';
        ammoCost[1]= 'b';
        this.description ="basic mode: Choose a room you can see, but not the room"+
                "\nyou are in. Deal 1 damage to everyone in that room."+
                "\nin cozy fire mode: Choose a square exactly one move"+
                "\naway. Deal 1 damage and 1 mark to everyone on that"+
                "\nsquare.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
    }
    /**
     * This applies the base/base effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applyBaseEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetRoom().getPlayers(),playersChoice,1,0);
    }
    /**
     * This applies the alternative effect
     * @param currentController it the current controller of the game
     * @param playersChoice are the choices the player wants to apply
     */
    @Override
    void applySecondaryEffect(Controller currentController, ChosenActions playersChoice) {
        ActionManager.giveDmgandMksToPlayers(currentController,playersChoice.getTargetCell().getPlayers(),playersChoice,1,1);
    }

    /**
     * Choose a room you can see, but not the room you are in. Deal 1 damage to everyone in that room.
     * @param currentController it the current controller of the game
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<Room> rooms=new ArrayList<>();
        try{
        for (int i = 0; i < 4; i++){
            if(player.getPosition().getEdge(i).equals(CellEdge.DOOR))
                if(!MapManager.getRoom(currentController,MapManager.getCellInDirection(board, player.getPosition(), 1, i)).getPlayers().isEmpty())
                    rooms.add(MapManager.getRoom(currentController,MapManager.getCellInDirection(board, player.getPosition(), 1, i)));
        }
        }catch (OuterWallException e){
            //Won't ever happen
        }

        actions.addToTargetRooms(rooms);
    }

    /**
     * Choose a square exactly one move away. Deal 1 damage and 1 mark to everyone on that square.
     * @param currentController it the current controller of the game
     * @param  actions
     * @param player
     *
     */
    @Override
    void targetsOfSecondaryEffect(Controller currentController, SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = currentController.getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<NewCell> cells = new ArrayList<>();
        try{
        for (int i = 0; i < 4; i++)
            if(player.getPosition().getEdge(i).equals(CellEdge.DOOR)||player.getPosition().getEdge(i).equals(CellEdge.ROOM))
                cells.add(MapManager.getCellInDirection(board, player.getPosition(), 1, i));
        }catch (OuterWallException e){
            //Won't ever happen
        }

        actions.addToTargetCells(cells);
    }
    @Override
    public GunCard clone() {
        GunCard gunCard = new Furnace();
        gunCard.setLoaded(this.isLoaded());

        return gunCard;
    }
}