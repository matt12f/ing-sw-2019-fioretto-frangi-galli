package it.polimi.se2019.view;

import it.polimi.se2019.model.game.NewCell;

import java.util.ArrayList;

public class CellView {
    private NewCell correspondingCell;
    private int lineIndex;
    private int columnIndex;
    private Square[][] matrix; //TODO sarà 3x3 e ci vanno le figure e le ammotiles
    private ArrayList<FigureView> playerFigures;

    public CellView(int lineIndex, int columnIndex, NewCell playerPosition) {
        this.lineIndex = lineIndex;
        this.columnIndex = columnIndex;
        this.matrix = new Square[3][3];
        this.playerFigures = new ArrayList<>();
    }

    public void setPlayerFigures(ArrayList<FigureView> toSet){
        this.playerFigures = toSet;
    }

    public ArrayList<FigureView> getPlayerFigures(){
        return this.playerFigures;
    }

    public NewCell getCorrespondingCell() {
        return correspondingCell;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Square[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Square[][] matrix) {
        this.matrix = matrix;
    }


}


