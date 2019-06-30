package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.exceptions.FullException;
import it.polimi.se2019.model.cards.CyberBlade;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    @Test
    public void testPlayerClone(){
        Player player1=new Player(2,"george", Color.BLUE);

        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        NewCell position2= new NewCell(Color.YELLOW, CellEdge.DOOR,CellEdge.WALL,CellEdge.WALL,CellEdge.ROOM, CellType.SPAWN);

        player1.setScore(112);

        player1.getFigure().setCell(position1);
        assertDoesNotThrow(()-> player1.getPlayerBoard().getHand().setGun(new CyberBlade()));

        player1.getPlayerBoard().getActionTileNormal().setAdrenalineMode1(true);

        Player player2=player1.clone();

        assertNotEquals(player2,player1);

    }
}
