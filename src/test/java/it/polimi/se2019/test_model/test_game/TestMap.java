package it.polimi.se2019.test_model.test_game;


import com.google.gson.Gson;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.model.game.*;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestMap {

    @Test
    public void testGetBoardMatrix(){
        Map testMap=null;
        try {
            testMap = createMap(1);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        NewCell[][] testCellMatrix=testMap.getBoardMatrix();
        assertNotNull(testCellMatrix);
    }

    @Test
    public void testGetRoom(){
        Map testMap=null;
        try {
            testMap = createMap(2);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }        Room[] testRoom=testMap.getRooms();
        assertNotNull(testRoom);
    }
    @Test
    public void testMap(){
        Map testMap=null;
        try {
            testMap = createMap(1);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        assertEquals(6,testMap.getRooms().length);
        assertEquals(11,countCells(testMap.getBoardMatrix()));

        testMap=null;
        try {
            testMap = createMap(2);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        assertEquals(6,testMap.getRooms().length);
        assertEquals(10,countCells(testMap.getBoardMatrix()));

        testMap=null;
        try {
            testMap = createMap(3);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        assertEquals(6,testMap.getRooms().length);
        assertEquals(12,countCells(testMap.getBoardMatrix()));

        testMap=null;
        try {
            testMap = createMap(4);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        assertEquals(6,testMap.getRooms().length);
        assertEquals(11,countCells(testMap.getBoardMatrix()));

    }

    public static Map createMap(int config)throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/JSONfiles/map"+config+".json");
        return gson.fromJson(reader, Map.class);
    }

    private int countCells(NewCell [][] boardMatrix){
        int count=0;
        for(int i=0; i<boardMatrix.length;i++)
            for(int j=0;j<boardMatrix[i].length;j++)
                if(!boardMatrix[i][j].getCellType().equals(CellType.OUTSIDEBOARD))
                    count++;
        return count;
    }

}
