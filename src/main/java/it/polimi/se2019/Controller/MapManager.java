package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Cell;
import it.polimi.se2019.model.game.DropCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.SpawnCell;

public class MapManager {
    public void refillController(Cell cell){
        //TODO scrivere metodo
        if (cell instanceof DropCell){
                //fill il drop
            ((DropCell) cell).setDrop(Controller.getMainGameModel().getCurrentDecks().getAmmotilesDeck().draw());

        }else if(cell instanceof SpawnCell){
                //fill lo spazio mancante
        }
    }
    public void movePlayer(Player player, Cell cellStart, Cell cellFinish){
        //TODO scrivere metodo

    }
}
