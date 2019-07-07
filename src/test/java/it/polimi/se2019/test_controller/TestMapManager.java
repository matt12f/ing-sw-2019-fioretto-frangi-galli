package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.CellInfo;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.FictitiousPlayer;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.OuterWallException;
import it.polimi.se2019.model.cards.GunCard;
import it.polimi.se2019.model.game.Map;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.test_model.test_game.TestMap;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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

        assertDoesNotThrow(()->MapManager.getCellInDirection(board,board[0][0],2,3));
        try {
            assertEquals(board[0][2],MapManager.getCellInDirection(board,board[0][0],2,3));
        } catch (OuterWallException e) {
            fail();
        }

        assertThrows(OuterWallException.class,()->MapManager.getCellInDirection(board,board[0][0],2,1));

        assertThrows(OuterWallException.class,()->MapManager.getCellInDirection(board,board[2][2],3,0));

        try {
            assertEquals(board[0][2],MapManager.getCellInDirection(board,board[2][2],2,0));
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

    @Test
    public void testGetRoom(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Controller controller=new Controller(players,1,5);

        NewCell cell=controller.getMainGameModel().getCurrentMap().getBoardMatrix()[2][1];

        assertEquals(cell.getColor(),MapManager.getRoom(controller,cell).getColor());

        NewCell cell1=new NewCell(Color.VIOLET, CellEdge.WALL,CellEdge.WALL,CellEdge.WALL,CellEdge.WALL, CellType.DROP);

        assertNull(MapManager.getRoom(controller,cell1));

    }

    @Test
    public void testIndexOfMove(){
        assertEquals(0,MapManager.getIndexOfMove("Up"));
        assertEquals(1,MapManager.getIndexOfMove("Down"));
        assertEquals(2,MapManager.getIndexOfMove("Left"));
        assertEquals(3,MapManager.getIndexOfMove("Right"));
        assertEquals(-1,MapManager.getIndexOfMove("Bargwc"));
    }

    @Test
    public void testRefillEmptiedCells(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Controller controller=new Controller(players,1,5);

        NewCell [][] board=controller.getMainGameModel().getCurrentMap().getBoardMatrix();

        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
                if(board[i][j].getCellType().equals(CellType.DROP))
                    assertNotNull(board[i][j].getDrop());
                else if(board[i][j].getCellType().equals(CellType.SPAWN))
                    assertEquals(3,board[i][j].getWeaponCards().size());
                else
                    assertEquals(board[i][j].getCellType(),CellType.OUTSIDEBOARD);

        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
                if(board[i][j].getCellType().equals(CellType.DROP))
                    assertNotNull(board[i][j].pickItem());
                else if(board[i][j].getCellType().equals(CellType.SPAWN)){
                    ArrayList<GunCard> gunCards=new ArrayList<>();
                    for(GunCard gunCard: board[i][j].getWeaponCards())
                        gunCards.add(gunCard.clone());
                    for(GunCard gunCard: gunCards)
                        assertEquals(gunCard,board[i][j].pickItem(gunCard));
                }
                else
                    assertEquals(board[i][j].getCellType(),CellType.OUTSIDEBOARD);


        MapManager.refillEmptiedCells(controller.getMainGameModel().getCurrentMap().getBoardMatrix(),
                controller.getMainGameModel().getCurrentDecks());

        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
                if(board[i][j].getCellType().equals(CellType.DROP))
                    assertNotNull(board[i][j].getDrop());
                else if(board[i][j].getCellType().equals(CellType.SPAWN))
                    assertEquals(3,board[i][j].getWeaponCards().size());
                else
                    assertEquals(board[i][j].getCellType(),CellType.OUTSIDEBOARD);

    }

    @Test
    public void test1GetCellInRadius2(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Controller controller=new Controller(players,1,5);

        NewCell [][] board=controller.getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell position1 = board[1][2];
        player1.getFigure().setCell(position1);


        CellInfo cellInfo=new CellInfo(player1.getFigure().getCell(),false,false);

        FictitiousPlayer testPlayer=new FictitiousPlayer(controller, player1, cellInfo,
                false,false);

        ArrayList<NewCell> cellsInRadius= MapManager.squaresInRadius2(controller,testPlayer);

        assertEquals("[Cella BLUE di tipo: SPAWN\n" +
                " Top: WALL\n" +
                " Bottom: DOOR\n" +
                " Left: ROOM\n" +
                " Right: DOOR, Cella YELLOW di tipo: DROP\n" +
                " Top: ROOM\n" +
                " Bottom: WALL\n" +
                " Left: DOOR\n" +
                " Right: ROOM, Cella YELLOW di tipo: DROP\n" +
                " Top: DOOR\n" +
                " Bottom: ROOM\n" +
                " Left: ROOM\n" +
                " Right: WALL, Cella BLUE di tipo: DROP\n" +
                " Top: WALL\n" +
                " Bottom: WALL\n" +
                " Left: ROOM\n" +
                " Right: ROOM, Cella GREEN di tipo: DROP\n" +
                " Top: WALL\n" +
                " Bottom: DOOR\n" +
                " Left: DOOR\n" +
                " Right: WALL, Cella WHITE di tipo: DROP\n" +
                " Top: DOOR\n" +
                " Bottom: WALL\n" +
                " Left: WALL\n" +
                " Right: DOOR, Cella YELLOW di tipo: SPAWN\n" +
                " Top: ROOM\n" +
                " Bottom: WALL\n" +
                " Left: ROOM\n" +
                " Right: WALL]",cellsInRadius.toString());
        assertEquals(7, cellsInRadius.size());
    }

    @Test
    public void test2GetCellInRadius2(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Controller controller=new Controller(players,3,5);

        NewCell [][] board=controller.getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell position1 = board[2][1];
        player1.getFigure().setCell(position1);


        CellInfo cellInfo=new CellInfo(player1.getFigure().getCell(),false,false);

        FictitiousPlayer testPlayer=new FictitiousPlayer(controller, player1, cellInfo,
                false,false);

        ArrayList<NewCell> cellsInRadius= MapManager.squaresInRadius2(controller,testPlayer);

        assertEquals("[Cella VIOLET di tipo: DROP\n" +
                " Top: DOOR\n" +
                " Bottom: DOOR\n" +
                " Left: WALL\n" +
                " Right: WALL, Cella WHITE di tipo: DROP\n" +
                " Top: DOOR\n" +
                " Bottom: WALL\n" +
                " Left: WALL\n" +
                " Right: ROOM, Cella YELLOW di tipo: DROP\n" +
                " Top: ROOM\n" +
                " Bottom: WALL\n" +
                " Left: DOOR\n" +
                " Right: ROOM, Cella BLUE di tipo: DROP\n" +
                " Top: WALL\n" +
                " Bottom: WALL\n" +
                " Left: DOOR\n" +
                " Right: ROOM, Cella RED di tipo: SPAWN\n" +
                " Top: ROOM\n" +
                " Bottom: DOOR\n" +
                " Left: WALL\n" +
                " Right: WALL, Cella YELLOW di tipo: DROP\n" +
                " Top: DOOR\n" +
                " Bottom: ROOM\n" +
                " Left: WALL\n" +
                " Right: ROOM, Cella YELLOW di tipo: SPAWN\n" +
                " Top: ROOM\n" +
                " Bottom: WALL\n" +
                " Left: ROOM\n" +
                " Right: WALL]",cellsInRadius.toString());
        assertEquals(7, cellsInRadius.size());
    }
}

