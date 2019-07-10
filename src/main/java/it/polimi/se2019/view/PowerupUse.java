package it.polimi.se2019.view;

import it.polimi.se2019.enums.Color;

import java.io.Serializable;

public class PowerupUse  implements Serializable {
    private int indexInHand;
    private Color colorPlayerToMove;
    private String directionOfMove;
    private int movementDistance;
    private int lineForMove;
    private int columnForMove;

    PowerupUse(int indexInHand, Color colorPlayerToMove, int movementDistance, String directionOfMove, int lineForMove,int columnForMove) {
        this.indexInHand=indexInHand;
        this.colorPlayerToMove = colorPlayerToMove ;
        this.directionOfMove = directionOfMove;
        this.movementDistance = movementDistance;
        this.lineForMove=lineForMove;
        this.columnForMove=columnForMove;
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

    public int getLineForMove() {
        return lineForMove;
    }

    public int getColumnForMove() {
        return columnForMove;
    }
}
