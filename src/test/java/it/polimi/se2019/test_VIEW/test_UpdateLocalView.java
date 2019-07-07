package it.polimi.se2019.test_VIEW;

import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.controller.PlayerManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.CardNotFoundException;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.network.ClientHandler;
import it.polimi.se2019.network.GameHandler;
import it.polimi.se2019.view.LocalView;
import it.polimi.se2019.view.RemoteView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class test_UpdateLocalView {
    @Test
    public void testView() throws FullException, CardNotFoundException {
        RemoteView remoteView;
        ArrayList<Player> players = new ArrayList<>();
        Player player=new Player(0,"george000",Color.YELLOW);
        players.add(player);
        player=new Player(1,"george001",Color.BLUE);
        players.add(player);
        player=new Player(2,"george010",Color.WHITE);
        players.add(player);
        player=new Player(3,"george011",Color.GREEN);
        players.add(player);
        player=new Player(4,"george100",Color.VIOLET);
        players.add(player);
        GameModel model = new GameModel(players, 1, 8);
        remoteView = new RemoteView(model, 1, 8);
        model.addObserver(remoteView);
        model.getPlayerList().get(0).getPlayerBoard().getHand().setGun(model.getCurrentDecks().getGunDeck().draw());
        model.notifyRemoteView();
        assertEquals(remoteView.getPlayerHands().get(0).getGuns()[0],  model.getPlayerList().get(0).getPlayerBoard().getHand().getGuns()[0  ]);
        model.getCurrentMap().getBoardMatrix()[0][0].pickItem();
        model.notifyRemoteView();
        assertEquals(remoteView.getMapView().getCell(0, 0).getCorrespondingCell().getDrop(), model.getCurrentMap().getBoardMatrix()[0][0].getDrop());
        MapManager.refillEmptiedCells(model.getCurrentMap().getBoardMatrix(), model.getCurrentDecks());
        model.notifyRemoteView();
        assertEquals(remoteView.getMapView().getCell(0, 0).getCorrespondingCell().getDrop(), model.getCurrentMap().getBoardMatrix()[0][0].getDrop());
    }
}
