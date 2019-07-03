package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.view.CellView;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TestPlayerManager {
    @Test
    public void testSpawnPlayers() throws FullException {
        ArrayList<Player> players = new ArrayList<>();
        CellView cellView;
        NewCell newCell;
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
        PlayerManager.getCardsToSpawn(true, controller, 0);
        PlayerManager.spawnPlayers(controller, 0, controller.getMainGameModel().getPlayerList().get(0).getPlayerBoard().getHand().getPowerup(1));
        newCell = controller.getMainGameModel().getPlayerList().get(0).getFigure().getCell();
        cellView = controller.getRemoteView().getMapView().getPlayerPosition(players.get(0).getFigure().getColor());
        if (cellView == null){
            System.out.println("giocatore non ci sta");
        }
        assertEquals(newCell, cellView.getCorrespondingCell());
    }
}
