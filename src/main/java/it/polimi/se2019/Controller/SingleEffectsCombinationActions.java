package it.polimi.se2019.controller;

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

/**
 * This class creates objects that represent the things a player must choose from to use a certain effect
 */
public class SingleEffectsCombinationActions {
    private ArrayList<Player> targetList1; //To choose from
    private ArrayList<Player> targetList2; //To choose from
    private int maxNumberOfTargets; //For the GUI/CLI
    private String directionOfMovement; //To Choose from
    private int maxDistanceOfMovement; //For the GUI/CLI
    private boolean allowedMovement; //For the GUI/CLI
    private boolean yourOrTheirMovement; //For the GUI/CLI
    private ArrayList<NewCell> targetCells; //To choose from


    public SingleEffectsCombinationActions(GunCard gunCard, Player player) {
        //TODO evaluation for this combination of guncard, player, effectsOrder
    }

    /* returns the targets a certain player can see */
    private ArrayList<Player> visibleTargets(Player playersPOV){
        return new ArrayList<>();//TODO scrivere codice
    }

    /* returns the squares you can see/in one direction*/
    private ArrayList<NewCell> squareRetriever(String direction, boolean visible, int movesAway){
        return new ArrayList<>();//TODO scrivere codice
    }

    /* returns a list of players different from a given list, effectively removing the players you can’t hurt*/
    private ArrayList<Player> untouchableRemover(ArrayList<Player> NotAvailablePlayers){
        return new ArrayList<>();//TODO scrivere codice
    }

    /* checks if a cell is N moves away from another cell*/
    private boolean isAcellNmovesAway(int desiredDistance, NewCell cell1, NewCell cell2){
        return false;//TODO scrivere codice
    }
}

