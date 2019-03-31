package it.polimi.se2019.game;

public class Figure {
    private char color;
    private int cellId;

    public Figure(){

    }

    public char getColor() {
        return color;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

}
