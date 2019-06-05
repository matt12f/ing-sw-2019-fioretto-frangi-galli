package it.polimi.se2019.view;

import it.polimi.se2019.enums.CardName;

import java.util.ArrayList;

public class PowerupUse {
    private ArrayList<CardName> usedPowerups;
    private int idPlayerToMove;
    private String directionOfMove;
    private int movementDistance;
    private CellView cellForSelfMovement;

    PowerupUse(CardName usedPwUp,int idPlayerToMove, String directionOfMove, int movementDistance, CellView cellForSelfMovement) {
        this.usedPowerups=new ArrayList<>();
        this.usedPowerups.add(usedPwUp);
        this.idPlayerToMove = idPlayerToMove;
        this.directionOfMove = directionOfMove;
        this.movementDistance = movementDistance;
        this.cellForSelfMovement = cellForSelfMovement;
    }

    public ArrayList<CardName> getUsedPowerups() {
        return usedPowerups;
    }

    public int getIdPlayerToMove() {
        return idPlayerToMove;
    }

    public String getDirectionOfMove() {
        return directionOfMove;
    }

    public int getMovementDistance() {
        return movementDistance;
    }

    public CellView getCellForSelfMovement() {
        return cellForSelfMovement;
    }
}
