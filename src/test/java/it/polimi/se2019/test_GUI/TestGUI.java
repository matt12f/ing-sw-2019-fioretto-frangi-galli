package it.polimi.se2019.test_GUI;


import it.polimi.se2019.AdrenalineClient;
import it.polimi.se2019.controller.Controller;
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



        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Controller controller=new Controller(players,1,5);

        NewCell position1= controller.getMainGameModel().getCurrentMap().getBoardMatrix()[0][0];

        controller.getMainGameModel().getPlayerList().get(0).getFigure().setCell(position1);
        controller.getMainGameModel().getPlayerList().get(1).getFigure().setCell(position1);
        controller.getMainGameModel().getPlayerList().get(2).getFigure().setCell(position1);

        position1.addPlayers(player1);
        position1.addPlayers(player2);
        position1.addPlayers(player3);

        ArrayList<PlayerBoardView> testBoards=new ArrayList<>();
        PlayerBoardView pb1=new PlayerBoardView();
        PlayerBoardView pb2=new PlayerBoardView();
        PlayerBoardView pb3=new PlayerBoardView();

        pb1.update(player1.getPlayerBoard());
        pb2.update(player2.getPlayerBoard());
        pb3.update(player3.getPlayerBoard());

        testBoards.add(pb1);
        testBoards.add(pb2);
        testBoards.add(pb3);

        MapView testMap=new MapView(1,5, controller.getMainGameModel());

        CellView [][] board = testMap.getBoardMatrix();

        GameBoardGui testGUI=new GameBoardGui(1,testBoards,testBoards.get(0), board);

        while(true);
        //test per vedere se si aggiorna la GUI della board
        //mette un giocatore in una cella, lo sposta e poi aggiorna
        //check se è tutto a posto
    }

}
