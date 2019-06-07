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
    public void refillEmptiedCells(){
        NewCell[][] mapMatrixToFill= AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 4; j++)
                    if(mapMatrixToFill[i][j].getCellType().equals(CellType.DROP)){
                        if (mapMatrixToFill[i][j].needsRefill(true))
                            refillCell(mapMatrixToFill[i][j]);
                    }else if(mapMatrixToFill[i][j].getCellType().equals(CellType.SPAWN))
                        if (mapMatrixToFill[i][j].needsRefill(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().isEmpty()))
                            refillCell(mapMatrixToFill[i][j]);
    }//TODO rivedere per ottimizzare con setup board in controller

    private void refillCell(NewCell cell){
        try {
            cell.setItem(AdrenalineServer.getMainController().getMainGameModel().getCurrentDecks().getAmmotilesDeck().draw());
        }catch (FullException e){
            LOGGER.log(Level.FINE,"MapManager refill",e);
        }
    }

    public void movePlayer(Player player, NewCell cellStart, NewCell cellFinish){
        //TODO fare caso di test
        for (Player temp : cellStart.getPlayers()) {
            if (temp.getNickname().equals(player.getNickname())){
                cellStart.removePlayers(temp);
            }
        }
        cellFinish.addPlayers(player);
    }

    public static NewCell cellViewToNewCell(CellView cellView){
        return AdrenalineServer.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix()[cellView.getLineIndex()][cellView.getColumnIndex()];
    }
}
