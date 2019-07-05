package it.polimi.se2019.model.game;

public class Map {
    private Room [] rooms;
    private NewCell [][]boardMatrix;

    public Room [] getRooms() {
        return rooms;
    }

    public NewCell[][] getBoardMatrix(){
            return boardMatrix;
    }

}

