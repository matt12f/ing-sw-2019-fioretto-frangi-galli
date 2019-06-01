package it.polimi.se2019.view;

import java.util.ArrayList;

public class CellView {
    private Square[][] matrix;
    private ArrayList<FigureView> playerFigures;

    public void setPlayerFigures(ArrayList<FigureView> toSet){
        this.playerFigures = toSet;
    }

    public ArrayList<FigureView> getPlayerFigures(){
        return this.playerFigures;
    }

    public Square[][] getSquare() {
        return matrix;
    }

    public void setSquare(Square[][] matrix) {
        this.matrix = matrix;
    }
}


