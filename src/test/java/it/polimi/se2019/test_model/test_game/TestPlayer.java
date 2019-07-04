package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.cards.CyberBlade;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    @Test
    public void testPlayerClone(){
        Player player1=new Player(2,"george", Color.BLUE);

        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        player1.setScore(112);

        player1.getFigure().setCell(position1);
        assertDoesNotThrow(()-> player1.getPlayerBoard().getHand().setGun(new CyberBlade()));

        player1.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);

        Player player2=player1.clone();

        //it works because we've made a custom equals
        assertEquals(player2, player1);

        //this is a clone! it's not the same
        assertNotEquals(player1.getPlayerBoard(),player2.getPlayerBoard());

    }

    @Test
    public void testDuplicateList(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);

        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        player1.getFigure().setCell(position1);

        player2.getFigure().setCell(position1);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);

        player1.setScore(23);
        player2.setScore(23);

        ArrayList<Player> playersDeepCopy= Player.duplicateList(players);

        assertEquals(players,playersDeepCopy);

        players.remove(player2);

        assertNotEquals(players, playersDeepCopy);
    }
}
