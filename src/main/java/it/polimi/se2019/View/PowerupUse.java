package it.polimi.se2019.view;

public class PowerupUse {
    private int idPlayerToMove;
    private String directionOfMove;
    private int movementDistance;
    private CellView cellForSelfMovement;

    PowerupUse(int idPlayerToMove, String directionOfMove, int movementDistance, CellView cellForSelfMovement) {
        this.idPlayerToMove = idPlayerToMove;
        this.directionOfMove = directionOfMove;
        this.movementDistance = movementDistance;
        this.cellForSelfMovement = cellForSelfMovement;
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
