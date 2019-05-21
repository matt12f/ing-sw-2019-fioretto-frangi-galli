package it.polimi.se2019.controller.weapon_managers;

import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;

import java.util.ArrayList;

public   class TargetManager {
   private static ArrayList<Player> targetList = new ArrayList<>();
   private static Player target;
    public static ArrayList<Player>  getCellTarget(NewCell cell){

        targetList = cell.getPlayers();

        return targetList;
    }

    public static ArrayList<Player>  getRoomTarget(Room room){
        targetList = room.getPlayers();

        return targetList;
    }

    public static Player  getSingleCellTarget(NewCell cell){


        return target;
    }

}
