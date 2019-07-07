package it.polimi.se2019.test_GUI;


import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.*;
import it.polimi.se2019.model.game.KillShotTrack;
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
        PlayerBoardView pb1=new PlayerBoardView(player1.getPlayerBoard(),player1.getScore());
        PlayerBoardView pb2=new PlayerBoardView(player2.getPlayerBoard(),player2.getScore());
        PlayerBoardView pb3=new PlayerBoardView(player3.getPlayerBoard(),player3.getScore());

        pb1.update(player1.getPlayerBoard());
        pb2.update(player2.getPlayerBoard());
        pb3.update(player3.getPlayerBoard());

        testBoards.add(pb1);
        testBoards.add(pb2);
        testBoards.add(pb3);

        MapView testMap=new MapView(1,5, controller.getMainGameModel());

        CellView [][] board = testMap.getBoardMatrix();

        GameBoardGUI testGUI=new GameBoardGUI(1,testBoards,testBoards.get(0), board);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            //nothing to see here
        }

        //updated damage tracks
        char [] damage= {'b','b','y','y','g','g'};
        player1.getPlayerBoard().getDamageTrack().dealDamage(damage);
        player1.getPlayerBoard().getDamageTrack().addMark('b');
        player1.getPlayerBoard().getDamageTrack().addMark('b');
        player1.getPlayerBoard().getDamageTrack().addMark('y');
        player1.getPlayerBoard().getDamageTrack().addMark('g');
        pb1.update(player1.getPlayerBoard());

        damage[0]='w';
        player2.getPlayerBoard().getDamageTrack().dealDamage(damage);
        player2.getPlayerBoard().getDamageTrack().addMark('w');
        player2.getPlayerBoard().getDamageTrack().addMark('b');
        player2.getPlayerBoard().getDamageTrack().addMark('y');
        player2.getPlayerBoard().getDamageTrack().addMark('g');
        pb2.update(player2.getPlayerBoard());

        damage[0]='v';
        player3.getPlayerBoard().getDamageTrack().dealDamage(damage);
        player3.getPlayerBoard().getDamageTrack().addMark('v');
        player3.getPlayerBoard().getDamageTrack().addMark('b');
        player3.getPlayerBoard().getDamageTrack().addMark('y');
        player3.getPlayerBoard().getDamageTrack().addMark('g');
        pb3.update(player3.getPlayerBoard());


        //updated killshot track
        KillShotTrackerView killShotTrackerView=new KillShotTrackerView(5);
        KillShotTrack killShotTrack=new KillShotTrack(5);
        killShotTrack.setKills("bb");
        killShotTrack.setKills("w");
        killShotTrack.setKills("yy");
        killShotTrack.setKills("gg");

        killShotTrackerView.update(killShotTrack);

        //updated hand view for the player1
        GunCard [] gunsForP1=new GunCard[3];
        gunsForP1[0]=new CyberBlade();
        gunsForP1[1]=new Electroscythe();
        gunsForP1[2]=new Zx2();

        PowerupCard [] pWUPForP1=new PowerupCard[3];
        pWUPForP1[0]=new PowerupCard("TargettingScope",'b');
        pWUPForP1[1]=new PowerupCard("NewTon",'r');
        pWUPForP1[2]=new PowerupCard("Teleporter",'y');

        PlayerHandView playerHandView=new PlayerHandView();
        playerHandView.setGuns(gunsForP1);
        playerHandView.setPowerups(pWUPForP1);

        NewCell position2= controller.getMainGameModel().getCurrentMap().getBoardMatrix()[2][2];

        controller.getMainGameModel().getPlayerList().get(0).getFigure().setCell(position2);
        controller.getMainGameModel().getPlayerList().get(1).getFigure().setCell(position2);
        controller.getMainGameModel().getPlayerList().get(2).getFigure().setCell(position2);

        position1.removePlayers(player1);
        position1.removePlayers(player2);
        position1.removePlayers(player3);

        position2.addPlayers(player1);
        position2.addPlayers(player2);
        position2.addPlayers(player3);

        testMap.uploadBoardMatrix(controller.getMainGameModel().getCurrentMap().getBoardMatrix());


        testGUI.updateBoardGame(testBoards,testBoards.get(0),board,killShotTrackerView,playerHandView);
        while(true);

        //test per vedere se si aggiorna la GUI della board
        //mette un giocatore in una cella, lo sposta e poi aggiorna
        //check se è tutto a posto
    }

}
