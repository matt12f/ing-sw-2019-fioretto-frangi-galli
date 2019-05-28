package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;

import java.util.ArrayList;

/**
 * This class creates objects that represent the things a player must choose from
 * It can have more than one targetList of players
 */
public class SingleEffectActions {
    private ArrayList<Player> targetList1;
    private ArrayList<Player> targetList2;
    private int maxNumberOfTargets;
    private String directionOfMovement;
    private int maxDistanceOfMovement;
    private boolean allowedMovement;
    private boolean yourOrTheirMovement;
    private ArrayList<NewCell> targetCells;
    private ArrayList<Room> targetRooms;

}
