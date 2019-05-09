package it.polimi.se2019.controller;

import it.polimi.se2019.Adrenaline;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.Cell;
import it.polimi.se2019.model.game.DropCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.SpawnCell;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MapManager {
    private static final Logger LOGGER = Logger.getLogger(MapManager.class.getName());
    public void refillEmptiedCells(){
        Cell[][] mapMatrixToFill=Adrenaline.getMainController().getMainGameModel().getCurrentMap().getBoardMatrix();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 4; j++)
                    if(mapMatrixToFill[i][j] instanceof DropCell){
                        if (mapMatrixToFill[i][j].needsRefill(true))
                            refillCell( (DropCell) mapMatrixToFill[i][j]);
                    }else if(mapMatrixToFill[i][j] instanceof SpawnCell)
                        if (mapMatrixToFill[i][j].needsRefill(Adrenaline.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().isEmpty()))
                            refillCell( (SpawnCell) mapMatrixToFill[i][j]);
    }//TODO magari rivedere per ottimizzare con setup board in controller

    //TODO verificare se instanceof va bene (costruire classe di test)
    private void refillCell(DropCell cell){
        try {
            cell.setItem(Adrenaline.getMainController().getMainGameModel().getCurrentDecks().getAmmotilesDeck().draw());
        }catch (FullException e){
            LOGGER.log(Level.FINE,"MapManager refill dropcell",e);
        }
    }
    private void refillCell(SpawnCell cell){
        try{
            cell.setItem(Adrenaline.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().draw());
        }catch (FullException e){
            LOGGER.log(Level.FINE,"MapManager refill spawncell",e);
        }
    }
    public void movePlayer(Player player, Cell cellStart, Cell cellFinish){
        //TODO scrivere metodo

    }
}
