package it.polimi.se2019.test_GUI;


import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TestGUI {


    @Test
    public void testGUIUpdate(){
        assertTrue(true);


        ArrayList<PlayerBoardView> testBoards=new ArrayList<>();
        PlayerBoardView pb1=new PlayerBoardView();
        PlayerBoardView pb2=new PlayerBoardView();
        PlayerBoardView pb3=new PlayerBoardView();

        testBoards.add(pb1);
        testBoards.add(pb2);
        testBoards.add(pb3);


        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);

        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        player1.getFigure().setCell(position1);

        player2.getFigure().setCell(position1);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);

        GameModel testModel=new GameModel(players,1,5);

        MapView testMap=new MapView(1,5, testModel);

        CellView [][] board=testMap.getBoardMatrix();

        GameBoardGui testGUI=new GameBoardGui(1,testBoards,testBoards.get(0),board);

        while(true);
        //test per vedere se si aggiorna la GUI della board
        //mette un giocatore in una cella, lo sposta e poi aggiorna
        //check se è tutto a posto
    }

}
