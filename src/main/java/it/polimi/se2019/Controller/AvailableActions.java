package it.polimi.se2019.controller;

import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

public class AvailableActions {
    private DestinationCell arrivalCell; //for move and grab actions
    private ArrayList<Player> targetList; //for shoot actions

    public AvailableActions() {
        DestinationCell arrivalCell=new DestinationCell();
        ArrayList<Player> targetList =new ArrayList<>();
        //TODO riempire vettore
        this.arrivalCell = arrivalCell;
        this.targetList = targetList;
    }
}
