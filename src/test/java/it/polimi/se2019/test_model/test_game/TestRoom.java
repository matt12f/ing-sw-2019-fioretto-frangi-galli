package it.polimi.se2019.test_model.test_game;

import it.polimi.se2019.enums.CellEdge;
import it.polimi.se2019.enums.CellType;
import it.polimi.se2019.enums.Color;
import it.polimi.se2019.model.game.GameModel;
import it.polimi.se2019.model.game.NewCell;
import it.polimi.se2019.model.game.Player;
import it.polimi.se2019.model.game.Room;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestRoom {

    @Test
    public void testRoom(){
        Player player1=new Player(1,"frank", Color.BLUE);
        Player player2=new Player(2,"george",Color.YELLOW);

        NewCell position1= new NewCell(Color.BLUE, CellEdge.WALL,CellEdge.DOOR,CellEdge.ROOM,CellEdge.ROOM, CellType.DROP);

        player1.getFigure().setCell(position1);

        player2.getFigure().setCell(position1);

        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);

        GameModel testModel=new GameModel(players,1,5);

        Room room=testModel.getCurrentMap().getRooms()[0];

        assertEquals(Color.RED,testModel.getCurrentMap().getRooms()[0].getColor());
        assertEquals(Color.YELLOW,testModel.getCurrentMap().getRooms()[1].getColor());
        assertEquals(Color.BLUE,testModel.getCurrentMap().getRooms()[2].getColor());
        assertEquals(Color.WHITE,testModel.getCurrentMap().getRooms()[3].getColor());
        assertEquals(Color.GREEN,testModel.getCurrentMap().getRooms()[4].getColor());
        assertNull(testModel.getCurrentMap().getRooms()[5].getColor());

    }

}
