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
    public void refillController(Cell cell){
        try{
        if (cell instanceof DropCell)
            cell.setItem(Adrenaline.getMainController().getMainGameModel().getCurrentDecks().getAmmotilesDeck().draw());
        else if(cell instanceof SpawnCell)
            cell.setItem(Adrenaline.getMainController().getMainGameModel().getCurrentDecks().getGunDeck().draw());
        }catch (FullException e){
            LOGGER.log(Level.FINE,"MapManager refill",e);
        }
    }
    public void movePlayer(Player player, Cell cellStart, Cell cellFinish){
        //TODO scrivere metodo

    }
}
