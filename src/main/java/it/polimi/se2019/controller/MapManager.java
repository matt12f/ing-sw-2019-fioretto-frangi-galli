package it.polimi.se2019.controller;

import it.polimi.se2019.AdrenalineServer;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.*;
import it.polimi.se2019.view.CellView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MapManager {
    private static final Logger LOGGER = Logger.getLogger(MapManager.class.getName());

    public static void refillEmptiedCells(){
        NewCell[][] mapMatrixToFill= AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 4; j++)
                    if(mapMatrixToFill[i][j].getCellType().equals(CellType.DROP)){
                        if (mapMatrixToFill[i][j].needsRefill(false))
                            refillCell(mapMatrixToFill[i][j]);
                    }else if(mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN)){
                        if (mapMatrixToFill[i][j].needsRefill(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().getActiveDeck().isEmpty()))
                            refillCell(mapMatrixToFill[i][j]);
                    }
    }

    private static void refillCell(NewCell cell){
        try {
            if(cell.getCellType().equals(CellType.DROP))
                cell.setItem(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getAmmotilesDeck().draw());
            else
                cell.setItem(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().draw());
        }catch (FullException e){
            LOGGER.log(Level.FINE,"MapManager refill",e);
        }
    }

    public static NewCell getCellInDirection(NewCell referenceCell, int distance, String direction){
        int lineIndex=0;
        int columnIndex=0;
        NewCell[][] board=AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
        //here I'll save the indexes of referenceCell
        for (int i=0; i < board.length; i++)
            for(int j=0; j < board[i].length; j++)
                if (board[lineIndex][columnIndex].equals(referenceCell)){
                    lineIndex=i;
                    columnIndex=j;
                    break;
                }

        switch (direction){
            case "Up": return board[lineIndex-distance][columnIndex];
            case "Down": return board[lineIndex+distance][columnIndex];
            case "Left": return board[lineIndex][columnIndex-distance];
            case "Right": return board[lineIndex][columnIndex+distance];
            default: return null;
        }
    }

    public static void movePlayer(Player player, NewCell arrivalCell){
        player.getFigure().getCell().removePlayers(player);
        arrivalCell.addPlayers(player);
    }

    public static NewCell cellViewToNewCell(CellView cellView){
        return AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix()[cellView.getLineIndex()][cellView.getColumnIndex()];
    }
}
