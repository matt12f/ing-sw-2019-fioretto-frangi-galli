package it.polimi.se2019.game;

public class Map {
    public Room rooms[];
    private Cell boardMatrix[][];

    public Map(){

    }

    public Cell[][] getBoardMatrix(){
            return boardMatrix;
    }
}
