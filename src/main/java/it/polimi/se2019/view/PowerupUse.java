package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import java.io.Serializable;

public class PowerupUse  implements Serializable {
    private int indexInHand;
    private Color colorPlayerToMove;
    private String directionOfMove;
    private int movementDistance;
    private CellView cellForSelfMovement;

    PowerupUse(int indexInHand, Color colorPlayerToMove, int movementDistance, String directionOfMove, CellView cellForSelfMovement) {
        this.indexInHand=indexInHand;
        this.colorPlayerToMove = colorPlayerToMove ;
        this.directionOfMove = directionOfMove;
        this.movementDistance = movementDistance;
        this.cellForSelfMovement = cellForSelfMovement;
    }

    public int getIndexInHand() {
        return indexInHand;
    }

    public Color getColorPlayerToMove() {
        return colorPlayerToMove;
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
