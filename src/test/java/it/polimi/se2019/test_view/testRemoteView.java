package it.polimi.se2019.test_view;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class testRemoteView {
    @Test
    public  void testGetCardsToSpawn(){
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
        Controller controller = new Controller(players, 1, 5);
        try {
            PlayerManager.getCardsToSpawn(true, controller, 0);
        } catch (FullException e) {
            e.printStackTrace();
        }
        assertArrayEquals(controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getHand().getPowerups(), controller.getRemoteView().getPlayerHands().get(0).getPowerups());
    }
}
