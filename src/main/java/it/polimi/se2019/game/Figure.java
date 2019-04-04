package it.polimi.se2019.game;

public class Figure {
    private char color;
    private Cell currentCell;

    public Figure(){

    }

    public char getColor() {
        return color;
    }

    public Cell getCellId() {
        return currentCell;
    }

    public void setCellId(Cell currentCell) {
        this.currentCell = currentCell;
    }

}
