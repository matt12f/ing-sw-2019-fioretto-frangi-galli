package it.polimi.se2019.model.cards;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;
import it.polimi.se2019.view.ChosenActions;
import it.polimi.se2019.controller.SingleEffectsCombinationActions;

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
        this.description ="basic mode: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.\n"+
                "in cozy fire mode: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.";
        this.secondaryEffectCost = new char[1];
        secondaryEffectCost[0] = 'n';
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
     * Choose a room you can see, but not the room you are in. Deal 1 damage to everyone in that room.
     * @param actions
     * @param player
     */
    @Override
    void targetsOfBaseEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
        ArrayList<Room> rooms=new ArrayList<>();
        try{
        for (int i = 0; i < 4; i++){
            if(player.getPosition().getEdge(i).equals(CellEdge.DOOR))
                rooms.add(MapManager.getRoom(MapManager.getCellInDirection(board, player.getPosition(), 1, i)));
        }
        }catch (OuterWallException e){
            //Won't ever happen
        }

        actions.addToTargetRooms(rooms);
    }

    /**
     * Choose a square exactly one move away. Deal 1 damage and 1 mark to everyone on that square.
     * @param actions
     * @param player
     */
    @Override
    void targetsOfSecondaryEffect(SingleEffectsCombinationActions actions, FictitiousPlayer player) {
        NewCell[][] board = AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
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
}