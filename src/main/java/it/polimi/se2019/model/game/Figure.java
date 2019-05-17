package it.polimi.se2019.model.game;

public class Figure {
    private char color;
    private NewCell currentCell;

    public Figure(char color){
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public NewCell getCell() {
        return currentCell;
    }

    /**
     * The currentCell must be set when the player chooses where to first spawn
     * and when it respawns after a player's death
     * @param currentCell
     */
    public void setCell(NewCell currentCell) {
        this.currentCell = currentCell;
    }

}
