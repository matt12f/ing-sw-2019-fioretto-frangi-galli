package it.polimi.se2019.controller;

import it.polimi.se2019.exceptions.UnavailableEffectCombinationException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;

import java.util.ArrayList;

/**
 * This class creates objects that represent the things a player must choose from to use a certain effect
 */
public class SingleEffectsCombinationActions {
    private ArrayList<Player> targetList1; //To choose from
    private ArrayList<Player> targetList2; //To choose from
    private int maxNumberOfTargetsList1; //For the GUI/CLI
    private int maxNumberOfTargetsList2; //For the GUI/CLI
    private String directionOfMovement; //To Choose from
    private int maxDistanceOfMovement; //For the GUI/CLI
    private boolean allowedMovement; //For the GUI/CLI
    private boolean yourOrTheirMovement; //For the GUI/CLI
    private ArrayList<NewCell> targetCells; //To choose from
    private ArrayList<Room> targetRooms; //To choose from

    public SingleEffectsCombinationActions() {
        this.targetList1=new ArrayList<>();
        this.targetList2=new ArrayList<>();
        this.maxNumberOfTargetsList1=0;
        this.directionOfMovement="None";
        this.maxDistanceOfMovement=0;
        this.allowedMovement=false;
        this.targetCells=new ArrayList<>();
        this.targetRooms=new ArrayList<>();
    }

    public void addToTargetList1(ArrayList<Player> targetList1) {
        this.targetList1.addAll(targetList1);
    }

    public void addToTargetList2(ArrayList<Player> targetList2) {
        this.targetList1.addAll(targetList2);
    }

    public void setMaxNumberOfTargetsList1(int maxNumberOfTargets) {
        this.maxNumberOfTargetsList1 = maxNumberOfTargets;
    }

    public void setMaxNumberOfTargetsList2(int maxNumberOfTargetsList2) {
        this.maxNumberOfTargetsList2 = maxNumberOfTargetsList2;
    }

    public void setDirectionOfMovement(String directionOfMovement) {
        this.directionOfMovement = directionOfMovement;
    }

    public void setMaxDistanceOfMovement(int maxDistanceOfMovement) {
        this.maxDistanceOfMovement = maxDistanceOfMovement;
    }

    public void setAllowedMovement(boolean allowedMovement) {
        this.allowedMovement = allowedMovement;
    }

    public void setYourOrTheirMovement(boolean yourOrTheirMovement) {
        this.yourOrTheirMovement = yourOrTheirMovement;
    }

    public void setTargetCells(ArrayList<NewCell> targetCells) {
        this.targetCells = targetCells;
    }

    public void addToTargetRooms(ArrayList<Room> targetRooms) {
        this.targetRooms.addAll(targetRooms);
    }

    public void addToTargetCells(ArrayList<NewCell> targetCells) {
        this.targetCells.addAll(targetCells);
    }

    //TODO c'è altro da controllare?
    public void validate() throws UnavailableEffectCombinationException{
        if (this.targetList1.isEmpty() &&this.targetList2.isEmpty() &&this.targetCells.isEmpty() && this.targetRooms.isEmpty())
            throw new UnavailableEffectCombinationException();
    }
}

