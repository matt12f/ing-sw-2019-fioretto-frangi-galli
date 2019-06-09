package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.game.Map;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.test_model.test_game.TestMap;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TestMapManager {

    @Test
    public void testGetLineOrColumnIndex(){
        Map testMap = null;
        try {
            testMap = TestMap.createMap(2);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        int lineIndex;
        int columnIndex;

        NewCell[][] board=testMap.getBoardMatrix();

        lineIndex= MapManager.getLineOrColumnIndex(board, board[2][3],true);
        columnIndex= MapManager.getLineOrColumnIndex(board, board[2][3],false);
        assertEquals(2,lineIndex);
        assertEquals(3,columnIndex);

        lineIndex= MapManager.getLineOrColumnIndex(board, board[2][0],true);
        columnIndex= MapManager.getLineOrColumnIndex(board, board[2][0],false);
        assertEquals(2,lineIndex);
        assertEquals(0,columnIndex);

        testMap = null;
        try {
            testMap = TestMap.createMap(3);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        board=testMap.getBoardMatrix();

        lineIndex= MapManager.getLineOrColumnIndex(board, board[2][3],true);
        columnIndex= MapManager.getLineOrColumnIndex(board, board[2][3],false);
        assertEquals(2,lineIndex);
        assertEquals(3,columnIndex);

        lineIndex= MapManager.getLineOrColumnIndex(board, board[1][0],true);
        columnIndex= MapManager.getLineOrColumnIndex(board, board[1][0],false);
        assertEquals(1,lineIndex);
        assertEquals(0,columnIndex);

    }

    @Test
    public void testGetCellInDirection(){
        Map testMap = null;
        try {
            testMap = TestMap.createMap(2);
        } catch (FileNotFoundException e) {
            fail();
        }

        NewCell[][] board=testMap.getBoardMatrix();

        assertDoesNotThrow(()->MapManager.getCellInDirection(board,board[0][0],2,"Right"));
        try {
            assertEquals(board[0][2],MapManager.getCellInDirection(board,board[0][0],2,"Right"));
        } catch (OuterWallException e) {
            fail();
        }

        assertThrows(OuterWallException.class,()->MapManager.getCellInDirection(board,board[0][0],2,"Down"));

        assertThrows(OuterWallException.class,()->MapManager.getCellInDirection(board,board[2][2],3,"Up"));

        try {
            assertEquals(board[0][2],MapManager.getCellInDirection(board,board[2][2],2,"Up"));
        } catch (OuterWallException e) {
            fail();
        }


    }

    @Test
    public void testDistanceBetweenCells() {
        Map testMap = null;
        try {
            testMap = TestMap.createMap(3);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }
        int distance;

        NewCell[][] board=testMap.getBoardMatrix();
        distance = MapManager.distanceBetweenCells(board, board[2][3], board[1][0]);
        assertEquals(4, distance);

        distance = MapManager.distanceBetweenCells(board, board[0][2], board[2][1]);
        assertEquals(3, distance);

        testMap = null;
        try {
            testMap = TestMap.createMap(2);
        } catch (FileNotFoundException e) {
            //nothing to see here
        }

        board=testMap.getBoardMatrix();
        distance = MapManager.distanceBetweenCells(board, board[0][0], board[2][3]);
        assertEquals(5, distance);

        distance = MapManager.distanceBetweenCells(board, board[2][1], board[0][1]);
        assertEquals(4, distance);

    }

}

