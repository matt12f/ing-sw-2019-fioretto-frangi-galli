package it.polimi.se2019.game;

public class Map {
    public Room rooms[];
    public Cell boardMatrix[][];

    public Map(int config){
        /**
         * room standard: 1-red 2-yellow 3-blue 4-white 5-green 6-violet
         */
        //this.boardMatrix = new Cell[3][4];
        //this.rooms = new Room[6];
        switch (config){
            case 1: //config with only 1 green cell
                //room creation
                this.rooms[0] = new Room('r');
                this.rooms[1] = new Room('y');
                this.rooms[2] = new Room('b');
                this.rooms[3] = new Room('w');
                this.rooms[4] = new Room('g');
                this.rooms[5] = new Room('n');
                /**cells creation pattern id color top-bottom-left-right
                 * w=wall, d= door, r= room
                 */
                this.boardMatrix[0][0]= new DropCell( 1, 'b',  'w', 'd',  'w',  'r');
                this.boardMatrix[0][1]= new DropCell( 2, 'b',  'w', 'w',  'r',  'r');
                this.boardMatrix[0][2]= new SpawnCell( 3, 'b',  'w', 'd',  'r',  'd');
                this.boardMatrix[0][3]= new DropCell( 4, 'g',  'w', 'd',  'd',  'w');
                this.boardMatrix[1][0]= new SpawnCell( 5, 'r',  'd', 'w',  'w',  'r');
                this.boardMatrix[1][1]= new DropCell( 6, 'r',  'w', 'd',  'r',  'w');
                this.boardMatrix[1][2]= new DropCell( 7, 'y',  'd', 'r',  'w',  'r');
                this.boardMatrix[1][3]= new DropCell( 8, 'y',  'd', 'r',  'r',  'w');
                this.boardMatrix[2][1]= new DropCell( 10, 'w',  'd', 'w',  'w',  'd');
                this.boardMatrix[2][2]= new DropCell( 11, 'y',  'r', 'w',  'd',  'r');
                this.boardMatrix[2][2]= new SpawnCell( 12, 'y',  'r', 'w',  'r',  'w');
                break;
            case 2: //config with  no special rooms
                //room creation
                rooms[0] = new Room('r');
                rooms[1] = new Room('y');
                rooms[2] = new Room('b');
                rooms[3] = new Room('w');
                rooms[4] = new Room('n');
                rooms[5] = new Room('n');

                this.boardMatrix[0][0]= new DropCell( 1, 'b',  'w', 'd',  'w',  'r');
                this.boardMatrix[0][1]= new DropCell( 2, 'b',  'w', 'w',  'r',  'r');
                this.boardMatrix[0][2]= new SpawnCell( 3, 'b',  'w', 'd',  'r',  'd');
                this.boardMatrix[1][0]= new SpawnCell( 5, 'r',  'd', 'w',  'w',  'r');
                this.boardMatrix[1][1]= new DropCell( 6, 'r',  'w', 'd',  'r',  'r');
                this.boardMatrix[1][2]= new DropCell( 7, 'r',  'w', 'w',  'r',  'd');
                this.boardMatrix[1][3]= new DropCell( 8, 'y',  'w', 'r',  'd',  'w');
                this.boardMatrix[2][1]= new DropCell( 10, 'w',  'd', 'w',  'w',  'r');
                this.boardMatrix[2][2]= new DropCell( 11, 'w',  'w', 'w',  'r',  'd');
                this.boardMatrix[2][3]= new SpawnCell( 12, 'y',  'r', 'w',  'd',  'w');
                break;
            case 3: //config with green and violet cell
                //room creation
                rooms[0] = new Room('r');
                rooms[1] = new Room('y');
                rooms[2] = new Room('b');
                rooms[3] = new Room('w');
                rooms[4] = new Room('g');
                rooms[5] = new Room('v');

                this.boardMatrix[0][0]= new DropCell( 1, 'r',  'w', 'r',  'w',  'd');
                this.boardMatrix[0][1]= new DropCell( 2, 'b',  'w', 'w',  'd',  'r');
                this.boardMatrix[0][2]= new SpawnCell( 3, 'b',  'w', 'd',  'r',  'd');
                this.boardMatrix[0][3]= new DropCell( 4, 'g',  'w', 'd',  'd',  'w');
                this.boardMatrix[1][0]= new SpawnCell( 5, 'r',  'r', 'd',  'w',  'w');
                this.boardMatrix[1][1]= new DropCell( 6, 'v',  'd', 'd',  'w',  'w');
                this.boardMatrix[1][2]= new DropCell( 7, 'y',  'd', 'r',  'w',  'r');
                this.boardMatrix[1][3]= new DropCell( 8, 'y',  'd', 'r',  'r',  'w');
                this.boardMatrix[2][0]= new DropCell( 9, 'w',  'd', 'w',  'w',  'r');
                this.boardMatrix[2][1]= new DropCell( 10, 'w',  'd', 'w',  'r',  'd');
                this.boardMatrix[2][2]= new DropCell( 11, 'y',  'r', 'w',  'd',  'r');
                this.boardMatrix[2][2]= new SpawnCell( 12, 'y',  'r', 'w',  'r',  'w');

                break;
            case 4: //config with 2 violet cells
                //room creation
                rooms[0] = new Room('r');
                rooms[1] = new Room('y');
                rooms[2] = new Room('b');
                rooms[3] = new Room('w');
                rooms[4] = new Room('n');
                rooms[5] = new Room('v');

                this.boardMatrix[0][0]= new DropCell( 1, 'r',  'w', 'r',  'w',  'd');
                this.boardMatrix[0][1]= new DropCell( 2, 'b',  'w', 'w',  'd',  'r');
                this.boardMatrix[0][2]= new SpawnCell( 3, 'b',  'w', 'd',  'r',  'w');
                this.boardMatrix[1][0]= new SpawnCell( 5, 'r',  'r', 'd',  'w',  'w');
                this.boardMatrix[1][1]= new DropCell( 6, 'v',  'd', 'd',  'w',  'r');
                this.boardMatrix[1][2]= new DropCell( 7, 'v',  'd', 'w',  'r',  'd');
                this.boardMatrix[1][3]= new DropCell( 8, 'y',  'w', 'r',  'd',  'w');
                this.boardMatrix[2][0]= new DropCell( 9, 'w',  'd', 'w',  'w',  'r');
                this.boardMatrix[2][1]= new DropCell( 10, 'w',  'd', 'w',  'r',  'r');
                this.boardMatrix[2][2]= new DropCell( 11, 'w',  'w', 'w',  'r',  'd');
                this.boardMatrix[2][2]= new SpawnCell( 12, 'y',  'r', 'w',  'd',  'w');
                break;
            default:
                break;
        }
    }

    public Cell[][] getBoardMatrix(){
            return boardMatrix;
    }
}
