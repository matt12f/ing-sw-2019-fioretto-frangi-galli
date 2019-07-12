package it.polimi.se2019.view;

import it.polimi.se2019.controller.CellWithTargets;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.*;

import java.io.Serializable;
import java.util.ArrayList;

public class MapView implements Serializable {

    private int mapNumber;
    private CellView[][] boardMatrix;
    private KillShotTrackerView killView;

    /**
     * create a view representation of the map
     * @param mapNum number of the map configuration
     * @param skulls maximum kills allowed
     * @param model model data
     */
    public MapView(int mapNum, int skulls, GameModel model){
        this.mapNumber = mapNum;
        this.killView = new KillShotTrackerView(skulls);
        this.boardMatrix = new CellView[3][4];
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <4 ; j++) {
                this.boardMatrix[i][j] = new CellView(i, j, model.getCurrentMap().getBoardMatrix()[i][j]);
            }
        }
    }

    /**
     *
     * @return the number of the map config
     */
    public int getMapNumber(){
        return mapNumber;
    }

    /**
     *
     * @return the kill tracker view
     */
    public KillShotTrackerView getKillView() {
        return killView;
    }

    /**
     *
     * @param i row
     * @param j column
     * @return the chosen cell view
     */
    public CellView getCell (int i, int j){
        return this.boardMatrix[i][j];
    }

    /**
     *
     * @return the cell view matrix
     */
    public CellView[][] getBoardMatrix() {
        return boardMatrix;
    }

    /**
     *
     * @param playerColor
     * @return the cell of the player with that color
     */
    public CellView getPlayerPosition(Color playerColor){
        for (CellView[] row: boardMatrix)
            for (CellView singleCell: row) {
                for(Figure player: singleCell.getPlayerFigures())
                    if(player.getColor().equals(playerColor))
                        return singleCell;
            }
        return null; //won't happen
    }


    /**
     * this method returns the visual index of the cell on the X axis
     * @return effective index in the matrix + 1 for improved readability by user
     */
    public int getXIndex(NewCell cell){

        return getXorYindex(getViewCell(cell),true)+1;
    }

    /**
     * this method returns the visual index of the cell on the Y axis
     * @return effective index in the matrix + 1 for improved readability by user
     */
    public int getYIndex(NewCell cell){
        return getXorYindex(getViewCell(cell),false)+1;
    }

    private int getXorYindex(CellView position, boolean xOrY){
        for (int i=0;i<boardMatrix.length;i++)
            for (int j=0;j<boardMatrix[i].length;j++){
                if(position.equals(boardMatrix[i][j]))
                    if(xOrY)
                        return i;
                    else
                        return j;
            }
        return -1;
    }

    /**
     *
     * @param cell
     * @return the corrispondent cell
     */
    private CellView getViewCell(NewCell cell){
        for (CellView[] row: boardMatrix)
            for (CellView singleCell: row) {
                if(singleCell.getCorrespondingCell().equals(cell))
                    return singleCell;
            }
        return null;
    }

    /**
     *  upload the cell view matrix
     * @param newBoard
     */
    public void uploadBoardMatrix(NewCell[][] newBoard)  {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                this.getCell(i, j).setPlayerFigures(newBoard[i][j]);
                this.getCell(i, j).setCell(newBoard[i][j]);
            }
        }
    }

    /**
     * returns a list of all the available coordinates in a map removing the ones where the player currently is
     */
    public ArrayList<Coordinates> availableCoordinates(CellView position){
        ArrayList<Coordinates> coordinates=new ArrayList<>();
        for (int i=0;i<boardMatrix.length;i++)
            for (int j=0;j<boardMatrix[i].length;j++){
                if( !(i==position.getLineIndex() && j==position.getColumnIndex()) &&
                        !boardMatrix[i][j].getCorrespondingCell().getCellType().equals(CellType.OUTSIDEBOARD))
                    coordinates.add(new Coordinates(i,j));
            }
        return coordinates;
    }

    /**
     * this method reduces the list of cells to move an opponent on to only the cells that the opponent can be actually
     * moved on (the cells one move away from him)
     * @param cells
     * @param target
     * @return
     */
    public ArrayList<CellWithTargets> reduceToCellsOneMoveAway(ArrayList<CellWithTargets> cells, Player target){
        NewCell targetCell= target.getFigure().getCell();
        int xPlayer= getXIndex(target.getFigure().getCell());
        int yPlayer= getYIndex(target.getFigure().getCell());

        ArrayList<CellWithTargets> cellsOk = new ArrayList<>();

        //cells one move away are: (xPlayer -1; yPlayer) (xPlayer +1; yPlayer) (xPlayer; yPlayer-1) (xPlayer; yPlayer+1)
        //with no wall in that direction
        for (CellWithTargets singleCell: cells) {
            if(getXIndex(singleCell.getTargetCell())==(xPlayer -1) && getYIndex(singleCell.getTargetCell())==(yPlayer) && !targetCell.getEdge(0).equals(CellEdge.WALL))
                cellsOk.add(singleCell);
            else if(getXIndex(singleCell.getTargetCell())==(xPlayer +1) && getYIndex(singleCell.getTargetCell())==(yPlayer) && !targetCell.getEdge(1).equals(CellEdge.WALL))
                cellsOk.add(singleCell);
            else if(getXIndex(singleCell.getTargetCell())==(xPlayer) && getYIndex(singleCell.getTargetCell())==(yPlayer-1) && !targetCell.getEdge(2).equals(CellEdge.WALL))
                cellsOk.add(singleCell);
            else if(getXIndex(singleCell.getTargetCell())==(xPlayer) && getYIndex(singleCell.getTargetCell())==(yPlayer+1) && !targetCell.getEdge(3).equals(CellEdge.WALL))
                cellsOk.add(singleCell);
        }

        return cellsOk;
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

    /**
     *
     * @return the coordinates in Sting form
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("Cella in coordinate (visive) x: ");
        stringBuilder.append(this.getX()+1);
        stringBuilder.append(" y: ");
        stringBuilder.append(this.getY()+1);
        return stringBuilder.toString();
    }
}
