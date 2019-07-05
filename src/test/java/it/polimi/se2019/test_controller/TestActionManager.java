package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.ActionManager;
import it.polimi.se2019.controller.Controller;
import it.polimi.se2019.controller.MapManager;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.PowerupCard;
import it.polimi.se2019.model.game.Ammo;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestActionManager {

    @Test
    public void testMovePlayer(){
        Player player1=new Player(1,"frank", Color.BLUE);

        ArrayList<Player> players=new ArrayList<>();

        Controller controller=new Controller(players,2,3);

        NewCell[][] board=controller.getMainGameModel().getCurrentMap().getBoardMatrix();

        NewCell position1 = board[2][1];

        NewCell position2 = board[1][3];

        NewCell position3 = board[2][3];

        //positions the player on the map
        player1.getFigure().setCell(position1);
        position1.addPlayers(player1);
        MapManager.getRoom(controller, position1).addPlayers(player1);

        //check before
        assertTrue(position1.getPlayers().contains(player1));
        assertTrue(MapManager.getRoom(controller, position1).getPlayers().contains(player1));
        assertFalse(position2.getPlayers().contains(player1));
        assertFalse(MapManager.getRoom(controller, position2).getPlayers().contains(player1));

        //moves the player
        ActionManager.movePlayer(controller,player1,position2);

        //check after
        assertFalse(position1.getPlayers().contains(player1));
        assertFalse(MapManager.getRoom(controller, position1).getPlayers().contains(player1));
        assertTrue(position2.getPlayers().contains(player1));
        assertTrue(MapManager.getRoom(controller, position2).getPlayers().contains(player1));

        //moves the player
        ActionManager.movePlayer(controller,player1,position3);

        //check after
        assertFalse(position2.getPlayers().contains(player1));
        assertTrue(position3.getPlayers().contains(player1));
        //Different checks, because it's the same room
        assertTrue(MapManager.getRoom(controller, position2).getPlayers().contains(player1));
        assertEquals(MapManager.getRoom(controller, position3),MapManager.getRoom(controller,position2));


    }

    @Test
    public void testCanAffordCost(){
        Player player1=new Player(1,"frank", Color.BLUE);


        Ammo availableAmmo= player1.getPlayerBoard().getAmmo();

        char [] cost={'b','b','y'};
        assertFalse(ActionManager.canAffordCost(player1,availableAmmo,cost,true));
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost,false));

        char [] cost2={'b','y'};
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost2,true));

        assertDoesNotThrow(()->player1.getPlayerBoard().getHand().setPowerup(new PowerupCard("TargettingScope",'b')));
        assertDoesNotThrow(()->player1.getPlayerBoard().getHand().setPowerup(new PowerupCard("Newton",'y')));
        assertDoesNotThrow(()->player1.getPlayerBoard().getHand().setPowerup(new PowerupCard("Teleporter",'r')));

        char [] cost3={'b','b','b','y','r'};
        assertFalse(ActionManager.canAffordCost(player1,availableAmmo,cost3,true));
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost3,false));

        char [] cost4={'b','r','y'};
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost4,true));

    }


}
