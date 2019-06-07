package it.polimi.se2019.view;

import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;

import java.util.ArrayList;

/**
 * this class is going to send back the objects it received to "select" them
 */
public class ChosenActions {//TODO convert to JSON string before sending
    //for move and grab actions
    private NewCell arrivalCell;
    private int gunCardToSwap;
    private boolean pickDrop;

    //for shoot actions
    private String cardToUse;

    private ArrayList<String> orderOfExecution; //List of the effects in the order of desired use for easy method calls
    private ArrayList<Player> targetList1;
    private ArrayList<Player> targetList2;
    private String directionOfMovement;
    private NewCell targetCells;

    //TODO scrivere costruttore
    public ArrayList<String> getOrderOfExecution() {
        return orderOfExecution;
    }
}
