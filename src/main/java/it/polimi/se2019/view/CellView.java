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

    public CellView(int lineIndex, int columnIndex, NewCell position) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
        this.playerFigures = new ArrayList<>();
        this.correspondingCell=position;
        setPlayerFigures(position);
        setCell(position);
    }

    public void setPlayerFigures(NewCell playerPosition){
        this.playerFigures.clear();

        if(playerPosition != null && !playerPosition.getPlayers().isEmpty())
            for (int i = 0; i < playerPosition.getPlayers().size(); i++){
                    this.playerFigures.add(playerPosition.getPlayers().get(i).getFigure());
            }
    }

    public ArrayList<Figure> getPlayerFigures(){
        return this.playerFigures;
    }

    public NewCell getCorrespondingCell() {
        return correspondingCell;
    }

    public void setCell(NewCell newCorrespondingCell){
        this.correspondingCell = newCorrespondingCell;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public String getDrop(){
        if(this.correspondingCell.getCellType().equals(CellType.DROP))
            return this.correspondingCell.getDrop().getContent();
        return "spawn";
    }

}


