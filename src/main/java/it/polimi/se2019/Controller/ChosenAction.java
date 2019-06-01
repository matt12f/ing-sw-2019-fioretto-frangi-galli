package it.polimi.se2019.controller;

//TODO convert to JSON string before sending

import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;
import java.util.Map;

/**
 * this class is going to send back the objects it received to "select" them
 */
public class ChosenAction {
    //for move and grab actions
    private NewCell arrivalCell;
    private int gunCardToSwap;
    private boolean pickDrop;

    //for shoot actions
    private GunCard cardToUse; //TODO change to String? only the name would be fine

    private ArrayList<String> orderOfExecution; //List of the effects in the order of desired use for easy method calls
    private ArrayList<Player> targetList1;
    private ArrayList<Player> targetList2;
    private String directionOfMovement;
    private NewCell targetCells;

    public ArrayList<String> getOrderOfExecution() {
        return orderOfExecution;
    }
}
