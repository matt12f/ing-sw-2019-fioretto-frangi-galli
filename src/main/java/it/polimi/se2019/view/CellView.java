package it.polimi.se2019.view;

import it.polimi.se2019.enums.CellEdge;

import java.util.ArrayList;

public class CellView {
    protected CellEdge top;
    protected CellEdge bottom;
    protected CellEdge left;
    protected CellEdge right;
    private int lineIndex;
    private int columnIndex;
    private Square[][] matrix; //TODO sarà 3x3 e ci vanno le figure e le ammotiles
    private ArrayList<FigureView> playerFigures;

    public CellView(int lineIndex, int columnIndex, CellEdge top, CellEdge bottom, CellEdge left, CellEdge right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
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

    public boolean isUpWall(){
        return this.top.equals(CellEdge.WALL);
    }

    public boolean isDownWall(){
        return this.bottom.equals(CellEdge.WALL);
    }

    public boolean isLeftWall(){
        return this.left.equals(CellEdge.WALL);
    }

    public boolean isRightWall(){
        return this.right.equals(CellEdge.WALL);
    }
}


