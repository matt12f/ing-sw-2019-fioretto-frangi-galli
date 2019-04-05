package it.polimi.se2019.game;

public class Figure {
    private char color;
    private Cell currentCell;

    public Figure(char color){
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public Cell getCell() {
        return currentCell;
    }

    public void setCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

}
