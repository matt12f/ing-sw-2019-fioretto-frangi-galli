package it.polimi.se2019.view;

public class PowerupUse {
    private int indexInHand;
    private int idPlayerToMove;
    private String directionOfMove;
    private int movementDistance;
    private CellView cellForSelfMovement;

    PowerupUse(int indexInHand, int idPlayerToMove, int movementDistance, String directionOfMove, CellView cellForSelfMovement) {
        this.indexInHand=indexInHand;
        this.idPlayerToMove = idPlayerToMove;
        this.directionOfMove = directionOfMove;
        this.movementDistance = movementDistance;
        this.cellForSelfMovement = cellForSelfMovement;
    }

    public int getIndexInHand() {
        return indexInHand;
    }

    public int getIdPlayerToMove() {
        return idPlayerToMove;
    }

    public String getDirectionOfMove() {
        return directionOfMove;
    }

    public int getIndexOfMove() {
        switch (directionOfMove){
            case "Up":return 0;
            case "Down":return 1;
            case"Left":return 2;
            case "Right": return 3;
        }
        return -1;
    }

    public int getMovementDistance() {
        return movementDistance;
    }

    public CellView getCellForSelfMovement() {
        return cellForSelfMovement;
    }
}
