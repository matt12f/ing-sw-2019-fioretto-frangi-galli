package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.model.game.Cell;
import it.polimi.se2019.model.game.Map;
import it.polimi.se2019.model.game.Room;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMap {

    @Test
    public void testGetBoardMatrix(){
        Map testMap=new Map(1);
        Cell[][] testCellMatrix=testMap.getBoardMatrix();
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

    private int countCells(Cell [][] boardMatrix){
        int count=0;
        for(int i=0; i<boardMatrix.length;i++)
            for(int j=0;j<boardMatrix[i].length;j++)
                if(boardMatrix[i][j]!=null)
                    count++;
        return count;
    }
}
