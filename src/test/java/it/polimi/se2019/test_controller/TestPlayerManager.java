package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.RemoteView;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class TestPlayerManager {
    @Test
     void markDealer(){
        RemoteView remoteView;
        Controller controller;
        ArrayList<Player> players = new ArrayList<>();
        Player player=new Player(0,"george000", Color.YELLOW);
        players.add(player);
        player=new Player(1,"george001",Color.BLUE);
        players.add(player);
        player=new Player(2,"george010",Color.WHITE);
        players.add(player);
        player=new Player(3,"george011",Color.GREEN);
        players.add(player);
        player=new Player(4,"george100",Color.VIOLET);
        players.add(player);
        controller = new Controller(players, 1, 5);
        char[] marks = new char[2];
        marks[0] = players.get(1).getFigure().getColorChar();
        marks[1] = players.get(1).getFigure().getColorChar();
        PlayerManager.markerDealer(controller, players.get(0), marks);
        assertEquals(2, controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getDamageTrack().getMarks().size());
        assertEquals('b', controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getDamageTrack().getMarks().get(0));
        assertEquals('b', controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getDamageTrack().getMarks().get(1));
        controller.getMainGameModel().notifyRemoteView();
        assertEquals(2, controller.getRemoteView().getPlayerBoardViews().get(0).getDamageView().getMarks().size());
        assertEquals('b', controller.getRemoteView().getPlayerBoardViews().get(0).getDamageView().getMarks().get(0));
        assertEquals('b', controller.getRemoteView().getPlayerBoardViews().get(0).getDamageView().getMarks().get(1));
    }
}
