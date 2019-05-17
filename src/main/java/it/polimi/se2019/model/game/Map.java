package it.polimi.se2019.model.game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;

public class Map {
    private Room [] rooms;
    private NewCell [][]boardMatrix;

    @Deprecated
   public Map(int config){
        //room standard: 1-red 2-yellow 3-blue 4-white 5-green 6-violet

        this.boardMatrix = new NewCell[3][4];
        this.rooms = new Room[6];
        switch (config){
            case 1: //config with only 1 green cell
                //room creation
                this.rooms[0] = new Room(Color.RED);
                this.rooms[1] = new Room(Color.YELLOW);
                this.rooms[2] = new Room(Color.BLUE);
                this.rooms[3] = new Room(Color.WHITE);
                this.rooms[4] = new Room(Color.GREEN);
                this.rooms[5] = new Room(null);
                //cells creation pattern  color top-bottom-left-right

                this.boardMatrix[0][0]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[0][1]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[0][2]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.ROOM,  CellEdge.DOOR,CellType.SPAWN);
                this.boardMatrix[0][3]= new NewCell(Color.GREEN,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.DOOR,  CellEdge.WALL,CellType.DROP);

                this.boardMatrix[1][0]= new NewCell(Color.RED,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.WALL,  CellEdge.ROOM,CellType.SPAWN);
                this.boardMatrix[1][1]= new NewCell(Color.RED,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.ROOM,  CellEdge.WALL,CellType.DROP);
                this.boardMatrix[1][2]= new NewCell(Color.YELLOW,  CellEdge.DOOR, CellEdge.ROOM,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[1][3]= new NewCell(Color.YELLOW,  CellEdge.DOOR, CellEdge.ROOM,  CellEdge.ROOM,  CellEdge.WALL,CellType.DROP);

                this.boardMatrix[2][1]= new NewCell(Color.WHITE,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.WALL,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[2][2]= new NewCell(Color.YELLOW,  CellEdge.ROOM, CellEdge.WALL,  CellEdge.DOOR,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[2][3]= new NewCell(Color.YELLOW,  CellEdge.ROOM, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.WALL,CellType.SPAWN);
                break;
            case 2: //config with  no special rooms
                //room creation
                this.rooms[0] = new Room(Color.RED);
                this.rooms[1] = new Room(Color.YELLOW);
                this.rooms[2] = new Room(Color.BLUE);
                this.rooms[3] = new Room(Color.WHITE);
                this.rooms[4] = new Room(null);
                this.rooms[5] = new Room(null);

                this.boardMatrix[0][0]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[0][1]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[0][2]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.ROOM,  CellEdge.DOOR,CellType.SPAWN);
                this.boardMatrix[1][0]= new NewCell(Color.RED,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.WALL,  CellEdge.ROOM,CellType.SPAWN);
                this.boardMatrix[1][1]= new NewCell(Color.RED,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.ROOM,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[1][2]= new NewCell(Color.RED,  CellEdge.WALL, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[1][3]= new NewCell(Color.YELLOW,  CellEdge.WALL, CellEdge.ROOM,  CellEdge.DOOR,  CellEdge.WALL,CellType.DROP);
                this.boardMatrix[2][1]= new NewCell(Color.WHITE,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[2][2]= new NewCell(Color.WHITE,  CellEdge.WALL, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[2][3]= new NewCell(Color.YELLOW,  CellEdge.ROOM, CellEdge.WALL,  CellEdge.DOOR,  CellEdge.WALL,CellType.SPAWN);
                break;
            case 3: //config with green and violet cell
                //room creation
                rooms[0] = new Room(Color.RED);
                rooms[1] = new Room(Color.YELLOW);
                rooms[2] = new Room(Color.BLUE);
                rooms[3] = new Room(Color.WHITE);
                rooms[4] = new Room(Color.GREEN);
                rooms[5] = new Room(Color.VIOLET);

                this.boardMatrix[0][0]= new NewCell(Color.RED,  CellEdge.WALL, CellEdge.ROOM,  CellEdge.WALL,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[0][1]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.WALL,  CellEdge.DOOR,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[0][2]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.ROOM,  CellEdge.DOOR,CellType.SPAWN);
                this.boardMatrix[0][3]= new NewCell(Color.GREEN,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.DOOR,  CellEdge.WALL,CellType.DROP);
                this.boardMatrix[1][0]= new NewCell(Color.RED,  CellEdge.ROOM, CellEdge.DOOR,  CellEdge.WALL,  CellEdge.WALL,CellType.SPAWN);
                this.boardMatrix[1][1]= new NewCell(Color.VIOLET,  CellEdge.DOOR, CellEdge.DOOR,  CellEdge.WALL,  CellEdge.WALL,CellType.DROP);
                this.boardMatrix[1][2]= new NewCell(Color.YELLOW,  CellEdge.DOOR, CellEdge.ROOM,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[1][3]= new NewCell(Color.YELLOW,  CellEdge.DOOR, CellEdge.ROOM,  CellEdge.ROOM,  CellEdge.WALL,CellType.DROP);
                this.boardMatrix[2][0]= new NewCell(Color.WHITE,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[2][1]= new NewCell(Color.WHITE,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[2][2]= new NewCell(Color.YELLOW,  CellEdge.ROOM, CellEdge.WALL,  CellEdge.DOOR,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[2][3]= new NewCell( Color.YELLOW,  CellEdge.ROOM, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.WALL,CellType.SPAWN);

                break;
            case 4: //config with 2 violet cells
                //room creation
                rooms[0] = new Room(Color.RED);
                rooms[1] = new Room(Color.YELLOW);
                rooms[2] = new Room(Color.BLUE);
                rooms[3] = new Room(Color.WHITE);
                rooms[4] = new Room(null);
                rooms[5] = new Room(Color.VIOLET);

                this.boardMatrix[0][0]= new NewCell(Color.RED,  CellEdge.WALL, CellEdge.ROOM,  CellEdge.WALL,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[0][1]= new NewCell( Color.BLUE,  CellEdge.WALL, CellEdge.WALL,  CellEdge.DOOR,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[0][2]= new NewCell(Color.BLUE,  CellEdge.WALL, CellEdge.DOOR,  CellEdge.ROOM,  CellEdge.WALL, CellType.SPAWN);
                this.boardMatrix[1][0]= new NewCell(Color.RED,  CellEdge.ROOM, CellEdge.DOOR,  CellEdge.WALL,  CellEdge.WALL,CellType.SPAWN);
                this.boardMatrix[1][1]= new NewCell(Color.VIOLET,  CellEdge.DOOR, CellEdge.DOOR,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[1][2]= new NewCell(Color.VIOLET,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[1][3]= new NewCell( Color.YELLOW,  CellEdge.WALL, CellEdge.ROOM,  CellEdge.DOOR,  CellEdge.WALL,CellType.DROP);
                this.boardMatrix[2][0]= new NewCell(Color.WHITE,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.WALL,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[2][1]= new NewCell(Color.WHITE,  CellEdge.DOOR, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.ROOM,CellType.DROP);
                this.boardMatrix[2][2]= new NewCell(Color.WHITE,  CellEdge.WALL, CellEdge.WALL,  CellEdge.ROOM,  CellEdge.DOOR,CellType.DROP);
                this.boardMatrix[2][3]= new NewCell(Color.YELLOW,  CellEdge.ROOM, CellEdge.WALL,  CellEdge.DOOR,  CellEdge.WALL,CellType.SPAWN);
                break;
            default: this.boardMatrix=null; this.rooms=null;
                break;
        }
    }

    public Map(NewCell[][] board,Room[]rooms){
        this.rooms=rooms;
        this.boardMatrix=board;
    }
    public Room[] getRooms() { return rooms; }

    public NewCell[][] getBoardMatrix(){
            return boardMatrix;
    }

}

