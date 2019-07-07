package it.polimi.se2019.view;

import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.model.game.Figure;
import it.polimi.se2019.model.game.NewCell;

import java.io.Serializable;
import java.util.ArrayList;

public class CellView implements Serializable {
    private NewCell correspondingCell;
    private int lineIndex;
    private int columnIndex;

    private ArrayList<Figure> playerFigures;

    /**
     * create a view of the cell to store the data of the corrisponding cell
     * @param lineIndex line position of the cell
     * @param columnIndex column position of the cell
     * @param position model data of the cell
     */
    public CellView(int lineIndex, int columnIndex, NewCell position) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
        this.playerFigures = new ArrayList<>();
        this.correspondingCell=position;
        setPlayerFigures(position);
        setCell(position);
    }

    /**
     * update of the players in the cell
     * @param playerPosition  model data of the cell
     */
    public void setPlayerFigures(NewCell playerPosition){
        this.playerFigures.clear();

        if(playerPosition != null && !playerPosition.getPlayers().isEmpty())
            for (int i = 0; i < playerPosition.getPlayers().size(); i++){
                    this.playerFigures.add(playerPosition.getPlayers().get(i).getFigure());
            }
    }

    /**
     *
     * @return the list of player's figures
     */
    public ArrayList<Figure> getPlayerFigures(){
        return this.playerFigures;
    }

    /**
     *
     * @return model data of the cell
     */
    public NewCell getCorrespondingCell() {
        return correspondingCell;
    }

    /**
     * set the data of the cell view
     * @param newCorrespondingCell
     */
    public void setCell(NewCell newCorrespondingCell){
        this.correspondingCell = newCorrespondingCell;
    }

    /**
     *
     * @return line position
     */
    public int getLineIndex() {
        return lineIndex;
    }

    /**
     *
     * @return column position
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     *
     * @return the drop inside the cell
     */
    public String getDrop(){
        if(this.correspondingCell.getCellType().equals(CellType.DROP))
            return this.correspondingCell.getDrop().getContent();
        return "spawn";
    }

}


