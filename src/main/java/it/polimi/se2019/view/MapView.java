package it.polimi.se2019.view;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;

import java.util.ArrayList;

public class MapView {

    private int mapNumber;
    private CellView[][] boardMatrix;
    private KillShotTrackerView killView;

    public CellView getCell (int i, int j){
        return boardMatrix[i][j];
    }

    public CellView[][] getBoardMatrix() {
        return boardMatrix;
    }

    public CellView getPlayerPosition(Color playerColor){
        for (CellView[] row: boardMatrix)
            for (CellView singleCell: row) {
                for(FigureView player: singleCell.getPlayerFigures())
                    if(player.getColor().equals(playerColor))
                        return singleCell;
            }
        return null; //won't happen
    }

    /**
     * returns a list of all the available coordinates in a map removing the ones where the player currently is
     */
    public ArrayList<Coordinates> availableCoordinates(CellView position){
        ArrayList<Coordinates> coordinates=new ArrayList<>();
        for (int i=0;i<boardMatrix.length;i++)
            for (int j=0;j<boardMatrix[i].length;j++){
                if(i!=position.getLineIndex() && j!=position.getColumnIndex() && !boardMatrix[i][j].getCorrespondingCell().getCellType().equals(CellType.OUTSIDEBOARD))
                    coordinates.add(new Coordinates(i,j));
            }
        return coordinates;
    }

}

class Coordinates{
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("Cella in coordinate (visive) x: ");
        stringBuilder.append(this.x+1);
        stringBuilder.append(" y: ");
        stringBuilder.append(this.x+1);
        return stringBuilder.toString();
    }
}
