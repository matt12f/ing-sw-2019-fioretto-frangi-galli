package it.polimi.se2019.model.game;

import it.polimi.se2019.enums.Color;

public class Figure {
    private Color color;
    private NewCell currentCell;

    public Figure(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public char getColorChar(){
        switch (this.color){
            case RED: return 'r';
            case BLUE:return 'b';
            case GREEN:return 'g';
            case VIOLET:return 'v';
            case YELLOW:return 'y';
            default: return ' ';
        }
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
