package it.polimi.se2019.test_model.test_game;


import com.google.gson.Gson;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.*;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TestMap {

    @Test
    public void testGetBoardMatrix(){
        Map testMap=new Map(1);
        NewCell[][] testCellMatrix=testMap.getBoardMatrix();
        assertNotNull(testCellMatrix);
    }

    @Test
    public void testGetRoom(){
        Map testMap=new Map(2);
        Room[] testRoom=testMap.getRooms();
        assertNotNull(testRoom);
    }
    @Test
    public void testMap(){
        Map testMap=new Map(1);
        assertEquals(6,testMap.getRooms().length);
        assertEquals(11,countCells(testMap.getBoardMatrix()));

        testMap=new Map(2);
        assertEquals(6,testMap.getRooms().length);
        assertEquals(10,countCells(testMap.getBoardMatrix()));

        testMap=new Map(3);
        assertEquals(6,testMap.getRooms().length);
        assertEquals(12,countCells(testMap.getBoardMatrix()));

        testMap=new Map(4);
        assertEquals(6,testMap.getRooms().length);
        assertEquals(11,countCells(testMap.getBoardMatrix()));

        testMap=new Map(5);
        assertNull(testMap.getBoardMatrix());
        assertNull(testMap.getRooms());
    }

    private int countCells(NewCell [][] boardMatrix){
        int count=0;
        for(int i=0; i<boardMatrix.length;i++)
            for(int j=0;j<boardMatrix[i].length;j++)
                if(boardMatrix[i][j]!=null)
                    count++;
        return count;
    }

    //TODO questo non è un test junit, è servito per tradurre gli oggetti map in json
    @Test
    public void testJson(){
        NewCell [][] boardMatrix = new NewCell[3][4];
        Room[] rooms = new Room[6];
                rooms[0] = new Room(Color.RED);
                rooms[1] = new Room(Color.YELLOW);
                rooms[2] = new Room(Color.BLUE);
                rooms[3] = new Room(Color.WHITE);
                rooms[4] = new Room(Color.GREEN);
                rooms[5] = new Room(null);
                /**cells creation pattern  color top-bottom-left-right
                 */

                Map test=new Map(boardMatrix, rooms);
        Gson gson = new Gson();
        try {
            gson.toJson(test, new FileWriter("src/main/JSONfiles/map1.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
