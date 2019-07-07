package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.*;
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

        players.add(player1);

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
        assertFalse(ActionManager.canAffordCost(player1,availableAmmo,cost,false));
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost,true));

        char [] cost2={'b','y'};
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost2,false));

        assertDoesNotThrow(()->player1.getPlayerBoard().getHand().setPowerup(new PowerupCard("TargettingScope",'b')));
        assertDoesNotThrow(()->player1.getPlayerBoard().getHand().setPowerup(new PowerupCard("Newton",'y')));
        assertDoesNotThrow(()->player1.getPlayerBoard().getHand().setPowerup(new PowerupCard("Teleporter",'r')));

        char [] cost3={'b','b','b','y','r'};
        assertFalse(ActionManager.canAffordCost(player1,availableAmmo,cost3,false));
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost3,true));

        char [] cost4={'b','r','y'};
        assertTrue(ActionManager.canAffordCost(player1,availableAmmo,cost4,false));

    }

    @Test
    public void testVisibleTargets(){
        Player player1=new Player(1,"frank", Color.BLUE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        Controller controller=new Controller(players,4,3);
        NewCell[][] board=controller.getMainGameModel().getCurrentMap().getBoardMatrix();
        NewCell position1 = board[1][1];

        //setup player1's position
        player1.getFigure().setCell(position1);
        position1.addPlayers(player1);
        MapManager.getRoom(controller, position1).addPlayers(player1);

        CellInfo cellInfo=new CellInfo(player1.getFigure().getCell(),false,false);
        FictitiousPlayer testPlayer=new FictitiousPlayer(controller, player1, cellInfo,
                false,false);

        //test without other players on the board
        ArrayList<Player> visiblePlayers= ActionManager.visibleTargets(controller, testPlayer);
        assertTrue(visiblePlayers.isEmpty());

        //adding other players to the board
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);
        Player player4=new Player(4,"beppe", Color.BLUE);

        NewCell position2 = board[1][2]; //visible
        NewCell position3 = board[0][2]; //visible
        NewCell position4 = board[2][2]; //visible

        //setup player1's position
        player2.getFigure().setCell(position2);
        position2.addPlayers(player2);
        MapManager.getRoom(controller, position2).addPlayers(player2);

        player3.getFigure().setCell(position3);
        position3.addPlayers(player3);
        MapManager.getRoom(controller, position3).addPlayers(player3);

        player4.getFigure().setCell(position4);
        position4.addPlayers(player4);
        MapManager.getRoom(controller, position4).addPlayers(player4);

        //test with other players on the board
        visiblePlayers= ActionManager.visibleTargets(controller, testPlayer);
        assertFalse(visiblePlayers.isEmpty());
        assertEquals("[YELLOW Player with id: 2; with nickname: george; Scored 0 points!, WHITE Player with id: 3; with nickname: miles; Scored 0 points!, BLUE Player with id: 4; with nickname: beppe; Scored 0 points!]",visiblePlayers.toString());

    }

    @Test
    public void testNotVisibleTargets(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);
        Player player3=new Player(3,"miles", Color.WHITE);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Controller controller=new Controller(players,4,3);
        NewCell[][] board=controller.getMainGameModel().getCurrentMap().getBoardMatrix();
        NewCell position1 = board[1][1];
        NewCell position2 = board[1][2]; //visible
        NewCell position3 = board[0][2]; //visible

        //setup player1's position
        player1.getFigure().setCell(position1);
        position1.addPlayers(player1);
        MapManager.getRoom(controller, position1).addPlayers(player1);

        //setup player1's position
        player2.getFigure().setCell(position2);
        position2.addPlayers(player2);
        MapManager.getRoom(controller, position2).addPlayers(player2);

        player3.getFigure().setCell(position3);
        position3.addPlayers(player3);
        MapManager.getRoom(controller, position3).addPlayers(player3);

        CellInfo cellInfo=new CellInfo(player1.getFigure().getCell(),false,false);
        FictitiousPlayer testPlayer=new FictitiousPlayer(controller, player1, cellInfo,
                false,false);

        //test without other players on the board
        ArrayList<Player> notVisiblePlayers= ActionManager.notVisibleTargets(controller, testPlayer);
        assertTrue(notVisiblePlayers.isEmpty());

        //test with other players on the board
        notVisiblePlayers= ActionManager.notVisibleTargets(controller, testPlayer);
        assertTrue(notVisiblePlayers.isEmpty());

        //adding a not visible target
        Player player4=new Player(4,"beppe", Color.BLUE);
        players.add(player4);

        NewCell position4 = board[0][0]; //not visible

        player4.getFigure().setCell(position4);
        position4.addPlayers(player4);
        MapManager.getRoom(controller, position4).addPlayers(player4);

        notVisiblePlayers= ActionManager.notVisibleTargets(controller, testPlayer);
        assertFalse(notVisiblePlayers.isEmpty());
        assertEquals("[BLUE Player with id: 4; with nickname: beppe; Scored 0 points!]",notVisiblePlayers.toString());

    }


}

